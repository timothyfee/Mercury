/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiddleWare;

/**
 *
 * @author feet
 */
public abstract class Cipher implements StringWare {
    
    public abstract String encrypt(String dataStream);
    
    public abstract String decrypt(String dataStream);
    
    @Override
    public String processInput(String dataStream, int connectionId){
        return decrypt(dataStream);
    }
    
    @Override
    public String processOutput(String dataStream, int connectionId){
        return encrypt(dataStream);
    }
}
