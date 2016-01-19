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
public interface StringWare {
    
    String processInput(String dataStream, int connectionId);
    String processOutput(String dataStream, int connectionId);
}
