/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import daos.ClientsDAO;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import model.Client;
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
 *This class represents the phone operator that takes orders from the bakery's clients
 * @author Jorge A. Cano
 */
public final class BakeryPhoneSalesman extends Controller {

    private final SalesSheet salesSheet;
    

    private final String REMOVE_PRODUCT_QUESTION = "¿Que parte de la orden desea eliminar?";
    private final String CONFIRM_SALE_MESSAGE = "¿Está seguro que desea guardar la orden?";
    private final String CONFIRM_SALE_CANCEL_MESSAGE = "¿Está seguro que desea cancelar la orden?";
    
    private final int INITIAL_PRODUCT_QUANTITY = 1;
    private final int CLIENT_NAME = 0;
    private final int CLIENT_ADDRESS = 2;
    private final int CLIENT_LOCATION_REFERENCES = 3;
    
    
    public BakeryPhoneSalesman() {
        
        this.salesSheet = new SalesSheet();
        
        StoreSupervisor storeManager = new StoreSupervisor(this.salesSheet.getServiceWindow());
        
        this.addActionListeners();
    }
    
    @Override
    protected void addActionListeners() {

        this.salesSheet.getCalculateSale().addActionListener( this );
        this.salesSheet.getRemoveProduct().addActionListener( this );
        this.salesSheet.getAddProduct().addActionListener( this );
        this.salesSheet.getStoreOrder().addActionListener( this );
        this.salesSheet.getClientSearch().addActionListener( this );
        this.salesSheet.getCancelOrder().addActionListener( this );
    }

    
    @Override
    public void actionPerformed( ActionEvent input_event ) {
        
        Object eventSource = input_event.getSource();

        if ( this.isSearchingForClient( eventSource ) ) {
            this.tendToClientPhonecall();

        } else if ( this.isAddingProductToOrder( eventSource ) ) {
            this.addProductToOrder();
            
        } else if ( this.isRemovingProductFromOrder( eventSource ) ) {
            this.removeProduct();
            
        } else if( this.isCalculatingSales( eventSource ) ){
            this.writeSalePricesInSaleSheet();
            
        } else if( this.isStoringOrder( eventSource ) ){
            
            if( this.askForConfirmation( this.CONFIRM_SALE_MESSAGE ) ){
                this.tellSalesManegerToStoreSale();
            }
            
        } else if( this.isCancelingOrder( eventSource ) ){
            
            if( this.askForConfirmation( this.CONFIRM_SALE_CANCEL_MESSAGE ) ){
                this.prepareForNextClient();
            }
        }        
    }
    
    
    private boolean isSearchingForClient( Object input_eventSource ){
        return input_eventSource == this.salesSheet.getClientSearch();
    }
    
    
    private boolean isAddingProductToOrder( Object input_eventSource ){
        return input_eventSource == this.salesSheet.getAddProduct();
    }
    
    
    private boolean isRemovingProductFromOrder( Object input_eventSource ){
        return input_eventSource == this.salesSheet.getRemoveProduct();
    }
    
    
    private boolean isCalculatingSales( Object input_eventSource ){
        return input_eventSource == this.salesSheet.getCalculateSale();
    }
    
    
    private boolean isStoringOrder( Object input_eventSource ){
        return input_eventSource ==  this.salesSheet.getStoreOrder();
    }
    
    
    private boolean isCancelingOrder( Object input_eventSource ){
        return input_eventSource == this.salesSheet.getCancelOrder();
    }
    
    
    //ask for confirmation before doing a critical action like saving or canceling an
    //order
    private boolean askForConfirmation( String input_confirmationMessage ){
        
        int answer = JOptionPane.showConfirmDialog(
            this.salesSheet, 
            input_confirmationMessage, 
            null, 
            JOptionPane.YES_NO_OPTION, 
            JOptionPane.QUESTION_MESSAGE
        );
        
        return answer == JOptionPane.YES_OPTION;
    }
    
    
    //obtain the information of the client if he is registered. If not, notify that the client
    //is not registered
    private void tendToClientPhonecall() {
        
        String clientPhonenumber = this.salesSheet.getClientPhoneNumber().getText();
            
        try {
            
            Client client = this.requestClientInfo( clientPhonenumber );
            boolean isFound = client != null;
            
            if( isFound ){
                
                this.writeClientInfo( client );
                this.readyForTakingOrders( true );
            } else{
                
                this.tellErrorMessagerToShowMessage( ErrorMessager.CLIENT_NOT_FOUND );
            }
        } catch ( SQLException ex ) {
            
            this.tellErrorMessagerToShowMessage( ErrorMessager.DATABASE_ERROR );
        }
    }
    
    
    private Client requestClientInfo( String input_clientPhonenumber ) throws SQLException{
        return ClientManager.searchForClientInfo( input_clientPhonenumber );
    }
    
    //write down the client information
    private void writeClientInfo( Client input_clientInfo ) {
        
        this.salesSheet.getClientName().setText( input_clientInfo.getName() );
        this.salesSheet.getClientAddress().setText( input_clientInfo.getLocation().getAddress() );
        this.salesSheet.getReferences().setText( input_clientInfo.getLocation().getReferences() );
    }
    
    
    //make sure that no more changer are being made to the orders list
    private void confirmChangesInOrdersList(){
        
        if( this.salesSheet.getOrdersTable().isEditing() ){
            this.salesSheet.getOrdersTable().getCellEditor().stopCellEditing();
        }
    }
    
    
    //add a new product to the order list
    private void addProductToOrder() {
       
        this.confirmChangesInOrdersList();
        
        Object[] newOrder = { null, new ProductsList(),
            this.INITIAL_PRODUCT_QUANTITY, 0.0 };
        this.salesSheet.getOrdersList().addRow( newOrder );
        
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
    private void removeProductFromOrder( int input_productToRemove ) {
        
        OrdersList orders = this.salesSheet.getOrdersList();
        orders.removeRow( ( input_productToRemove - 1 ) );

    }
    
    
    //give the store manager all the information about the sale so that he can
    //save it in the archives
    private void tellSalesManegerToStoreSale() {
        
        try {
            this.confirmChangesInOrdersList();
            
            this.writeSalePricesInSaleSheet();
            
            SalesManager.callSalesManager().storeSale(
                    this.salesSheet.getOrdersList(),
                    this.salesSheet.getClientPhoneNumber().getText(),
                    (int) this.salesSheet.getDueDay().getValue(),
                    (int) this.salesSheet.getDueMonth().getValue(),
                    (int) this.salesSheet.getDueYear().getValue(),
                    (int) this.salesSheet.getDueHour().getValue(),
                    (int) this.salesSheet.getDueMinute().getValue()
            );
            
            this.prepareForNextClient();
        } catch ( SQLException ex ) {
            
            this.tellErrorMessagerToShowMessage( ErrorMessager.DATABASE_ERROR );
        }
    }
    
    
    //get ready for the next client by cleaning the sheet used to take the orders
    private void prepareForNextClient(){
        
        this.cleanSaleSheet();
        this.readyForTakingOrders( false );
    }
    
    
    //write down the total and parcial prices calculated by the accountant
    private void writeSalePricesInSaleSheet() {
        
        try {
            this.confirmChangesInOrdersList();
            
            SalesAccountant salesAccountant = SalesAccountant.getSalesAccountant();
            salesAccountant.calculatePartialCosts( this.salesSheet.getOrdersList() );
            
            double saleTotal = salesAccountant.
                totalPriceOfSale( this.salesSheet.getOrdersList() );
            this.salesSheet.setTotalSale( saleTotal );
            
        } catch (SQLException ex) {
            
            this.tellErrorMessagerToShowMessage( ErrorMessager.DATABASE_ERROR );
        }
    }

    
    
    private void cleanSaleSheet() {
        
        this.salesSheet.setOrdersList( new OrdersList( 0 ) );
        this.salesSheet.setTotalSale( 0.0 );
        this.salesSheet.getClientName().setText( Keywords.EMPTY );
        this.salesSheet.getClientAddress().setText( Keywords.EMPTY );
        this.salesSheet.getClientPhoneNumber().setText( Keywords.EMPTY );
        this.salesSheet.getReferences().setText( Keywords.EMPTY );
    }
    
    
    //enables or disables all UI components necessary for taking an order
    private void readyForTakingOrders( boolean input_isReady ){
        
        //enable or disable the table where individual orders are taken
        this.salesSheet.getOrdersTable().setEnabled( input_isReady );
        
        //enable or disable all the buttons related with taking an order
        this.salesSheet.getAddProduct().setEnabled( input_isReady );
        this.salesSheet.getRemoveProduct().setEnabled( input_isReady );
        this.salesSheet.getStoreOrder().setEnabled( input_isReady );
        this.salesSheet.getCalculateSale().setEnabled( input_isReady );
        this.salesSheet.getCancelOrder().setEnabled( input_isReady );
        
        //enable or disable the spinner fields used to enter a due date for an order
        this.salesSheet.getDueDay().setEnabled( input_isReady );
        this.salesSheet.getDueHour().setEnabled( input_isReady );
        this.salesSheet.getDueMinute().setEnabled( input_isReady );
        this.salesSheet.getDueMonth().setEnabled( input_isReady );
        this.salesSheet.getDueYear().setEnabled( input_isReady );
        
        this.rejectChangesInClientPhonenumber( input_isReady );
        
        this.setDefaultDueDate();
    }

    
    //once the client has been found in the archives, the phonenumber cannot be changed
    //until the sales is canceled or stored
    private void rejectChangesInClientPhonenumber( boolean input_isLocked ) {
        
        this.salesSheet.getClientPhoneNumber().setEnabled( !input_isLocked );
        this.salesSheet.getClientSearch().setEnabled( !input_isLocked );
    }
    
    
    //put new default templates for taking the due date in the sales sheet
    private void setDefaultDueDate() {
        
        this.salesSheet.getDueDay().setModel( new DueDayTemplate() );
        this.salesSheet.getDueMonth().setModel( new DueMonthTemplate() );
        this.salesSheet.getDueYear().setModel( new DueYearTemplate() );
        this.salesSheet.getDueHour().setModel( new DueHourTemplate() );
        this.salesSheet.getDueMinute().setModel( new DueMinuteTemplate() );
    }

    private void tellErrorMessagerToShowMessage( String input_ErrorMessage ){
        
        ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
        errorMessager.showErrorMessage( input_ErrorMessage );
    }
}
