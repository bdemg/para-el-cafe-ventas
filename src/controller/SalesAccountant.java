/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.PricesDAO;
import daos.ReportDAO;
import daos.SalesDAO;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.Object;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import jxl.write.WriteException;
import model.ConfirmationMessager;
import model.ErrorMessager;
import model.OrdersList;
import model.ProductsList;
import model.SalesReceipt;
import view.ReportForm;
/**
 *
 * @author Evan-Ian-Ray
 */
public class SalesAccountant extends Controller{
    
    private final int FOLIO_COLUMN = 0;
    private final int CLIENT_PHONENUMBER_COLUMN = 1;
    private final int PRODUCT_NAME_COLUMN = 2;
    private final int QUANTITY_COLUMN = 3;
    private final int SUBTOTAL_COLUMN = 4;
    private final int DATE_COLUMN = 5;
    
    private final ReportForm reportForm;
    
    
    public SalesAccountant(){
        
        this.reportForm = new ReportForm();
        this.addActionListeners();
    }

    @Override
    public void actionPerformed( ActionEvent input_event ) {

        Object eventSource = input_event.getSource();
        
        if( this.isGeneratingMonthlyReport( eventSource ) ){
            
            try {
                if( this.tellConfirmationMessagerToAskForConfirmation( 
                ConfirmationMessager.CONFIRM_REPORT_WRITING ) ){
                    
                this.writeDownReport();
                }
            } catch (IOException ex) {
                
                this.tellErrorMessagerToShowMessage( ErrorMessager.FILE_ACCESS_ERROR );
            } catch (WriteException ex) {
                
                this.tellErrorMessagerToShowMessage( ErrorMessager.FILE_WRITE_ERROR );
            } catch (SQLException ex) {
                
                this.tellErrorMessagerToShowMessage( ErrorMessager.DATABASE_ERROR );
            }
        }

    }

    @Override
    protected void addActionListeners() {

        this.reportForm.getGenerateReportButton().addActionListener(this);
    }
    
    
    private boolean isGeneratingMonthlyReport( Object input_eventSource ){
        
        return input_eventSource == this.reportForm.getGenerateReportButton();
    }
    
    
    private void writeDownReport() throws IOException, WriteException, SQLException {
        
        ReportDAO salesReport = new ReportDAO(); 
        
        SalesReceipt[] salesReceipts = salesReceipts();
        
        for ( int rowCount = 0; rowCount < salesReceipts.length; rowCount++ ){
            
            salesReport.writeDownLabel(
                    this.FOLIO_COLUMN, 
                    rowCount, 
                    String.valueOf(salesReceipts[rowCount].getFolio()));
            
            salesReport.writeDownLabel(
                    this.CLIENT_PHONENUMBER_COLUMN, 
                    rowCount, 
                    ( String ) salesReceipts[rowCount].getPhoneNumber());
            
            salesReport.writeDownLabel(
                    this.PRODUCT_NAME_COLUMN, 
                    rowCount, 
                    ( String ) salesReceipts[rowCount].getProductName());
            
            salesReport.writeDownNumber(
                    this.QUANTITY_COLUMN, 
                    rowCount, 
                    (int) salesReceipts[rowCount].getProductQuantity());
            
            salesReport.writeDownNumber(
                    this.SUBTOTAL_COLUMN, 
                    rowCount, 
                    (double) salesReceipts[rowCount].getSubtotal());
            
            salesReport.writeDownLabel(
                    this.DATE_COLUMN, 
                    rowCount, 
                    ( String ) salesReceipts[rowCount].getDate());
        }
        
        salesReport.finishReport();
    }
    
    //Obtains all the sales completed in a month
    private SalesReceipt[] salesReceipts() throws SQLException{
        
        SalesReceipt[] receiptsOfTheMonth = SalesDAO.getSalesDAO().getMonthlySales( 
                this.monthlyReportDate( 
                        
                        ( int )this.reportForm.getMonthSpinner().getValue(), 
                        ( int )this.reportForm.getYearSpinner().getValue() ) );
        return receiptsOfTheMonth;
    }
    
    private Timestamp monthlyReportDate(
        int input_DueMonth,
        int input_DueYear
    ) {
        Calendar dueDate = Calendar.getInstance();
        
        dueDate.set(
            input_DueYear,
            ( input_DueMonth - 1 ),
            0,
            0,
            0
        );

        return new Timestamp(dueDate.getTimeInMillis());
    }
    
    private void tellErrorMessagerToShowMessage( String input_ErrorMessage ){
        
        ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
        errorMessager.showErrorMessage( input_ErrorMessage );
    }

    private boolean tellConfirmationMessagerToAskForConfirmation( String input_ErrorMessage ){
        
        ConfirmationMessager confirmationMessager = ConfirmationMessager.callConfirmationMessager();
        return confirmationMessager.askForConfirmation( input_ErrorMessage );
    }
    
    
    //calculates the price of every product based on a stored price and the product quantity
    static void calculatePartialCosts(
        OrdersList mod_ordersList //list of the current ordered products. Calculated prices will be stored here 
    ) throws SQLException {
        
        //cycle through the whole list and calculate each price
        for ( int productCount = 0; productCount < mod_ordersList.getRowCount(); productCount++ ) {

            //obtain the unitary price of the current product
            ProductsList productList = ( ProductsList ) mod_ordersList.
                getValueAt( productCount, OrdersList.PRODUCT_NAME );
            String productName = ( String ) productList.getSelectedItem();
            
            double unitaryPrice = PricesDAO.getPricesDAO().getProductPrice( productName );

            //adjust the price based on the product quantity
            double adjustedPrice = unitaryPrice *
                (int) mod_ordersList.getValueAt( productCount, OrdersList.PRODUCT_QUANTITY );

            //write the adjusted price in the orders list
            mod_ordersList.setValueAt( adjustedPrice, productCount, OrdersList.PRODUCT_PRICE );
        }
    }
    
    
    //calculate and return the sale total based on the partial costs
    static double totalPriceOfSale( 
        OrdersList input_ordersList //list of the current ordered products with partial costs already calculated
    ){
        
        double totalCost = 0; //the acumulated prices of all products in the order
        
        //cycle through the whole list and add the price of a product to the total
        for( int productCount = 0; productCount < input_ordersList.getRowCount(); productCount++ ){
            
            totalCost = totalCost + 
                    (double) input_ordersList.getValueAt( productCount, OrdersList.PRODUCT_PRICE );
            
        }
        
        return totalCost;
    
    }
}
