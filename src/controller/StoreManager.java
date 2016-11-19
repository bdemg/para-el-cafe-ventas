/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import view.OptionsMenuBar;

/**
 *
 * @author Jorge A. Cano
 */
public class StoreManager extends Controller{

    private final OptionsMenuBar optionsMenuBar;
    
    StoreManager(OptionsMenuBar input_optionsMenuBar) {
        
        this.optionsMenuBar = input_optionsMenuBar;
        
        this.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object eventSource = e.getSource();
        
        if( eventSource == this.optionsMenuBar.getRegisterClient() ){
            this.callClientManager();
            
        }else if( eventSource == this.optionsMenuBar.getMonthlyReport()){
            this.callReportManager();
            
        }else if( eventSource == this.optionsMenuBar.getTodaysDeliveries()){
            this.callDeliverySecretary();
            
        }
    }

    @Override
    protected void addActionListeners() {
        
        this.optionsMenuBar.getRegisterClient().addActionListener( this );
        this.optionsMenuBar.getMonthlyReport().addActionListener( this );
        this.optionsMenuBar.getTodaysDeliveries().addActionListener( this );
    }

    private void callClientManager() {
        
        new ClientManager();
    }

    private void callReportManager() {
        ;//EMPTY FOR NOW
    }

    private void callDeliverySecretary() {
        ;//EMPTY FOR NOW
    }
    
}