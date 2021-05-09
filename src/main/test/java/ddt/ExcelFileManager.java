package ddt;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelFileManager {

    public static Object[][] getExcelData(String filePath , String SheetName) throws IOException, InvalidFormatException {
        File excelFile = new File(filePath);
        FileInputStream fs = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fs);      /* XSSFWorkbook --> xlsx 2010
                                                           HSSFWorkbook      xls */
        XSSFSheet mySheet = workbook.getSheet(SheetName); /* You can use also getSheetAt(int sheetNum --> starts from 0 ) */
        int rowNum = mySheet.getPhysicalNumberOfRows();
        int colsNum = mySheet.getRow(0).getLastCellNum();

        String[][] arrData = new String[rowNum - 1][colsNum];


        for (int i = 1; i < rowNum; i++) {
            var row = mySheet.getRow(i);
            for (int j = 0; j < colsNum; j++) {
                arrData[i - 1][j] = row.getCell(j).toString();
            }
        }

        workbook.close();
        return arrData;

    }


   /* public  Object[][] readExcelLimitedSize(int Row , int Col , String fPath , String SheetName) throws IOException {
        File excelFile = new File(fPath);
        FileInputStream fs = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fs);      *//* XSSFWorkbook --> xlsx 2010
                                                           HSSFWorkbook      xls *//*
        XSSFSheet mySheet = workbook.getSheet(SheetName); *//* You can use also getSheetAt(int sheetNum --> starts from 0 ) *//*
        int rowNum = mySheet.getPhysicalNumberOfRows();
        int colsNum = mySheet.getRow(0).getLastCellNum();

        String[][] arrData = new String[rowNum - 1][colsNum];


        for (int i = 1; i < Row; i++) {
            var RR = mySheet.getRow(i);
            for (int j = 0; j < Col; j++) {
                arrData[i - 1][j] = RR.getCell(j).toString();
            }
        }

        workbook.close();
        return arrData;

    }*/
}
