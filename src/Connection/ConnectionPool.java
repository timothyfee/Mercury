/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import java.util.ArrayList;

/**
 *
 * @author feet
 */
public class ConnectionPool {
    public static final int FIRST_CONNECTION = 0;
    public static final int SECOND_CONNECTION = 1;
    public static final int THIRD_CONNECTION = 2;
    
    private int nextConnectionId;
    
    private ArrayList<Connection> connections;
    
    public int maxConnections;
    
    public ConnectionPool(int maxConnections){
        this.connections = new ArrayList<>();
        this.maxConnections = maxConnections;
        this.nextConnectionId = 0;
        add(maxConnections);
    }
    
    public int size(){
        return connections.size();
    }
    
    public boolean add(){
        if (connections.size() < maxConnections){
            Connection newConnection = new Connection();
            newConnection.connectionId = nextConnectionId;
            nextConnectionId++;
            connections.add(newConnection);
            return true;
        }
        return false;
    }
    
    public boolean add(int count){
        boolean ret = true;
        for (int i = 0; ret && i < count; i++){
            ret = add();
        }
        return ret;
    }
    
    public void remove(int index){
        if (connections.size() != 0){
            connections.get(index).disconnect();
            connections.remove(index);
        }
    }
    
    public Connection getConnection(int index){
        return connections.get(index);
    }
    
    public Connection getConnectionById(int connectionId){
        for (Connection connection : connections){
            if (connection.connectionId == connectionId){
                return connection;
            }
        }
        return null;
    }
}
