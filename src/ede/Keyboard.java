/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ede;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Ian
 */
public class Keyboard {
    
    private static final String URL = "http://quotesondesign.com/wp-json/posts?filter[orderby]=rand&filter[posts_per_page]=1";
    String str;
    
    
    Keyboard() {
    
    }
    
    public void run() {
        str = jsonGetRequest(URL);
        str = removeSpecialChars(str);
        typeString(str);
    }
    
    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }
    
    public static String jsonGetRequest(String urlQueryString) {
        String json = null;
        try {
          URL url = new URL(urlQueryString);
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setDoOutput(true);
          connection.setInstanceFollowRedirects(false);
          connection.setRequestMethod("GET");
          connection.setRequestProperty("Content-Type", "application/json");
          connection.setRequestProperty("charset", "utf-8");
          connection.connect();
          InputStream inStream = connection.getInputStream();
          json = streamToString(inStream); // input stream to string
        } catch (IOException ex) {
          ex.printStackTrace();
        }
        return json;
    }
    
    public static String removeSpecialChars(String str) {
        str = str.replaceAll("[^A-Za-z0-9]", "");
        return str;
    }
    
    public static void typeString(String str) {
        
        int counter = 0;
        int max_char = str.length() / getRandomNumberInRange(1,6);
        
        try {
            Robot r = new Robot();
            for (char c : str.toCharArray()) {
                
                if(counter != max_char) {
                    int code = KeyEvent.getExtendedKeyCodeForChar(c);
                    if (Character.isUpperCase(c))
                        r.keyPress(KeyEvent.VK_SHIFT);
                    r.keyPress(code);
                    r.keyRelease(code);
                    if (Character.isUpperCase(c))
                        r.keyRelease(KeyEvent.VK_SHIFT);
                }
                System.out.println("Typing... " + counter);
                counter++;
            }
        }
        catch(Exception e) {
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
