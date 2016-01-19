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
public abstract class CompressionAlgorithm implements StringWare {
    
    public abstract String compress(String dataStream);
    
    public abstract String decompress(String dataStream);
    
    @Override
    public String processInput(String dataStream, int connectionId){
        return decompress(dataStream);
    }
    
    @Override
    public String processOutput(String dataStream, int connectionId){
        return compress(dataStream);
    }
}
