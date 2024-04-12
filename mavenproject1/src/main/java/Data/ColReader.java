/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;
import java.util.*;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author User
 */
public class ColReader {
    Manager manager;
    public DescriptiveStatistics run(HashMap<String,ArrayList<Double>> list, Manager manager){
        this.manager = manager;
        for (String key: list.keySet()){
            DescriptiveStatistics descrip= new DescriptiveStatistics();
             ArrayList<Double> values = list.get(key);
        for (Double value : values) {
            descrip.addValue(value);
        }
        return descrip;
        }
        return null;
        
    }
    public DescriptiveStatistics getCol(DescriptiveStatistics col){
        
        return col;
        
    }
}
