/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * This class is used to extend the functionality of the Timestamp
 * @author Jorge A. Cano
 */
public class RevisedTimestamp {
    
    private final Timestamp timestamp;

    public RevisedTimestamp( Timestamp timestamp ) {
        this.timestamp = timestamp;
    }

    
    @Override
    public String toString() {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis( this.timestamp.getTime() );
        
        String outputDate = ""
                + calendar.get( Calendar.DAY_OF_MONTH )+"/"
                + ( calendar.get( Calendar.MONTH ) + 1 )+"/"
                + calendar.get( Calendar.YEAR )+" "
                + calendar.get( Calendar.HOUR_OF_DAY )+":"
                + calendar.get( Calendar.MINUTE );
        
        return outputDate;
    }
    
    
    //get a date that is a month from now
    public Timestamp nextMonth(){
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.timestamp.getTime());
        calendar.set( Calendar.MONTH, ( calendar.get(Calendar.MONTH) + 1 ) );
        
        return new Timestamp( calendar.getTimeInMillis() );
    }
}
