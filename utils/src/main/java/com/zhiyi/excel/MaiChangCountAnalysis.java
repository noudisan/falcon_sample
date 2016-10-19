package com.zhiyi.excel;


import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaiChangCountAnalysis {
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


        String name = "欧尚";

        String middlePath = "/Users/zhoutaotao/Downloads/result_final/" + name + "中学数据.txt";
        List<String> middlePathList = Files.readAllLines(Paths.get(middlePath), Charset.defaultCharset());

        String smallPath = "/Users/zhoutaotao/Downloads/result_final/" + name + "小学数据.txt";
        List<String> smallPathList = Files.readAllLines(Paths.get(smallPath), Charset.defaultCharset());

        String communityPath = "/Users/zhoutaotao/Downloads/result_final/" + name + "小区数据.txt";
        List<String> communityPathList = Files.readAllLines(Paths.get(communityPath), Charset.defaultCharset());

        StringBuffer stringBuffer =new StringBuffer();
        for(String totalLine :totalAddress ){
            int middle_count = 0;
            int small_count = 0;
            int community_count = 0;
            for(String middle :middlePathList ){
                if(middle.contains(totalLine)){
                    middle_count++;
                }
            }
            for(String small :smallPathList ){
                if(small.contains(totalLine)){
                    small_count++;
                }
            }
            for(String community :communityPathList ){
                if(community.contains(totalLine)){
                    community_count++;
                }
            }
            stringBuffer.append(totalLine).append("####").append(small_count)
                    .append("####").append(middle_count)
                    .append("####").append(community_count).append("\n");
        }
        System.out.println("》》》》》《《《《《");
        Files.write(Paths.get("/Users/zhoutaotao/Downloads/result_count/"+name+"统计数据.txt"), stringBuffer.toString().getBytes());
        System.out.println("完成");
       /* final String name = "欧尚";
        //2、地址坐标
        new Thread(){
            @Override
            public void run() {
                try {
                    MaiChangCountAnalysis.createSchoolfile(totalAddress, "小", name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                try {

                    MaiChangCountAnalysis.createSchoolfile(totalAddress, "中", name);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
        //4、地址中学
        //5、坐标小区*/
    }

    public static void createSchoolfile(List<String> totalAddress, String type, String name) throws IOException {
        String primaryPath = "/Users/zhoutaotao/Downloads/new result coordinates/无联华"+type+"学.txt";
        List<String> primaryList = Files.readAllLines(Paths.get(primaryPath), Charset.defaultCharset());

        Map<String,String> coordinatesMap = new HashMap<String,String>();
        for(String primaryLine :primaryList){
            if(primaryLine.contains("address")){
                String[] coordinatesArray = primaryLine.split("####");
                String key = coordinatesArray[0].substring(coordinatesArray[0].indexOf("address") + 8);

                coordinatesMap.put(key, coordinatesArray[1]);
            }
        }
        //3、地址小学
        StringBuffer stringBuffer = new StringBuffer();
        //卖场  地址  小学名称  小学地址
        for(String totalLine : totalAddress){
            String[] totalArray  = totalLine.split("####");
            String address = totalArray[1];
            String latLng =coordinatesMap.get(address);

            for(String primaryLine : primaryList){
                if(StringUtils.isBlank(primaryLine)){
                    continue;
                }
                if(primaryLine.contains(latLng) && !primaryLine.contains("address")){
                    String st = primaryLine.substring(primaryLine.indexOf("####") + 4).replace("名称：","").replace("地址：","");
                    //stringBuffer.append(line).append("####").append(st).append("\n");

                    stringBuffer.append(totalLine).append("####").append(st).append("\n");
                }
            }
        }
        Files.write(Paths.get("/Users/zhoutaotao/Downloads/new result coordinates/"+name+type+"学数据.txt"), stringBuffer.toString().getBytes());
    }
}
