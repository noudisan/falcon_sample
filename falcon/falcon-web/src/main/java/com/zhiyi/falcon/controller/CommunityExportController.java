package com.zhiyi.falcon.controller;

import com.zhiyi.city.api.IBaseCityService;
import com.zhiyi.community.api.IDeliveryCommunityService;
import com.zhiyi.community.dto.CommunityElevatorFlag;
import com.zhiyi.community.dto.CommunityExportSearchDto;
import com.zhiyi.community.dto.DeliveryCommunityDto;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by yangjj on 2015/12/11.
 */
@RequestMapping(value = "/communityExport")
@Controller
public class CommunityExportController {
	@Autowired
	private IBaseCityService baseCityService;

	@Autowired
	private IDeliveryCommunityService deliveryCommunityService;


	private Logger logger = Logger.getLogger(CommunityExportController.class);


	@RequestMapping(method = RequestMethod.GET)
	public String index(ModelMap model) {
		List<String> cityList = baseCityService.cityList();
		model.addAttribute("cityList", cityList);
		return "communityExport";
	}

	/**
	 * 查询该经度附近的小区信息
	 *
	 * @param distance  距离
	 * @param longitude 经度
	 * @param latitude  纬度
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public List<DeliveryCommunityDto> search(@RequestParam("distance") Integer distance,
	                                         @RequestParam("longitude") Double longitude,
	                                         @RequestParam("latitude") Double latitude) {
		CommunityExportSearchDto communityExportSearchDto = new CommunityExportSearchDto();
		communityExportSearchDto.setDistance(distance);
		communityExportSearchDto.setLatitude(latitude);
		communityExportSearchDto.setLongitude(longitude);
		return deliveryCommunityService.searchNearbyCommunity(communityExportSearchDto);
	}

	/**
	 * 导出
	 *
	 * @param distance
	 * @param longitude
	 * @param latitude
	 */
	@RequestMapping(value = "export", method = RequestMethod.GET)
	public void export(@RequestParam("distance") Integer distance,
	                   @RequestParam("longitude") Double longitude,
	                   @RequestParam("latitude") Double latitude, HttpServletResponse response) {
		CommunityExportSearchDto communityExportSearchDto = new CommunityExportSearchDto();
		communityExportSearchDto.setDistance(distance);
		communityExportSearchDto.setLatitude(latitude);
		communityExportSearchDto.setLongitude(longitude);
		List<DeliveryCommunityDto> deliveryCommunityDtoList = deliveryCommunityService.searchNearbyCommunity(communityExportSearchDto);
		OutputStream fOut = null;
		try {
			int len = deliveryCommunityDtoList.size();
			response.addHeader("Content-Disposition", "attachment;filename=" + System.currentTimeMillis() + ".xlsx");
			response.setContentType("application/vnd.ms-excel");
			SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(len);
			sxssfWorkbook.setCompressTempFiles(true);
			Sheet sheet = sxssfWorkbook.createSheet();
			String[] headStr = new String[]{"小区编号", "小区名称", "小区类型", "城市", "区域", "板块", "是否有电梯", "户数", "价格", "建筑类型", "用途类型", "竣工时间"};
			createHeadRow(0, sheet, headStr);
			for (int i = 0; i < len; i++) {
				DeliveryCommunityDto deliveryCommunityDto = deliveryCommunityDtoList.get(i);
				String communityType = deliveryCommunityDto.getCommunityType() == null ? "" : deliveryCommunityDto.getCommunityType().getStatus();
				if (communityType.equalsIgnoreCase("未知")) {
					communityType = "";
				}
				String elevatorFlag = deliveryCommunityDto.getElevatorFlag() == null ? "" : deliveryCommunityDto.getElevatorFlag().getStatus();
				if (elevatorFlag.equalsIgnoreCase("未知")) {
					elevatorFlag = "";
				}
				String buildType = deliveryCommunityDto.getBuildType() == null ? "" : deliveryCommunityDto.getBuildType().getStatus();
				if (buildType.equalsIgnoreCase("未知")) {
					buildType = "";
				}
				createHeadRow(i + 1, sheet, new String[]{deliveryCommunityDto.getCommunityCode(), deliveryCommunityDto.getCommunityName(),
						communityType, deliveryCommunityDto.getCity(),
						deliveryCommunityDto.getArea(), deliveryCommunityDto.getSection(), elevatorFlag,
						deliveryCommunityDto.getHouseholds() + "", deliveryCommunityDto.getPrices() + "",
						buildType, deliveryCommunityDto.getFunType(), deliveryCommunityDto.getModifyYears()
				});
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


}
