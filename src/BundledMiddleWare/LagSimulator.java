/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BundledMiddleWare;

import MiddleWare.StringWare;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Timothy
 */
public class LagSimulator implements StringWare {

    private final int lag;

    public LagSimulator(int lag) {
        this.lag = lag;
    }

    @Override
    public String processInput(String dataStream, int connectionId) {
        long time = System.currentTimeMillis();
        while (System.currentTimeMillis() - time < lag){}
        return dataStream;
    }

    @Override
    public String processOutput(String dataStream, int connectionId) {
        long time = System.currentTimeMillis();
        while (System.currentTimeMillis() - time < lag){}
        return dataStream;
    }
}
