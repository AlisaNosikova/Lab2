/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author User
 */
public class Storage {
private HashMap<String,ArrayList<Double>> results = new HashMap<>();
private HashMap<String,ArrayList<Double>> matrixInfo = new HashMap<>();
    public void addToMap(String name,ArrayList<Double> array){
        results.put(name,array);
    }
    public void addToMatrix(String key, ArrayList<Double> covInfo){
        matrixInfo.put(key, covInfo);
    }
    public HashMap<String,ArrayList<Double>> getResults(){
        return results;
    }
    public HashMap<String,ArrayList<Double>> getMatrixInfo(){
        return matrixInfo;
    }

}
