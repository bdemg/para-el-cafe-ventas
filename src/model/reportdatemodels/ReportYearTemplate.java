/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.reportdatemodels;

import java.util.Calendar;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Evan-Ian-Ray
 */
public class ReportYearTemplate extends SpinnerNumberModel {
    
    private static final int FIRST_YEAR_OF_REPORTS = 2016;
    private static final int STEP_BY = 1;
    
    public ReportYearTemplate(){
        super(
                Calendar.getInstance().get( Calendar.YEAR ), 
                ReportYearTemplate.FIRST_YEAR_OF_REPORTS , 
                Calendar.getInstance().get( Calendar.YEAR ), 
                ReportYearTemplate.STEP_BY
        );
    }
    
}
