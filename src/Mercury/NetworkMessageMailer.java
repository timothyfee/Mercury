/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mercury;

import DataTransferUnits.NetworkMessage;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author feet
 */
public class NetworkMessageMailer {

    private final ConcurrentLinkedQueue<NetworkMessage> messages = new ConcurrentLinkedQueue<>();
    
    public NetworkMessageMailer(){
    }
    
    public int size(){
        return messages.size();
    }

    public void push(int connectionId, NetworkMessage message) {
        message.connectionId = connectionId;
        messages.add(message);
    }

    public void push(int connectionId, ArrayList<NetworkMessage> m) {
        for (NetworkMessage message : m) {
            message.connectionId = connectionId;
        }
        messages.addAll(m);
    }

    public void push(ArrayList<NetworkMessage> m) {
        messages.addAll(m);
    }

    public ArrayList<NetworkMessage> dump() {
        ArrayList<NetworkMessage> ret = new ArrayList<>();
        while (!messages.isEmpty()) {
            ret.add(messages.poll());
        }
        return ret;
    }

    NetworkMessage pop() {
        return messages.poll();
    }

    NetworkMessage pop(int connectionId) {
        for (int i = 0; i < messages.size(); i++) {
            NetworkMessage message = messages.peek();
            if (message.connectionId == connectionId) {
                messages.remove(message);
                return message;
            }
        }
        return null;
    }

    NetworkMessage peek() {
        return messages.peek();
    }

    NetworkMessage peek(int connectionId) {
        for (int i = 0; i < messages.size(); i++) {
            NetworkMessage message = messages.peek();
            if (message.connectionId == connectionId) {
                return message;
            }
        }
        return null;
    }

    public ArrayList<NetworkMessage> dump(int connectionId) {
        ArrayList<NetworkMessage> ret = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++) {
            NetworkMessage message = messages.peek();
            if (message.connectionId == connectionId) {
                ret.add(message);
                messages.remove(message);
            }
        }
        return ret;
    }

    public ArrayList<NetworkMessage> copyStack() {
        ArrayList<NetworkMessage> ret = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++) {
            ret.add(messages.peek());
        }
        return ret;
    }

    public ArrayList<NetworkMessage> copyStack(int connectionId) {
        ArrayList<NetworkMessage> ret = new ArrayList<>();
        for (int i = 0; i < messages.size(); i++) {
            NetworkMessage message = messages.peek();
            if (message.connectionId == connectionId) {
                ret.add(message);
            }
        }
        return ret;
    }
}
