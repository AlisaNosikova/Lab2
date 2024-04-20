package Data;



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class Writer {
    private Workbook book = new XSSFWorkbook();
    private Sheet sheet1 = book.createSheet("First sheet");
    private Sheet sheet2 = book.createSheet("Second sheet");
    private String name;
    public  Writer( String name){
        this.name = name;
        
    }
    public void writeIntoExcel(Set<String> sampleNames, HashMap<String,ArrayList<Double>> results, HashMap<String,ArrayList<Double>> infoMatrix) throws FileNotFoundException, IOException {
            createTable( sampleNames, results, sheet1);
            createMatrix(infoMatrix, sheet2);
            book.write(new FileOutputStream(name));
            book.close();
       
}
    
    public void createTable(Set<String> namesOfSamples, HashMap<String,ArrayList<Double>> infoList, Sheet sheet){
         Set<String> namesOfColms = infoList.keySet();
         System.out.println(infoList);
         createColNames(namesOfColms, sheet);
         createRowNames(namesOfSamples,sheet);
         int colNum = namesOfColms.size();
         int i=0;
         for (String nameOfCol: namesOfColms){
             if (!"confidenceInterval".equals(nameOfCol)){
                for (int j=0;j<namesOfSamples.size();j++){
                     Row row = sheet.getRow(j+1);
                     if(row==null){
                         row = sheet.createRow(j+1);
                     }
                      Cell cell = row.createCell(i+1);
                    
                  try{
              cell.setCellValue(infoList.get(nameOfCol).get(j));
                  }catch( IndexOutOfBoundsException en){
                      System.out.println("d");
                  }
                      
            }
             }
             else{
                 for (int j=0;j<namesOfSamples.size();j++){
                     Row row = sheet.getRow(j+1);
                     if(row==null){
                         row = sheet.createRow(j+1);
                     }
                      Cell cell = row.createCell(i+1);
                      cell.setCellValue("[" + infoList.get(nameOfCol).get(j) + ";" + infoList.get(nameOfCol).get(j+1) + "]");
                      
                 }         
            }
             
               
         i++;  
         }
    }
        
    
        public void createMatrix( HashMap<String,ArrayList<Double>> infoList, Sheet sheet){
         Set<String> namesOfColms = infoList.keySet();
         createColNames(namesOfColms, sheet);
         createRowNames(namesOfColms,sheet);
         int colNum = namesOfColms.size();
         int i=0;
         for (String nameOfCol: namesOfColms){
                for (int j=0;j<namesOfColms.size();j++){
                       Row row = sheet.getRow(j+1);
                     if(row==null){
                         row = sheet.createRow(j+1);
                     }
                      Cell cell = row.createCell(i+1);
                      cell.setCellValue(infoList.get( nameOfCol).get(j));
            }
                i++;
            }  
    }
    
public void createColNames(Set<String> colnames, Sheet sheet) {
    Row row = sheet.createRow(0);
    int columnIndex = 1;
    for (String columnName : colnames) {
        Cell cell = row.createCell(columnIndex);
        cell.setCellValue(columnName);
        columnIndex++;
    }
}
  public void createRowNames(Set<String> rownames, Sheet sheet) {
    int rowIndex = 1;
    for (String rowName : rownames) {
        Row row = sheet.createRow(rowIndex);
        Cell cell = row.createCell(0);
        cell.setCellValue(rowName);
        rowIndex++;
    }
}
  
}

