/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Mercury.NetworkMessageMailer;
import Mercury.Interpreter;
import Mercury.Codec;
import Connection.ConnectionBuffer;
import Connection.*;
import DataTransferUnits.KeyValuePair;
import DataTransferUnits.NetworkMessage;
import MiddleWareContainers.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timothy
 */
public class SenderThread implements Runnable {

    public int threadInterval;
    public boolean stop;

    private String stream;
    private final NetworkMessageMailer mailer;
    private final MessageWareContainer messageWareContainer;
    private final Interpreter compiler;
    private final PairWareContainer pairWareContainer;
    private final Codec encoder;
    private final StringWareContainer stringWareContainer;
    private final ConnectionBuffer sender;
    private final ConnectionPool connectionPool;

    public SenderThread(ConnectionPool connectionPool,
            NetworkMessageMailer mailer,
            MessageWareContainer messageWareContainer,
            Interpreter compiler,
            PairWareContainer pairWareContainer,
            Codec encoder,
            StringWareContainer stringWareContainer) {
        this.threadInterval = 0;
        this.stop = false;
        
        this.stream = "";
        this.connectionPool = connectionPool;
        this.mailer = mailer;
        this.messageWareContainer = messageWareContainer;
        this.compiler = compiler;
        this.pairWareContainer = pairWareContainer;
        this.encoder = encoder;
        this.stringWareContainer = stringWareContainer;
        this.sender = new ConnectionBuffer(encoder);
    }

    @SuppressWarnings("SleepWhileInLoop")
    @Override
    public void run() {
        this.stop = false;
        while (!stop) {
        for (int i = 0; i < connectionPool.size(); i++) {
            Connection connection = connectionPool.getConnection(i);
            ArrayList<NetworkMessage> messages = mailer.dump(i);
            if (connection.isConnected()) {
                ArrayList<NetworkMessage> processedMessages = messageWareContainer.processForOutput(messages, i);
                ArrayList<KeyValuePair> pairs = compiler.compile(processedMessages);
                ArrayList<KeyValuePair> processedPairs = pairWareContainer.processForOutput(pairs, i);
                stream = encoder.encode(processedPairs);
                stream = stringWareContainer.processForOutput(stream, i);
                sender.sendDataStream(connection, stream);
            }
        }
            try {
                Thread.sleep(threadInterval);
            } catch (InterruptedException ex) {
                Logger.getLogger(SenderThread.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }
}
