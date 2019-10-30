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
public class Matrix_entry<E> {
    private Integer columnr;
    private Integer rownr;
    private E data;

    public Matrix_entry(E data, Integer columnr, Integer rownr ) {
        this.columnr = columnr;
        this.rownr = rownr;
        this.data = data;
    }
 
    public Integer getRow(){
        return this.rownr;
    }
    
    public Integer getColum(){
        return this.columnr;
    }
    
    public E getData(){
        return this.data;
    }
    
    
    
    
}
