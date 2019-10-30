/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Recommender_System;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class for Creating Tables from CVS Files 
 * @author mathi
 */
public class CsvLoader {
    private ArrayList<ArrayList> table;

    public CsvLoader(String data) {
        table = new ArrayList<>();
        File f = new File(data); 
  
            // Get the absolute path of file f 
            String absolute = f.getAbsolutePath();
            
        // create an instance of BufferedReader
        // using try with resource, Java 7 feature to close resources
        try (BufferedReader br = new BufferedReader(new FileReader(absolute));) {

            // read the first line from the text file and create colums
            String line = br.readLine();
            String[] colums = line.split(",");
            for (String colum : colums){
                    ArrayList<String> columX = new ArrayList<>();
                    columX.add(colum);
                    table.add(columX);
                }
           while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                    Integer counterColums = 0;
                for (String value : values){
                    table.get(counterColums).add(value);
                    counterColums++;
                    }
            }
            
            
            
         
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
    
    public ArrayList<String> getColum(Integer number){
        return table.get(number);
    }
    
    public ArrayList<ArrayList> getTable(){
        return table;
    }
    
    
    
}
