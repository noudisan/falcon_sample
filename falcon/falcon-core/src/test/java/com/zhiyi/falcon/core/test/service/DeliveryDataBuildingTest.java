package com.zhiyi.falcon.core.test.service;


import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingDto;
import com.zhiyi.falcon.api.dto.DeliveryDataBuildingSearchDto;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;
import com.zhiyi.falcon.api.service.IDeliveryDataBuildingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-applicationContext-core.xml")
public class DeliveryDataBuildingTest {
	@Autowired
	private IDeliveryDataBuildingService iDeliveryDataBuildingService;

	@Test
	public void testSave() throws Exception {
		DeliveryDataBuildingDto deliveryDataBuildingDto = new DeliveryDataBuildingDto();
		deliveryDataBuildingDto.setBuildId(5);
		deliveryDataBuildingDto.setCommunityId(3);
		deliveryDataBuildingDto.setDeliveryEmployeeId(6);
		deliveryDataBuildingDto.setDeliveryTaskId(3);
		deliveryDataBuildingDto.setDeliveryNum(18);
		deliveryDataBuildingDto.setDeliveryStatus(DeliveryStatus.COMPLETE_DELIVERY);
		deliveryDataBuildingDto.setBeginDt(dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		deliveryDataBuildingDto.setEndDt(dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		deliveryDataBuildingDto.setDeliveryDt(dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
		deliveryDataBuildingDto.setRemark("楼栋数据2");

		iDeliveryDataBuildingService.save(deliveryDataBuildingDto);
	}

	private DeliveryDataBuildingDto getOne(int buildId){
		CommonResult<DeliveryDataBuildingDto> result = iDeliveryDataBuildingService.queryDeliveryData(buildId);
//		System.out.println(result);
		return result.getData();
	}

	@Test
	public void testQuery() throws Exception {
		DeliveryDataBuildingDto deliveryDataBuildingDto = getOne(1);
		System.out.println(deliveryDataBuildingDto);
	}

	@Test
	public void testUpdate() throws Exception {
		DeliveryDataBuildingDto deliveryDataBuildingDto = getOne(1);
		deliveryDataBuildingDto.setRemark("doubi");
		iDeliveryDataBuildingService.updateDeliveryData(deliveryDataBuildingDto);
		DeliveryDataBuildingDto result = getOne(1);
		Assert.assertEquals("doubi", result.getRemark());
	}

	@Test
	public void testSelect(){
		DeliveryDataBuildingSearchDto searchDto = new DeliveryDataBuildingSearchDto();

		List<DeliveryDataBuildingDto> result = iDeliveryDataBuildingService.search(searchDto);
		System.out.println(result);
		Assert.assertNotNull(result);

		Integer count = iDeliveryDataBuildingService.count(searchDto);
		System.out.println(count);
		Assert.assertNotNull(count);
	}

	public static boolean checkNullStr(String str) {
		if (null == str || "undefined".equalsIgnoreCase(str)|| "null".equalsIgnoreCase(str) || "".equals(str.trim())) {
			return true;
		}
		return false;
	}

	public static String dateToString(Date date, String formatString) {
		if (checkNullStr(formatString)) {
			formatString = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat dd = new SimpleDateFormat(formatString);
		String str = "";
		if (null != date) {
			str = dd.format(date);
		}
		return str;
	}

	public static Date stringToDate(String date, String format) {
		if (checkNullStr(date)) {
			return null;
		}
		if (checkNullStr(format)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}


}
