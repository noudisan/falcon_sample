package com.zhiyi.excel;

public class ExcelData {
    private String sheetName;
    //private String[] columns;

    private String[][] objects;

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

//    public String[] getColumns() {
//        return columns;
//    }
//
//    public void setColumns(String[] columns) {
//        this.columns = columns;
//    }

    public String[][] getObjects() {
        return objects;
    }

    public void setObjects(String[][] objects) {
        this.objects = objects;
    }
}
