/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.ReportDAO;
import daos.SalesDAO;
import static daos.SalesDAO.getSalesDAO;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.Object;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.write.WriteException;
import model.ConfirmationMessager;
import model.ErrorMessager;
import model.reportdatemodels.*;
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
        
        //Object salesRecipts = salesRecipts();
        
        for ( int rowCount = 0; rowCount < salesReceipts().length; rowCount++ ){
            
            salesReport.writeDownLabel(
                    this.FOLIO_COLUMN, 
                    rowCount, 
                    String.valueOf(salesReceipts()[rowCount][this.FOLIO_COLUMN]));
            
            salesReport.writeDownLabel(
                    CLIENT_PHONENUMBER_COLUMN, 
                    rowCount, 
                    ( String ) salesReceipts()[rowCount][CLIENT_PHONENUMBER_COLUMN]);
            
            salesReport.writeDownLabel(
                    PRODUCT_NAME_COLUMN, 
                    rowCount, 
                    ( String ) salesReceipts()[rowCount][PRODUCT_NAME_COLUMN]);
            
            salesReport.writeDownNumber(
                    QUANTITY_COLUMN, 
                    rowCount, 
                    (int) salesReceipts()[rowCount][QUANTITY_COLUMN]);
            
            salesReport.writeDownNumber(
                    SUBTOTAL_COLUMN, 
                    rowCount, 
                    (double) salesReceipts()[rowCount][SUBTOTAL_COLUMN]);
            
            salesReport.writeDownLabel(
                    DATE_COLUMN, 
                    rowCount, 
                    ( String ) salesReceipts()[rowCount][DATE_COLUMN]);
        }
        
        salesReport.finishReport();
    }
    
    private Object[][] salesReceipts() throws SQLException{
        
        Object[][] receiptsOfTheMonth = SalesDAO.getSalesDAO().getMonthlySales( 
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
}
