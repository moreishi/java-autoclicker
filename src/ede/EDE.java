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
public class EDE {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Validation validate = new Validation();
        boolean status = validate.validateDate(null);
        
        if(!status) System.exit(1);
        
        Tray t = new Tray("EDE");
        t.run();
        
    }
    
}
