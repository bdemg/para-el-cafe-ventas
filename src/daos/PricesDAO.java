/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge A. Cano
 */
public class PricesDAO {
    private static final PricesDAO pricesDAO = new PricesDAO();
    
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String HOST = "jdbc:mysql://localhost/DBCafe?autoReconnect=true&useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "rootluigi44_44";
    
    private final String PRICE_QUERY = "select * from product where name=?";
    
    private final int NAME_COLUMN = 1;
    
    private final String PRICE_COLUMN_NAME = "price";
    
    private Connection connection = null;
    private java.sql.Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    
    public static PricesDAO getPricesDAO(){
        return pricesDAO;
    }
    
    
    private PricesDAO(){
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
    
    
    public double getProductPrice(String input_ProductName){
        
        try {
            preparedStatement = ( PreparedStatement ) connection
                .prepareStatement( this.PRICE_QUERY );
            
            this.preparedStatement.setString( this.NAME_COLUMN, input_ProductName );
            
            resultSet = this.preparedStatement.executeQuery();
            
            double productPrice = resultSet.getDouble(this.PRICE_COLUMN_NAME);
            return productPrice;
        } catch (SQLException ex) {
            Logger.getLogger(PricesDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            this.closeResultSet();
            this.closeStatement();
        }
        return -1;
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
