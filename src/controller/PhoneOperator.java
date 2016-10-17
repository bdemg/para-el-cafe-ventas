/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Calculator;
import model.ErrorMessager;
import model.OrdersTaker;
import model.ProductsList;
import view.SalesSheet;

/**
 *
 * @author Jorge A. Cano
 */
public final class PhoneOperator extends Controller {

    private final SalesSheet salesSheet;
    
    private final String EMPTY = "";
    private final String REMOVE_PRODUCT_QUESTION = "¿Que parte de la orden desea eliminar?";
    
    private final int NAME = 1;
    private final int ADDRESS = 2;
    private final int INITIAL_PRODUCT_QUANTITY = 1;
    
    
    
    public PhoneOperator() {
        this.salesSheet = new SalesSheet();
        this.salesSheet.setVisible(true);
        this.salesSheet.setLocationRelativeTo(null);

        this.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent inputEvent) {
        
        Object eventSource = inputEvent.getSource();

        if (eventSource == this.salesSheet.getClientSearchButton()) {
            this.searchClient();

        } else if (eventSource == this.salesSheet.getAddProductButton()) {
            this.addProductToOrder();
            
        } else if (eventSource == this.salesSheet.getRemoveProductButton()) {
            this.removeProductFromOrder();
            
        } else if(eventSource == this.salesSheet.getCalculateSaleButton()){
            this.calculateSale();
            
        } else if(eventSource ==  this.salesSheet.getStoreOrderButton()){
            this.storeSale();
        }
    }

    
    @Override
    protected void addActionListeners() {

        this.salesSheet.getCalculateSaleButton().addActionListener(this);
        this.salesSheet.getRemoveProductButton().addActionListener(this);
        this.salesSheet.getAddProductButton().addActionListener(this);
        this.salesSheet.getStoreOrderButton().addActionListener(this);
        this.salesSheet.getClientSearchButton().addActionListener(this);
    }

    private void searchClient() {
        
        String clientPhonenumber = this.salesSheet.getClientPhoneNumber().getText();
        
        try {
            //boolean isFound = false;
            //String[] clientInfo = ClientsDAO.callErrorMessager().searchClientPhonenumber(clientPhonenumber, isFound);
            if(true){
                
                //this.writeClientInfo(clientInfo);
                this.lockClientPhonenumber(true);
                this.readyForTakingOrders(true);
            } else{
                
                ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
                errorMessager.showErrorMessage(ErrorMessager.CLIENT_NOT_FOUND);
            }
        } catch (Exception ex) {
            Logger.getLogger(PhoneOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void writeClientInfo(String[] inputClientInfo) {
        
        String clientName = inputClientInfo[ this.NAME ];
        this.salesSheet.getClientName().setText(clientName);
        
        String clientAddress = inputClientInfo[ this.ADDRESS ];
        this.salesSheet.getClientAddress().setText(clientAddress);
    }
    
    
    public void addProductToOrder() {
       
        this.salesSheet.saveChangesInOrdersTable();
        
        Object[] newOrder = {null, new ProductsList(),
            this.INITIAL_PRODUCT_QUANTITY, 0.0};
        OrdersTaker orders = this.salesSheet.getOrdersTaker();
        orders.addRow(newOrder);
    }
    
    
    private void removeProductFromOrder() {
        
        this.salesSheet.saveChangesInOrdersTable();
        
        //cambiarlo a inputDialog con selectionValues 
        boolean isCanceled = false;
        int productToRemove = askForProductToRemove( isCanceled );
        
            
        if(!isCanceled){
            this.removeProduct(productToRemove);
        }
    }
    
    
    public void removeProduct(int inputProductToRemove) {
        
        OrdersTaker orders = this.salesSheet.getOrdersTaker();
        orders.removeRow((inputProductToRemove - 1));

    }
    

    private void storeSale() {
        
        this.salesSheet.saveChangesInOrdersTable();
        
        this.calculateSale();
        
        //guarda la venta por medio del DAO
        
        this.cleanSaleSheet();
        this.lockClientPhonenumber(false);
        this.readyForTakingOrders(false);
    }

    
    private void cleanSaleSheet() {
        
        this.salesSheet.cleanOrdersTaker();
        this.salesSheet.getClientName().setText( this.EMPTY );
        this.salesSheet.getClientAddress().setText( this.EMPTY );
        this.salesSheet.getClientPhoneNumber().setText( this.EMPTY );
        this.salesSheet.getTotalSale().setText( this.EMPTY );
    }
    
    
    public void readyForTakingOrders(boolean isReady){
        
        this.salesSheet.getOrdersTable().setEnabled(isReady);
        
        this.salesSheet.getAddProductButton().setEnabled(isReady);
        this.salesSheet.getRemoveProductButton().setEnabled(isReady);
        this.salesSheet.getStoreOrderButton().setEnabled(isReady);
        this.salesSheet.getCalculateSaleButton().setEnabled(isReady);
        
        this.salesSheet.getDueDay().setEnabled(isReady);
        this.salesSheet.getDueHour().setEnabled(isReady);
        this.salesSheet.getDueMinute().setEnabled(isReady);
        this.salesSheet.getDueMonth().setEnabled(isReady);
        this.salesSheet.getDueYear().setEnabled(isReady);
        
        this.salesSheet.resetDueDate();
    }

    
    private void lockClientPhonenumber(boolean inputIsLocked) {
        
        this.salesSheet.getClientPhoneNumber().setEnabled( !inputIsLocked );
        this.salesSheet.getClientSearchButton().setEnabled( !inputIsLocked );
    }

    private void calculateSale() {
        
        this.salesSheet.saveChangesInOrdersTable();
        
        Calculator calculator = Calculator.getCalculator();
        calculator.calculatePartialCosts( this.salesSheet.getOrdersTaker() );
        double saleTotal = calculator.saleTotal( this.salesSheet.getOrdersTaker() );
        
        this.salesSheet.getTotalSale().setText( String.valueOf( saleTotal ) );
    }

    private int askForProductToRemove(boolean outputIsCanceled) {
        
        OrdersTaker ordersTaker = this.salesSheet.getOrdersTaker();
        Object[] productNumbers = new Object[ ordersTaker.getRowCount() ];
        
        for( int productCount = 0; productCount < ordersTaker.getRowCount(); productCount++ ){
            productNumbers[ productCount ] = productCount + 1;
        }
        
        Object productToRemove = JOptionPane.showInputDialog(this.salesSheet,
            this.REMOVE_PRODUCT_QUESTION, null, JOptionPane.QUESTION_MESSAGE,
            null, productNumbers, 1);
        
        outputIsCanceled = productToRemove == null;
        
        if( !outputIsCanceled ){
            
            return (int) productToRemove;
        } else{
            
            return 0;
        }
    }

}
