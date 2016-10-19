package com.zhiyi.excel;


import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommunityAnalysis {
    public static void main(String[] args) throws Exception {
        //1、地址
        List<String> totalAddress = new ArrayList<String>();
        totalAddress.add("欧尚上海中原店####上海市中原路102号");
        totalAddress.add("欧尚上海闵行店####上海市闵行区东川路2092号");
        totalAddress.add("欧尚上海南汇店####上海市浦东新区惠南镇人民西路955号");
        totalAddress.add("欧尚上海张江店####上海市浦东新区金科路3057号");
        totalAddress.add("欧尚上海长阳店####上海市杨浦区长阳路1750号");
        totalAddress.add("欧尚上海金山店####上海市金山区蒙山北路399号");
        totalAddress.add("欧尚上海外高桥店####上海浦东新区启帆路618号");
        totalAddress.add("欧尚上海金都店####上海市闵行区金都路3759号");
        totalAddress.add("欧尚上海嘉定店####上海市嘉定区博乐路99号");

        //2、地址坐标
        String type = "小";
        String primaryPath = "/Users/zhoutaotao/Downloads/new result coordinates/无联华" + type + "学.txt";
        List<String> primaryList = Files.readAllLines(Paths.get(primaryPath), Charset.defaultCharset());

        Map<String,String> coordinatesMap = new HashMap<String,String>();
        for(String primaryLine :primaryList){
            if(primaryLine.contains("address")){
                String[] coordinatesArray = primaryLine.split("####");
                String key = coordinatesArray[0].substring(coordinatesArray[0].indexOf("address") + 8);

                coordinatesMap.put(coordinatesArray[1],key);
            }
        }
//        31.308604,121.424387####文坊崇文苑####闸北平型关路680弄####0########29000
//        31.308604,121.424387####新弘国际公寓####闸北老沪太路199弄####578########28223
//        31.308604,121.424387####临汾路1555弄####临汾路1555弄####0####null####0

        String communityPath = "/Users/zhoutaotao/Downloads/new result coordinates/小区数据.txt";
        List<String> communityList = Files.readAllLines(Paths.get(communityPath), Charset.defaultCharset());
        System.out.println(communityList.size());
        StringBuffer stringBuffer =new StringBuffer();
        for(String communityLine :communityList){
            String[] communityArray = communityLine.split("####");

            String coordinates = communityArray[0];
            String address = coordinatesMap.get(coordinates);
            for(String total :totalAddress){
                if(total.contains(address)){
                    stringBuffer.append(total).append("####").append(communityLine).append("\n");
                }
            }
        }

        Files.write(Paths.get("/Users/zhoutaotao/Downloads/new result coordinates/欧尚小区数据.txt"), stringBuffer.toString().getBytes());
        //4、地址中学
        //5、坐标小区
    }
}
