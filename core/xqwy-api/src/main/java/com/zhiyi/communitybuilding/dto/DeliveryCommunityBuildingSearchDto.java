package com.zhiyi.communitybuilding.dto;


import com.zhiyi.common.dto.PageSearchDto;

import java.io.Serializable;
import java.util.Date;

public class DeliveryCommunityBuildingSearchDto extends PageSearchDto implements Serializable{
    private static final long serialVersionUID = 4932723737751445827L;

    private Integer id;

    private String name;

    private String communityName;

    private Integer communityId;

    private String modifyUser;

    private String createUser;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser;
    }



    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }
}
