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
HashMap<String,ArrayList<Double>> list = new HashMap<>();
    public void addToMap(String name,ArrayList<Double> array){
        list.put(name,array);
    }
    public HashMap<String,ArrayList<Double>> getMap(){
        return list;
    }

}
