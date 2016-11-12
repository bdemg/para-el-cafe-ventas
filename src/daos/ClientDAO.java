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

    private final String INSERT_CLIENT = "INSERT INTO client VALUES (?, ?, ?, ?)";
    private final String QUERY_SEARCH = "SELECT * FROM client WHERE phone_number=?";
    
    private final int CLIENT_NAME = 1;
    private final int CLIENT_PHONENUMBER = 2;
    private final int CLIENT_ADDRESS = 3;
    private final int CLIENT_REFERENCES = 4;
    
    private final int NAME_COLUMN = 0;
    private final int PHONENUMBER_COLUMN = 1;
    private final int ADDRESS_COLUMN = 2;
    private final int REFERENCES_COLUMN = 3;
    
    private final String NAME_COLUMN_NAME = "phonenumber";
    private final String PHONENUMBER_COLUMN_NAME = "name";
    private final String ADDRESS_COLUMN_NAME = "address";
    private final String REFERENCES_COLUMN_NAME = "references";

    private Connection connectionToDatabase = null;
    private Statement statement = null;
    private PreparedStatement queryStatement = null;
    private ResultSet resultSet = null;

    private ClientDAO() {
        
        try {
            Class.forName(this.DRIVER);
            this.connectionToDatabase = DriverManager.getConnection(this.HOST, this.USER, this.PASSWORD);

            this.statement = this.connectionToDatabase.createStatement();

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
        String input_Name,
        String input_PhoneNumber,
        String input_Address,
        String input_References
    ) {
        
        try {
            this.queryStatement = 
                (PreparedStatement) 
                this.connectionToDatabase.prepareStatement( this.INSERT_CLIENT );
            
            this.queryStatement.setString(this.CLIENT_NAME, input_Name);
            this.queryStatement.setString(this.CLIENT_PHONENUMBER, input_PhoneNumber);
            this.queryStatement.setString(this.CLIENT_ADDRESS, input_Address);
            this.queryStatement.setString(this.CLIENT_REFERENCES, input_References);
            this.queryStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            
        } finally {
            this.closeQueryInformation();
        }
    }

    public boolean searchClientPhoneNumber( String input_PhoneNumber ) {
        
        try {
            this.queryStatement = (PreparedStatement) this.connectionToDatabase.prepareStatement( this.QUERY_SEARCH );
            this.queryStatement.setString(1, input_PhoneNumber);
            this.resultSet = this.queryStatement.executeQuery();
            
            return this.resultSet.last();

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;

        } finally {
            this.closeQueryInformation();
        }
    }
    
    public String[] getClientInfo( String input_PhoneNumber ){
        
        try {
            this.queryStatement = (PreparedStatement) this.connectionToDatabase.prepareStatement( this.QUERY_SEARCH );
            this.queryStatement.setString(1, input_PhoneNumber);
            this.resultSet = this.queryStatement.executeQuery();
            
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
            this.closeQueryInformation();
        }
        return null;
    }
    
    private void closeQueryInformation(){
        
        this.closeResultSet();
        this.closeQueryStatement();
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
    
    private void closeQueryStatement(){
        
        try {
            if ( this.queryStatement != null ) {
                this.queryStatement.close();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void closeConnection() {

        try {
            if ( this.connectionToDatabase != null ) {
                this.connectionToDatabase.close();
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
