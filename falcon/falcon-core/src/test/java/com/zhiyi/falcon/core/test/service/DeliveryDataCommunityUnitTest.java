package com.zhiyi.falcon.core.test.service;


import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityUnitSearchDto;
import com.zhiyi.falcon.api.enumType.DeliveryResult;
import com.zhiyi.falcon.api.enumType.DeliveryType;
import com.zhiyi.falcon.api.service.IDeliveryDataCommunityUnitService;
import com.zhiyi.utils.DateUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:test-applicationContext-core.xml")
public class DeliveryDataCommunityUnitTest {
	@Autowired
	private IDeliveryDataCommunityUnitService iDeliveryDataCommunityUnitService;

	@Test
	public void testSave() throws Exception {
		DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto = new DeliveryDataCommunityUnitDto();
		deliveryDataCommunityUnitDto.setRemark("fuck");
		deliveryDataCommunityUnitDto.setDeliveryResult(DeliveryResult.CANDELIVERY);
		deliveryDataCommunityUnitDto.setDeliveryDt(DateUtils.format(new Date()));
		deliveryDataCommunityUnitDto.setBuildId(1);
		deliveryDataCommunityUnitDto.setDeliveryEmployeeId(1001);
		deliveryDataCommunityUnitDto.setCommunityUnitId(10011);
		deliveryDataCommunityUnitDto.setDeliveryNum(100);
		deliveryDataCommunityUnitDto.setDeliveryTaskId(2);
		deliveryDataCommunityUnitDto.setDeliveryType(DeliveryType.LIFT);
		iDeliveryDataCommunityUnitService.save(deliveryDataCommunityUnitDto);
	}

	private DeliveryDataCommunityUnitDto getOne(int communityUnitId){
		CommonResult<DeliveryDataCommunityUnitDto> result = iDeliveryDataCommunityUnitService.queryDeliveryData(communityUnitId);
		return result.getData();
	}

	@Test
	public void testQuery() throws Exception {
		DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto = getOne(1);
		System.out.println(deliveryDataCommunityUnitDto);
	}

	@Test
	public void testUpdate() throws Exception {
		DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto = getOne(1);
		deliveryDataCommunityUnitDto.setRemark("doubi");
		iDeliveryDataCommunityUnitService.updateDeliveryData(deliveryDataCommunityUnitDto);
		DeliveryDataCommunityUnitDto result = getOne(1);
		Assert.assertEquals("doubi", result.getRemark());
	}

	@Test
	public void testSelect(){
		DeliveryDataCommunityUnitSearchDto searchDto = new DeliveryDataCommunityUnitSearchDto();

		List<DeliveryDataCommunityUnitDto> result = iDeliveryDataCommunityUnitService.search(searchDto);

		Assert.assertNotNull(result);

		Integer count = iDeliveryDataCommunityUnitService.count(searchDto);

		Assert.assertNotNull(count);
	}


}
