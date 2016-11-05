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
    private final String USER_AND_PASSWORD = "user=sqluser&password=sqluserpw";
    private final String HOST = "jdbc:mysql://localhost/feedback?";
    
    private final String PRICE_QUERY = "select * from sales.products where name= ";
    
    private final String FOLIO_COLUMN_NAME = "price";
    
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    
    public static PricesDAO getPricesDAO(){
        return pricesDAO;
    }
    
    
    private PricesDAO(){
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName( this.DRIVER );
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection( this.HOST + this.USER_AND_PASSWORD );

            // Statements allow to issue SQL queries to the database
            statement = ( Statement ) connect.createStatement();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public double getPrice(String inputProductName){
        
        try {
            resultSet = statement.executeQuery(this.PRICE_QUERY + inputProductName);
            double productPrice = resultSet.getDouble(this.FOLIO_COLUMN_NAME);
            this.close();
            return productPrice;
        } catch (SQLException ex) {
            Logger.getLogger(PricesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    private void close() {
        try {
            
            if ( resultSet != null ) {
                
                resultSet.close();
            }

            if ( statement != null ) {
                
                statement.close();
            }

            if ( connect != null ) {
                
                connect.close();
            }
        } catch (Exception e) {

        }
    }

}
