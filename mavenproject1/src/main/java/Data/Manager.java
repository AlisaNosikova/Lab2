package Data;


import Data.*;
import Lab2.Reader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

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
    private Calculator calculator;
    private ColReader colReader = new ColReader();
    private HashMap<String,ArrayList<Double>> list;
    
    public void goToReader(String name, int index) throws IOException{
        list = reader.readFromExcel(name, index);
    }
    public void sendToCalculator(String name) throws IOException{
        ArrayList<Double> results = new ArrayList<>();
        double result = 0.0;
        for (String key: list.keySet()){
             calculator.getDescip(list.get(key));
        switch(name){
            case "means" ->
                result = calculator.calcMean(); 
            case "geomerticMeans" ->
               result = calculator.calcGeometricMean();
            case "maxValues" ->
                result = calculator.calcMaxValues();
            case "minValues" ->
                result = calculator.calcMinValues();
            case "ranges" ->
                result = calculator.calcRanges();
            case "StandartDeviations" ->
                result = calculator.calcStandartDeviations();
            case "CofVars" ->
                result = calculator.calcVariances();
            case "Variances" ->
                result = calculator.calcVariances();         
        }
    results.add(result);
    }
    storage.addToMap(name,results);
    }
    public void goToWriter(String name) throws IOException{
        Writer writer = new Writer(name);
        writer.writeIntoExcel(storage.getMap());
        
    }
}
