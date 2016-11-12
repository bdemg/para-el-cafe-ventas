package daos;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Antonio Soto
 */
public class ClientsDAO extends DatabaseDAO{

    private static final ClientsDAO clientsDAO = new ClientsDAO();

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
    
    private final String NAME_COLUMN_NAME = "name";
    private final String PHONENUMBER_COLUMN_NAME = "phone_number";
    private final String ADDRESS_COLUMN_NAME = "address";
    private final String REFERENCES_COLUMN_NAME = "location_references";

    private ClientsDAO() {
        
        super();
    }

    public static ClientsDAO getClientsDAO() {
        
        return clientsDAO;
    }

    public void insertClientInformation(
        String input_Name,
        String input_PhoneNumber,
        String input_Address,
        String input_References
    ) {
        
        try {
            PreparedStatement queryStatement = (PreparedStatement) 
                    super.connectionToDatabase.prepareStatement( this.INSERT_CLIENT );
            
            queryStatement.setString( this.CLIENT_NAME, input_Name );
            queryStatement.setString( this.CLIENT_PHONENUMBER, input_PhoneNumber );
            queryStatement.setString( this.CLIENT_ADDRESS, input_Address );
            queryStatement.setString( this.CLIENT_REFERENCES, input_References );
            queryStatement.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean searchClientPhoneNumber( String input_PhoneNumber ) {
        
        try {
            PreparedStatement queryStatement = 
                    (PreparedStatement) super.connectionToDatabase.prepareStatement( this.QUERY_SEARCH );
            queryStatement.setString(1, input_PhoneNumber);
            
            ResultSet resultSet = queryStatement.executeQuery();
            
            boolean isClientPhoneNumberFound = resultSet.last();
            return isClientPhoneNumberFound;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    public String[] getClientInfo( String input_PhoneNumber ) throws SQLException{
        
        try {
            PreparedStatement queryStatement = (PreparedStatement) 
                    super.connectionToDatabase.prepareStatement( this.QUERY_SEARCH );
            queryStatement.setString(1, input_PhoneNumber);
            ResultSet resultSet = queryStatement.executeQuery();
            
            boolean isClientFound = resultSet.last();
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
            throw ex;
        }
    }
}
