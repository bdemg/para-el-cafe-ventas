/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.PhoneOperator;
import javax.swing.UIManager;

/**
 *
 * @author Jorge A. Cano
 */
public class EntryPoint {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        setSystemLookAndFeel();
        new PhoneOperator();
    }
    
    
    private static void setSystemLookAndFeel() {
        try {
            
            javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EntryPoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EntryPoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EntryPoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EntryPoint.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
}
