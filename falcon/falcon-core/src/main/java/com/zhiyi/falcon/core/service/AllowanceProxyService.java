package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.AllowanceDto;
import com.zhiyi.falcon.api.service.IAllowanceService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Adminstrator on 2015-07-21.
 */
@Service("allowanceProxyService")
public class AllowanceProxyService implements IAllowanceService {

    private Logger logger = Logger.getLogger(AllowanceProxyService.class);

    @Autowired
    private AllowanceService allowanceService;

    /**
     * 添加补贴信息
     * @param allowanceDto
     * @return
     */
    @Override
    public CommonResult<Integer> save(AllowanceDto allowanceDto) {
        logger.info("添加补贴信息");
        CommonResult<Integer> commonResult = new CommonResult<>();
        Integer result = 0;
        try{
            result = allowanceService.save(allowanceDto);
            commonResult.setData(result);
        }catch (Exception e){
            logger.error("添加补贴信息异常", e);
            commonResult.doErrorHandle("添加补贴信息异常");
        }
        return commonResult;
    }

    @Override
    public CommonResult<Integer> update(AllowanceDto allowanceDto) {
        logger.info("更新补贴信息");
        CommonResult<Integer> commonResult = new CommonResult<>();
        Integer result = 0;
        try{
            result = allowanceService.update(allowanceDto);
            commonResult.setData(result);
        }catch (Exception e){
            logger.error("更新补贴信息异常", e);
            commonResult.doErrorHandle("更新补贴信息异常");
        }
        return commonResult;
    }

    @Override
    public CommonResult<AllowanceDto> detail(int id) {
        logger.info("查看补贴详细信息");
        CommonResult<AllowanceDto> commonResult = new CommonResult<>();
        AllowanceDto allowanceDto = null;
        try{
            allowanceDto = allowanceService.detail(id);
            commonResult.setData(allowanceDto);
        }catch (Exception e){
            logger.error("查看补贴详细信息异常", e);
            commonResult.doErrorHandle("查看补贴详细信息异常");
        }
        return commonResult;
    }

    @Override
    public List<AllowanceDto> search(AllowanceDto allowanceDto) {
        logger.info("查询补贴信息");
        List<AllowanceDto> allowanceDtoList = null;
        try{
            allowanceDtoList = allowanceService.search(allowanceDto);
        }catch (Exception e){
            logger.error("查询补贴信息异常", e);
        }
        return allowanceDtoList;
    }

    @Override
    public Integer count(AllowanceDto allowanceDto) {
        logger.info("查询补贴信息");
        Integer result = 0;
        try{
            result = allowanceService.count(allowanceDto);
        }catch (Exception e){
            logger.error("查询补贴信息异常", e);
        }
        return result;
    }
}
