/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NetworkMessages;

import DataTransferUnits.NetworkMessage;

/**
 *
 * @author feet
 */
public class PlainTextMessage extends NetworkMessage {
    
    public final String text;
    
    public PlainTextMessage(String text){
        this.text = text;
    }
}
