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
public class CosineSimilarity implements SimularityFunction {
    
    @Override
    public Double calculate(Double[] vector1, Double[] vector2){
        Double result = 0.; 
        Double norm1 = 0.;
        Double norm2 = 0.;
        for (Integer i = 1; i< vector1.length;i++){
            result = result + (vector1[i]*vector2[i]);
            if(vector2[i] != 0.){
            norm1 = norm1 + (vector1[i]*vector1[i]);
            }
            if(vector1[i] != 0.){
            norm2 = norm2 + (vector2[i]*vector2[i]);
            }
        }
        if (result == 0){
            return 0.00;
        }
        result = result / (Math.sqrt(norm1)*Math.sqrt(norm2));
        return (Math.round(100.0 * result) / 100.0);
    }
    
    public String toString(){
        return ("Cosine Similarity");
    }
    
}
