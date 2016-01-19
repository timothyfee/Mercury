/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mercury;

import DataTransferUnits.KeyValuePair;
import DataTransferUnits.NetworkMessage;
import java.util.ArrayList;

/**
 *
 * @author feet
 */
public interface Interpreter {
    public ArrayList<NetworkMessage> parse(ArrayList<KeyValuePair> pairs);
    public ArrayList<KeyValuePair> compile(ArrayList<NetworkMessage> messages);
}
