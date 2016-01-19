/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author feet
 */
public class Connection {

    public Socket socket;
    public ServerSocket serverSocket;

    private PrintWriter output;

    public int connectionId;

    public Connection() {
        connectionId = -1;
    }

    public void sendData(String data) {
        output.print(data);
    }

    public String getData() {
        try {
            if (socket != null && socket.getInputStream().available() != 0) {
                int availableBytes = socket.getInputStream().available();
                byte array[] = new byte[availableBytes];
                socket.getInputStream().read(array);
                return new String(array);
            }
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.WARNING, null, ex);
        }
        return "";
    }

    public boolean isConnected() {
        if (socket != null) {
            return socket.isConnected();
        }
        return false;
    }

    public String getIpAddress() {
        if (socket != null) {
            return socket.getInetAddress().toString();
        }
        return "";
    }

    public int getPort() {
        if (socket != null) {
            return socket.getPort();
        }
        return -1;
    }

    public void disconnect() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.WARNING, null, ex);
        }
        socket = null;
    }

    public boolean connect(String address, int port) {
        try {
            socket = new Socket(address, port);
            output = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return socket.isConnected();
    }

    public boolean connect(String address, int port, int attempts) {
        for (int i = 0; !isConnected() && i < attempts; i++) {
            connect(address, port);
        }
        return isConnected();
    }

    public boolean accept(int port) {
        try {
            serverSocket = new ServerSocket(port);
            socket = serverSocket.accept();
            output = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isConnected();
    }

    public boolean accept(int port, String address) {
        try {
            serverSocket = new ServerSocket(port, 1000, InetAddress.getByName(address));
            socket = serverSocket.accept();
            output = new PrintWriter(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return isConnected();
    }
}
