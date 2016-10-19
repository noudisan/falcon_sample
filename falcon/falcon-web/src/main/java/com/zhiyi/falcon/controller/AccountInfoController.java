package com.zhiyi.falcon.controller;

import com.zhiyi.common.web.DtPagerResponse;
import com.zhiyi.common.web.DtRequest;
import com.zhiyi.common.web.PagerQueryResult;
import com.zhiyi.falcon.api.dto.AccountInfoDto;
import com.zhiyi.falcon.api.service.IAccountInfoService;
import org.apache.log4j.Logger;
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
 * 账户查询
 */
@Controller
@RequestMapping("/account")
public class AccountInfoController {

    Logger logger = Logger.getLogger(AccountInfoController.class);

    @Autowired
    @Qualifier("iAccountInfoService")
    private IAccountInfoService iAccountInfoService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model){
        return "account";
    }


    /**
     *  账号信息查询
     * @param accountInfoDto
     * @param dtRequest
     * @return
     */
    @ResponseBody
    @RequestMapping("/search")
    public DtPagerResponse<AccountInfoDto> search(AccountInfoDto accountInfoDto ,
                                                  DtRequest dtRequest){
        logger.info("搜索");
        //int currentPage = dtRequest.getiDisplayStart();
        //int pageSize = dtRequest.getiDisplayLength();

        Integer count = iAccountInfoService.count(accountInfoDto);

        accountInfoDto.resetPagination(dtRequest.currentPage(), dtRequest.pageSize(), dtRequest.getiSortCol_0(), dtRequest.getsSortDir_0());
        List<AccountInfoDto> list = iAccountInfoService.search(accountInfoDto);


        PagerQueryResult<AccountInfoDto> pagerQueryResult = new PagerQueryResult<AccountInfoDto>();
        pagerQueryResult.setDataList(list);
        pagerQueryResult.setTotal(count);

        DtPagerResponse<AccountInfoDto> dtoDtPagerResponse = new DtPagerResponse<AccountInfoDto>();
        dtoDtPagerResponse.setupResult(pagerQueryResult);
        dtoDtPagerResponse.setsEcho(dtRequest.getsEcho());
        return dtoDtPagerResponse;
    }


}
