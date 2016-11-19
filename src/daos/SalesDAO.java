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
            + "(phone_number, name, quantity, total, date, isBaked) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    
    private final int PHONENUMBER_COLUMN = 1;
    private final int PRODUCT_COLUMN = 2;
    private final int QUANTITY_COLUMN = 3;
    private final int COST_COLUMN = 4;
    private final int DUE_DATE_COLUMN = 5;
    private final int IS_BAKED_COLUMN = 6;
    
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
            
            preparedStatement.setString( this.PHONENUMBER_COLUMN, input_phonenumber );
            preparedStatement.setString( this.PRODUCT_COLUMN, input_product );
            preparedStatement.setInt( this.QUANTITY_COLUMN, input_quantity );
            preparedStatement.setDouble( this.COST_COLUMN, input_cost );
            preparedStatement.setTimestamp(this.DUE_DATE_COLUMN, input_dueDate );
            preparedStatement.setBoolean( this.IS_BAKED_COLUMN, false );
            
            preparedStatement.execute();
            
        }catch ( SQLException ex ) {
            throw ex;
        }
    }
    
}
