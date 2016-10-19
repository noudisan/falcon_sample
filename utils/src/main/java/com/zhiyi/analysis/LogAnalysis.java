package com.zhiyi.analysis;

import com.zhiyi.utils.GeoMapMetryUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LogAnalysis {

    public static void main(String[] args) throws IOException {

        if (args == null || args.length == 0) {
            System.out.println("请输入日志文件全路径");
            return;
        }
        String inFile = args[0];
        System.out.println("Fine name:" + inFile);
        //String inFile ="/Users/zhoutaotao/Downloads/20151129.search.log";
        File file = new File(inFile);
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        String parent = file.getParent();
        String fileName = file.getName();
        String name = fileName.substring(0, fileName.indexOf("."));
        String totalDeviceFile = parent + File.separator + "total" + name + ".log";

        LogAnalysis.filterCommunitySearch(inFile, totalDeviceFile);
        LogAnalysis.filterDeviceId(totalDeviceFile, parent + File.separator + "deviceId_" + name + ".log");
        LogAnalysis.filterCoordinates(totalDeviceFile, parent + File.separator + "coordinates_" + name + ".log");
        LogAnalysis.filterCsv(totalDeviceFile, parent + File.separator + "csv" + name + ".csv");
    }


    public static String filterCommunitySearch(String inFile, String outFile) throws IOException {

        StringBuffer stringBuffer = new StringBuffer();
        List<String> lines = Files.readAllLines(Paths.get(inFile), Charset.defaultCharset());
        List<String> deviceIdList = new ArrayList<>();
        for (String line : lines) {
            if (!line.contains("community/search") || line.contains("community/search/submitCommunity")) {
                continue;
            }
            String[] array = line.split(" ");
            boolean contain = false;
            String temp = "";
            String deviceId = "";
            String requestDate = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
            String type = "";
            if (line.toLowerCase().contains("ios")) {
                type = "IOS";
            } else {
                type = "ANDROID";
            }

            for (String total : array) {
                if (total.contains("latitude")) {
                    contain = true;
                    String[] blockArray = total.split("&");
                    boolean notContain = false;
                    String latitude = "";
                    String longitude = "";

                    for (String block : blockArray) {
                        if (block.contains("deviceId")) {
                            String[] arr = block.split("=");
                            deviceId = arr[1];
                        }
                        if (block.contains("longitude")) {
                            String[] arr = block.split("=");
                            longitude = arr[1];
                        }
                        if (block.contains("latitude")) {
                            String[] arr = block.split("=");
                            latitude = arr[1];
                        }
                    }
                    temp += deviceId + "," + longitude + "," + latitude + "," + requestDate + "," + type;
                }
            }

            if (contain) {
                if (!deviceIdList.contains(deviceId)) {
                    deviceIdList.add(deviceId);
                    stringBuffer.append(temp).append("\n");
                }
            }

        }

        Files.write(Paths.get(outFile), stringBuffer.toString().getBytes());

        return outFile;
    }

    private static void filterDeviceId(String inputFile, String outFile) throws IOException {
        StringBuffer stringBuffer = new StringBuffer();
        List<String> lines = Files.readAllLines(Paths.get(inputFile), Charset.defaultCharset());
        for (String line : lines) {
            try {
                String[] array = line.split(",");
                if (array == null || array.length < 2) {
                    continue;
                }
                double log = Double.parseDouble(array[1]);
                double lat = Double.parseDouble(array[2]);
                if (GeoMapMetryUtils.isContainsPoints(lat, log, HuamuArea.huamuArea3())) {
                    stringBuffer.append("'").append(array[0]).append("'").append(",");
                }
            } catch (Exception e) {
                System.out.println("error Line :" + line);
            }

        }

        Files.write(Paths.get(outFile), stringBuffer.toString().getBytes());
    }

    private static void filterCoordinates(String inputFile, String outFile) throws IOException {
//        String outFile=file.getParent()+File.separator+File.separator+"coordinates_"+fileName;

        StringBuffer stringBuffer = new StringBuffer();
        List<String> lines = Files.readAllLines(Paths.get(inputFile), Charset.defaultCharset());
        for (String line : lines) {
            try {
                String[] array = line.split(",");
                double log = Double.parseDouble(array[1]);
                double lat = Double.parseDouble(array[2]);
                if (log == 0d || lat == 0d) {
                    stringBuffer.append("'").append(array[0]).append("'").append(",");
                }
            } catch (Exception e) {
                System.out.println("filterCoordinates error Line :" + line);
            }
        }

        Files.write(Paths.get(outFile), stringBuffer.toString().getBytes());
    }


    private static void filterCsv(String inputFile, String outFile) throws IOException {
        //String outFile=file.getParent()+File.separator+File.separator+"csv_"+fileName+".csv";

        StringBuffer stringBuffer = new StringBuffer();
        List<String> lines = Files.readAllLines(Paths.get(inputFile), Charset.defaultCharset());

        for (String line : lines) {
            try {
                String[] array = line.split(",");
                double log = Double.parseDouble(array[1]);
                double lat = Double.parseDouble(array[2]);
                if (GeoMapMetryUtils.isContainsPoints(lat, log, HuamuArea.huamuArea3())) {
                    stringBuffer.append(line).append(",\n");
                }
            } catch (Exception e) {
                System.out.println("filterCsv error Line :" + line);
            }
        }

        Files.write(Paths.get(outFile), stringBuffer.toString().getBytes());
    }


}
