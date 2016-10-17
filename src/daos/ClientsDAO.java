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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge A. Cano
 */
public class ClientsDAO {
    
    private static final ClientsDAO clientsDAO = new ClientsDAO();

    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String USER_AND_PASSWORD = "user=sqluser&password=sqluserpw";
    private final String HOST = "jdbc:mysql://localhost/feedback?";
    private final String PHONE_QUERY = "select * from sales.clients where phonenumber= ";

    private final int PHONENUMBER_COLUMN = 0;
    private final int NAME_COLUMN = 1;
    private final int ADDRESS_COLUMN = 2;
    
    private final String PHONENUMBER_COLUMN_NAME = "phonenumber";
    private final String NAME_COLUMN_NAME = "name";
    private final String ADDRESS_COLUMN_NAME = "address";

    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    
    public static ClientsDAO getClientsDAO(){
        return clientsDAO;
    }
    

    public String[] searchClientPhonenumber(String inputPhonenumber, boolean modIsFound) throws Exception {
        try {
            // Result set get the result of the SQL query
            resultSet = statement.executeQuery(this.PHONE_QUERY + inputPhonenumber);
            
            modIsFound = resultSet.last();
            String[] queryResults;
            if ( modIsFound ) {

                queryResults = new String[resultSet.getMetaData().getColumnCount()];

                queryResults[ this.PHONENUMBER_COLUMN ] = 
                        resultSet.getString( this.PHONENUMBER_COLUMN_NAME );
                queryResults[ this.NAME_COLUMN ] = 
                        resultSet.getString( this.NAME_COLUMN_NAME );
                queryResults[ this.ADDRESS_COLUMN ] = 
                        resultSet.getString( this.ADDRESS_COLUMN_NAME );
                
            } else{
                queryResults = null;
            }
            return queryResults;
        } catch (Exception e) {
            throw e;
        } finally {
            close();
        }

    }

    
    private ClientsDAO() {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName(this.DRIVER);
            // Setup the connection with the DB
            connect = DriverManager
                    .getConnection(this.HOST + this.USER_AND_PASSWORD);

            // Statements allow to issue SQL queries to the database
            statement = (Statement) connect.createStatement();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientsDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ClientsDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
    
    
}
