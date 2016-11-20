/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import model.ErrorMessager;
import model.reportdatemodels.*;
import view.ReportForm;
/**
 *
 * @author Evan-Ian-Ray
 */
public class ReportManager extends Controller{
    
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
            
        }

    }

    @Override
    protected void addActionListeners() {

        this.reportForm.getGenerateReportButton().addActionListener(this);
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
}
