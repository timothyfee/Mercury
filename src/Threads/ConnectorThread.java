/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Connection.Connection;
import Connection.ConnectionPool;
import Mercury.NetworkMessageMailer;
import NetworkMessages.NewConnectionMessage;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author feet
 */
public class ConnectorThread implements Runnable {

    private final int connectionTimeout;
    public boolean stop;
    public int threadInterval;

    private final ConnectionPool connectionPool;
    private final NetworkMessageMailer mailer;
    private final int port;
    private final String address;

    public ConnectorThread(ConnectionPool connectionPool, NetworkMessageMailer mailer, int port, String acceptableAddress, int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
        this.stop = false;
        this.threadInterval = 0;

        this.connectionPool = connectionPool;
        this.mailer = mailer;
        this.port = port;
        this.address = acceptableAddress;
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        this.stop = false;
        while (!stop) {
            for (int i = 0; i < connectionPool.size(); i++) {
                Connection connection = connectionPool.getConnection(i);
                if (connection.isConnected()) {
                    continue;
                }
                connection.accept(port, address);
                try {
                    connection.socket.setSoLinger(true, connectionTimeout);
                } catch (SocketException ex) {
                    Logger.getLogger(ConnectorThread.class.getName()).log(Level.WARNING, null, ex);
                }
                mailer.push(i, new NewConnectionMessage(connection.getIpAddress(), connection.getPort()));
            }
            try {
                Thread.sleep(threadInterval);
            } catch (InterruptedException ex) {
                Logger.getLogger(ConnectorThread.class.getName()).log(Level.WARNING, null, ex);
            }
        }
    }
}
