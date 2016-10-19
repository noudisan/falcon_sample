package com.zhiyi.falcon.controller;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.falcon.api.dto.DeliveryTaskDto;
import com.zhiyi.falcon.api.dto.DeliveryTaskSearchDto;
import com.zhiyi.falcon.api.exception.BaseException;
import com.zhiyi.falcon.api.service.IDeliveryTaskService;
import com.zhiyi.falcon.utils.SessionManager;
import com.zhiyi.utils.DateUtils;
import com.zhiyi.utils.Utils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/deliverytask")
public class DeliveryTaskController {

    @Autowired
    @Qualifier("deliveryTaskService")
    private IDeliveryTaskService iDeliveryTaskService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {

        return "deliverytask";
    }

    @ResponseBody
    @RequestMapping(value = "/get", produces = "application/json;charset=UTF-8")
    public DeliveryTaskDto get(@RequestParam(value = "taskId") int taskId){
        CommonResult<DeliveryTaskDto> result = iDeliveryTaskService.queryTaskData(taskId);

        return result.getData();
    }

    @ResponseBody
    @RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public DtPagerResponse<DeliveryTaskDto> search( DeliveryTaskSearchDto searchDto,
                                                DtRequest dtReq){
        Integer count = iDeliveryTaskService.count(searchDto);

        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        List<DeliveryTaskDto> list = iDeliveryTaskService.search(searchDto);

        DtPagerResponse<DeliveryTaskDto> pagerResponse = new DtPagerResponse<>();
        pagerResponse.setsEcho(dtReq.getsEcho());

        PagerQueryResult<DeliveryTaskDto> pagerQueryResult = new PagerQueryResult<>();
        pagerQueryResult.setDataList(list);
        pagerQueryResult.setTotal(count);
        pagerResponse.setupResult(pagerQueryResult);
        return pagerResponse;
    }

    @ResponseBody
    @RequestMapping(value = "/saveOrUpdate", produces = "application/json;charset=UTF-8")
    @RequiresPermissions(value={"deliveryTask:add","deliveryTask:update"},logical = Logical.OR)
    public String saveOrUpdate(DeliveryTaskDto deliveryTaskDto){
        if(deliveryTaskDto.getId() == null){
            deliveryTaskDto.setCode(Utils.generateCode());
            deliveryTaskDto.setCreateDt(DateUtils.format(new Date()));
            deliveryTaskDto.setCreateUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());
            deliveryTaskDto.setModifyUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());
            deliveryTaskDto.setModifyDt(DateUtils.format(new Date()));
            iDeliveryTaskService.save(deliveryTaskDto);
        }else {
            deliveryTaskDto.setModifyDt(DateUtils.format(new Date()));
            deliveryTaskDto.setModifyUser(SessionManager.getLoginUser() == null ? "" : SessionManager.getLoginUser().getUserName());

            iDeliveryTaskService.update(deliveryTaskDto);
        }

        return "SUCCESS";
    }

    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    public String delete(@PathVariable("id") Integer id) throws IOException {
        try {
            iDeliveryTaskService.delete(id);
        } catch (Exception e) {
            throw new BaseException("删除" + id + "任务异常", e);
        }
        return "SUCCESS";
    }

}
