/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NetworkControllers;

import Mercury.Codec;
import Mercury.Interpreter;

/**
 *
 * @author feet
 */
public abstract class NetworkClientController extends NetworkController {

    protected NetworkClientController(Codec codec, Interpreter interpreter, int maxConnections, int connectionTimeout) {
        super(codec, interpreter, maxConnections, connectionTimeout);
    }
    
    protected NetworkClientController(Codec codec, Interpreter interpreter) {
        super(codec, interpreter, 1, 10000);
    }
    
    public abstract boolean connect(String address, int port);
    
    public abstract void disconnect();
    
    public abstract boolean isConnected();
}
