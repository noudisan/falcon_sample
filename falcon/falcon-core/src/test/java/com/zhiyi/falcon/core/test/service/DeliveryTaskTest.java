package com.zhiyi.falcon.core.test.service;


import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunityDto;
import com.zhiyi.falcon.api.dto.DeliveryDataCommunitySearchDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskSearchDto;
import com.zhiyi.falcon.api.enumType.DeliveryStatus;
import com.zhiyi.falcon.api.enumType.TaskSampling;
import com.zhiyi.falcon.api.enumType.TaskStatus;
import com.zhiyi.falcon.api.service.IDeliveryDataCommunityService;
import com.zhiyi.falcon.api.service.IDeliveryTaskService;
import com.zhiyi.utils.DateUtils;
import com.zhiyi.utils.MD5Utils;
import com.zhiyi.utils.Utils;
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
public class DeliveryTaskTest {
	@Autowired
	private IDeliveryTaskService iDeliveryTaskService;

	@Test
	public void testSave() throws Exception {
		DeliveryTaskDto deliveryTaskDto = new DeliveryTaskDto();
		deliveryTaskDto.setSendCount(15000);
		deliveryTaskDto.setTaskDesc("我就是任务达人");
		deliveryTaskDto.setStartTime(DateUtils.format(new Date()));
		deliveryTaskDto.setModifyDt(DateUtils.format(new Date()));
		deliveryTaskDto.setCode(Utils.generateCode());
		deliveryTaskDto.setCreateDt(DateUtils.format(new Date()));
		deliveryTaskDto.setCreateUser("逗比土豆");
		deliveryTaskDto.setDriver("逗比逗比");
		deliveryTaskDto.setDriverPhoneNum("15201712707");
		deliveryTaskDto.setIsSampling(TaskSampling.SAMPLING);
		deliveryTaskDto.setLeader("猪领导");
		deliveryTaskDto.setLeaderPhoneNum("13838389438");
		deliveryTaskDto.setMassAddress("小区无忧");
		deliveryTaskDto.setMassTime(DateUtils.format(new Date()));
		deliveryTaskDto.setModifyUser("逗比土豆");
		deliveryTaskDto.setStatus(TaskStatus.TASKBEGIN);
		iDeliveryTaskService.save(deliveryTaskDto);
	}

	private DeliveryTaskDto getOne(int taskId){
		CommonResult<DeliveryTaskDto> result = iDeliveryTaskService.queryTaskData(taskId);
//		System.out.println(result);
		return result.getData();
	}

	@Test
	public void testQuery() throws Exception {
		DeliveryTaskDto deliveryTaskDto = getOne(1);
		System.out.println(deliveryTaskDto);
	}

	@Test
	public void testUpdate() throws Exception {
		DeliveryTaskDto deliveryTaskDto = getOne(1);
		deliveryTaskDto.setLeader("装逼小王子");
		iDeliveryTaskService.update(deliveryTaskDto);
		DeliveryTaskDto result = getOne(1);
		Assert.assertEquals("装逼小王子", result.getLeader());
	}

	@Test
	public void testSelect(){
		DeliveryTaskSearchDto searchDto = new DeliveryTaskSearchDto();

		List<DeliveryTaskDto> result = iDeliveryTaskService.search(searchDto);
		System.out.println(result);
		Assert.assertNotNull(result);

		Integer count = iDeliveryTaskService.count(searchDto);
		System.out.println(count);
		Assert.assertNotNull(count);
	}


}
