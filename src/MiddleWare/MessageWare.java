/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiddleWare;

import DataTransferUnits.NetworkMessage;
import java.util.ArrayList;

/**
 *
 * @author feet
 */
public interface MessageWare {
    
    ArrayList<NetworkMessage> processInput(ArrayList<NetworkMessage> messages, int connectionId);
    ArrayList<NetworkMessage> processOutput(ArrayList<NetworkMessage> messages, int connectionId);
}
