//package com.framework.utils;
//
//public class ExcelWriter {
//
//}

package com.framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

public class ExcelWriter {

	/**
	 * Writes product comparison data to an Excel file.
	 * 
	 * @param filePath   path to the output Excel file
	 * @param products   list of maps, each containing: productName, size, price,
	 *                   rating, selected
	 */
	public static void writeProductComparison(String filePath, List<Map<String, String>> products) {

		try (Workbook wb = new XSSFWorkbook()) {

			Sheet sheet = wb.createSheet("Product Comparison");

			// Header row
			Row header = sheet.createRow(0);
			String[] columns = { "Sr No", "Product Name", "Size", "Price", "Rating", "Selected" };

			CellStyle headerStyle = wb.createCellStyle();
			Font headerFont = wb.createFont();
			headerFont.setBold(true);
			headerStyle.setFont(headerFont);
			headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
			headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			for (int i = 0; i < columns.length; i++) {
				Cell cell = header.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerStyle);
			}

			// Data rows
			int rowNum = 1;
			for (Map<String, String> product : products) {
				Row row = sheet.createRow(rowNum);
				row.createCell(0).setCellValue(rowNum);
				row.createCell(1).setCellValue(product.getOrDefault("productName", ""));
				row.createCell(2).setCellValue(product.getOrDefault("size", ""));
				row.createCell(3).setCellValue(product.getOrDefault("price", ""));
				row.createCell(4).setCellValue(product.getOrDefault("rating", ""));
				row.createCell(5).setCellValue(product.getOrDefault("selected", "N"));
				rowNum++;
			}

			// Auto-size columns
			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			// Ensure parent directory exists
			new File(filePath).getParentFile().mkdirs();

			try (FileOutputStream fos = new FileOutputStream(filePath)) {
				wb.write(fos);
			}

			System.out.println("=== Excel file written to: " + filePath + " ===");

		} catch (Exception e) {
			System.out.println("Error writing Excel file: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
