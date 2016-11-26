package controller;

import daos.PricesDAO;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import model.ErrorMessager;

import view.PricesBoard;

/**
 *
 * @author (c) Copyright 2016 José A. Soto. All Rights Reserved.
 */
public class PricesManager extends Controller{
    
    private final PricesBoard pricesBoard;
    
    public PricesManager(){
        
        this.pricesBoard = new PricesBoard();
        
        this.addActionListeners();
    }
    
    @Override
    protected void addActionListeners() {
        
        this.pricesBoard.getUpdatePrice().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        
        Object eventSource = event.getSource();
        if( this.isUpdatingPrice( eventSource ) ){
            this.updateProductPrice();
        }
        
    }
    
    private boolean isUpdatingPrice(Object input_eventSource){
        
        return input_eventSource == this.pricesBoard.getUpdatePrice();
    }

    private void updateProductPrice(){
        
        try {
            String input_productName = this.getSelectedProduct();
            double input_productPrice = this.getSelectedPrice();
            PricesDAO.getPricesDAO().updateProductPice( input_productName, input_productPrice );
            
        } catch (SQLException ex) {
            this.tellErrorMessagerToShowMessage( ErrorMessager.UPDATE_PRICES_ERROR );
        }
    }
    
    private String getSelectedProduct(){
        
        return (String)this.pricesBoard.getProductsList().getSelectedItem();
    }
    
    private Double getSelectedPrice(){
        
        return (Double)this.pricesBoard.getNewProductPrice().getValue();
    }
    
    private void tellErrorMessagerToShowMessage( String input_ErrorMessage ){
        
        ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
        errorMessager.showErrorMessage( input_ErrorMessage );
    }
}
