package com.zhiyi.falcon.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zhiyi.city.api.IBaseCityService;
import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.ResponseResult;
import com.zhiyi.falcon.utils.SessionManager;
import com.zhiyi.section.api.IDeliverySectionService;
import com.zhiyi.section.dto.DeliverySectionDto;
import com.zhiyi.section.dto.DeliverySectionPointDto;
import com.zhiyi.section.dto.DeliverySectionSearchDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 板块 controller 接口
 */
@Controller
@RequestMapping("/deliverySection")
public class DeliverySectionController {

	@Autowired
	private IBaseCityService iBaseCityService;

	@Autowired
	private IDeliverySectionService ideliverySectionService;


	@RequestMapping(value ={"","/index"}, method = RequestMethod.GET)
	public String index(Model model) {
		CommonResult<List<String>> commonResult = ideliverySectionService.querySectionCityList();
		List<String> cityList = commonResult.getData();
		if(cityList.isEmpty()){
			cityList.add("上海");
		}
		model.addAttribute("cityList",cityList);

		List<String> baseCityList = iBaseCityService.cityList();
		model.addAttribute("baseCityList",baseCityList);
		return "section";
	}

	@RequestMapping(value = "/get", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommonResult<DeliverySectionDto> get(Model model,@RequestParam("sectionId") Integer sectionId) {
		CommonResult<DeliverySectionDto> commonResult = ideliverySectionService.queryOneDeliverySection(sectionId);
		return commonResult;
	}

	@RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public DtPagerResponse<DeliverySectionDto> search(DeliverySectionSearchDto searchDto, DtRequest dtReq) {
		searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
		List<DeliverySectionDto> pageResultDto = ideliverySectionService.search(searchDto);
		searchDto.disablePaging();
		int totalSize = ideliverySectionService.count(searchDto);
		return ResponseResult.value(pageResultDto, totalSize, dtReq);

	}

	@RequestMapping(value = "/queryListSectionByCity", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommonResult<List<DeliverySectionDto>> queryListSectionByCity(@RequestParam(value = "cityName",required = false) String cityName,
																		 @RequestParam(value = "sectionName",required = false) String sectionName) {
		DeliverySectionSearchDto deliverySectionSearchDto = new DeliverySectionSearchDto();
		deliverySectionSearchDto.setCityName(cityName);
		deliverySectionSearchDto.setSectionName(sectionName);
		deliverySectionSearchDto.disablePaging();
		CommonResult<List<DeliverySectionDto>> commonResult = ideliverySectionService.queryListByCondition(deliverySectionSearchDto);

		return commonResult;
	}


	@RequestMapping(value = "/getSectionCommunityList", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommonResult<String> saveSectionCommunityList(@RequestParam("sectionId") Integer sectionId) {
		CommonResult<String> commonResult =ideliverySectionService.getSectionCommunityList(sectionId);
		return commonResult;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommonResult<DeliverySectionDto> saveDeliverySection(HttpServletRequest request) throws IOException {
		CommonResult<DeliverySectionDto> commonResult =new CommonResult<>();

		String postStr = getRequestPostStr(request);
		DeliverySectionDto deliverySectionDto = JSONObject.parseObject(postStr, DeliverySectionDto.class);
		JSONObject jsonObject = JSONObject.parseObject(postStr);

		List<DeliverySectionPointDto> list = JSON.parseArray(jsonObject.get("points").toString(), DeliverySectionPointDto.class);

		if(validateName(deliverySectionDto)){
			commonResult.doErrorHandle("板块名重复!");
		}

		deliverySectionDto.setDeliverySectionPoints(list);
		deliverySectionDto.setCreateDt(new Date());
		deliverySectionDto.setCreateUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());
		CommonResult<Integer> saveResult = ideliverySectionService.saveDeliverySection(deliverySectionDto);


		if(saveResult.getCode() ==CommonResult.RESULT_STATUS_SUCCESS){
			deliverySectionDto.setId(saveResult.getData());
			commonResult.setData(deliverySectionDto);
			//创建板块 绑定小区
			ideliverySectionService.saveSectionCommunityList(deliverySectionDto.getSectionName(),deliverySectionDto.getCity());

		}
		return commonResult;
	}

	@RequestMapping(value = "/update", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public CommonResult<DeliverySectionDto> update(DeliverySectionDto deliverySectionDto){

		CommonResult<Integer> updateResult= ideliverySectionService.updateDeliverySection(deliverySectionDto);
		if(updateResult.getCode() ==CommonResult.RESULT_STATUS_SUCCESS){

			return ideliverySectionService.queryOneDeliverySection(deliverySectionDto.getId());
		}
		return null;
	}

	private boolean validateName(DeliverySectionDto deliverySectionDto) {
		DeliverySectionSearchDto sectionSearchDto = new DeliverySectionSearchDto();
		sectionSearchDto.setSectionName(deliverySectionDto.getSectionName());
		sectionSearchDto.disablePaging();
		CommonResult<List<DeliverySectionDto>> result = ideliverySectionService.queryListByCondition(sectionSearchDto);

		if(result.getCode() == CommonResult.RESULT_STATUS_SUCCESS ){
			if(result.getData()!=null && !result.getData().isEmpty()){
				return true;
			}

		}
		return false;
	}


	@RequestMapping(value = "/deleteSection", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deleteSection(@RequestParam("sectionId") Integer sectionId) {
		CommonResult<Integer> result =ideliverySectionService.deleteDeliverySection(sectionId);
		if(result.getCode() ==CommonResult.RESULT_STATUS_SUCCESS){
			return "SUCCESS";
		}else {
			return "FAIL";
		}

	}

	/**
	 * Get request query string, form method : post
	 *
	 * @param request
	 * @return byte[]
	 * @throws IOException
	 */
	private byte[] getRequestPostBytes(HttpServletRequest request)
			throws IOException {
		int contentLength = request.getContentLength();
		/*当无请求参数时，request.getContentLength()返回-1 */
		if (contentLength < 0) {
			return null;
		}
		byte buffer[] = new byte[contentLength];
		for (int i = 0; i < contentLength; ) {

			int readlen = request.getInputStream().read(buffer, i,
					contentLength - i);
			if (readlen == -1) {
				break;
			}
			i += readlen;
		}
		return buffer;
	}

	/**
	 * Get request query string, form method : post
	 *
	 * @param request
	 * @return
	 * @throws IOException
	 */
	private String getRequestPostStr(HttpServletRequest request)
			throws IOException {
		byte buffer[] = getRequestPostBytes(request);
		String charEncoding = request.getCharacterEncoding();
		if (charEncoding == null) {
			charEncoding = "UTF-8";
		}
		return new String(buffer, charEncoding);
	}


}
