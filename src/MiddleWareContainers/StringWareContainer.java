/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiddleWareContainers;

import MiddleWare.StringWare;
import java.util.ArrayList;

/**
 *
 * @author feet
 */
public class StringWareContainer {
    
    private ArrayList<StringWare> middleWares;
    
    public StringWareContainer(){
        middleWares = new ArrayList<StringWare>();
    }
    
    public void add(StringWare middleWare){
        middleWares.add(middleWare);
    }
    
    public String processForInput(String dataStream, int connectionId){
        for (StringWare stringWare : middleWares){
            dataStream = stringWare.processInput(dataStream, connectionId);
        }
        return dataStream;
    }
    
    public String processForOutput(String dataStream, int connectionId){
        for (StringWare stringWare : middleWares){
            dataStream = stringWare.processOutput(dataStream, connectionId);
        }
        return dataStream;
    }
}
