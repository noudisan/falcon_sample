package com.zhiyi.falcon.gateway.result;

import com.zhiyi.falcon.api.dto.SettleDto;
import com.zhiyi.utils.DateUtils;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class SettleResult implements Serializable {

    private Integer totalPage;

    private Integer currentPage;

    private List<Settle> datas;

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<Settle> getDatas() {
        return datas;
    }

    public void setDatas(List<Settle> datas) {
        this.datas = datas;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 将查询出来的SettleDto转化为Settle数据返回给APP
     * @param settleDtoList
     * @return
     */
    public static List<Settle> settleDtoToSettle(List<SettleDto> settleDtoList){
        List<Settle> settleList = new ArrayList<Settle>();
        DecimalFormat df = new DecimalFormat("0.00");
        for(SettleDto settleDto : settleDtoList){
            Settle settle = new Settle();
            settle.setSettleId(settleDto.getId());
            settle.setTaskId(settleDto.getTaskId());
            settle.setDate(DateUtils.format(settleDto.getCreateDt(), "yyyy-MM-dd")+" "+DateUtils.dateOfWeek(settleDto.getCreateDt()));
            settle.setBalance(df.format(settleDto.getSettleAmount()));
            settleList.add(settle);
        }
        return settleList;
    }
}
