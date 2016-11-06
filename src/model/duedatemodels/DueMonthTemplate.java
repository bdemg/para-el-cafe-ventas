/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.duedatemodels;

import java.util.Calendar;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Jorge A. Cano
 */
public class DueMonthTemplate extends SpinnerNumberModel{
    
    private static final int STEP_BY = 1;

    private static final int MONTHS_IN_YEAR = 12;
    private static final int FIRST_MONTH = 1;
    
    
    public DueMonthTemplate() {
        super(
            ( Calendar.getInstance().get( Calendar.MONTH ) + 1 ),
            DueMonthTemplate.FIRST_MONTH,
            DueMonthTemplate.MONTHS_IN_YEAR,
            DueMonthTemplate.STEP_BY
        );
        
    }
    
}
