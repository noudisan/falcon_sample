package com.zhiyi.falcon.core.test.service;


import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunitySearchDto;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;
import com.zhiyi.falcon.api.service.IDeliveryDataCommunityService;
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
public class DeliveryDataCommunityTest {
	@Autowired
	private IDeliveryDataCommunityService iDeliveryDataCommunityService;

	@Test
	public void testSave() throws Exception {
		DeliveryDataCommunityDto deliveryDataCommunityDto = new DeliveryDataCommunityDto();
		deliveryDataCommunityDto.setSectionId(1);
		deliveryDataCommunityDto.setCommunityId(5);
		deliveryDataCommunityDto.setDeliveryTaskId(5);
		deliveryDataCommunityDto.setDeliveryNum(300);
		deliveryDataCommunityDto.setDeliveryStatus(DeliveryStatus.TO_DELIVERY);
		deliveryDataCommunityDto.setBeginDt(dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		deliveryDataCommunityDto.setDeliveryDt(dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
		deliveryDataCommunityDto.setRemark("小区数据5");
		iDeliveryDataCommunityService.save(deliveryDataCommunityDto);
	}

	private DeliveryDataCommunityDto getOne(int communityId){
		CommonResult<DeliveryDataCommunityDto> result = iDeliveryDataCommunityService.queryDeliveryData(communityId);
//		System.out.println(result);
		return result.getData();
	}

	@Test
	public void testQuery() throws Exception {
		DeliveryDataCommunityDto deliveryDataCommunityDto = getOne(1);
		System.out.println(deliveryDataCommunityDto);
	}

	@Test
	public void testUpdate() throws Exception {
		DeliveryDataCommunityDto deliveryDataCommunityDto = getOne(1);
		deliveryDataCommunityDto.setRemark("doubi");
		iDeliveryDataCommunityService.updateDeliveryData(deliveryDataCommunityDto);
		DeliveryDataCommunityDto result = getOne(1);
		Assert.assertEquals("doubi", result.getRemark());
	}

	@Test
	public void testSelect(){
		DeliveryDataCommunitySearchDto searchDto = new DeliveryDataCommunitySearchDto();

		List<DeliveryDataCommunityDto> result = iDeliveryDataCommunityService.search(searchDto);
		System.out.println(result);
		Assert.assertNotNull(result);

		Integer count = iDeliveryDataCommunityService.count(searchDto);
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
