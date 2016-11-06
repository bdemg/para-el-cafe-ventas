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
public class DueYearTemplate extends SpinnerNumberModel{
    
    private static final int STEP_BY = 1;

    
    public DueYearTemplate() {
        super(
            Calendar.getInstance().get( Calendar.YEAR ),
            Calendar.getInstance().get( Calendar.YEAR ),
            ( Calendar.getInstance().get(Calendar.YEAR) + 1 ),
            DueYearTemplate.STEP_BY
        );
    }
}
