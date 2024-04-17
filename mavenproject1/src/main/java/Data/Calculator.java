/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.util.ArrayList;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.correlation.Covariance;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

/**
 *
 * @author User
 */
public class Calculator {
    private DescriptiveStatistics descrip;
       public double calcMean(){
       return descrip.getMean();
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
        public double calcNumberOfElements(){
        return descrip.getN();
        }
        
     
    public double[] confidenceInterval(double alpha){
       // double confidenceLevel = 0.95;
        double standardDeviation = descrip.getStandardDeviation(); 
        double sampleMean = descrip.getMean();
        double sampleSize = descrip.getN();
        NormalDistribution normalDistribution = new NormalDistribution();
       double zValue = normalDistribution.inverseCumulativeProbability(1-0.95 / 2);
        double marginOfError = zValue * standardDeviation / Math.sqrt(sampleSize);
        double lowerBound = sampleMean - marginOfError;
        double upperBound = sampleMean + marginOfError;
        return new double[] {lowerBound, upperBound} ;
       
    }
    public double covariance(ArrayList<Double> column1, ArrayList<Double> column2){
          double[] firstMassive = new double[column1.size()];
          double[] secondMassive = new double[column1.size()];
           Covariance covariance = new Covariance();
        for (int i=0; i<column1.size();i++){
             firstMassive[i] = column1.get(i);
             secondMassive[i] = column2.get(i);
        }
   
       return covariance.covariance(firstMassive ,secondMassive);
        
    }
    public void getDescip(ArrayList<Double> column){
          descrip  =  new DescriptiveStatistics();
        for (Double value:column){
            descrip.addValue(value);   
        }
    
    }
}
