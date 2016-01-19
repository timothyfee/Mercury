package Mercury;


import Connection.Connection;
import DataTransferUnits.KeyValuePair;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author feet
 */
public interface Codec {
    
    public boolean checkHeader(String data, Connection connection);
    public boolean checkFooter(String data);
    
    public ArrayList<KeyValuePair> decode(String dataStream);
    public String encode(ArrayList<KeyValuePair> pairs);
}
