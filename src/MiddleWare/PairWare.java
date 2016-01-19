/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiddleWare;

import DataTransferUnits.KeyValuePair;
import java.util.ArrayList;

/**
 *
 * @author feet
 */
public interface PairWare {
    
    ArrayList<KeyValuePair> processInput(ArrayList<KeyValuePair> pairs, int connectionId);
    ArrayList<KeyValuePair> processOutput(ArrayList<KeyValuePair> pairs, int connectionId);
}
