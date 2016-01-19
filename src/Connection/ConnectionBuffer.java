/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Connection.Connection;
import Mercury.Codec;

/**
 *
 * @author feet
 */
public class ConnectionBuffer {
    
    private final Codec codec;
    
    private String dataStream;
    
    public ConnectionBuffer(Codec codec){
        this.codec = codec;
        this.dataStream = "";
    }
    
    public String getDataStream(Connection connection){
        String data = connection.getData();
        dataStream = data;
        
        if (!codec.checkHeader(data, connection)){
            return "";
        }
        
        for (int i = 0; !codec.checkFooter(data); i++){
            data = connection.getData();
            if (i == 1000 && data.equals("")){
                return "";
            }
            else if (i == 1000){
                i = 0;
            }
            dataStream += data;
        }
        
        return dataStream;
    }
    
    public void sendDataStream(Connection connection, String dataStream){
        String segment = "";
        for (int i = 0; i < dataStream.length();){
            for (int c = 0; c < 10000 && i < dataStream.length(); c++, i++){
                segment += dataStream.charAt(i);
            }
            connection.sendData(segment);
            segment = "";
        }
    }
}
