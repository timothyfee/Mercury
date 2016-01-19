/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NetworkControllers;

import Mercury.Interpreter;
import Mercury.Codec;
import Threads.ConnectorThread;

/**
 *
 * @author feet
 */
public class NetworkServerController extends NetworkController {
    
    protected ConnectorThread connector;
    
    protected NetworkServerController(Codec codec, Interpreter interpreter, int maxConnections, int port, String acceptableAddress, int connectionTimeout){
        super(codec, interpreter, maxConnections, connectionTimeout);
        connector = new ConnectorThread(connectionPool, incomingMailer, port, acceptableAddress, connectionTimeout);
    }
    
    protected NetworkServerController(Codec codec, Interpreter interpreter, int maxConnections, int port, String acceptableAddress){
        super(codec, interpreter, maxConnections, 10000);
        connector = new ConnectorThread(connectionPool, incomingMailer, port, acceptableAddress, 1);
    }
    
    protected NetworkServerController(Codec codec, Interpreter interpreter, int maxConnections, int port){
        super(codec, interpreter, maxConnections, 10000);
        connector = new ConnectorThread(connectionPool, incomingMailer, port, "0", 1);
    }
    
    @Override
    public void start(){
        executor.execute(connector);
        super.start();
    }
    
    @Override
    public void stop(){
        connector.stop = true;
        super.stop();
    }
}
