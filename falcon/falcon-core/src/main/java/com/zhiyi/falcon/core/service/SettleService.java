package com.zhiyi.falcon.core.service;

import com.zhiyi.falcon.api.dto.*;
import com.zhiyi.falcon.api.enumType.DeliveryResult;
import com.zhiyi.falcon.api.enumType.SettleStatus;
import com.zhiyi.falcon.api.enumType.SettleStyle;
import com.zhiyi.falcon.api.enumType.TaskSampling;
import com.zhiyi.falcon.core.dao.SettleMapper;
import com.zhiyi.falcon.core.model.Allowance;
import com.zhiyi.falcon.core.model.BaseEmployee;
import com.zhiyi.falcon.core.model.Settle;
import com.zhiyi.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 结算
 * Created by Adminstrator on 2015/6/24.
 */
@Transactional
@Service("settleService")
public class SettleService {

    private Logger logger = Logger.getLogger(SettleService.class);
    @Autowired
    private SettleMapper settleMapper;

    @Autowired
    private SettlePriceService settlePriceService;

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private DeliveryDataCommunityUnitService deliveryDataCommunityUnitService;

    @Autowired
    private BaseEmployeeService baseEmployeeService;

    @Autowired
    private DeliveryStepsService deliveryStepsService;

    @Autowired
    private SettleDetailService settleDetailService;

    @Autowired
    private AllowanceProxyService allowanceProxyService;




    public SettleDto getByid(Integer settleId) {
        Settle settle = settleMapper.selectByPrimaryKey(settleId);
        if(settle ==null){
            return null;
        }
        return Settle.modelToDto(settle);
    }

    /**
     *  保存
     * @param settleDto
     * @return
     */
    public Integer save(SettleDto settleDto) {
        Settle settle = Settle.dtoToModel(settleDto);
        settleMapper.insert(settle);
        return settle.getId();
    }

    /**
     *  保存结算和结算明细
     * @param settle
     * @return
     */
    public Integer settle(Settle settle) {

        return settleMapper.insert(settle);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public Integer delete(int id) {
        return settleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 更新
     * @param settleDto
     * @return
     */
    public Integer update(SettleDto settleDto) {
        Settle settle = Settle.dtoToModel(settleDto);
        return settleMapper.updateByPrimaryKeySelective(settle);
    }

    /**
     * 详情
     * @param id
     * @return
     */
    public SettleDto detail(int id) {
        Settle settle = settleMapper.selectByPrimaryKey(id);
        return Settle.modelToDto(settle);
    }

    /**
     * 根据条件查询数据
     * @param settleDto
     * @return
     */
    public List<SettleDto> search(SettleDto settleDto) {
        List<Settle> settleList = settleMapper.search(settleDto);
        return Settle.modelListToDtoList(settleList);
    }

    /**
     * 统计总数据条数
     * @param settleDto
     * @return
     */
    public Integer count(SettleDto settleDto) {
        return settleMapper.count(settleDto);
    }


    /**
     * 查询出用户的信息，定时任务入口
     */
    @Transactional
    public void settleAmountTask(){
        BaseEmployeeDto baseEmployeeQueryDto = new BaseEmployeeDto();
//        baseEmployeeQueryDto.setDeliveryDt(this.getDeliveryDtStr()); //查询前一天的派发人员信息
        baseEmployeeQueryDto.setSettleStatus(SettleStatus.UNBALANCED.name());
//        baseEmployeeQueryDto.setDeliveryResult(DeliveryResult.CANDELIVERY.name());
        baseEmployeeQueryDto.setTaskSampling(TaskSampling.SAMPLING.name());
        //查询需要结算人员
        List<BaseEmployee> baseEmployeeList = baseEmployeeService.queryActivityEmployee(baseEmployeeQueryDto);
        if(baseEmployeeList ==null || baseEmployeeList.isEmpty()){
            logger.info("没有找到需要结算的人员信息");
            return;
        }

        //缓存价格信息
        Map<String,List<SettlePriceDto>> settleMap =new HashMap<>();
        for(BaseEmployee baseEmployee : baseEmployeeList){
            //计算结算信息
            this.calculateEmployeeSettle(baseEmployee,settleMap);
        }
    }

    /**
     * 查询出每个用户派发的单元信息进行结算
     * @param baseEmployee
     * @param settleMap
     */
    private void calculateEmployeeSettle(BaseEmployee baseEmployee,Map<String,List<SettlePriceDto>> settleMap){
        DeliveryDataCommunityUnitSearchDto dataCommunityUnitSearchDto = this.getDeliveryDataCommunityUnitSearchDto(baseEmployee);
        //投递信息
        List<DeliveryDataCommunityUnitDto> unitDataList = deliveryDataCommunityUnitService.search(dataCommunityUnitSearchDto);
        if(unitDataList == null || unitDataList.isEmpty()){
            logger.info("没有找到需要结算的单元派送信息");
            return;
        }
        DeliveryDataCommunityUnitDto defaultInfo = unitDataList.get(0);
        String city = baseEmployee.getCity();
        //TODO insert  结算信息
        SettleDto settleDto =new SettleDto();
        settleDto.setName(baseEmployee.getUserName());
        settleDto.setTaskId(defaultInfo.getDeliveryTaskId());
        settleDto.setUserId(baseEmployee.getId());
        settleDto.setSettleDate(new Date());
        settleDto.setCreateDt(new Date());
        String deliveryDt = defaultInfo.getDeliveryDt();
        String deliveryCity = defaultInfo.getCity();
        settleDto.setDeliveryCity(deliveryCity);
        if( StringUtils.isNotBlank(deliveryDt)){
            settleDto.setStartTime(DateUtils.parse(deliveryDt, "yyyy-MM-dd HH:mm:ss"));
        }


        this.setSettleTaskInfoTime(settleDto, defaultInfo.getDeliveryTaskId(), baseEmployee.getId());
        Integer settleId =this.save(settleDto);

        BigDecimal totalAmount =BigDecimal.ZERO;
        Integer totalSendNum = 0;
        Set<Integer> communitySet =new HashSet<>();
        Set<Integer> communityBuildingSet =new HashSet<>();

        for(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto :unitDataList){
            //单元结算明细
            try {
                String deliveryType =  "";
                if( deliveryDataCommunityUnitDto.getDeliveryType() == null){
                    deliveryType = "";
                }else{
                    deliveryType = deliveryDataCommunityUnitDto.getDeliveryType().name();
                }

                SettlePriceDto settlePriceDto = getSettlePrice(city, deliveryType,settleMap);
                if(settlePriceDto == null){
                    logger.info("没有查找到对应的结算单价");
                    settlePriceDto = new SettlePriceDto(); //如果没有查询到对应的单价信息按照单价为0处理
                    settlePriceDto.setPrice(0.0);
                }
                //计算金额
                BigDecimal settlePrice = new BigDecimal(settlePriceDto.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP);
                Integer sendNum = deliveryDataCommunityUnitDto.getDeliveryNum() == null ? 0 : deliveryDataCommunityUnitDto.getDeliveryNum();
                BigDecimal settleAmount = settlePrice.multiply(new BigDecimal(sendNum)).setScale(2, BigDecimal.ROUND_HALF_UP);
                totalAmount = totalAmount.add(settleAmount);
                totalSendNum +=sendNum;
                communitySet.add(deliveryDataCommunityUnitDto.getCommunityId());
                communityBuildingSet.add(deliveryDataCommunityUnitDto.getBuildId());

                SettleDetailDto settleDetailDto  =this.getSettleDetailDto(deliveryDataCommunityUnitDto, settlePriceDto, settleAmount,settleId);
                settleDetailService.save(settleDetailDto);



                //修改结算状态为成功
                deliveryDataCommunityUnitDto.setSettleStatus(SettleStatus.SUCCESS);
                deliveryDataCommunityUnitService.updateDeliveryData(deliveryDataCommunityUnitDto);
            }catch (Exception e){
                //修改结算状态为失败
                logger.error("结算个人信息失败",e);
                deliveryDataCommunityUnitDto.setSettleStatus(SettleStatus.FAIL);
                deliveryDataCommunityUnitService.updateDeliveryData(deliveryDataCommunityUnitDto);
            }
        }
        //查询出餐补单价信息
        SettlePriceDto settlePriceFood = getSettlePrice(city, SettleStyle.FOOD.name(), settleMap);
        if(settlePriceFood !=null){
            AllowanceDto allowanceDto = new AllowanceDto();
            allowanceDto.setCreateUser("admin");
            allowanceDto.setCreateDt(new Date());
            allowanceDto.setAllowanceType(SettleStyle.FOOD.name());
            allowanceDto.setAllowanceAmount(settlePriceFood.getPrice());
            allowanceDto.setSettleId(settleId);
            allowanceDto.setTaskId(defaultInfo.getDeliveryTaskId());
            allowanceDto.setUserId(baseEmployee.getId());
            allowanceDto.setUserName(baseEmployee.getUserName());
            allowanceProxyService.save(allowanceDto);
        }


        settleDto.setId(settleId);
        settleDto.setSettleAmount(totalAmount.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        settleDto.setCommunityNum(communitySet.size());
        settleDto.setCommunityUnitNum(unitDataList.size());
        settleDto.setBuildingNum(communityBuildingSet.size());
        settleDto.setSendNum(totalSendNum);

        this.update(settleDto);

        //更新个人账户
        //FIXME 饭贴
        this.updateEmployeeAmount(baseEmployee, totalAmount);
    }

    /**
     * 更新用户的账户信息
     * @param baseEmployee
     * @param totalAmount
     */
    private void updateEmployeeAmount(BaseEmployee baseEmployee,BigDecimal totalAmount) {
        AccountInfoDto searchDto =new AccountInfoDto();
        searchDto.setUserId(baseEmployee.getId());
        List<AccountInfoDto> accountInfoDtoList = accountInfoService.search(searchDto);
        if(accountInfoDtoList ==null || accountInfoDtoList.isEmpty()){
            AccountInfoDto accountInfoDto =new AccountInfoDto();
            accountInfoDto.setUserId(baseEmployee.getId());
            accountInfoDto.setPhone(baseEmployee.getPhone());
            accountInfoDto.setUserName(baseEmployee.getUserName());
            accountInfoDto.setAccountAmount(totalAmount.doubleValue());
            accountInfoDto.setCreateDt(new Date());
            accountInfoService.saveOrUpdate(accountInfoDto);
        }else {
            AccountInfoDto accountInfoDto =accountInfoDtoList.get(0);
            accountInfoDto.setAccountAmount(totalAmount.doubleValue());
            accountInfoService.saveOrUpdate(accountInfoDto);
        }

    }

    private SettleDetailDto getSettleDetailDto(DeliveryDataCommunityUnitDto deliveryDataCommunityUnitDto,
                                               SettlePriceDto settlePriceDto, BigDecimal settleAmount, Integer settleId) {
        SettleDetailDto settleDetailDto =new  SettleDetailDto();
        settleDetailDto.setSettleId(settleId);
//        settleDetailDto.setSectionId(String.valueOf(settlePriceDto.getId()));
        if( deliveryDataCommunityUnitDto.getDeliveryType() == null){
            settleDetailDto.setSendStyle("");
        }else{
            settleDetailDto.setSendStyle(deliveryDataCommunityUnitDto.getDeliveryType().name());
        }
        Integer bookNum = deliveryDataCommunityUnitDto.getDeliveryNum() == null ? 0 : deliveryDataCommunityUnitDto.getDeliveryNum();
        settleDetailDto.setBookNum(bookNum);
        settleDetailDto.setPrice(settlePriceDto.getPrice());
        settleDetailDto.setSettleAmount(settleAmount.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        settleDetailDto.setCommunityName(deliveryDataCommunityUnitDto.getCommunityName());
        //settleDetailDto.setBuildingNum(deliveryDataCommunityUnitDto.get); //TODO
        settleDetailDto.setUserId(deliveryDataCommunityUnitDto.getDeliveryEmployeeId());
        settleDetailDto.setTaskId(deliveryDataCommunityUnitDto.getDeliveryTaskId());
        settleDetailDto.setBuildingId(deliveryDataCommunityUnitDto.getBuildId());
        settleDetailDto.setBuildName(deliveryDataCommunityUnitDto.getBuildingName());
        settleDetailDto.setCommunityId(deliveryDataCommunityUnitDto.getCommunityId());
        settleDetailDto.setDeliveryResult(deliveryDataCommunityUnitDto.getDeliveryResult().name());
        settleDetailDto.setSettleDt(new Date());
        settleDetailDto.setCommunityUnitId(deliveryDataCommunityUnitDto.getCommunityUnitId());

        return settleDetailDto;
    }


    private DeliveryDataCommunityUnitSearchDto getDeliveryDataCommunityUnitSearchDto(BaseEmployee baseEmployee) {
        DeliveryDataCommunityUnitSearchDto dataCommunityUnitSearchDto =new DeliveryDataCommunityUnitSearchDto();
        dataCommunityUnitSearchDto.setSettleStatus(SettleStatus.UNBALANCED.name());  //查询出未结算的
        dataCommunityUnitSearchDto.setTaskSampling(TaskSampling.SAMPLING.name());
        dataCommunityUnitSearchDto.setDeliveryEmployeeId(baseEmployee.getId());
        dataCommunityUnitSearchDto.disablePaging();
        return dataCommunityUnitSearchDto;
    }

    /**
     * 获取单价信息，如果单价不存在与map中则到数据库查询
     * @param city
     * @param deliveryType
     * @param settleMap
     * @return
     */
    private SettlePriceDto getSettlePrice(String city,String deliveryType,Map<String,List<SettlePriceDto>> settleMap){
        //查询单价信息
        List<SettlePriceDto> settlePriceDtoList = settleMap.get(city);
        if(settlePriceDtoList ==null || settlePriceDtoList.isEmpty()){
            SettlePriceDto searchDto = new SettlePriceDto();
            searchDto.setCity(city);
            searchDto.disablePaging();
            logger.info("查询单价信息");
            settlePriceDtoList = settlePriceService.search(searchDto);
            settleMap.put(city,settlePriceDtoList);
        }

        for(SettlePriceDto settlePriceDto :settlePriceDtoList){
            if(deliveryType.equalsIgnoreCase(settlePriceDto.getSendStyle())){
                return settlePriceDto;
            }
        }
        return null;
    }

    private void setSettleTaskInfoTime(SettleDto settleDto, Integer deliveryTaskId, Integer userId) {
        DeliveryStepsSearchDto searchDto =new DeliveryStepsSearchDto();
        searchDto.setTaskId(deliveryTaskId);
        searchDto.setUserId(userId);
        List<DeliveryStepsDto> result = deliveryStepsService.search(searchDto);
        if(result ==null || result.isEmpty()){
            return;
        }
        DeliveryStepsDto stepDto = result.get(0);

        settleDto.setStartDate(stepDto.getStartTime());
        settleDto.setEndDate(stepDto.getEndTime());

        if(stepDto.getStartTime()!=null && stepDto.getEndTime()!=null){
            //计算总共消耗时间
            Long totalTime = (stepDto.getEndTime().getTime() - stepDto.getStartTime().getTime());

            settleDto.setTotalTime(totalTime);
        }else{
            settleDto.setTotalTime(0L);
        }

    }

    /**
     * 获取前一天的日期
     * @return
     */
    public String getDeliveryDtStr(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, -1);
        Date deliverDt = calendar.getTime();
        return DateUtils.format(deliverDt, "yyyy-MM-dd");
    }




    /**
     * 添加补贴信息
     * @param settleIds
     * @return
     */
    public Map<String, String> addAllowance(String settleIds) {
        Map<String, String> map = new HashMap<>();
        if(StringUtils.isBlank(settleIds)
                ){
            map.put("msg", "参数错误");
            return map;
        }

        String[] settleId_arr = settleIds.split(",");
        for( String settleId : settleId_arr){
            Settle settle = settleMapper.selectByPrimaryKey(Integer.valueOf(settleId));
            BaseEmployeeDto baseEmployeeDto = baseEmployeeService.queryOneEmployee(settle.getUserId());
            SettlePriceDto settlePriceSearchDto = new SettlePriceDto();
            settlePriceSearchDto.setSendStyle(SettleStyle.BAD_WEATHER.name());
            settlePriceSearchDto.setCity(baseEmployeeDto.getCity());
            List<SettlePriceDto> settlePriceDtoList = settlePriceService.search(settlePriceSearchDto);
            if( settlePriceDtoList == null || settlePriceDtoList.size() == 0){
                continue;
            }
            SettlePriceDto settlePriceDto = settlePriceDtoList.get(0);
            AllowanceDto allowanceDto = new AllowanceDto();
            allowanceDto.setUserId(settle.getUserId());
            allowanceDto.setTaskId(settle.getTaskId());
            allowanceDto.setSettleId(settle.getId());
            allowanceDto.disablePaging();
            List<AllowanceDto> allowanceDtoList = allowanceProxyService.search(allowanceDto);
            if( allowanceDtoList != null && allowanceDtoList.size() > 0){ //如果查找到已经补贴过则跳过
                continue;
            }

            this.sendAllowance(settle, settlePriceDto.getPrice().toString(), settlePriceDto.getSendStyle());
            this.updateAccount(settle, settlePriceDto.getPrice().toString());
        }
        map.put("msg", "补贴已发放");
        return map;
    }


    /**
     * 保存补贴信息
     * @param settle
     * @param allowanceAmount
     * @param sendStyle
     */
    private void sendAllowance( Settle settle, String allowanceAmount, String sendStyle){
        AllowanceDto allowanceDto = new AllowanceDto();
        allowanceDto.setUserId(settle.getUserId());
        allowanceDto.setTaskId(settle.getTaskId());
        allowanceDto.setSettleId(settle.getId());
        allowanceDto.setUserName(settle.getName());
        allowanceDto.setAllowanceAmount(Double.valueOf(allowanceAmount));
        allowanceDto.setAllowanceType(sendStyle);

        allowanceProxyService.save(allowanceDto);
    }

    /**
     * 更新账户金额
     * @param settle
     * @param allowanceAmount
     */
    private void updateAccount(Settle settle, String allowanceAmount){
        AccountInfoDto accountInfoDto = new AccountInfoDto();
        accountInfoDto.setUserId(settle.getUserId());
        List<AccountInfoDto> accountInfoDtoList = accountInfoService.search(accountInfoDto);
        if( accountInfoDtoList != null && accountInfoDtoList.size() > 0){
            accountInfoDto = accountInfoDtoList.get(0);
            accountInfoDto.setAccountAmount( new BigDecimal(accountInfoDto.getAccountAmount()).add(new BigDecimal(allowanceAmount)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
            accountInfoService.update(accountInfoDto);
        }
    }
}
