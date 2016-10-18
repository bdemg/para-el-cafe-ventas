/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author Jorge A. Cano
 */
public class OrdersDAO {
    private static final OrdersDAO ordersDAO = new OrdersDAO();
    
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USER_AND_PASSWORD = "user=sqluser&password=sqluserpw";
    private final String HOST = "jdbc:mysql://localhost/feedback?";
    
    private final String LAST_FOLIO_QUERY = "select folio from sales.orders";
    private final String INSERT_ORDER_QUERY = "insert into  sales.orders values (default, ?, ?, ?, ? , ?, ?, ?)";
    
    private final String FOLIO_COLUMN_NAME = "folio";
    
    private final int FOLIO_COLUMN = 1;
    private final int PHONENUMBER_COLUMN = 2;
    private final int PRODUCT_COLUMN = 3;
    private final int QUANTITY_COLUMN = 4;
    private final int COST_COLUMN = 5;
    private final int DUE_DATE_COLUMN = 6;
    private final int IS_BAKED_COLUMN = 7;
    
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    
    
    public static OrdersDAO getOrdersDAO(){
        return ordersDAO;
    }
    
    
    private OrdersDAO(){
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName( this.DRIVER );
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection( this.HOST + this.USER_AND_PASSWORD );

            // Statements allow to issue SQL queries to the database
            statement = ( Statement ) connect.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClientsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public int getLastFolio() throws Exception{
        try {
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery( this.LAST_FOLIO_QUERY );
            
            boolean thereIsAFolio = resultSet.last();
            int lastFolio = 0;
            if ( thereIsAFolio ) {
                
                lastFolio = resultSet.getInt( this.FOLIO_COLUMN_NAME );
            } else{
                
                lastFolio = 0;
            }
            return lastFolio;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }
    }
    
    
    public void saveSale(
        String inputPhonenumber,
        String inputProduct,
        int inputQuantity,
        double inputCost,
        int inputDueDay,
        int inputDueMonth,
        int inputDueYear,
        int inputDueHour,
        int inputDueMinute
            
    ) throws Exception{
        try {
            preparedStatement = ( PreparedStatement ) connect
                    .prepareStatement( this.INSERT_ORDER_QUERY );
            
            int nextFolio = getLastFolio() + 1;
            preparedStatement.setInt( this.FOLIO_COLUMN, nextFolio );
            preparedStatement.setString( this.PHONENUMBER_COLUMN, inputPhonenumber );
            preparedStatement.setString( this.PRODUCT_COLUMN, inputProduct );
            preparedStatement.setInt( this.QUANTITY_COLUMN, inputQuantity );
            preparedStatement.setDouble( this.COST_COLUMN, inputCost );
            Date dueDate = this.createDate(
                    inputDueDay, 
                    inputDueMonth, 
                    inputDueYear, 
                    inputDueHour, 
                    inputDueMinute
            );
            preparedStatement.setDate( this.DUE_DATE_COLUMN, dueDate );
            preparedStatement.setBoolean( this.IS_BAKED_COLUMN, false );
            
            preparedStatement.executeUpdate();
            
        }catch (Exception e) {
            throw e;
        } finally {
            close();
        }
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

    private Date createDate(
        int inputDueDay,
        int inputDueMonth,
        int inputDueYear,
        int inputDueHour,
        int inputDueMinute
    ) {
        Calendar date = Calendar.getInstance();
        date.set(
            inputDueYear,
            ( inputDueMonth - 1 ), 
            inputDueDay, 
            inputDueDay, 
            inputDueDay
        );
        
        return new Date( date.getTimeInMillis() );
    }
}
