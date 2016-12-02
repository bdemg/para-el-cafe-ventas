/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * 
 * @author Jorge A. Cano
 */
public class DeliveriesDAO extends DAO{
    
    private static DeliveriesDAO deliveriesDAO;
    
    private final String TODAYS_DELIVERIES_QUERY = 
        "SELECT name, phoneNumber, address, product_name, quantity, subtotal, date "
        + "FROM client c JOIN (SELECT S.client_phoneNumber, S.product_name, S.quantity, S.subtotal, S.date FROM sales S WHERE s.date BETWEEN ? AND ?) SS "
        + "ON c.phoneNumber=SS.client_phoneNumber";

    private final int CLIENT_NAME_COLUMN = 0;
    private final int CLIENT_PHONENUMBER_COLUMN = 1;
    private final int CLIENT_ADDRESS_COLUMN = 2;
    private final int PRODUCT_NAME_COLUMN = 3;
    private final int QUANTITY_COLUMN = 4;
    private final int SUBTOTAL_COLUMN = 5;
    private final int DELIVERY_DATE_COLUMN = 6;
    
    private final String CLIENT_NAME_COLUMN_NAME = "name";
    private final String CLIENT_PHONENUMBER_COLUMN_NAME = "phoneNumber";
    private final String CLIENT_ADDRESS_COLUMN_NAME = "address";
    private final String PRODUCT_NAME_COLUMN_NAME = "product_name";
    private final String QUANTITY_COLUMN_NAME = "quantity";
    private final String SUBTOTAL_COLUMN_NAME = "subtotal";
    private final String DELIVERY_DATE_COLUMN_NAME = "date";
    
    
    
    private DeliveriesDAO() throws SQLException {
        
        super();
    }
    
    
    public static DeliveriesDAO getDeliveriesDAO() throws SQLException{
        
        if( DeliveriesDAO.deliveriesDAO == null ){
            
            DeliveriesDAO.deliveriesDAO = new DeliveriesDAO();
        }
        
        return DeliveriesDAO.deliveriesDAO;
    }
    
    
    public Object[][] getTodaysDeliveries() throws SQLException{
        
        PreparedStatement preparedStatement = ( PreparedStatement ) 
            super.connectionToDatabase.prepareStatement( this.TODAYS_DELIVERIES_QUERY );

        preparedStatement = this.addTodaysDateToQuery(preparedStatement);

        ResultSet resultSet = preparedStatement.executeQuery();

        return this.putTodaysDeliveriesIntoArray(resultSet);
    }

    
    
    private PreparedStatement addTodaysDateToQuery(PreparedStatement mod_preparedStatement) throws SQLException {
        
        Calendar startOfToday = Calendar.getInstance();
        startOfToday.set(Calendar.HOUR_OF_DAY, 0);
        startOfToday.set(Calendar.MINUTE, 0);
        startOfToday.set(Calendar.SECOND, 0);
        
        mod_preparedStatement.setTimestamp(QueryEnumeration.FIRST_QUERY_VALUE,
            new Timestamp( startOfToday.getTimeInMillis() ) );
        
        Calendar endOfToday = Calendar.getInstance();
        endOfToday.set( Calendar.HOUR_OF_DAY, 0 );
        endOfToday.set( Calendar.MINUTE, 0 );
        endOfToday.set( Calendar.SECOND, 0 );
        endOfToday.set(Calendar.DAY_OF_MONTH,
            ( endOfToday.get( Calendar.DAY_OF_MONTH ) + 1 ) );
        
        mod_preparedStatement.setTimestamp(QueryEnumeration.SECOND_QUERY_VALUE,
            new Timestamp( endOfToday.getTimeInMillis() ) );
        
        return mod_preparedStatement;
    }
    
    
    private Object[][] putTodaysDeliveriesIntoArray(ResultSet input_resultSet) throws SQLException {
        
        input_resultSet.last();
        int numberOfDeliveries = input_resultSet.getRow();
        Object[][] todaysDeliveries =
            new Object[ numberOfDeliveries ][ input_resultSet.getMetaData().getColumnCount() ];
        
        input_resultSet.first();
        
        //put each row of the result set into a row of the array
        for(int deliveryCount = 0; deliveryCount < numberOfDeliveries; deliveryCount++){
            
            todaysDeliveries[deliveryCount][ this.CLIENT_NAME_COLUMN ] = 
                input_resultSet.getString(this.CLIENT_NAME_COLUMN_NAME );
            
            todaysDeliveries[deliveryCount][ this.CLIENT_PHONENUMBER_COLUMN ] = 
                input_resultSet.getString( this.CLIENT_PHONENUMBER_COLUMN_NAME );
            
            todaysDeliveries[deliveryCount][ this.CLIENT_ADDRESS_COLUMN ] = 
                input_resultSet.getString( this.CLIENT_ADDRESS_COLUMN_NAME );
            
            todaysDeliveries[deliveryCount][ this.PRODUCT_NAME_COLUMN ] = 
                input_resultSet.getString( this.PRODUCT_NAME_COLUMN_NAME );
            
            todaysDeliveries[deliveryCount][ this.QUANTITY_COLUMN ] = 
                input_resultSet.getInt( this.QUANTITY_COLUMN_NAME );
            
            todaysDeliveries[deliveryCount][ this.SUBTOTAL_COLUMN ] = 
                input_resultSet.getDouble( this.SUBTOTAL_COLUMN_NAME );
            
            todaysDeliveries[deliveryCount][ this.DELIVERY_DATE_COLUMN ] = 
                new RevisedTimestamp( input_resultSet.getTimestamp( this.DELIVERY_DATE_COLUMN_NAME ) ).toString();
            
            input_resultSet.next();
        }
        
        return todaysDeliveries;
    }
}
