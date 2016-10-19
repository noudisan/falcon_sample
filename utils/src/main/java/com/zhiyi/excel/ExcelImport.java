package com.zhiyi.excel;


import org.apache.commons.lang.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelImport {
    private String filePath;
    private XSSFSheet sheet;

    public ExcelImport(String filePath) {
        this.filePath = filePath;
    }

    private void init() {
        File file = new File(filePath);
        try {
            InputStream input = new FileInputStream(file);
            XSSFWorkbook book = new XSSFWorkbook(input);
            sheet = book.getSheetAt(0);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ExcelData getImportExcel() {
        this.init();
        ExcelData data = new ExcelData();
        data.setSheetName(sheet.getSheetName());
        int columnNum = sheet.getRow(0).getLastCellNum();
        int rowNum = sheet.getLastRowNum();
        //String[] columns = new String[columnNum];
        String[][] objs = new String[rowNum][columnNum];
        try {
            for (int i = 0; i < columnNum; i++) {
                //columns[i] = sheet.getRow(0).getCell(i).getStringCellValue();
                for (int j = 0; j < rowNum; j++) {
                    try {
                        objs[j][i] = sheet.getRow(j).getCell(i) != null ? sheet.getRow(j).getCell(i).getStringCellValue() : "";
                    } catch (Exception e) {
                        try {
                            double numericCellValue = sheet.getRow(j).getCell(i).getNumericCellValue();
                            if(numericCellValue > 0d ){
                                String value =numericCellValue +"";
                                if(value.endsWith(".0")){
                                    objs[j][i] = value.substring(0,value.length()-2);
                                }else {
                                    objs[j][i] = value;
                                }
                            }
                            //sheet.getRow(j).getCell(i) != null ? numericCellValue : "";
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //data.setColumns(columns);
        data.setObjects(objs);
        return data;
    }

    public static void main(String[] args) throws IOException {
        //createSqlFile();

        createUpdateSqlFile();
    }
    private static void createUpdateSqlFile() throws IOException {
        ExcelImport excelImport = new ExcelImport("/Users/zhoutaotao/Downloads/bsgs_3.xlsx");
        ExcelData excelData = excelImport.getImportExcel();

        Pattern pattern = Pattern.compile("(\\d+\\.\\d+|\\d+)");

        StringBuffer fileBuffer = new StringBuffer();
        String[][] objs = excelData.getObjects();
        System.out.println("totalSize:"+objs.length);
        for (int i = 0; i < objs.length; i++) {
            StringBuffer stringBuffer = new StringBuffer();
            String city = objs[i][0];
            String name = objs[i][2];
            String households = objs[i][6];
            String price = objs[i][15];

            if(StringUtils.isBlank(city) || "NULL".equalsIgnoreCase(city) || StringUtils.isBlank(name) || "NULL".equalsIgnoreCase(name)){
                continue;
            }
            if((StringUtils.isBlank(households) || "NULL".equalsIgnoreCase(households)) && (StringUtils.isBlank(price) || "NULL".equalsIgnoreCase(price))){
                continue;
            }

            //stringBuffer.append("update tb_base_community set HOUSEHOLDS = '', PRICES = '' where city ='', name =''");
            stringBuffer.append("update tb_base_community set ");
            boolean canUpdate = false;
            if(StringUtils.isNotBlank(households) && !"NULL".equalsIgnoreCase(households)){
                Matcher m = pattern.matcher(households);
                if(m.find()){
                    canUpdate = true;
                    stringBuffer.append(" HOUSEHOLDS = '").append(m.group(1)).append("'");
                }
            }

            if(StringUtils.isNotBlank(price) && !"NULL".equalsIgnoreCase(price)){
                Matcher m = pattern.matcher(price);
                if(m.find()) {
                    if(canUpdate){
                        stringBuffer.append(" , PRICES = '").append(m.group(1)).append("'");
                    }else {
                        stringBuffer.append(" PRICES = '").append(m.group(1)).append("'");
                    }
                    canUpdate = true;
                }
            }



            stringBuffer.append(" where city ='").append(city).append("' and name ='").append(name).append("';\n");

            if(canUpdate){
                fileBuffer.append(stringBuffer);
            }
        }
        System.out.println("FileWrite~~~~~");
        Files.write(Paths.get("/Users/zhoutaotao/Downloads/newUpdateSQL.sql"), fileBuffer.toString().getBytes());
    }


    private static void createSqlFile() throws IOException {
        ExcelImport excelImport = new ExcelImport("/Users/zhoutaotao/Downloads/bsgs_3.xlsx");
        ExcelData excelData = excelImport.getImportExcel();

        Pattern pattern = Pattern.compile("(\\d+\\.\\d+|\\d+)");

        StringBuffer stringBuffer = new StringBuffer("INSERT INTO TB_EXTRA_COMMUNITY" +
                " (CITY, EXTRA_ID, NAME, ALIAS, OTHER_NAME, ADDRESS, ZONGHUSHU, " + //6
                "JIANZHUMIANJI, WUYEFEE, RONGJILV, TINGCHEWEI, LVHUALV, JIANZAONIANDAI," + //12
                " WUYEGONGSI, KIFASHANG, XIAOQUJUNJIA) VALUES " ); //15
        // ");");
        String[][] objs = excelData.getObjects();
        for (int i = 0; i < objs.length; i++) {
            stringBuffer.append("(");
            for (int j = 0; j < objs[0].length; j++) {
                String obj = objs[i][j];

                if("NULL".equalsIgnoreCase(obj) || StringUtils.isBlank(obj)){
                    if(j == 6  || j ==15){
                        stringBuffer.append("'0'");
                    }else {
                        stringBuffer.append("'").append(obj).append("'");
                    }
                }else {
                    if(j == 6  || j ==15){
                        Matcher m = pattern.matcher(obj);
                        if (m.find()) {
                            stringBuffer.append("'").append(m.group(1)).append("'");
                        }else {
                            stringBuffer.append("'0'");
                        }
                    }else {
                        stringBuffer.append("'").append(obj).append("'");
                    }

                }

                if(j !=objs[0].length-1 ){
                    stringBuffer.append(",");
                }
            }


            stringBuffer.append("),\n");
        }

        stringBuffer.append(";");
        Files.write(Paths.get("/Users/zhoutaotao/Downloads/newSQL.sql"), stringBuffer.toString().getBytes());
    }
}
