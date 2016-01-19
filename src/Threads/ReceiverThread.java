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
public class ReceiverThread implements Runnable {

    public int threadInterval;
    public boolean stop;

    private String stream;
    private final ConnectionPool connectionPool;
    private final ConnectionBuffer listener;
    private final StringWareContainer stringWareContainer;
    private final Codec decoder;
    private final PairWareContainer pairWareContainer;
    private final Interpreter parser;
    private final MessageWareContainer messageWareContainer;
    private final NetworkMessageMailer mailer;

    public ReceiverThread(ConnectionPool connectionPool,
            NetworkMessageMailer mailer,
            MessageWareContainer messageWareContainer,
            Interpreter parser,
            PairWareContainer pairWareContainer,
            Codec decoder,
            StringWareContainer stringWareContainer) {
        this.threadInterval = 0;
        this.stop = false;

        this.stream = "";
        this.connectionPool = connectionPool;
        this.mailer = mailer;
        this.messageWareContainer = messageWareContainer;
        this.parser = parser;
        this.pairWareContainer = pairWareContainer;
        this.decoder = decoder;
        this.stringWareContainer = stringWareContainer;
        this.listener = new ConnectionBuffer(decoder);
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        this.stop = false;
        while (!stop) {
            try {
                for (int i = 0; i < connectionPool.size(); i++) {
                    Connection connection = connectionPool.getConnection(i);
                    if (connection.isConnected()) {
                        stream = listener.getDataStream(connection);
                        stream = stringWareContainer.processForInput(stream, i);
                        ArrayList<KeyValuePair> pairs = decoder.decode(stream);
                        ArrayList<KeyValuePair> processedPairs = pairWareContainer.processForInput(pairs, i);
                        ArrayList<NetworkMessage> messages = parser.parse(processedPairs);
                        ArrayList<NetworkMessage> processedMessages = messageWareContainer.processForInput(messages, i);
                        mailer.push(i, processedMessages);
                    }
                }
                Thread.sleep(threadInterval);
            } catch (InterruptedException ex) {
                Logger.getLogger(ReceiverThread.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }
}
