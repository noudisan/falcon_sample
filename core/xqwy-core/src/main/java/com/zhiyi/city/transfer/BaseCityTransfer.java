package com.zhiyi.city.transfer;

import com.zhiyi.city.dto.BaseCityDto;
import com.zhiyi.city.dto.CityLockType;
import com.zhiyi.city.model.BaseCity;
import org.apache.commons.lang.StringUtils;

/**
 * Created by zhoutaotao on 6/24/15.
 */
public class BaseCityTransfer {

    public static BaseCityDto transForToDto(BaseCity baseCity) {
        BaseCityDto baseCityDto = new BaseCityDto();
        baseCityDto.setId(baseCity.getId());
        baseCityDto.setCityName(baseCity.getCityName());
        baseCityDto.setProvince(baseCity.getProvince());
        baseCityDto.setModifyDt(baseCity.getModifyDt());
        baseCityDto.setCityAbbr(baseCity.getCityAbbr());
        baseCityDto.setCreateDt(baseCity.getCreateDt());
        baseCityDto.setCreateUser(baseCity.getCreateUser());

        if(StringUtils.isNotBlank(baseCity.getIsLocked())){
            baseCityDto.setIsLocked(CityLockType.valueOf(baseCity.getIsLocked()));
        }


        baseCityDto.setModifyUser(baseCity.getModifyUser());
        return baseCityDto;
    }


    public static BaseCity transForToModel(BaseCityDto baseCityDto) {
        BaseCity baseCity = new BaseCity();
        baseCity.setId(baseCityDto.getId());
        baseCity.setCityName(baseCityDto.getCityName());
        baseCity.setProvince(baseCityDto.getProvince());
        baseCity.setModifyDt(baseCityDto.getModifyDt());
        baseCity.setCityAbbr(baseCityDto.getCityAbbr());
        baseCity.setCreateDt(baseCityDto.getCreateDt());
        baseCity.setCreateUser(baseCityDto.getCreateUser());

        if(baseCityDto.getIsLocked() !=null){
            baseCity.setIsLocked(baseCityDto.getIsLocked().name());
        }

        baseCity.setModifyUser(baseCityDto.getModifyUser());
        return baseCity;
    }


}
