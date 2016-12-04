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
import model.SalesReceipt;

/**
 * This class is used to interact with the sales information in the database
 * @author Jorge A. Cano
 */
public class SalesDAO extends DatabaseDAO{
    
    private static SalesDAO salesDAO;
    
    private final String INSERT_ORDER_QUERY = "INSERT INTO sales "
            + "(client_phoneNumber, product_name, quantity, subtotal, date, isBaked) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    
    private final String MONTHLY_SALES_QUERY = 
            "SELECT folio, client_phoneNumber, product_name, quantity, subtotal, date "
            + "FROM sales WHERE date BETWEEN ? AND ?";
    
    private final String FOLIO_COLUMN_NAME = "folio";
    private final String CLIENT_PHONENUMBER_COLUMN_NAME = "client_phoneNumber";
    private final String PRODUCT_NAME_COLUMN_NAME = "product_name";
    private final String QUANTITY_COLUMN_NAME = "quantity";
    private final String SUBTOTAL_COLUMN_NAME = "subtotal";
    private final String DATE_COLUMN_NAME = "date";
    
    
    public static SalesDAO getSalesDAO() throws SQLException{
        
        if( SalesDAO.salesDAO ==  null ){
            
            SalesDAO.salesDAO = new SalesDAO();
        }
        
        return SalesDAO.salesDAO;
    }
    
    
    private SalesDAO() throws SQLException{
        
        super();
    }
    
    
    //save a sale into the database
    public void saveSale(
        String input_phonenumber,
        String input_product,
        int input_quantity,
        double input_cost,
        Timestamp input_dueDate
            
    ) throws SQLException{
            
        PreparedStatement preparedStatement = ( PreparedStatement ) 
                super.connectionToDatabase.prepareStatement( this.INSERT_ORDER_QUERY );
            
        //add the values into the insertion query
        preparedStatement.setString( QueryEnumeration.FIRST_QUERY_VALUE, input_phonenumber );
        preparedStatement.setString( QueryEnumeration.SECOND_QUERY_VALUE, input_product );
        preparedStatement.setInt( QueryEnumeration.THIRD_QUERY_VALUE, input_quantity );
        preparedStatement.setDouble( QueryEnumeration.FOURTH_QUERY_VALUE, input_cost );
        preparedStatement.setTimestamp( QueryEnumeration.FIFTH_QUERY_VALUE, input_dueDate );
        preparedStatement.setBoolean( QueryEnumeration.SIXTH_QUERY_VALUE, false );

        preparedStatement.execute();
    }
    
    
    //obtain all the sales made in a month that are stored in the database
    public SalesReceipt[] getMonthlySales( Timestamp input_month ) throws SQLException{

        PreparedStatement preparedStatement = ( PreparedStatement )
                super.connectionToDatabase.prepareStatement( this.MONTHLY_SALES_QUERY );


        preparedStatement.setTimestamp(
            QueryEnumeration.FIRST_QUERY_VALUE, input_month );
        preparedStatement.setTimestamp( 
            QueryEnumeration.SECOND_QUERY_VALUE, 
            new RevisedTimestamp( input_month ).nextMonth()
        );

        ResultSet queryResult = preparedStatement.executeQuery();
        
        return this.putMontlyReportIntoReciptsArray(queryResult);
    }

    
    //put the data of a monthly report result set into an array of sales recipts
    private SalesReceipt[] putMontlyReportIntoReciptsArray(ResultSet input_queryResult) throws SQLException {
        
        input_queryResult.last();
        int numberOfSales = input_queryResult.getRow();
        SalesReceipt[] monthlyReport = new SalesReceipt[numberOfSales];
        
        input_queryResult.first();
        
        //put each row of the result set into a row of the array
        for(int saleCount = 0; saleCount < numberOfSales; saleCount++){
            
            monthlyReport[saleCount] = new SalesReceipt(
                input_queryResult.getInt( this.FOLIO_COLUMN_NAME ),
                input_queryResult.getString( this.CLIENT_PHONENUMBER_COLUMN_NAME ),
                input_queryResult.getString( this.PRODUCT_NAME_COLUMN_NAME ),
                input_queryResult.getInt( this.QUANTITY_COLUMN_NAME ),
                input_queryResult.getDouble( this.SUBTOTAL_COLUMN_NAME ),
                new RevisedTimestamp( input_queryResult.getTimestamp( this.DATE_COLUMN_NAME ) ).toString()
            );
            
            input_queryResult.next();
        }
        
        return monthlyReport;
    }
}
