/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Jorge A. Cano
 */
public class SalesDAO extends DAO{
    
    private static final SalesDAO salesDAO = new SalesDAO();
    
    private final String INSERT_ORDER_QUERY = "INSERT INTO sales "
            + "(client_phoneNumber, product_name, quantity, subtotal, date, isBaked) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    
    public static SalesDAO getSalesDAO(){
        return salesDAO;
    }
    
    
    private SalesDAO(){
        
        super();
    }
    
    
    public void saveSale(
        String input_phonenumber,
        String input_product,
        int input_quantity,
        double input_cost,
        Timestamp input_dueDate
            
    ) throws SQLException{
        try {
            
            PreparedStatement preparedStatement = ( PreparedStatement ) 
                    super.connectionToDatabase.prepareStatement( this.INSERT_ORDER_QUERY );
            
            preparedStatement.setString( QueryEnumeration.FIRST_QUERY_VALUE, input_phonenumber );
            preparedStatement.setString( QueryEnumeration.SECOND_QUERY_VALUE, input_product );
            preparedStatement.setInt( QueryEnumeration.THIRD_QUERY_VALUE, input_quantity );
            preparedStatement.setDouble( QueryEnumeration.FOURTH_QUERY_VALUE, input_cost );
            preparedStatement.setTimestamp( QueryEnumeration.FIFTH_QUERY_VALUE, input_dueDate );
            preparedStatement.setBoolean( QueryEnumeration.SIXTH_QUERY_VALUE, false );
            
            preparedStatement.execute();
            
        }catch ( SQLException ex ) {
            throw ex;
        }
    }
    
}
