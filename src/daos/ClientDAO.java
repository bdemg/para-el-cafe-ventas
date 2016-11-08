package daos;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Antonio Soto
 */
public class ClientDAO {

    private static final ClientDAO clientTableDAO = new ClientDAO();

    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String HOST = "jdbc:mysql://localhost/DBCafe?autoReconnect=true&useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "rootluigi44_44";

    private final String QUERY_INSERT = "INSERT INTO client VALUES (?, ?, ?, ?)";
    private final String QUERY_SEARCH = "SELECT * FROM client WHERE phone_number=?";
    
    private final int NAME_COLUMN = 0;
    private final int PHONENUMBER_COLUMN = 1;
    private final int ADDRESS_COLUMN = 2;
    private final int REFERENCES_COLUMN = 3;
    
    private final String NAME_COLUMN_NAME = "name";
    private final String PHONENUMBER_COLUMN_NAME = "phone_number";
    private final String ADDRESS_COLUMN_NAME = "address";
    private final String REFERENCES_COLUMN_NAME = "location_references";

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private ClientDAO() {
        
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

    public static ClientDAO getClientTableDAO() {
        
        return clientTableDAO;
    }

    public void insertClientInformation(
        String name,
        String phone_number,
        String address,
        String location_references
    ) {
        
        try {
            this.preparedStatement = (PreparedStatement) this.connection.prepareStatement( this.QUERY_INSERT );
            this.preparedStatement.setString(1, name);
            this.preparedStatement.setString(2, phone_number);
            this.preparedStatement.setString(3, address);
            this.preparedStatement.setString(4, location_references);
            this.preparedStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            
        } finally {
            this.closeResultSet();
            this.closeStatement();
        }
    }

    public boolean searchClientPhoneNumber( String input_PhoneNumber ) {
        
        try {
            this.preparedStatement = (PreparedStatement) this.connection.prepareStatement( this.QUERY_SEARCH );
            this.preparedStatement.setString(1, input_PhoneNumber);
            this.resultSet = this.preparedStatement.executeQuery();
            
            return this.resultSet.last();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;

        } finally {
            this.closeResultSet();
            this.closeStatement();
        }
    }
    
    public String[] getClientInfo( String input_PhoneNumber ){
        
        try {
            this.preparedStatement = (PreparedStatement) this.connection.prepareStatement( this.QUERY_SEARCH );
            this.preparedStatement.setString(1, input_PhoneNumber);
            this.resultSet = this.preparedStatement.executeQuery();
            
            boolean isClientFound = this.resultSet.last();
            String[] clientInformation;
            if ( isClientFound ) {

                clientInformation = new String[resultSet.getMetaData().getColumnCount()];

                clientInformation[ this.NAME_COLUMN ] = 
                        resultSet.getString( this.NAME_COLUMN_NAME );
                clientInformation[ this.PHONENUMBER_COLUMN ] = 
                        resultSet.getString( this.PHONENUMBER_COLUMN_NAME );
                clientInformation[ this.ADDRESS_COLUMN ] = 
                        resultSet.getString( this.ADDRESS_COLUMN_NAME );
                clientInformation[ this.REFERENCES_COLUMN ] = 
                        resultSet.getString( this.REFERENCES_COLUMN_NAME );
                
            } else{
                clientInformation = null;
            }
            return clientInformation;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        } finally {
            this.closeResultSet();
            this.closeStatement();
        }
        return null;
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
    
    private void closeConnection() {

        try {
            if ( this.connection != null ) {
                this.connection.close();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
