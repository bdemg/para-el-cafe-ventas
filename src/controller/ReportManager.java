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
import model.reportdatemodels.*;
import view.ReportForm;
/**
 *
 * @author Evan-Ian-Ray
 */
public class ReportManager extends Controller{
    
    private final int FOLIO_COLUMN = 0;
    private final int CLIENT_PHONENUMBER_COLUMN = 1;
    private final int PRODUCT_NAME_COLUMN = 2;
    private final int QUANTITY_COLUMN = 3;
    private final int SUBTOTAL_COLUMN = 4;
    private final int DATE_COLUMN = 5;
    
    private final int DEFAULT_PAGE_NUMBER = 0;
    private final String DEFAULT_SHEET_NAME = "Reporte del Mes";
    
    private final ReportForm reportForm;
    
    public ReportManager(){
        this.reportForm = new ReportForm();
        this.addActionListeners();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Object eventSource = event.getSource();
        
        if( this.isGeneratingMonthlyReport( eventSource ) ){
            
            try {
                if( this.tellConfirmationMessagerToAskForConfirmation( 
                ConfirmationMessager.CONFIRM_REPORT_WRITING ) ){
                    
                this.writeDownReport();
                }
            } catch (IOException ex) {
                
                Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (WriteException ex) {
                
                Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                
                Logger.getLogger(ReportManager.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void writeDownReport() throws IOException, WriteException, SQLException{
        
        Object[][] receiptsOfTheMonth = SalesDAO.getSalesDAO().getMonthlySales( 
                this.formatReportDate( 
                        ( int )this.reportForm.getMonthSpinner().getValue(), 
                        ( int )this.reportForm.getYearSpinner().getValue() ) );
        
        ReportDAO accountant = new ReportDAO(DEFAULT_SHEET_NAME, DEFAULT_PAGE_NUMBER);        
        accountant.writeInSheet("Monthly Report", 0);
        
        for ( int rowCount = 0; rowCount < receiptsOfTheMonth.length; rowCount++ ){
            
            accountant.writeDownLabeledCells(
                    FOLIO_COLUMN, 
                    rowCount, 
                    String.valueOf(receiptsOfTheMonth[rowCount][FOLIO_COLUMN]));
            
            accountant.writeDownLabeledCells(
                    CLIENT_PHONENUMBER_COLUMN, 
                    rowCount, 
                    ( String ) receiptsOfTheMonth[rowCount][CLIENT_PHONENUMBER_COLUMN]);
            
            accountant.writeDownLabeledCells(
                    PRODUCT_NAME_COLUMN, 
                    rowCount, 
                    ( String ) receiptsOfTheMonth[rowCount][PRODUCT_NAME_COLUMN]);
            
            accountant.writeDownNumberCells(
                    QUANTITY_COLUMN, 
                    rowCount, 
                    (int) receiptsOfTheMonth[rowCount][QUANTITY_COLUMN]);
            
            accountant.writeDownNumberCells(
                    SUBTOTAL_COLUMN, 
                    rowCount, 
                    (double) receiptsOfTheMonth[rowCount][SUBTOTAL_COLUMN]);
            
            accountant.writeDownLabeledCells(
                    DATE_COLUMN, 
                    rowCount, 
                    ( String ) receiptsOfTheMonth[rowCount][DATE_COLUMN]);
        }
        
        accountant.finishReport();
    }
    
    private Timestamp formatReportDate(
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

    private boolean tellConfirmationMessagerToAskForConfirmation( String input_ErrorMessage ){
        
        ConfirmationMessager confirmationMessager = ConfirmationMessager.callConfirmationMessager();
        return confirmationMessager.askForConfirmation( input_ErrorMessage );
    }
}
