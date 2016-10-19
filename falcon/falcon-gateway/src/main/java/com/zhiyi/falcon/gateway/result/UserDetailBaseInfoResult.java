package com.zhiyi.falcon.gateway.result;

/**
 * app个人详情：显示用户基本资料
 */
public class UserDetailBaseInfoResult {
    private String imageUrl;//头像

    private String name;//用户姓名

    private String gender;//性别

    private String profession;//职业

    private String phone;//手机号

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
