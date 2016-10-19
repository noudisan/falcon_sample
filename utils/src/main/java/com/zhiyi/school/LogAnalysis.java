package com.zhiyi.school;


import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogAnalysis {
    public static void main(String[] args) throws Exception {
        //1 schoolAnalysis();

        //2  coordinatesAnalysis();

        //select * from TB_BASE_COMMUNITY where (ABS((121.351737-longitude))*102834.74258026089786013677476285) < 5000 and (ABS((31.266139-latitude))*111712.69150641055729984301412873) < 5000;
    }


    private static void coordinatesAnalysis() throws IOException {
        String maiChangPath = "/Users/zhoutaotao/Downloads/new result coordinates/无联华分店.txt";
        List<String> maiChangList = Files.readAllLines(Paths.get(maiChangPath), Charset.defaultCharset());

        String primaryPath = "/Users/zhoutaotao/Downloads/new result coordinates/无联华中学.txt";
        List<String> primaryList = Files.readAllLines(Paths.get(primaryPath), Charset.defaultCharset());

        Map<String,String> coordinatesMap = new HashMap<String,String>();
        for(String primaryLine :primaryList){
            if(primaryLine.contains("address")){
                String[] coordinatesArray = primaryLine.split("####");
                String key = coordinatesArray[0].substring(coordinatesArray[0].indexOf("address") + 8);

                coordinatesMap.put(key, coordinatesArray[1]);
            }
        }

        StringBuffer stringBuffer =new StringBuffer();
        for(String line :maiChangList){
            if(StringUtils.isBlank(line)){
                continue;
            }
            String[] maiChangArray = line.split("####");

            String coordinates = coordinatesMap.get(maiChangArray[1]);
            stringBuffer.append(line).append("####").append(coordinates).append("\n");

        }
        //System.out.println(stringBuffer.toString());
        Files.write(Paths.get("/Users/zhoutaotao/Downloads/new result coordinates/无联华坐标数据.txt"), stringBuffer.toString().getBytes());
    }


    // 中小学分析
    private static void schoolAnalysis() throws IOException {
        String maiChangPath = "/Users/zhoutaotao/Downloads/new result coordinates/无联华分店.txt";
        List<String> maiChangList = Files.readAllLines(Paths.get(maiChangPath), Charset.defaultCharset());

        String type = "中";
        String primaryPath = "/Users/zhoutaotao/Downloads/new result coordinates/无联华" + type + "学.txt";
        List<String> primaryList = Files.readAllLines(Paths.get(primaryPath), Charset.defaultCharset());

        Map<String,String> coordinatesMap = new HashMap<String,String>();
        for(String primaryLine :primaryList){
            if(primaryLine.contains("address")){
                String[] coordinatesArray = primaryLine.split("####");
                String key = coordinatesArray[0].substring(coordinatesArray[0].indexOf("address") + 8);

                coordinatesMap.put(key, coordinatesArray[1]);
            }
        }

        StringBuffer stringBuffer =new StringBuffer();
        for(String line :maiChangList){
            if(StringUtils.isBlank(line)){
                continue;
            }
            String[] maiChangArray = line.split("####");

            String coordinates = coordinatesMap.get(maiChangArray[1]);
            for(String primaryLine : primaryList){
                if(StringUtils.isBlank(primaryLine)){
                    continue;
                }
                if(primaryLine.contains(coordinates) && !primaryLine.contains("address")){
                    String st = primaryLine.substring(primaryLine.indexOf("####") + 4);
                    stringBuffer.append(line).append("####").append(st).append("\n");
                }
            }
        }
        //System.out.println(stringBuffer.toString());
        Files.write(Paths.get("/Users/zhoutaotao/Downloads/new result coordinates/无联华" + type + "学数据.txt"), stringBuffer.toString().getBytes());
    }
}
