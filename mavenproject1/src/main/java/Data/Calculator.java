/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author User
 */
public class Calculator {
    DescriptiveStatistics descrip;
         public double calcMean(){
       return descrip.getGeometricMean();
}
         public double calcGeometricMean(){
       return descrip.getGeometricMean();
}
         public double calcMaxValues(){
       return  descrip.getMax();
}
         public double calcMinValues(){
         return descrip.getMin();
}
        public double calcRanges(){
        return descrip.getMax() - descrip.getMin();
}
        public double calcStandartDeviations(){
         return descrip.getStandardDeviation();
}
       public double calcCofVars(){
        return descrip.getStandardDeviation()/descrip.getMean()*100;
}
        public double calcVariances(){
        return descrip.getVariance();
        }
    public void getDescip(ArrayList<Double> column){
          descrip  =  new DescriptiveStatistics();
        for (Double value:column){
            descrip.addValue(value);
            
            
        }
    }
}
