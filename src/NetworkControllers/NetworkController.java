/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NetworkControllers;

import Mercury.NetworkMessageMailer;
import Mercury.Interpreter;
import Mercury.Codec;
import Connection.*;
import MiddleWare.*;
import MiddleWareContainers.*;
import Threads.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Timothy
 */
public class NetworkController {
    private final StringWareContainer stringWareContainer = new StringWareContainer();
    private final PairWareContainer pairWareContainer = new PairWareContainer();
    private final MessageWareContainer messageWareContainer = new MessageWareContainer();
    
    protected final ExecutorService executor = Executors.newCachedThreadPool();
    
    protected ReceiverThread receiver;
    protected SenderThread sender;
    
    protected void attach(StringWare middleWare){
        stringWareContainer.add(middleWare);
    }
    
    protected void attach(PairWare middleWare){
        pairWareContainer.add(middleWare);
    }
    
    protected void attach(MessageWare middleWare){
        messageWareContainer.add(middleWare);
    }
    
    public ConnectionPool connectionPool;
    public final NetworkMessageMailer incomingMailer = new NetworkMessageMailer();
    public final NetworkMessageMailer outgoingMailer = new NetworkMessageMailer();
    
    protected NetworkController(Codec codec, Interpreter interpreter, int maxConnections, int connectionTimeout){
        connectionPool = new ConnectionPool(maxConnections);
        receiver = new ReceiverThread(connectionPool, incomingMailer, messageWareContainer, interpreter, pairWareContainer, codec, stringWareContainer);
        sender = new SenderThread(connectionPool, outgoingMailer, messageWareContainer, interpreter, pairWareContainer, codec, stringWareContainer);
    }
    
    public void start(){
        executor.execute(receiver);
        executor.execute(sender);
    }
    
    public void stop(){
        sender.stop = true;
        receiver.stop = true;
    }
}
