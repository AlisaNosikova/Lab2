package Data;



import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class Writer {
    String name;
    public  Writer(String name){
        this.name = name;
    }
    public void writeIntoExcel( HashMap<String,ArrayList<Double>> list) throws IOException {
        try (Workbook book = new HSSFWorkbook()) {
            Sheet sheet = book.createSheet("First sheet");
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("John");
            book.write(new FileOutputStream(name));
        }

}
}

