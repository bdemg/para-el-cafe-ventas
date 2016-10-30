/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.OrdersDAO;
import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import model.SalesCalculator;
import model.ErrorMessager;
import model.OrdersList;
import model.ProductsList;
import model.SalesManager;
import model.Time;
import view.SalesSheet;

/**
 *
 * @author Jorge A. Cano
 */
public final class BakeryPhoneOperator extends Controller {

    private final SalesSheet salesSheet;
    
    private final String EMPTY = "";
    private final String REMOVE_PRODUCT_QUESTION = "¿Que parte de la orden desea eliminar?";
    
    private final int STEP_BY = 1;
    private final int INITIAL_PRODUCT_QUANTITY = 1;
    private final int NAME = 1;
    private final int ADDRESS = 2;
    private final int CANCEL_ORDER = -1;
    
    
    
    public BakeryPhoneOperator() {
        this.salesSheet = new SalesSheet();
        this.salesSheet.setVisible(true);
        this.salesSheet.setLocationRelativeTo(null);

        this.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent input_event) {
        
        Object eventSource = input_event.getSource();

        if (this.isSearchingForClient(eventSource)) {
            this.searchRegisteredClient();

        } else if (this.isAddingProductToOrder(eventSource)) {
            this.addProductToOrder();
            
        } else if (this.isRemovingProductFromOrder(eventSource)) {
            this.removeProductFromOrder();
            
        } else if(this.isCalculatingSales(eventSource)){
            this.calculateSale();
            
        } else if(this.isStoringOrder(eventSource)){
            this.storeSale();
        }
    }
    
    
    private boolean isSearchingForClient(Object input_eventSource){
        return input_eventSource == this.salesSheet.getClientSearchButton();
    }
    
    
    private boolean isAddingProductToOrder(Object input_eventSource){
        return input_eventSource == this.salesSheet.getAddProductButton();
    }
    
    
    private boolean isRemovingProductFromOrder(Object input_eventSource){
        return input_eventSource == this.salesSheet.getRemoveProductButton();
    }
    
    
    private boolean isCalculatingSales(Object input_eventSource){
        return input_eventSource == this.salesSheet.getCalculateSaleButton();
    }
    
    
    private boolean isStoringOrder(Object input_eventSource){
        return input_eventSource ==  this.salesSheet.getStoreOrderButton();
    }
    
    
    @Override
    protected void addActionListeners() {

        this.salesSheet.getCalculateSaleButton().addActionListener(this);
        this.salesSheet.getRemoveProductButton().addActionListener(this);
        this.salesSheet.getAddProductButton().addActionListener(this);
        this.salesSheet.getStoreOrderButton().addActionListener(this);
        this.salesSheet.getClientSearchButton().addActionListener(this);
    }
    
    //Considerar moverlo a algo como ClientManager
    private void searchRegisteredClient() {
        
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
            Logger.getLogger(BakeryPhoneOperator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    //Escribe la información del cliente (nombre y dirección) en la ventana
    private void writeClientInfo(String[] input_clientInfo) {
        
        String clientName = input_clientInfo[ this.NAME ];
        this.salesSheet.getClientName().setText(clientName);
        
        String clientAddress = input_clientInfo[ this.ADDRESS ];
        this.salesSheet.getClientAddress().setText(clientAddress);
    }
    
    
    
    private void addProductToOrder() {
       
        this.salesSheet.saveChangesInOrdersTable();
        
        Object[] newOrder = {null, new ProductsList(),
            this.INITIAL_PRODUCT_QUANTITY, 0.0};
        OrdersList orders = this.salesSheet.getOrdersList();
        orders.addRow(newOrder);
    }
    
    
    private void removeProductFromOrder() {
        
        this.salesSheet.saveChangesInOrdersTable();
       
        int productToRemove = askForProductToRemove();
        
        boolean isCanceled = productToRemove == this.CANCEL_ORDER;    
        if(!isCanceled){
            this.removeProduct(productToRemove);
        }
    }
    
    
    private void removeProduct(int input_productToRemove) {
        
        OrdersList orders = this.salesSheet.getOrdersList();
        orders.removeRow((input_productToRemove - 1));

    }
    
    //cambiarlo para que interactue con SalesManager
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
        
        SalesManager.callSalesManager().storeSale(
            currentOrders,
            phoneNumber,
            DueDay,
            DueMonth,
            DueYear,
            DueHour,
            DueMinute
        );
        
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
    
    
    //Enables or disables all UI components necessary for taking an order
    private void readyForTakingOrders(boolean input_isReady){
        
        //Enable or disable the table where individual orders are taken
        this.salesSheet.getOrdersTable().setEnabled(input_isReady);
        
        //Enable or disable all the buttons related with taking an order
        this.salesSheet.getAddProductButton().setEnabled(input_isReady);
        this.salesSheet.getRemoveProductButton().setEnabled(input_isReady);
        this.salesSheet.getStoreOrderButton().setEnabled(input_isReady);
        this.salesSheet.getCalculateSaleButton().setEnabled(input_isReady);
        
        //Enable or disable the spinner fields used to enter a due date for an order
        this.salesSheet.getDueDay().setEnabled(input_isReady);
        this.salesSheet.getDueHour().setEnabled(input_isReady);
        this.salesSheet.getDueMinute().setEnabled(input_isReady);
        this.salesSheet.getDueMonth().setEnabled(input_isReady);
        this.salesSheet.getDueYear().setEnabled(input_isReady);
        
        this.resetDueDate();
        
    }//End of readyForTakingOrders(boolean isReady)

    
    private void lockClientPhonenumber(boolean input_isLocked) {
        
        this.salesSheet.getClientPhoneNumber().setEnabled( !input_isLocked );
        this.salesSheet.getClientSearchButton().setEnabled( !input_isLocked );
    }
    
    
    private void calculateSale() {
        
        this.salesSheet.saveChangesInOrdersTable();
        
        SalesCalculator salesCalculator = SalesCalculator.getCalculator();
        salesCalculator.calculatePartialCosts( this.salesSheet.getOrdersList() );
        
        double saleTotal = salesCalculator.saleTotal( this.salesSheet.getOrdersList() );
        this.salesSheet.getTotalSale().setText( String.valueOf( saleTotal ) );
    }

    
    private int askForProductToRemove() {
        
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
        
        boolean IsCanceled = productToRemove == null;
        
        if( IsCanceled ){
            
            return (int) productToRemove;
        } else{
            
            return this.CANCEL_ORDER;
        }
    }
    
    
    private void resetDueDate() {
        
        Calendar today = Calendar.getInstance();
        
        this.salesSheet.getDueDay().setModel(new SpinnerNumberModel(
                today.get( Calendar.DAY_OF_MONTH ),
                Time.FIRST_DAY,
                Time.MAX_DAYS_IN_MONTH,
                this.STEP_BY)
        );
        
        this.salesSheet.getDueMonth().setModel(new SpinnerNumberModel( 
                (today.get( Calendar.MONTH ) + 1),
                Time.FIRST_MONTH,
                Time.MONTHS_IN_YEAR,
                this.STEP_BY)
        );
        
        this.salesSheet.getDueYear().setModel(new SpinnerNumberModel( 
                today.get( Calendar.YEAR ),
                today.get( Calendar.YEAR ),
                ( today.get(Calendar.YEAR) + 1 ),
                this.STEP_BY)
        );
        
        this.salesSheet.getDueHour().setModel(new SpinnerNumberModel( 
                today.get(Calendar.HOUR_OF_DAY),
                Time.FIRST_HOUR_IN_DAY,
                Time.LAST_HOUR_IN_DAY,
                this.STEP_BY)
        );
        
        this.salesSheet.getDueMinute().setModel(new SpinnerNumberModel( 
                today.get(Calendar.MINUTE),
                Time.FIRST_MINUTE_IN_HOUR,
                Time.LAST_MINUTE_IN_HOUR,
                this.STEP_BY)
        );
    }
    

}
