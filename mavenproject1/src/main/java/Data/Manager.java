package Data;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class Manager {
    private Reader reader = new Reader();
    private Storage storage = new Storage();
    private Calculator calculator = new Calculator();
    private HashMap<String,ArrayList<Double>> list;
    
    public void goToReader(String name, int index) throws IOException{
        list = reader.readFromExcel(name, index);
        
       
    }
    public void goToReaderToGetIndex(String name, String nameSheet) throws IOException{
        goToReader(name, reader.getIndex(name, nameSheet));
    }
    public HashMap<String,ArrayList<Double>> getMap(){
         return list;
    }
    public void sendToCalculator(String name) throws IOException{
        ArrayList<Double> results = new ArrayList<>();
        double result = 0.0;
        for (String key: list.keySet()){
             calculator.getDescip(list.get(key));
        switch(name){
            case "means" ->
                results.add(calculator.calcMean());
            case "geometricMeans" ->
             results.add(calculator.calcGeometricMean());
            case "maxValues" ->
                results.add(calculator.calcMaxValues());
            case "minValues" ->
                results.add(calculator.calcMinValues());
            case "ranges" ->
                results.add(calculator.calcRanges());
            case "StandartDeviations" ->
                results.add(calculator.calcStandartDeviations());
            case "CofVars" ->
                results.add(calculator.calcVariances());
            case "Variances" ->
                results.add(calculator.calcVariances()); 
            case "confidenceInterval" -> {
                for (double value : calculator.confidenceInterval()) {
                      results.add(value); 
             }
            }
            case "numberOfElements" ->
                results.add(calculator.calcNumberOfElements());
            case "covariance" -> {
                goToMatrixInfo();
            }
        }
    }
    if (name.equals("covariance") == false){
        storage.addToMap(name,results);
    }
  
    }
    
    public void goToMatrixInfo(){
        for (String key1: list.keySet()){
            ArrayList<Double> values = new ArrayList<>();
            for (String key2: list.keySet()){
                values.add(calculator.covariance(list.get(key1), list.get(key2)));
            }
           
          storage.addToMatrix(key1,values);
        }
    }
    public void goToWriter(String name) throws IOException{
        Writer writer = new Writer(name);
        writer.writeIntoExcel(list.keySet(),storage.getResults(), storage.getMatrixInfo());
        
    }
}
