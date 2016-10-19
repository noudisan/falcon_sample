package com.zhiyi.analysis;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class DeviceAnalysis {
    public static String id = "19";
    public static String submitCommunity ="/Users/zhoutaotao/Downloads/"+id+"SubmitCommunity";
    public static String devicenocommunityCode ="/Users/zhoutaotao/Downloads/"+id+"DevicenocommunityCode";
    public static String sqlCodeFile ="/Users/zhoutaotao/Downloads/"+id+"DeviceSQL.sql";

    public static void main(String[] args) throws Exception {
        List<String> deviceIdList = getDeviceId();
        System.out.println("deviceIdList.size::"+deviceIdList.size());

        Map<String,String> deviceIdMap = getCommunityCode(deviceIdList);
        Set<String> set = deviceIdMap.keySet();
        System.out.println("存在小区的deviceId："+set.size());

        StringBuffer stringBuffer = new StringBuffer();
        for(String key :set){
            System.out.println("key:"+key+" value:"+deviceIdMap.get(key));
            stringBuffer.append("update tb_basic_device_info set COMMUNITY_CODE ='")
                    .append(deviceIdMap.get(key))
                    .append("' where DEVICE_ID = '")
                    .append(key)
                    .append("' and COMMUNITY_CODE is null;").append("\n");
        }

        Files.write(Paths.get(sqlCodeFile), stringBuffer.toString().getBytes());

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static Map<String,String> getCommunityCode(List<String> deviceIdList) throws Exception{
        Map<String,String> map = new HashMap<>();

        List<String> lines = Files.readAllLines(Paths.get(submitCommunity), Charset.defaultCharset());
        for (String line : lines) {
            for(String deviceId :deviceIdList){
                if(line.contains(deviceId) && line.contains("communityCode=")){
                    int pox = line.indexOf("communityCode=");
                    String communityCode = line.substring(pox + 14, pox + 30);
                    map.put(deviceId,communityCode);
                }
            }
        }
        return map;
    }

    public static List<String> getDeviceId() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(devicenocommunityCode), Charset.defaultCharset());
        List<String> deviceIdList = new ArrayList<>();
        for (String line : lines) {
            deviceIdList.add(line);
        }
        return deviceIdList;
    }


}
