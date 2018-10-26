/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ede;

/**
 *
 * @author Ian
 */
public class Execute {
    private static Mouse m = null;
    Execute() {
        m = new Mouse();
    }
    
    public void run() {
        m.setOn();
        Thread t1 = new Thread(m);
        t1.start();        
    }
    
    public static void setOn() {
        m.setOn();
    }
    
    public static void setOff() {
        m.setOff();
    }
    
}
