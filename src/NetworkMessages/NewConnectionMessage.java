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
public class NewConnectionMessage extends NetworkMessage {
    
    public final String ipAddress;
    
    public final int port;
    
    public NewConnectionMessage(String ipAddress, int port){
        this.ipAddress = ipAddress;
        this.port = port;
    }
}
