/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ede;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 *
 * @author Ian
 */
public class Tab {
    
    Tab() {
    
    }
    
    public static void run() {
        switchTab(getRandomNumberInRange(3,10));
    }
    
   public static void switchTab(int count){
       try {
           Robot rb = new Robot();
           int counter = 0;
           while(counter < count) {
               rb.keyPress(KeyEvent.VK_CONTROL);
               rb.keyPress(KeyEvent.VK_SHIFT);
               rb.keyPress(KeyEvent.VK_TAB);
               rb.keyRelease(KeyEvent.VK_CONTROL);
               rb.keyRelease(KeyEvent.VK_SHIFT);
               rb.keyRelease(KeyEvent.VK_TAB);
               counter++;
           }
       } catch(Exception e) {
           System.out.println(e.getMessage());
       }
   }
    
    private static int getRandomNumberInRange(int min, int max) {

            if (min >= max) {
                    throw new IllegalArgumentException("max must be greater than min");
            }

            Random r = new Random();
            return r.nextInt((max - min) + 1) + min;
    }
    
}
