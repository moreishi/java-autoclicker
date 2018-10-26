/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ede;

import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.util.Random;

/**
 *
 * @author Ian
 */
public class Mouse implements Runnable {
    
    public static final int FIVE_SECONDS = 1000 * 15;
    public static int MAX_Y = 0;
    public static int MAX_X = 0;
    
    public static volatile boolean running = true;
    
    public void run() {
        try {
            runRobot();
        }
        catch(AWTException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void runRobot() throws AWTException {

            try {
                Robot robot = new Robot();
                Random random = new Random();
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                
                MAX_X = resolutionHeight(screenSize);
                MAX_Y = resolutionWidth(screenSize);
                
                Keyboard kb = new Keyboard();
                Tab tb = new Tab();
                
                while (running) {
                    mouseLastPosition(robot);
                    mouseClickTrigger(robot);
                    kb.run();
                    focusActiveWindow(robot);
                    tb.run();
                    mousePositionRandomize(robot);
                    Thread.sleep(FIVE_SECONDS);
                }
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
                setOff();
            }
        
    }
    
    public static void setOff() {
        running = false;
    }
    
    public static void setOn() {
        running = true;
    }
    
    public static int resolutionWidth(Dimension screenSize) {
        return (int) screenSize.getWidth();
    }
    
    public static int resolutionHeight(Dimension screenSize) {
        return (int) screenSize.getHeight();
    }
    
    public static void mouseLastPosition(Robot robot) {
        robot.mouseMove((int) MAX_Y - (int) (MAX_Y * 0.25), MAX_X);
    }
    
    public static void mousePositionRandomize(Robot robot) {
        int count = mouseMoveCount();
        for(int i = 0; i < count; i++) {
            Random r = new Random();
            int x = r.nextInt(MAX_X);
            int y = r.nextInt(MAX_Y);
            robot.mouseMove(x, y);
            System.out.println("Mouse is moving... " + count);
        }
        
    }
    
    public static int mouseMoveCount() {
        int count = getRandomNumberInRange(400,1000);
        return count;
    }
    
    public static void mouseClickTrigger(Robot robot) {
        int  clicks = getRandomNumberInRange(100,1000);
        System.out.println("Number of mouse clicks: " + clicks);
        for(int i = 0; i < clicks; i++) {
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        }
    }
    
    private static int getRandomNumberInRange(int min, int max) {

            if (min >= max) {
                    throw new IllegalArgumentException("max must be greater than min");
            }

            Random r = new Random();
            return r.nextInt((max - min) + 1) + min;
    }
    
    
    /* @void Select the active maximized window */
    public static void focusActiveWindow(Robot robot) {
        try {
            robot.mouseMove((int) MAX_Y - (int) (MAX_Y * 0.01), 
                (int) MAX_X - (int) (MAX_X * 0.75));
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
        }
        catch(Exception ex) { 
            System.out.println(ex.getMessage()); 
        }
    }
    
}
