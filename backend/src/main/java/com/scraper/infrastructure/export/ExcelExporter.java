package com.scraper.infrastructure.export;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Component
public class ExcelExporter {

    public byte[] export(List<Map<String, Object>> data) throws Exception {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Data");

            if (data.isEmpty()) {
                return new byte[0];
            }

            // Header
            Row headerRow = sheet.createRow(0);
            Map<String, Object> firstRow = data.get(0);
            int colIndex = 0;
            for (String key : firstRow.keySet()) {
                Cell cell = headerRow.createCell(colIndex++);
                cell.setCellValue(key);
            }

            // Data rows
            for (int i = 0; i < data.size(); i++) {
                Row row = sheet.createRow(i + 1);
                Map<String, Object> rowData = data.get(i);
                colIndex = 0;
                for (String key : firstRow.keySet()) {
                    Cell cell = row.createCell(colIndex++);
                    Object value = rowData.get(key);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    }
                }
            }

            return workbook.getBytes();
        }
    }
}