package com.zhiyi.falcon.controller;

import com.zhiyi.city.api.IBaseCityService;
import com.zhiyi.community.dto.DeliveryCommunityDto;
import com.zhiyi.falcon.api.dto.bMap.BMapNearbyDto;
import com.zhiyi.falcon.api.dto.bMap.BMapNearbyResultDto;
import com.zhiyi.utils.FastJsonUtils;
import com.zhiyi.utils.HttpRequestUtil;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by yangjj on 2015/12/11.
 */
@RequestMapping(value = "/communityNearby")
@Controller
public class CommunityNearbyController {

    private static final String requestBMapPlaceApi = "http://api.map.baidu.com/place/v2/search?query=%s&location=%s&radius=%s&output=json&ak=%s&page_num=%s&page_size=%s";

    private Logger logger = Logger.getLogger(CommunityNearbyController.class);


    @Value("${baidu.ak}")
    private String ak;

    @Autowired
    private IBaseCityService baseCityService;


    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model) {
        List<String> cityList = baseCityService.cityList();
        model.addAttribute("cityList", cityList);
        return "communityNearby";
    }

    /**
     * 导出数据
     *
     * @param geo        纬度,经度;
     * @param areaRadius 范围多少(米)
     * @param keyword    关键字
     */
    @RequestMapping(value = "/export", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
    public void export(@RequestParam("keyword") String keyword,
                       @RequestParam("geo") String geo,
                       @RequestParam("areaRadius") Integer areaRadius,
                       @RequestParam("pageNum") Integer pageNum, HttpServletResponse response) throws Exception {
        BMapNearbyResultDto nearbyResultDto = requestBMap(keyword, geo, areaRadius, pageNum - 1);
        List<BMapNearbyDto> resultList;
        if (nearbyResultDto.isOk()) {
            resultList = nearbyResultDto.getResults();
            if (resultList == null || resultList.size() == 0) resultList = new ArrayList<>();
            OutputStream fOut = null;
            try {
                int len = resultList.size();
                response.addHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + ".xlsx");
                response.setContentType("application/vnd.ms-excel");
                SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(20);
                sxssfWorkbook.setCompressTempFiles(true);
                Sheet sheet = sxssfWorkbook.createSheet();
                String headContent = "查询内容共有" + nearbyResultDto.getTotal() + "条记录,当前第" + pageNum + "页,每页显示20条记录";
                createHeadRow(0, sheet, new String[]{headContent});
                String[] headStr = new String[]{"名称", "地址", "电话"};
                createHeadRow(1, sheet, headStr);
                for (int i = 0; i < len; i++) {
                    BMapNearbyDto nearbyDto = resultList.get(i);
                    createHeadRow(i + 2, sheet, new String[]{nearbyDto.getName(), nearbyDto.getAddress(), nearbyDto.getTelephone()});
                }
                fOut = response.getOutputStream();
                sxssfWorkbook.write(fOut);
            } catch (Exception e) {
                logger.error("文件导出异常", e);
            } finally {
                if (fOut != null) {
                    try {
                        fOut.close();
                    } catch (IOException e) {
                        logger.error("文件导出,关闭流异常", e);
                    }
                }
            }
        }

    }


    /**
     * 创建行的数据
     *
     * @param sheet       sheet内容
     * @param contentList 头信息
     * @return
     */
    private void createHeadRow(int index, Sheet sheet, String[] contentList) {
        Row row = sheet.createRow(index);
        int strLen = contentList.length;
        for (int i = 0; i < strLen; i++) {
            String content = contentList[i] == null ? "" : contentList[i];
            CellUtil.createCell(row, i, content);
        }
    }


    private BMapNearbyResultDto requestBMap(String query, String location, Integer areaRadius, Integer pageNum) {
        String result = HttpRequestUtil.get(String.format(Locale.CHINA, requestBMapPlaceApi, query, location, areaRadius, ak, pageNum, 20), null, null);
        return FastJsonUtils.getData(result, BMapNearbyResultDto.class);
    }

}
