/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recommender_System;

import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author mathi
 */
public class Matrix {
    private ArrayList<Matrix_entry<Double>> matrix;


    public Matrix() {
        matrix = new ArrayList();    
    }
    
    public Integer size(){
        return matrix.size();
    }
    
    public Matrix_entry<Double> getEntry(Integer value){
        return matrix.get(value);
    }
    
    public void add(Matrix_entry entry){
      for(Matrix_entry value : matrix){
          if(value.getColum() == entry.getColum() && value.getRow() == entry.getData()){
              this.delete(value.getColum(), value.getRow());
          }
      }
      matrix.add(entry);
    }    
    
    public void delete(Integer colum, Integer row){
        for(Matrix_entry value : matrix){
          if(value.getColum() == colum && value.getRow() == row){
              matrix.remove(value);
          }
      }
    }
    
    public Matrix splitRandom(Double prozent){
        Double counter = matrix.size() * (prozent/100);
        Matrix second = new Matrix();
        Random r = new Random();
        for (Integer i = 1; i <= counter;i++){
            Integer placeholder = r.nextInt(matrix.size());
            second.add(matrix.get(placeholder));
            matrix.remove(matrix.get(placeholder));
        }
        return second;  
    }
    
   public Integer maxColum(){
       Integer colum = -1;
       for(Matrix_entry value : matrix){
          if(value.getColum() > colum){
              colum = value.getColum();
          }
      }
       return colum;
   }
    
   public Integer maxRow(){
       Integer row = -1;
       for(Matrix_entry value : matrix){
          if(value.getRow() > row){
              row = value.getRow();
          }
      }
       return row;
   }
   
   public String toString(){
       String table = "";
       Boolean found = false;
       for (Integer i = 1;i<= this.maxColum();i++){
           table = table + "   " + i;
       }
       table = table + System.lineSeparator();
       for (Integer i = 1;i<= this.maxRow();i++){
           table = table + i + " ";
           for (Integer j = 1;j<= this.maxColum();j++){
            for(Matrix_entry value : matrix){
                if(value.getColum() == j && value.getRow() == i){
                    table = table + value.getData() + " ";
                    found = true;
                }
              
            }
            if (found){
                found = false;
            }
            else{
                table = table + " " + 0 + "  ";
            }
            
       }
           table = table + System.lineSeparator();
           
   }
       return table;
   }
   
   public Double[] columVector(Integer colum){
       Double[] vector = new Double[this.maxRow()+1];
           for(Matrix_entry value : matrix){
                if(value.getColum() == colum){
                    vector[value.getRow()] = (Double) value.getData();
                }
              
           } 
           for (Integer i = 1; i< vector.length;i++){
               if(vector[i] == null){
                   vector[i] = 0.;
               }
           }
           
       return vector;
   }
   
   public Double[] rowVector(Integer row){
       Double[] vector = new Double[this.maxColum()+1];
           for(Matrix_entry value : matrix){
                if(value.getRow() == row){
                    vector[value.getColum()] = (Double) value.getData();
                }
              
           } 
           for (Integer i = 1; i< vector.length;i++){
               if(vector[i] == null){
                   vector[i] = 0.;
               }
           }
           
       return vector;
   }
   
   public Double[][] toArray(){
       Double[][] array = new Double[this.maxRow()+1][this.maxColum()+1];
           for(Matrix_entry value : matrix){
                array[value.getRow()][value.getColum()] = (Double) value.getData();   
           } 
          for (Integer i = 1; i < array.length;i++){
            for (Integer j = 1; j < array[i].length;j++){
                  if (array[i][j] == null){
                      array[i][j] = 0.;
                  }
                }
        }
           
       return array;
   }
    
}

    
    
    

