package Utilities;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class ExcelUtils {

    public static String username;
    public static String password;

    @SuppressWarnings("resource")
    public void readExcelData() throws IOException {
        String excelFilePath = "./testData/TestData.xlsx";
        File file = new File(excelFilePath);
        System.out.println(file.getAbsolutePath());
        FileInputStream inputStream = new FileInputStream(file);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getLastRowNum();
        int cols = sheet.getRow(1).getLastCellNum();

        System.out.println(rows);
        System.out.println(cols);

        for (int r = 1; r <= rows; r++) {
            XSSFRow row = sheet.getRow(r);

            for (int c = 0; c < 1; c++) {
                XSSFCell cell = row.getCell(c);

                row = sheet.getRow(r);
                cell = row.getCell(c + 0);

                username = cell.getStringCellValue();
                System.out.println(username);

                cell = row.getCell(c + 1);
                password = cell.getStringCellValue();
                System.out.println(password);

            }
        }
    }

    @SuppressWarnings("resource")
    public void writeExcelData(String firstNameValue, String lastNameValue, String emailValue, String phoneNumber, String zipCodeValue) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("User");

        Object data[][] = {{"FirstName", "LastName", "Email", "Phone", "Zip"}, {firstNameValue, lastNameValue, emailValue, phoneNumber, zipCodeValue}};

        int rows = data.length;
        int cols = data[0].length;

        for (int r = 0; r < rows; r++) {
            XSSFRow row = sheet.createRow(r);
            for (int c = 0; c < cols; c++) {
                XSSFCell cell = row.createCell(c);
                Object value = data[r][c];

                if (value instanceof Integer) {
                    cell.setCellValue((Integer) value);
                }
                if (value instanceof String) {
                    cell.setCellValue((String) value);
                }
                if (value instanceof Double) {
                    cell.setCellValue((Double) value);
                }
                if (value instanceof Boolean) {
                    cell.setCellValue((Boolean) value);
                }
            }
        }

        String filePath = ".\\testData\\Data.xlsx";
        FileOutputStream outputStream = new FileOutputStream(filePath);
        workbook.write(outputStream);
        outputStream.close();
        System.out.println("Successfully Write Data on Excel File");
    }
}


