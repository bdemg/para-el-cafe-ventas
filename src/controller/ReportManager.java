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
import model.ErrorMessager;
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
    
    private final ReportForm reportForm;
    
    public ReportManager(){
        this.reportForm = new ReportForm();
        this.setReportForm();
        this.addActionListeners();
    }
    
    private void setReportForm(){
        this.reportForm.setVisible(true);
        this.reportForm.setResizable(true);
        this.reportForm.setLocationRelativeTo(null);
        this.setDefaultReportDate();
    }

    @Override
    public void actionPerformed(ActionEvent event) {

        Object eventSource = event.getSource();
        
        if( this.isGeneratingMonthlyReport( eventSource )){
            try {
                this.writeDownReport();
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
    
    private void writeDownReport() throws IOException, WriteException, SQLException{
        
        Object[][] fg = SalesDAO.getSalesDAO().getMonthlySales( 
                this.formatMonthlyReport( 
                        ( int )this.reportForm.getMonthSpinner().getValue(), 
                        ( int )this.reportForm.getYearSpinner().getValue() ) );
        
        ReportDAO re = new ReportDAO();        
        re.createSheet("Monthly Report", 0);
        
        for ( int rowCount = 0; rowCount < fg.length; rowCount++ ){
            
            re.writeDownLabeledCells(FOLIO_COLUMN, rowCount, String.valueOf(fg[rowCount][FOLIO_COLUMN]));
            re.writeDownLabeledCells(CLIENT_PHONENUMBER_COLUMN, rowCount, ( String ) fg[rowCount][CLIENT_PHONENUMBER_COLUMN]);
            re.writeDownLabeledCells(PRODUCT_NAME_COLUMN, rowCount, ( String ) fg[rowCount][PRODUCT_NAME_COLUMN]);
            re.writeDownNumberCells(QUANTITY_COLUMN, rowCount, (int) fg[rowCount][QUANTITY_COLUMN]);
            re.writeDownNumberCells(SUBTOTAL_COLUMN, rowCount, (double) fg[rowCount][SUBTOTAL_COLUMN]);
            re.writeDownLabeledCells(DATE_COLUMN, rowCount, ( String ) fg[rowCount][DATE_COLUMN]);
        }
        
        re.closeReportWorkbook();
    }
    
    private boolean isGeneratingMonthlyReport( Object input_eventSource ){
        
        return input_eventSource == this.reportForm.getGenerateReportButton();
    }
    
    private void setDefaultReportDate(){
        
        this.reportForm.getMonthSpinner().setModel( new ReportMonthTemplate() );
        this.reportForm.getYearSpinner().setModel( new ReportYearTemplate() );
        
    }
    
    private void tellErrorMessagerToShowMessage ( String input_ErrorMessage ){
        
        ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
        errorMessager.showErrorMessage( input_ErrorMessage );
    }
    
    private Timestamp formatMonthlyReport(
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
}
