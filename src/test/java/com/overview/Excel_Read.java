package com.overview;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class Excel_Read {
   public static void main(String[] args){
       XSSFWorkbook excelworkbook;
       XSSFSheet excelsheet;
       XSSFCell excelcell;

       String path = "//Users//waquaralam//Framework_POM//src/main//resources//Electronic Wastage.xlsx";
       String sheetname = "Ewaste";

       try{
           FileInputStream fs = new FileInputStream(path);
           excelworkbook = new XSSFWorkbook(fs);
           excelsheet = excelworkbook.getSheet(sheetname);
           excelcell = excelsheet.getRow(5).getCell(0);

           String dataincell = excelcell.getStringCellValue();
           System.out.println(dataincell);

       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
