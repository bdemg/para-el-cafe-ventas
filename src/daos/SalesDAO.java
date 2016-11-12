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

/**
 *
 * @author Jorge A. Cano
 */
public class SalesDAO {
    private static final SalesDAO salesDAO = new SalesDAO();
    
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String HOST = "jdbc:mysql://localhost/DBCafe?autoReconnect=true&useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "rootluigi44_44";
    
    private final String INSERT_ORDER_QUERY = "INSERT INTO sales "
            + "(phone_number, name, quantity, total, date, isBaked) "
            + "VALUES (?, ?, ?, ?, ?, ?)";
    
    private final int PHONENUMBER_COLUMN = 1;
    private final int PRODUCT_COLUMN = 2;
    private final int QUANTITY_COLUMN = 3;
    private final int COST_COLUMN = 4;
    private final int DUE_DATE_COLUMN = 5;
    private final int IS_BAKED_COLUMN = 6;
    
    private Connection connection = null;
    private java.sql.Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    
    public static SalesDAO getSalesDAO(){
        return salesDAO;
    }
    
    
    private SalesDAO(){
        try {
            Class.forName(this.DRIVER);
            this.connection = DriverManager.getConnection(this.HOST, this.USER, this.PASSWORD);

            this.statement = this.connection.createStatement();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void saveSale(
        String input_phonenumber,
        String input_product,
        int input_quantity,
        double input_cost,
        Date input_dueDate
            
    ) throws SQLException{
        try {
            preparedStatement = ( PreparedStatement ) connection
                    .prepareStatement( this.INSERT_ORDER_QUERY );
            
            preparedStatement.setString( this.PHONENUMBER_COLUMN, input_phonenumber );
            preparedStatement.setString( this.PRODUCT_COLUMN, input_product );
            preparedStatement.setInt( this.QUANTITY_COLUMN, input_quantity );
            preparedStatement.setDouble(this.COST_COLUMN, input_cost );
            preparedStatement.setDate( this.DUE_DATE_COLUMN, input_dueDate );
            preparedStatement.setBoolean( this.IS_BAKED_COLUMN, false );
            
            preparedStatement.execute();
            
        }catch (SQLException ex) {
            throw ex;
        } finally {
            this.closeResultSet();
            this.closeStatement();
        }
        
    }
   
    
    private void closeResultSet() {

        try {
            if ( this.resultSet != null ) {
                this.resultSet.close();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    
    private void closeStatement() {

        try {
            if ( this.statement != null ) {
                this.statement.close();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
