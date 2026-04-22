//package com.framework.utils;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.FileInputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ExcelReader {
//
//    public static List<String[]> getData(String path) {
//
//        List<String[]> data = new ArrayList<>();
//
//        try {
//            FileInputStream fis = new FileInputStream(path);
//            Workbook wb = new XSSFWorkbook(fis);
//            Sheet sheet = wb.getSheetAt(0);
//
//            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
//
//                Row row = sheet.getRow(i);
//
//                String keyword = row.getCell(0).toString();
//                String locator = row.getCell(1).toString();
//                String value = row.getCell(2).toString();
//
//                data.add(new String[]{keyword, locator, value});
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println(data);
//        return data;
//    }
//}


package com.framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class ExcelReader {

    // Sheet1 → Steps
    public static List<String[]> getSteps(String path) {

        List<String[]> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(path);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                String page = getCell(row.getCell(0));
                String keyword = getCell(row.getCell(1));
                String locator = getCell(row.getCell(2));
                String dataKey = getCell(row.getCell(3));
                String run = getCell(row.getCell(4));

                data.add(new String[]{page, keyword, locator, dataKey, run});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    // Sheet2 → Data
    public static Map<String, String> getTestData(String path) {

        Map<String, String> data = new HashMap<>();

        try (FileInputStream fis = new FileInputStream(path);
             Workbook wb = new XSSFWorkbook(fis)) {

            Sheet sheet = wb.getSheetAt(1);

            Row header = sheet.getRow(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);
                if (row == null) continue;

                String run = getCell(row.getCell(0));

                if (!run.equalsIgnoreCase("Y")) continue;

                for (int j = 1; j < header.getLastCellNum(); j++) {

                    String key = header.getCell(j).toString();
                    String value = getCell(row.getCell(j));

                    data.put(key, value);
                }

                break; // only first Y row
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    private static String getCell(Cell cell) {
        return (cell == null) ? "" : cell.toString();
    }
}