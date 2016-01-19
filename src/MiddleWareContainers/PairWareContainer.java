/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiddleWareContainers;

import DataTransferUnits.KeyValuePair;
import MiddleWare.PairWare;
import java.util.ArrayList;

/**
 *
 * @author feet
 */
public class PairWareContainer {
    
    private ArrayList<PairWare> middleWares;
    
    public PairWareContainer(){
        middleWares = new ArrayList<PairWare>();
    }
    
    public void add(PairWare middleWare){
        middleWares.add(middleWare);
    }
    
    public ArrayList<KeyValuePair> processForInput(ArrayList<KeyValuePair> pairs, int connectionId){
        for (PairWare pairWare : middleWares){
            pairs = pairWare.processInput(pairs, connectionId);
        }
        return pairs;
    }
    
    public ArrayList<KeyValuePair> processForOutput(ArrayList<KeyValuePair> pairs, int connectionId){
        for (PairWare pairWare : middleWares){
            pairs = pairWare.processOutput(pairs, connectionId);
        }
        return pairs;
    }
}
