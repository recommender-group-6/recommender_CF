/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recommender_System;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author mathi
 */
public class Recommender {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Matrix matrix = loadMatrix("ratings.txt");
       Double[][] userSim = new Double[matrix.maxRow()+1][matrix.maxRow()+1];
       Double[][] contentSim = new Double[matrix.maxColum()+1][matrix.maxColum()+1];
       ArrayList<SimularityFunction> simFuncs = new ArrayList(); 
       ArrayList<Evaluation> evalFuncs = new ArrayList(); 
       simFuncs.add(new CosineSimilarity());
       evalFuncs.add(new AverageSquaredError());
       Matrix test = matrix.splitRandom(20.);
       
       
       for (SimularityFunction simFunc : simFuncs){
           for (Evaluation evalFunc : evalFuncs){
           //UserSimularity
       for (Integer i = 1; i < userSim.length;i++){
          for (Integer j = 1; j < userSim[i].length;j++){
              userSim[i][j] = simFunc.calculate(matrix.rowVector(i), matrix.rowVector(j));
            }
       }
        System.out.println(simFunc.toString() + " {");
        System.out.println(evalFunc.toString() + " {");
        System.out.println("User Simularity: " + evalFunc.calculate(test, prediction(userSim, matrix.toArray(), true)));
        
            //ContentSimularity
        for (Integer i = 1; i < contentSim.length;i++){
          for (Integer j = 1; j < contentSim[i].length;j++){
              contentSim[i][j] = simFunc.calculate(matrix.columVector(i), matrix.columVector(j));
            }
          
       }
        System.out.println("Content Simularity: " + evalFunc.calculate(test, prediction(contentSim, matrix.toArray(), false)));
        System.out.println("}");
       }
        System.out.println("}");
       }
       
        
    }
    
    
    //Build Table
    private static Matrix loadMatrix(String dokument){
        CsvLoader rating = new CsvLoader(dokument);
        HashMap<Integer,Integer> contentMap = new HashMap();
       Matrix matrix = new Matrix();
       Integer counter = 1;
       for (Integer i = 1; i< rating.getColum(0).size();i++){
           Integer colum = 0;
           if(contentMap.containsKey(Integer.valueOf(rating.getColum(1).get(i)))){
               colum = contentMap.get(Integer.valueOf(rating.getColum(1).get(i)));
           }
           else{
               contentMap.put(Integer.valueOf(rating.getColum(1).get(i)), counter);
               colum = counter;
               counter++;
           }
           Matrix_entry<Double> entry;
           Double data = Double.valueOf(rating.getColum(2).get(i));
           Integer row = Integer.valueOf(rating.getColum(0).get(i));
           entry  = new Matrix_entry(data,colum,row);
           matrix.add(entry);
       }
       return matrix;
    }
    
    private static void arrayAusgeben(Double[][] arr){
        for (Integer i = 1; i < arr.length;i++){
          for (Integer j = 1; j < arr[i].length;j++){
              System.out.print(arr[i][j] + " ");
            }
          System.out.println();
       }
        System.out.println();
    }
    
    private static Double[][] prediction(Double[][] simMatrix, Double[][] matrix,Boolean user){
        for (Integer i = 1; i < matrix.length;i++){
          for (Integer j = 1; j < matrix[i].length;j++){
              if (matrix[i][j] == 0.){
                  Double topValue = 0.;
                  Double bottomValue = 0.;
                  for (Integer k = 1; k < simMatrix.length;k++){
                      if(user){
                        topValue = topValue + (rowVector(simMatrix, i)[k]*rowVector(matrix, i)[k]);
                        bottomValue = bottomValue + rowVector(simMatrix, i)[k];
                      }
                      else{
                        topValue = topValue + (columVector(simMatrix, i)[k]*columVector(matrix, i)[k]);
                        bottomValue = bottomValue + columVector(simMatrix, i)[k];
                      }
                    }
                  matrix[i][j] = Math.round(100.0 * (topValue/bottomValue)) / 100.0;
              }
           }
       }
        return matrix;
    }
    
    private static Double[] rowVector(Double[][] matrix, Integer row){
        Double[] vector = new Double[matrix.length]; 
        for (Integer i = 1; i < matrix.length;i++){
          for (Integer j = 1; j < matrix[i].length;j++){
              if (j == row){
                  vector[i] = matrix[i][j];
              }
            }
          
       }
        return vector;
    }
    
    private static Double[] columVector(Double[][] matrix, Integer colum){
        Double[] vector = new Double[matrix[1].length]; 
        for (Integer i = 1; i < matrix.length;i++){
          for (Integer j = 1; j < matrix[i].length;j++){
              if (i == colum){
                  vector[j] = matrix[i][j];
              }
            }
          
       }
        return vector;
    }
    
}
