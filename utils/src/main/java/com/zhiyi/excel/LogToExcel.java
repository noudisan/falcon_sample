package com.zhiyi.excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LogToExcel {
    public static void main(String[] args) throws IOException {
        //File fileDir =new File("/Users/zhoutaotao/Downloads/result_final");
        //File fileDir =new File("/Users/zhoutaotao/Downloads/result_count");
        File fileDir =new File("/Users/zhoutaotao/Downloads/result_middle");
        File[] fileArray = fileDir.listFiles();
        for(File logFile :fileArray ){
            if(logFile.getName().contains("Store")){
                continue;
            }
            String path = logFile.getPath();
            System.out.println("文件路径："+path);
            List<String> logList = Files.readAllLines(Paths.get(path), Charset.defaultCharset());
            String excelFilePath = path + ".xlsx";

            LogToExcel.writeExcel(logList, excelFilePath, logFile.getName());
        }
    }


    public static  void writeExcel(List<String> logList,String excelFilePath,String sheetName) {
        File file = new File(excelFilePath);
        try {
            XSSFWorkbook wb = new XSSFWorkbook();
            FileOutputStream fileOut = new FileOutputStream(file);
            XSSFSheet sheet = wb.createSheet();
            wb.setSheetName(0, sheetName);
            writeData(logList,sheet);
            wb.write(fileOut);
            fileOut.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeData(List<String> logList, XSSFSheet sheet) {

        /*Object[][] objs = data.getObjects();
        for (int i = 0; i < objs.length; i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < objs[i].length; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue((String)objs[i][j]);
            }
        }*/
        for(int i = 0; i < logList.size(); i++){
            String line = logList.get(i);

            XSSFRow row = sheet.createRow(i);
            String[] columns = line.split("####");
            for(int j = 0; j <columns.length; j++){
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(columns[j]);
            }
        }

    }
}
