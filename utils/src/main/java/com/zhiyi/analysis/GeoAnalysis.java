package com.zhiyi.analysis;

import com.zhiyi.utils.GeoMapMetryUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class GeoAnalysis {
    public static void main(String[] args) throws IOException {

        final double[][] doubles = new double[23][2];
        doubles[0][1] = 31.220526;
        doubles[0][0] = 121.541079;
        doubles[1][1] = 31.216851;
        doubles[1][0] = 121.554338;
        doubles[2][1] = 31.216943;
        doubles[2][0] = 121.556818;
        doubles[3][1] = 31.218951;
        doubles[3][0] = 121.563429;
        doubles[4][1] = 31.219754;
        doubles[4][0] = 121.56695;
        doubles[5][1] = 31.219939;
        doubles[5][0] = 121.568136;
        doubles[6][1] = 31.220402;
        doubles[6][0] = 121.571909;
        doubles[7][1] = 31.218117;
        doubles[7][0] = 121.572053;
        doubles[8][1] = 31.21753;
        doubles[8][0] = 121.571119;
        doubles[9][1] = 31.21716;
        doubles[9][0] = 121.568819;
        doubles[10][1] = 31.21055;
        doubles[10][0] = 121.570939;
        doubles[11][1] = 31.210303;
        doubles[11][0] = 121.568963;
        doubles[12][1] = 31.210303;
        doubles[12][0] = 121.566771;
        doubles[13][1] = 31.207555;
        doubles[13][0] = 121.567058;
        doubles[14][1] = 31.207524;
        doubles[14][0] = 121.562998;
        doubles[15][1] = 31.210087;
        doubles[15][0] = 121.562423;
        doubles[16][1] = 31.20845;
        doubles[16][0] = 121.557967;
        doubles[17][1] = 31.206041;
        doubles[17][0] = 121.553548;
        doubles[18][1] = 31.203755;
        doubles[18][0] = 121.548014;
        doubles[19][1] = 31.203447;
        doubles[19][0] = 121.544601;
        doubles[20][1] = 31.203663;
        doubles[20][0] = 121.541115;
        doubles[21][1] = 31.205115;
        doubles[21][0] = 121.534971;
        doubles[22][1] = 31.205948;
        doubles[22][0] = 121.535294;

		/*for(int i=14; i<= 23 ; i++){
			final  int index = i;
			new Thread(){
				@Override
				public void run() {
					try {
						// GeoMapMetryUtils.method1(doubles,index);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		}*/


		/*for(int i=14; i<= 23 ; i++){
			final  int index = i;
			new Thread(){
				@Override
				public void run() {
					try {
						 GeoMapMetryUtils.methodNolaglat(index);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}.start();
		}*/


        method1(doubles);

        methodNolaglat();

        method_csv(doubles);
    }

    private static void method1(double[][] doubles) throws IOException {
        String inFile="/Users/zhoutaotao/Downloads/out_1129.txt";
        String outFile="/Users/zhoutaotao/Downloads/deviceId_1129.txt";

        StringBuffer stringBuffer =new StringBuffer();
        List<String> lines = Files.readAllLines(Paths.get(inFile), Charset.defaultCharset());
        for (String line : lines) {
            String[] array = line.split(",");
            double log = Double.parseDouble(array[1]);
            double lat = Double.parseDouble(array[2]);
            if(GeoMapMetryUtils.isContainsPoints(lat, log, doubles)){
                stringBuffer.append("'").append(array[0]).append("'").append(",");
            }

        }

        Files.write(Paths.get(outFile), stringBuffer.toString().getBytes());
    }

    private static void methodNolaglat() throws IOException {
        String inFile="/Users/zhoutaotao/Downloads/out_1129.txt";
        String outFile="/Users/zhoutaotao/Downloads/coordinates_1129.txt";

        StringBuffer stringBuffer =new StringBuffer();
        List<String> lines = Files.readAllLines(Paths.get(inFile), Charset.defaultCharset());
        for (String line : lines) {
            String[] array = line.split(",");
            double log = Double.parseDouble(array[1]);
            double lat = Double.parseDouble(array[2]);
            if(log == 0d || lat == 0d){
                stringBuffer.append("'").append(array[0]).append("'").append(",");
            }

        }

        Files.write(Paths.get(outFile), stringBuffer.toString().getBytes());
    }


    private static void method_csv(double[][] doubles) throws IOException {
        String inFile="/Users/zhoutaotao/Downloads/out_1129.txt";
        String outFile="/Users/zhoutaotao/Downloads/csv_1129.csv";

        StringBuffer stringBuffer =new StringBuffer();
        List<String> lines = Files.readAllLines(Paths.get(inFile), Charset.defaultCharset());

        for (String line : lines) {
            String[] array = line.split(",");
            double log = Double.parseDouble(array[1]);
            double lat = Double.parseDouble(array[2]);
            if(GeoMapMetryUtils.isContainsPoints(lat, log, doubles)){
                stringBuffer.append(line).append(",\n");
            }

        }

        Files.write(Paths.get(outFile), stringBuffer.toString().getBytes());
    }

}
