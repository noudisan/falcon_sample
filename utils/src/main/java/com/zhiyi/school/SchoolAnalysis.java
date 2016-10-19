package com.zhiyi.school;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoutaotao on 12/24/15.
 */
public class SchoolAnalysis {

    public static void main(String[] args) throws Exception {
        List<String> totalList = new ArrayList<>();
        totalList.add("欧尚上海中原店####上海市中原路102号");
        totalList.add("欧尚上海闵行店####上海市闵行区东川路2092号");
        totalList.add("欧尚上海南汇店####上海市浦东新区惠南镇人民西路955号");
        totalList.add("欧尚上海张江店####上海市浦东新区金科路3057号");
        totalList.add("欧尚上海长阳店####上海市杨浦区长阳路1750号");
        totalList.add("欧尚上海金山店####上海市金山区蒙山北路399号");
        totalList.add("欧尚上海外高桥店####上海浦东新区启帆路618号");
        totalList.add("欧尚上海金都店####上海市闵行区金都路3759号");
        totalList.add("欧尚上海嘉定店####上海市嘉定区博乐路99号");

        school(totalList,"欧尚.txt");
    }

    private static void school(List<String> totalList,String fileName) throws IOException {
        StringBuffer stringBuffer =new StringBuffer();
        //String fileName ="乐购.txt";
        String schoolUrl = "/Users/zhoutaotao/Downloads/结果/"+fileName;
        List<String> lines = Files.readAllLines(Paths.get(schoolUrl), Charset.defaultCharset());
        int index = 0;
        String total = totalList.get(index);
        for (String line : lines) {
            if(StringUtils.isBlank(line)){
                continue;
            }
            if(line.contains("result.keyword:")){
                total = totalList.get(index);
                index ++;
            }else {
                String[] address = line.split(" ");
                if(address.length == 1 && address[0].contains("####")){
                    stringBuffer.append(total+"####"+address[0]).append("\n");
                }else {
                    stringBuffer.append(total+"####"+address[1]).append("\n");
                }
            }
        }

        Files.write(Paths.get("/Users/zhoutaotao/Downloads/result/"+fileName), stringBuffer.toString().getBytes());

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
    }
}
