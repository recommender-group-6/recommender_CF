/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recommender_System;

/**
 *
 * @author mathi
 */
public class AverageSquaredError implements Evaluation {

    @Override
    public Double calculate(Matrix test, Double[][] results) {
        Double sum = 0.;
        for (Integer i = 0;i<test.size();i++){
            Integer row = test.getEntry(i).getRow();
            Integer colum = test.getEntry(i).getColum();
            Double squared = results[row][colum]-test.getEntry(i).getData();
            sum = sum + squared*squared;
        }
        return (sum/test.size());
    }
    
    @Override
    public String toString(){
        return ("Average of Squared Error");
    }
}
