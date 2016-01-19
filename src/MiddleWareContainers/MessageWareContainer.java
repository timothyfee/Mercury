/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiddleWareContainers;

import DataTransferUnits.NetworkMessage;
import MiddleWare.MessageWare;
import java.util.ArrayList;

/**
 *
 * @author feet
 */
public class MessageWareContainer {
    
    private ArrayList<MessageWare> middleWares;
    
    public MessageWareContainer(){
        middleWares = new ArrayList<MessageWare>();
    }
    
    public void add(MessageWare middleWare){
        middleWares.add(middleWare);
    }
    
    public ArrayList<NetworkMessage> processForInput(ArrayList<NetworkMessage> messages, int connectionId){
        for (MessageWare messageWare : middleWares){
            messages = messageWare.processInput(messages, connectionId);
        }
        return messages;
    }
    
    public ArrayList<NetworkMessage> processForOutput(ArrayList<NetworkMessage> messages, int connectionId){
        for (MessageWare messageWare : middleWares){
            messages = messageWare.processOutput(messages, connectionId);
        }
        return messages;
    }
}
