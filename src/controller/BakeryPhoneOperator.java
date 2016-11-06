/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.awt.event.ActionEvent;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;

import daos.OrdersDAO;
import model.SalesAccountant;
import model.ErrorMessager;
import model.Keywords;
import model.OrdersList;
import model.ProductsList;
import model.SalesManager;
import model.duedatemodels.DueDayTemplate;
import model.duedatemodels.DueHourTemplate;
import model.duedatemodels.DueMinuteTemplate;
import model.duedatemodels.DueMonthTemplate;
import model.duedatemodels.DueYearTemplate;
import view.SalesSheet;

/**
 *
 * @author Jorge A. Cano
 */
public final class BakeryPhoneOperator extends Controller {

    private final SalesSheet salesSheet;
    

    private final String REMOVE_PRODUCT_QUESTION = "¿Que parte de la orden desea eliminar?";
    
    private final int STEP_BY = 1;
    private final int INITIAL_PRODUCT_QUANTITY = 1;
    private final int NAME = 1;
    private final int ADDRESS = 2;    
    
    
    public BakeryPhoneOperator() {
        this.salesSheet = new SalesSheet();
        this.salesSheet.setVisible(true);
        this.salesSheet.setLocationRelativeTo(null);

        this.addActionListeners();
    }
    
    
    @Override
    protected void addActionListeners() {

        this.salesSheet.getCalculateSale().addActionListener(this);
        this.salesSheet.getRemoveProduct().addActionListener(this);
        this.salesSheet.getAddProduct().addActionListener(this);
        this.salesSheet.getStoreOrder().addActionListener(this);
        this.salesSheet.getClientSearch().addActionListener(this);
        this.salesSheet.getCancelOrder().addActionListener(this);
    }

    
    @Override
    public void actionPerformed(ActionEvent input_event) {
        
        Object eventSource = input_event.getSource();

        if (this.isSearchingForClient(eventSource)) {
            this.searchRegisteredClient();

        } else if (this.isAddingProductToOrder(eventSource)) {
            this.addProductToOrder();
            
        } else if (this.isRemovingProductFromOrder(eventSource)) {
            this.removeProduct();
            
        } else if(this.isCalculatingSales(eventSource)){
            this.writeSalePricesInSaleSheet();
            
        } else if(this.isStoringOrder(eventSource)){
            this.tellStoreManegerToStoreSale();
            
        } else if(this.isCancelingOrder(eventSource)){
            this.prepareForNextClient();
        }
    }
    
    
    private boolean isSearchingForClient(Object input_eventSource){
        return input_eventSource == this.salesSheet.getClientSearch();
    }
    
    
    private boolean isAddingProductToOrder(Object input_eventSource){
        return input_eventSource == this.salesSheet.getAddProduct();
    }
    
    
    private boolean isRemovingProductFromOrder(Object input_eventSource){
        return input_eventSource == this.salesSheet.getRemoveProduct();
    }
    
    
    private boolean isCalculatingSales(Object input_eventSource){
        return input_eventSource == this.salesSheet.getCalculateSale();
    }
    
    
    private boolean isStoringOrder(Object input_eventSource){
        return input_eventSource ==  this.salesSheet.getStoreOrder();
    }
    
    
    private boolean isCancelingOrder(Object input_eventSource){
        return input_eventSource == this.salesSheet.getCancelOrder();
    }
    
    
    //Considerar moverlo a algo como ClientManager
    private void searchRegisteredClient() {
        
        String clientPhonenumber = this.salesSheet.getClientPhoneNumber().getText();
        
        try {
            //boolean isFound = false;
            //String[] clientInfo = ClientsDAO.callErrorMessager().searchClientPhonenumber(clientPhonenumber, isFound);
            if(true){
                
                //this.writeClientInfo(clientInfo);
                this.rejectChangesInClientPhonenumber(true);
                this.readyForTakingOrders(true);
                this.setDefaultDueDate();
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
    
    
    private void confirmChangesInOrdersList(){
        if( this.salesSheet.getOrdersTable().isEditing() ){
            this.salesSheet.getOrdersTable().getCellEditor().stopCellEditing();
        }
    }
    
    
    private void addProductToOrder() {
       
        this.confirmChangesInOrdersList();
        
        Object[] newOrder = {null, new ProductsList(),
            this.INITIAL_PRODUCT_QUANTITY, 0.0};
        OrdersList orders = this.salesSheet.getOrdersList();
        orders.addRow(newOrder);
    }
    
    
    //remove a product from the order or cancel the removal if the client wants to 
    private void removeProduct() {
        
        this.confirmChangesInOrdersList();
       
        int productToRemove = askForProductToRemove();
        
        boolean isRemovalCanceled = productToRemove == Keywords.CANCEL_REMOVAL;   
        
        //a product is removed only if the removal was not canceled
        if( !isRemovalCanceled ){
            this.removeProductFromOrder( productToRemove );
        }
    }
    
    
    //ask and obtain the product that the client wants to remove from the order 
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
        
        boolean IsRemovalCanceled = productToRemove == null;
        
        //if the removal was not canceled, give the number of the product to remove
        if( !IsRemovalCanceled ){
            
            return (int) productToRemove;
        
        //...if the removal was canceled, it's cancelling is notified instead
        } else{
            
            return Keywords.CANCEL_REMOVAL;
        }
    }
    
    
    //remove the indicated product from the order
    private void removeProductFromOrder(int input_productToRemove) {
        
        OrdersList orders = this.salesSheet.getOrdersList();
        orders.removeRow((input_productToRemove - 1));

    }
    
    
    private void tellStoreManegerToStoreSale() {
        
        this.confirmChangesInOrdersList();
        /*
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
        */
        this.prepareForNextClient();
    }
    
    
    private void prepareForNextClient(){
        this.cleanSaleSheet();
        this.rejectChangesInClientPhonenumber(false);
        this.readyForTakingOrders(false);
        this.setDefaultDueDate();
    }
    
    private void writeSalePricesInSaleSheet() {
        
        this.confirmChangesInOrdersList();
        
        SalesAccountant salesAccountant = SalesAccountant.getSalesAccountant();
        salesAccountant.calculatePartialCosts( this.salesSheet.getOrdersList() );
        
        double saleTotal = salesAccountant.totalPriceOfSale( this.salesSheet.getOrdersList() );
        this.salesSheet.getTotalSale().setText( String.valueOf( saleTotal ) );
    }

    
    private void cleanSaleSheet() {
        
        this.salesSheet.setOrdersList(new OrdersList(0));
        this.salesSheet.getClientName().setText( Keywords.EMPTY );
        this.salesSheet.getClientAddress().setText( Keywords.EMPTY );
        this.salesSheet.getClientPhoneNumber().setText( Keywords.EMPTY );
        this.salesSheet.getTotalSale().setText( Keywords.EMPTY );
    }
    
    
    //Enables or disables all UI components necessary for taking an order
    private void readyForTakingOrders( boolean input_isReady ){
        
        //Enable or disable the table where individual orders are taken
        this.salesSheet.getOrdersTable().setEnabled( input_isReady );
        
        //Enable or disable all the buttons related with taking an order
        this.salesSheet.getAddProduct().setEnabled( input_isReady );
        this.salesSheet.getRemoveProduct().setEnabled( input_isReady );
        this.salesSheet.getStoreOrder().setEnabled( input_isReady );
        this.salesSheet.getCalculateSale().setEnabled( input_isReady );
        this.salesSheet.getCancelOrder().setEnabled(input_isReady);
        
        //Enable or disable the spinner fields used to enter a due date for an order
        this.salesSheet.getDueDay().setEnabled( input_isReady );
        this.salesSheet.getDueHour().setEnabled( input_isReady );
        this.salesSheet.getDueMinute().setEnabled( input_isReady );
        this.salesSheet.getDueMonth().setEnabled( input_isReady );
        this.salesSheet.getDueYear().setEnabled( input_isReady );
        
    }//End of readyForTakingOrders(boolean isReady)

    
    private void rejectChangesInClientPhonenumber(boolean input_isLocked) {
        
        this.salesSheet.getClientPhoneNumber().setEnabled( !input_isLocked );
        this.salesSheet.getClientSearch().setEnabled( !input_isLocked );
    }
    
    
    private void setDefaultDueDate() {
        
        this.salesSheet.getDueDay().setModel( new DueDayTemplate() );
        this.salesSheet.getDueMonth().setModel( new DueMonthTemplate() );
        this.salesSheet.getDueYear().setModel( new DueYearTemplate() );
        this.salesSheet.getDueHour().setModel( new DueHourTemplate() );
        this.salesSheet.getDueMinute().setModel( new DueMinuteTemplate() );
    }
    

}
