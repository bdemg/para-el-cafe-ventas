/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import view.SupervisorServiceWindow;

/**
 * This class represents the store supervisor, who calls other employees when they are needed
 * @author Jorge A. Cano
 */
public final class StoreSupervisor extends Controller{

    private final SupervisorServiceWindow optionsMenuBar;
    
    
    StoreSupervisor(SupervisorServiceWindow input_optionsMenuBar) {
        
        this.optionsMenuBar = input_optionsMenuBar;
        
        this.addActionListeners();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        Object eventSource = e.getSource();
        
        if( this.isClientManagerNeeded( eventSource ) ){
            this.callClientManager();
            
        }else if( this.isReportManagerRequired( eventSource ) ){
            this.callReportManager();
            
        }else if( this.isDeliverySecretaryNeeded( eventSource ) ){
            this.callDeliverySecretary();
            
        }
    }
    
    
    private boolean isClientManagerNeeded( Object input_eventSource ){
        return input_eventSource == this.optionsMenuBar.getRegisterClient();
    }
    
    
    private boolean isReportManagerRequired( Object input_eventSource ){
        return input_eventSource == this.optionsMenuBar.getMonthlyReport();
    }
    
    
    private boolean isDeliverySecretaryNeeded( Object input_eventSource ){
        return input_eventSource == this.optionsMenuBar.getTodaysDeliveries();
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
        
        new ReportManager();
    }

    
    private void callDeliverySecretary() {
        ;//EMPTY FOR NOW
    }
    
}
