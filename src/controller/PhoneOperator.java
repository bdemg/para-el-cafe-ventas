/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.OrdersDAO;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.SalesCalculator;
import model.ErrorMessager;
import model.OrdersList;
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
        OrdersList orders = this.salesSheet.getOrdersList();
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
        
        OrdersList orders = this.salesSheet.getOrdersList();
        orders.removeRow((inputProductToRemove - 1));

    }
    

    private void storeSale() {
        
        this.salesSheet.saveChangesInOrdersTable();
        
        this.calculateSale();
        
        String phoneNumber = this.salesSheet.getClientPhoneNumber().getText();
        
        int DueDay = (int) this.salesSheet.getDueDay().getValue();
        int DueMonth = (int) this.salesSheet.getDueMonth().getValue();
        int DueYear = (int) this.salesSheet.getDueYear().getValue();
        int DueHour = (int) this.salesSheet.getDueHour().getValue();
        int DueMinute = (int) this.salesSheet.getDueMinute().getValue();
        
        OrdersList currentOrders = this.salesSheet.getOrdersList();
        
        for( int ordersCount = 0; ordersCount < currentOrders.getRowCount(); ordersCount++ ){
            
            ProductsList productList = (ProductsList) currentOrders.getValueAt(ordersCount,
                OrdersList.PRODUCT_NAME );
            String productName = (String) productList.getSelectedItem();
            
            int productQuantity = (int) currentOrders.getValueAt(ordersCount,
                    OrdersList.PRODUCT_QUANTITY );
            
            double productPrice = (double) currentOrders.getValueAt(ordersCount,
                    OrdersList.PRODUCT_PRICE);
            
            try {
                
                OrdersDAO.getOrdersDAO().saveSale(
                        phoneNumber,
                        productName,
                        productQuantity,
                        productPrice,
                        DueDay,
                        DueMonth,
                        DueYear,
                        DueHour,
                        DueMinute);
                
            } catch (Exception ex) {
                Logger.getLogger(PhoneOperator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.cleanSaleSheet();
        this.lockClientPhonenumber(false);
        this.readyForTakingOrders(false);
    }

    
    private void cleanSaleSheet() {
        
        this.salesSheet.cleanOrdersList();
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
        
        SalesCalculator salesCalculator = SalesCalculator.getCalculator();
        salesCalculator.calculatePartialCosts( this.salesSheet.getOrdersList() );
        
        double saleTotal = salesCalculator.saleTotal( this.salesSheet.getOrdersList() );
        this.salesSheet.getTotalSale().setText( String.valueOf( saleTotal ) );
    }

    private int askForProductToRemove(boolean outputIsCanceled) {
        
        OrdersList ordersList = this.salesSheet.getOrdersList();
        Object[] productNumbers = ordersList.getProductNumberList();
        
        Object productToRemove = JOptionPane.showInputDialog(
            this.salesSheet,
            this.REMOVE_PRODUCT_QUESTION,
            null,
            JOptionPane.QUESTION_MESSAGE,
            null,
            productNumbers,
            1
        );
        
        outputIsCanceled = productToRemove == null;
        
        if( !outputIsCanceled ){
            
            return (int) productToRemove;
        } else{
            
            return 0;
        }
    }

}
