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
public class DueHourTemplate extends SpinnerNumberModel{

    private static final int STEP_BY = 1;
    
    private static final int LAST_HOUR_IN_DAY = 23; 
    private static final int FIRST_HOUR_IN_DAY = 0;
    
    
    public DueHourTemplate() {
        super(
            Calendar.getInstance().get( Calendar.HOUR_OF_DAY ),
            DueHourTemplate.FIRST_HOUR_IN_DAY,
            DueHourTemplate.LAST_HOUR_IN_DAY,
            DueHourTemplate.STEP_BY
        );
    }
    
}
