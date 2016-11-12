/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.duedatemodels;

import java.util.Calendar;
import javax.swing.SpinnerNumberModel;

/**
 *This class is used as a template for the due minute field. It dictates 
 *the maximun and minimum due minute that can be set for a delivery
 * @author Jorge A. Cano
 */
public class DueMinuteTemplate extends SpinnerNumberModel{
    
    private static final int STEP_BY = 1;

    private static final int LAST_MINUTE_IN_HOUR = 59;
    private static final int FIRST_MINUTE_IN_HOUR = 0;
    
    
    public DueMinuteTemplate() {
        super(
            Calendar.getInstance().get( Calendar.MINUTE ),
            DueMinuteTemplate.FIRST_MINUTE_IN_HOUR,
            DueMinuteTemplate.LAST_MINUTE_IN_HOUR,
            DueMinuteTemplate.STEP_BY
        );
    }
    
    
}
