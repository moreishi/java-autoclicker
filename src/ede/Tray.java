/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ede;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Ian
 */
public class Tray {
   
   public String name = null;
   private static Execute execute = null;
    
   Tray(String name) {
       this.name = name;
       execute = new Execute();
   }
   
   public void run() {
       System.out.println(this.name);
       if(!this.checkSystemTray()) System.exit(1);
       System.out.println("EDE initializing...");
       this.systemTray();
   }
   
   public boolean checkSystemTray() {
       if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return false;
        }
       return true;
   }
   
   public void systemTray() {
       
        final PopupMenu popup = new PopupMenu();
        Image createImage = Toolkit.getDefaultToolkit().getImage("light.png");
        final TrayIcon trayIcon = new TrayIcon(createImage);
        final SystemTray tray = SystemTray.getSystemTray();
        
        // Create a pop-up menu components
        MenuItem aboutItem = new MenuItem("About");
        MenuItem cb1 = new MenuItem("Da!");
        MenuItem cb2 = new MenuItem("Nyet!");
        Menu displayMenu = new Menu("Display");
        MenuItem errorItem = new MenuItem("Error");
        MenuItem warningItem = new MenuItem("Warning");
        MenuItem infoItem = new MenuItem("Info");
        MenuItem noneItem = new MenuItem("None");
        MenuItem exitItem = new MenuItem("Exit");
        
        // Action
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        
        cb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                try{
                    execute.setOn();
                    execute.run();
                } catch(Exception ex) {
                    System.out.print(ex.getMessage());
                }

            }
        });
        
        cb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               
                try{
                   execute.setOff();
                } catch(Exception ex) {
                    System.out.print(ex.getMessage());
                }

            }
        });
        
        
        //Add components to pop-up menu
        popup.add(aboutItem);
        popup.addSeparator();
        popup.add(cb1);
        popup.add(cb2);
//        popup.addSeparator();
//        popup.add(displayMenu);
//        displayMenu.add(errorItem);
//        displayMenu.add(warningItem);
//        displayMenu.add(infoItem);
//        displayMenu.add(noneItem);
        popup.add(exitItem);
        
        trayIcon.setPopupMenu(popup);
        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
        
   }
    
}
