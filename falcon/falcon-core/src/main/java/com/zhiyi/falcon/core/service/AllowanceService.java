package com.zhiyi.falcon.core.service;

import com.zhiyi.falcon.api.dto.AllowanceDto;
import com.zhiyi.falcon.core.dao.AllowanceMapper;
import com.zhiyi.falcon.core.model.Allowance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Adminstrator on 2015-07-21.
 * @author tangkai
 * @email kai.tangk@xqwy.cn
 */
@Transactional
@Service("allowanceService")
public class AllowanceService {


    @Autowired
    private AllowanceMapper allowanceMapper;

    /**
     * 添加用户补贴信息
     * @param allowanceDto
     * @return
     */
    @Transactional
    public Integer save(AllowanceDto allowanceDto) {
        Allowance allowance = Allowance.dtoToModel(allowanceDto);
        int result = allowanceMapper.insert(allowance);
        return result;
    }

    /**
     * 删除补贴信息
     * @param id
     * @return
     */
    public Integer delete(int id) {
        return null;
    }

    /**
     * 修改补贴信息
     * @param allowanceDto
     * @return
     */
    public Integer update(AllowanceDto allowanceDto) {
        Allowance allowance = Allowance.dtoToModel(allowanceDto);
        Integer result = allowanceMapper.updateByPrimaryKeySelective(allowance);
        return result;
    }

    /**
     * 查询出补贴的详细信息
     * @param id
     * @return
     */
    public AllowanceDto detail(int id) {
        Allowance allowance = allowanceMapper.selectByPrimaryKey(id);
        return Allowance.modelTodto(allowance);
    }

    public Integer count(AllowanceDto allowanceDto){
        Integer count = allowanceMapper.count(allowanceDto);
        return count;
    }

    /**
     * 根据条件查询补贴信息
     * @param allowanceDto
     * @return
     */
    public List<AllowanceDto> search(AllowanceDto allowanceDto) {
        List<Allowance> result = allowanceMapper.search(allowanceDto);
        return Allowance.modelListToDtoList(result);

    }

    /**
     * 保存或更新补贴
     * @param AllowanceMapper
     */
    public void saveOrUpdate(AllowanceDto AllowanceMapper) {



    }
}
