package com.zhiyi.utils;

import java.util.UUID;


public class Utils {
    public static  String generateCommunityCode() {
        UUID uuid = UUID.randomUUID();
        String code = uuid.toString();
       return  MD5Utils.encode(code);

    }

    public static  String generateCode() {
        UUID uuid = UUID.randomUUID();
        String code = uuid.toString();
        code = code.replaceAll("-", "");
        code = MD5Utils.encode(code);
        code = code.substring(8, 24);
        return code;
    }

    public static  String generatePinYin(String name) {
        return ChineseToPinyinUtils.getPinYinHeadChar(name) + "," + ChineseToPinyinUtils.getPingYin(name);
    }

}
