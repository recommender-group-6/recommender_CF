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
public interface Evaluation {
    public Double calculate(Matrix test, Double[][] result); 
    @Override
    public String toString();
}
