package com.zhiyi.test.section;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.section.api.IDeliverySectionService;
import com.zhiyi.section.dto.DeliverySectionDto;
import com.zhiyi.section.dto.DeliverySectionPointDto;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 板块服务层测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-core.xml")
public class DeliverySectionServiceTest {
	@Autowired
	private IDeliverySectionService deliverySectionService;

	@Test
	public void testSaveDeliverySection() throws Exception {
		DeliverySectionDto deliverySectionDto = new DeliverySectionDto();
		deliverySectionDto.setLatitude(123.001);
		deliverySectionDto.setLongitude(45.003);
		deliverySectionDto.setAddress("上海");
		deliverySectionDto.setCity("上海");
		deliverySectionDto.setCreateUser("createUser");
		deliverySectionDto.setCreateDt(new Date());
		deliverySectionDto.setModifyDt(new Date());
		deliverySectionDto.setModifyUser("modifyUser");
		deliverySectionDto.setSectionName("sectionName");
		deliverySectionDto.setStatusFlag("statusFlag");

		List<DeliverySectionPointDto> list = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			DeliverySectionPointDto deliverySectionPointDto = new DeliverySectionPointDto();
			deliverySectionPointDto.setLat(110.003);
			deliverySectionPointDto.setLng(34.002);
			list.add(deliverySectionPointDto);
		}
		deliverySectionDto.setDeliverySectionPoints(list);
		CommonResult<Integer> commonResult = deliverySectionService.saveDeliverySection(deliverySectionDto);
		Assert.assertEquals(1111, commonResult.getCode());

	}

	@Test
	public void testUpdateDeliverySection() throws Exception {
		DeliverySectionDto deliverySectionDto = new DeliverySectionDto();
		deliverySectionDto.setId(5);
		deliverySectionDto.setLatitude(123.001);
		deliverySectionDto.setLongitude(45.003);
		deliverySectionDto.setAddress("上海1");
		deliverySectionDto.setCity("上海2");
		deliverySectionDto.setCreateUser("createUser");
		deliverySectionDto.setCreateDt(new Date());
		deliverySectionDto.setModifyDt(new Date());
		deliverySectionDto.setModifyUser("modifyUser");
		deliverySectionDto.setSectionName("sectionName");
		deliverySectionDto.setStatusFlag("statusFlag");

		List<DeliverySectionPointDto> list = new ArrayList<>();
		for (int i = 1; i < 4; i++) {
			DeliverySectionPointDto deliverySectionPointDto = new DeliverySectionPointDto();
			deliverySectionPointDto.setId(i);
			deliverySectionPointDto.setLat(110.003);
			deliverySectionPointDto.setLng(34.002);
			list.add(deliverySectionPointDto);
		}
		deliverySectionDto.setDeliverySectionPoints(list);
		CommonResult<Integer> commonResult = deliverySectionService.updateDeliverySection(deliverySectionDto);
		Assert.assertEquals(1111, commonResult.getCode());
	}



	@Test
	public void testQueryOneDeliveySection() throws Exception {
		CommonResult<DeliverySectionDto> commonResult = deliverySectionService.queryOneDeliverySection(5);
		Assert.assertEquals(1111,commonResult.getCode());
		System.out.println(commonResult.getData());
	}

	public void testQueryListByCondition() throws Exception {

	}
	@Test
	public void testDeleteDeliverySection() throws Exception {
		CommonResult<Integer> commonResult = deliverySectionService.deleteDeliverySection(1);
		Assert.assertEquals(1111,commonResult.getCode());

	}


}
