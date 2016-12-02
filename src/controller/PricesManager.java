package controller;

import daos.PricesDAO;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import model.ConfirmationMessager;
import model.ErrorMessager;

import view.PricesBoard;

/**
 * This class manages the prices of the products.
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
            this.validateProductPrice();
        }
        
    }
    
    private boolean isUpdatingPrice(Object input_eventSource){
        
        return input_eventSource == this.pricesBoard.getUpdatePrice();
    }
    
    // Asks if is Ok to update the price of a product.
    private void validateProductPrice(){
        
        boolean isOkToUpdate = 
                ConfirmationMessager.callConfirmationMessager().
                        askForConfirmation(
                                ConfirmationMessager.CONFIRM_PRICE_UPDATE
                        );
        if( isOkToUpdate ){
            this.updateProductPrice();
        }
    }
    
    // Replaces the old product price with the new product price.
    private void updateProductPrice(){
        
        try {
            String input_productName = this.getSelectedProduct();
            double input_productPrice = this.getSelectedPrice();
            PricesDAO.getPricesDAO().updateProductPrice( input_productName, input_productPrice );
            
        } catch (SQLException ex) {
            this.tellErrorMessagerToShowMessage( ErrorMessager.DATABASE_ERROR );
        }
    }
    
    // Gets the user's product.
    private String getSelectedProduct(){
        
        return (String)this.pricesBoard.getProductsList().getSelectedItem();
    }
    
    // Gets the user's new price.
    private Double getSelectedPrice(){
        
        return (Double)this.pricesBoard.getNewProductPrice().getValue();
    }
    
    // Shows an error message to the user.
    private void tellErrorMessagerToShowMessage( String input_ErrorMessage ){
        
        ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
        errorMessager.showErrorMessage( input_ErrorMessage );
    }
}
