package ua.gmail.autobot.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Excel {
    public static String[] getValidEmailsFromExcel(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);

        String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1);
        Workbook workbook;
        if (fileExtension.equals("xls")) {
            workbook = new HSSFWorkbook(file);
        } else if (fileExtension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file);
        } else {
            throw new IllegalArgumentException("File format not supported");
        }

        Sheet sheet = workbook.getSheetAt(0);
        int rowCount = sheet.getLastRowNum();
        List<String> validEmails = new ArrayList<>();
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
        for (int i = 0; i <= rowCount; i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                Cell cell = row.getCell(2);
                String email = null;
                if (cell != null) {
                    email = cell.getStringCellValue().trim();
                    Matcher matcher = emailPattern.matcher(email);
                    if (matcher.matches()) {
                        validEmails.add(email);
                    }
                }
            }
        }
        workbook.close();
        return validEmails.toArray(new String[0]);
    }
}
