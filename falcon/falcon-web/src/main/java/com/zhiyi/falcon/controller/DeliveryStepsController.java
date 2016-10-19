package com.zhiyi.falcon.controller;

import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.falcon.api.dto.DeliveryStepsDto;
import com.zhiyi.falcon.api.dto.DeliveryStepsSearchDto;
import com.zhiyi.falcon.api.service.IDeliveryStepsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by renfj on 2015/6/30.
 */

@Controller
@RequestMapping("/deliverysteps")
public class DeliveryStepsController {

    @Autowired
    @Qualifier("deliveryStepsService")
    private IDeliveryStepsService iDeliveryStepsService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {

        return "deliverysteps";
    }

    @ResponseBody
    @RequestMapping(value = "/search", produces = "application/json;charset=UTF-8")
    public DtPagerResponse<DeliveryStepsDto> search(DeliveryStepsSearchDto searchDto, DtRequest dtReq) {

        Integer count = iDeliveryStepsService.count(searchDto);

        searchDto.resetPagination(dtReq.currentPage(), dtReq.pageSize(), dtReq.getiSortCol_0(), dtReq.getsSortDir_0());
        List<DeliveryStepsDto> list = iDeliveryStepsService.search(searchDto);

        DtPagerResponse<DeliveryStepsDto> pagerResponse = new DtPagerResponse<>();
        pagerResponse.setsEcho(dtReq.getsEcho());

        PagerQueryResult<DeliveryStepsDto> pagerQueryResult = new PagerQueryResult<>();
        pagerQueryResult.setDataList(list);
        pagerQueryResult.setTotal(count);
        pagerResponse.setupResult(pagerQueryResult);
        return pagerResponse;
    }
}
