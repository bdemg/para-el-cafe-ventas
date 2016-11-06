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
public class DueDayTemplate extends SpinnerNumberModel {
    
    private static final int STEP_BY = 1;

    private static final int MAX_DAYS_IN_MONTH = 31;
    private static final int FIRST_DAY = 1;
    
    
    public DueDayTemplate() {
        super(
            Calendar.getInstance().get( Calendar.DAY_OF_MONTH ),
            DueDayTemplate.FIRST_DAY,
            DueDayTemplate.MAX_DAYS_IN_MONTH,
            DueDayTemplate.STEP_BY
        );
    }
    
    
}
