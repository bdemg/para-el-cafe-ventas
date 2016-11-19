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
public class ReportMonthTemplate extends SpinnerNumberModel{
    
    private static final int MONTHS_IN_YEAR = 12;
    private static final int FIRST_MONTH = 1;
    
    private static final int STEP_BY = 1;
    
    public ReportMonthTemplate() {
        super( ( Calendar.getInstance().get( Calendar.MONTH ) + 1 ), 
                ReportMonthTemplate.FIRST_MONTH, 
                ReportMonthTemplate.MONTHS_IN_YEAR, 
                ReportMonthTemplate.STEP_BY
        );
    }
}
