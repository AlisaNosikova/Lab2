/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;


import org.apache.poi.xssf.usermodel.*;


/**
 *
 * @author User
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class Reader {

    public HashMap<String, ArrayList<Double>> readFromExcel(String name,int index) throws IOException {
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(name));
        XSSFSheet myExcelSheet = myExcelBook.getSheetAt(index);
        HashMap<String,ArrayList<Double>> mapList = new HashMap<>(); 
       
        int colNum = myExcelSheet.getRow(0).getLastCellNum(); 
        int rowNum = myExcelSheet.getLastRowNum();

        for (int i = 0; i < colNum; i++) {
            String key = myExcelSheet.getRow(0).getCell(i).getStringCellValue(); 
            
            ArrayList<Double> list = new ArrayList<>();

            for (int j = 1; j <= rowNum; j++) { 
                XSSFRow row = myExcelSheet.getRow(j);
                if (row != null) {
                    Cell cell = row.getCell(i);
                    if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                        Double data = cell.getNumericCellValue();
                        list.add(data); 
                    }
                }
            }

            mapList.put(key, list); 
            
        }
        
        myExcelBook.close();
   
        return mapList;
        
    }
    public int getIndex(String name, String nameSheet) throws IOException{
        XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(name));
        return myExcelBook.getSheetIndex((nameSheet))-1;
    }

}
