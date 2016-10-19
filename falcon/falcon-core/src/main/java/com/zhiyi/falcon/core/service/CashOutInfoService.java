package com.zhiyi.falcon.core.service;

import com.zhiyi.common.dto.CommonResult;
import com.zhiyi.falcon.api.dto.AccountInfoDto;
import com.zhiyi.falcon.api.dto.BaseEmployeeDto;
import com.zhiyi.falcon.api.dto.BaseEmployeeSearchDto;
import com.zhiyi.falcon.api.dto.CashOutInfoDto;
import com.zhiyi.falcon.core.dao.CashOutInfoMapper;
import com.zhiyi.falcon.core.model.AccountInfo;
import com.zhiyi.falcon.core.model.CashOutInfo;
import com.zhiyi.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 提现信息
 * Created by Adminstrator on 2015/6/24.
 */
@Transactional
@Service("cashOutInfoService")
public class CashOutInfoService {

    Logger logger = Logger.getLogger(CashOutInfoService.class);

    @Autowired
    private CashOutInfoMapper cashOutInfoMapper;

    @Autowired
    private BaseEmployeeService baseEmployeeService;

    @Autowired
    private AccountInfoService accountInfoService;

    /**
     * 保存提现信息
     * @param cashOutInfoDto
     * @return
     */
    public Integer save(CashOutInfoDto cashOutInfoDto) {
        logger.info("保存提现信息");
        cashOutInfoDto.setMidifyDt(new Date());
        cashOutInfoDto.setCreateDt(new Date());
        CashOutInfo cashOutInfo = CashOutInfo.dtoToModel(cashOutInfoDto);
        Integer result = cashOutInfoMapper.insert(cashOutInfo);
        return result;
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public CommonResult<Integer> delete(int id) {

        return null;
    }

    /**
     * 修改
     * @param cashOutInfoDtoList
     * @return
     */
    public Integer update(List<CashOutInfoDto> cashOutInfoDtoList) {
        Integer result = 0;
        for(CashOutInfoDto cashOutInfoDto : cashOutInfoDtoList){
            CashOutInfo cashOutInfo = CashOutInfo.dtoToModel(cashOutInfoDto);
            cashOutInfo.setMidifyDt(new Date());

            if( cashOutInfo.getDealStatus() == 2){ //提现失败退回金额
                CashOutInfoDto cashOutInfoDtoSearch = new CashOutInfoDto();
                cashOutInfoDtoSearch.setId(cashOutInfo.getId());
                cashOutInfoDtoSearch.setDealStatus(0); //仅查询申请中的记录
                List<CashOutInfo> cashOutInfoListRequest = cashOutInfoMapper.search(cashOutInfoDtoSearch);
                if( cashOutInfoListRequest != null && cashOutInfoListRequest.size() > 0){
                    CashOutInfo cashOutInfoDetail = cashOutInfoListRequest.get(0);
                    AccountInfoDto accountInfoDto = new AccountInfoDto();
                    accountInfoDto.setUserId(cashOutInfoDetail.getUserId());
                    List<AccountInfoDto> accountInfoDtoList = accountInfoService.search(accountInfoDto);
                    if( accountInfoDtoList != null && accountInfoDtoList.size() > 0){
                        AccountInfoDto accountInfoDto1 = accountInfoDtoList.get(0);
                        BigDecimal accountAmount = new BigDecimal(accountInfoDto1.getAccountAmount()).add(new BigDecimal(cashOutInfoDetail.getCashAmount()));
                        accountAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
                        accountInfoDto1.setAccountAmount(accountAmount.doubleValue());
                        accountInfoService.update(accountInfoDto1);
                    }
                }
            }

            result = cashOutInfoMapper.updateByPrimaryKeySelective(cashOutInfo);
        }
        return result;
    }

    /**
     * 提现详细信息
     * @param id
     * @return
     */
    public CashOutInfoDto detail(int id) {
        return CashOutInfo.modelToDto(cashOutInfoMapper.selectByPrimaryKey(id));
    }

    /**
     * 统计数据总条数
     * @param cashOutInfoDto
     * @return
     */
    public int count(CashOutInfoDto cashOutInfoDto) {
        return cashOutInfoMapper.count(cashOutInfoDto);
    }

    /**
     * 根据条件搜索查询
     * @param cashOutInfoDto
     * @return
     */
    public List<CashOutInfoDto> search(CashOutInfoDto cashOutInfoDto) {
        List<CashOutInfo> cashOutInfoList = cashOutInfoMapper.search(cashOutInfoDto);
        return CashOutInfo.modelListToDtoList(cashOutInfoList);
    }

    /**
     * 提现操作
     * @param deviceId
     * @param version
     * @param userId
     * @param amount
     */
    public String cashOut(String deviceId, String version, String userId, String amount) {
        DecimalFormat df = new DecimalFormat("0.00");
        BaseEmployeeSearchDto baseEmployeeSearchDto = new BaseEmployeeSearchDto();
        String result = "";
        if( StringUtils.isBlank(userId) ||
            StringUtils.isBlank(amount) ){
            result = "参数错误！";
            return result;
        }
        Integer user_id = Integer.valueOf(userId);
        baseEmployeeSearchDto.setId(user_id);
        List<BaseEmployeeDto> baseEmployeeList = baseEmployeeService.search(baseEmployeeSearchDto);
        if( baseEmployeeList == null ||
                baseEmployeeList.size() == 0){
            logger.info("没有查询到用户userID="+userId);
            result = "没有查询到用户";
            return result;
        }
        BaseEmployeeDto baseEmployeeDto = baseEmployeeList.get(0);
        if( StringUtils.isBlank(baseEmployeeDto.getBankNo()) ||
                StringUtils.isBlank(baseEmployeeDto.getBankName())){
            logger.info("银行卡号，或银行名称为空，提现失败");
            result = "银行卡号，或银行名称为空，提现失败";
            return result;
        }
        BigDecimal cashAmount = new BigDecimal(amount);

        AccountInfoDto accountInfoDto = new AccountInfoDto();
        accountInfoDto.setUserId(user_id);
        List<AccountInfoDto> accountInfoDtoList = accountInfoService.search(accountInfoDto);
        if( accountInfoDtoList == null || accountInfoDtoList.size() == 0){ //
            logger.info("没有查询到该账户信息 userId"+userId);
            result = "没有查询到该账户信息";
            return result;
        }

        accountInfoDto = accountInfoDtoList.get(0);
        BigDecimal accountAmount = new BigDecimal(accountInfoDto.getAccountAmount());

        if( accountAmount.compareTo(cashAmount) < 0){
            logger.info("账户余额小于提现金额，提现失败");
            result = "账户余额小于提现金额，提现失败";
            return result;
        }

        BigDecimal balanceAmount = accountAmount.subtract(cashAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
        balanceAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
        accountInfoDto.setAccountAmount(balanceAmount.doubleValue());
        accountInfoService.update(accountInfoDto); //更新账户余额

        CashOutInfo cashOutInfo = new CashOutInfo();
        cashOutInfo.setUserId(user_id);
        cashOutInfo.setDealStatus(0);
        cashOutInfo.setOpenBank(baseEmployeeDto.getBankName());
        cashOutInfo.setCashCard(baseEmployeeDto.getBankNo());
        cashOutInfo.setCashDate(new Date());
        cashOutInfo.setCashAmount(Double.valueOf(amount));
        cashOutInfo.setName(baseEmployeeDto.getUserName());
        cashOutInfo.setModifyUser(baseEmployeeDto.getUserName());
        cashOutInfo.setCreateUser(baseEmployeeDto.getUserName());

        cashOutInfoMapper.insert(cashOutInfo);
        result = "提现成功";
        return result;
    }

    public CashOutInfoDto balanceQuery(String deviceId, String version, String userId) {

        DecimalFormat df = new DecimalFormat("0.00");
        CashOutInfoDto cashOutInfoDto = new CashOutInfoDto();
        AccountInfoDto accountInfoDto = new AccountInfoDto();

        if(StringUtils.isBlank(userId) ){
            logger.info("用户编号为空");
            cashOutInfoDto.setMsg("用户编号不能为空");
            cashOutInfoDto.setCode(1);
            return cashOutInfoDto;
        }
        Integer user_id = Integer.valueOf(userId);

        //获取派送人员信息
        BaseEmployeeSearchDto baseEmployeeSearchDto = new BaseEmployeeSearchDto();
        baseEmployeeSearchDto.setId(user_id);
        baseEmployeeSearchDto.disablePaging();
        List<BaseEmployeeDto> baseEmployeeList = baseEmployeeService.search(baseEmployeeSearchDto);
        if( baseEmployeeList == null || baseEmployeeList.size() == 0){
            cashOutInfoDto.setCode(1);
            cashOutInfoDto.setMsg("没有查询出派送人员信息");
            logger.info("没有查询出派送人员信息");
            return cashOutInfoDto;
        }

        //获取账户信息
        accountInfoDto.setUserId(user_id);
        accountInfoDto.disablePaging();
        List<AccountInfoDto> accountInfoDtoList = accountInfoService.search(accountInfoDto);
        if( accountInfoDtoList == null || accountInfoDtoList.size() == 0){ //如果用户为新用户，那么账户还未创建

            //计算到账时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_YEAR, 9);
            Date date = new Date(calendar.getTimeInMillis());
            //判断提现日期是否为周末
            Integer weekNum = DateUtils.dayOfWeek(date);
            if( weekNum == 7){
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                date = new Date(calendar.getTimeInMillis());
            }else if( weekNum == 6){
                calendar.add(Calendar.DAY_OF_YEAR, 2);
                date = new Date(calendar.getTimeInMillis());
            }
            cashOutInfoDto.setToDate(DateUtils.format(date, "yyyy-MM-dd") + " " + DateUtils.dateOfWeek(date));

            BaseEmployeeDto baseEmployeeDto = baseEmployeeList.get(0);
            String bankName = "";
            if( StringUtils.isNotBlank(baseEmployeeDto.getBankName())){
                bankName = baseEmployeeDto.getBankName();
            }
            cashOutInfoDto.setBankName(bankName); //银行名称
            cashOutInfoDto.setUpAmount("0.00");
            String cardNo = "";
            if( StringUtils.isNotBlank(baseEmployeeDto.getBankNo())){
                cardNo = baseEmployeeDto.getBankNo().substring(baseEmployeeDto.getBankNo().length() - 4); // 银行卡号后四位
            }
            cashOutInfoDto.setTailNumber(cardNo);
            logger.info("没有查询到该用户的账户信息");
            return cashOutInfoDto;
        }

        accountInfoDto = accountInfoDtoList.get(0);
        cashOutInfoDto.setUpAmount(df.format(accountInfoDto.getAccountAmount())); //设置可提现金额

        BaseEmployeeDto baseEmployeeDto = baseEmployeeList.get(0);

        String bankName = "";
        if( StringUtils.isNotBlank(baseEmployeeDto.getBankName())){
            bankName = baseEmployeeDto.getBankName();
        }
        cashOutInfoDto.setBankName(bankName); //银行名称
        String cardNo = "";
        if( StringUtils.isNotBlank(baseEmployeeDto.getBankNo()) &&baseEmployeeDto.getBankNo().length() > 4 ){
            cardNo = baseEmployeeDto.getBankNo().substring(baseEmployeeDto.getBankNo().length() - 4); // 银行卡号后四位
        }
        cashOutInfoDto.setTailNumber(cardNo); // 银行卡号后四位

        //计算到账时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_YEAR, 9);
        Date date = new Date(calendar.getTimeInMillis());
        //判断提现日期是否为周末
        Integer weekNum = DateUtils.dayOfWeek(date);
        if( weekNum == 7){
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            date = new Date(calendar.getTimeInMillis());
        }else if( weekNum == 6){
            calendar.add(Calendar.DAY_OF_YEAR, 2);
            date = new Date(calendar.getTimeInMillis());
        }
        cashOutInfoDto.setToDate(DateUtils.format(date, "yyyy-MM-dd")+" "+DateUtils.dateOfWeek(date));
        cashOutInfoDto.setCode(0);
        return cashOutInfoDto;
    }
}
