/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ErrorMessager;
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
    public void actionPerformed(ActionEvent input_event) {
        
        Object eventSource = input_event.getSource();
        
        if( this.isClientManagerNeeded( eventSource ) ){
            this.callClientManager();
            
        }else if( this.isReportManagerRequired( eventSource ) ){
            this.callReportManager();
            
        }else if( this.isDeliverySecretaryNeeded( eventSource ) ){
            this.callDeliverySecretary();
            
        }else if( this.isPricesManagerNeeded( eventSource ) ){
            this.callPricesManager();
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
    
    
    private boolean isPricesManagerNeeded( Object input_eventSource ){
        
        return input_eventSource == this.optionsMenuBar.getChangePrice();
    }
   
    
    @Override
    protected void addActionListeners() {
        
        this.optionsMenuBar.getRegisterClient().addActionListener( this );
        this.optionsMenuBar.getMonthlyReport().addActionListener( this );
        this.optionsMenuBar.getTodaysDeliveries().addActionListener( this );
        this.optionsMenuBar.getChangePrice().addActionListener( this );
    }

    
    private void callClientManager() {
        
        new ClientManager();
    }

    
    private void callReportManager() {
        
        new SalesAccountant();
    }

    
    private void callDeliverySecretary() {
        
        try {
            
            new DeliverySecretary();
        } catch ( SQLException ex ) {
            
            this.tellErrorMessagerToShowMessage( ErrorMessager.DATABASE_ERROR );
        }
    }
    
    
    private void callPricesManager(){
        new PricesManager();
    }
    
    private void tellErrorMessagerToShowMessage( String input_ErrorMessage ){
        
        ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
        errorMessager.showErrorMessage( input_ErrorMessage );
    }
}
