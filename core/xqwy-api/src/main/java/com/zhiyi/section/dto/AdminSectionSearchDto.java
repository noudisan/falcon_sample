package com.zhiyi.section.dto;

import com.zhiyi.common.dto.PageSearchDto;

import java.util.Date;

/**
 * 查询dto
 */
public class AdminSectionSearchDto extends PageSearchDto {

    private String city;
    private String area;
    private String name;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
