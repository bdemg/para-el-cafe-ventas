package daos;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class manages the clients data with the database.
 * @author (c) Copyright 2016 José A. Soto. All Rights Reserved.
 */
public class ClientsDAO extends DAO{

    private static final ClientsDAO clientsDAO = new ClientsDAO();

    private final String INSERT_CLIENT = "INSERT INTO client VALUES (?, ?, ?, ?)";
    private final String QUERY_SEARCH = "SELECT * FROM client WHERE phoneNumber=?";
    
    private final int NAME_COLUMN = 0;
    private final int PHONENUMBER_COLUMN = 1;
    private final int ADDRESS_COLUMN = 2;
    private final int REFERENCES_COLUMN = 3;
    
    private final String NAME_COLUMN_NAME = "name";
    private final String PHONENUMBER_COLUMN_NAME = "phoneNumber";
    private final String ADDRESS_COLUMN_NAME = "address";
    private final String REFERENCES_COLUMN_NAME = "locationReferences";

    private ClientsDAO() {
        
        super();
    }

    public static ClientsDAO getClientsDAO() {
        
        return clientsDAO;
    }
    
    // Inserts new client information to the database.
    public void insertClientInformation(
        String input_Name,
        String input_PhoneNumber,
        String input_Address,
        String input_References
    ) {
        
        try {
            PreparedStatement queryStatement = (PreparedStatement) 
                    super.connectionToDatabase.prepareStatement( this.INSERT_CLIENT );
            
            queryStatement.setString( QueryEnumeration.FIRST_QUERY_VALUE, input_Name );
            queryStatement.setString( QueryEnumeration.SECOND_QUERY_VALUE, input_PhoneNumber );
            queryStatement.setString( QueryEnumeration.THIRD_QUERY_VALUE, input_Address );
            queryStatement.setString( QueryEnumeration.FOURTH_QUERY_VALUE, input_References );
            queryStatement.execute();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    // Searches the existence of a single client based on his phone number.
    public boolean findClient( String input_PhoneNumber ) {
        
        try {
            PreparedStatement queryStatement = 
                    (PreparedStatement) super.connectionToDatabase.prepareStatement( this.QUERY_SEARCH );
            queryStatement.setString( QueryEnumeration.FIRST_QUERY_VALUE, input_PhoneNumber );
            
            ResultSet resultSet = queryStatement.executeQuery();
            
            boolean isClientPhoneNumberFound = resultSet.last();
            return isClientPhoneNumberFound;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    // Gets all the client information from the database table.
    public String[] getClientInfo( String input_PhoneNumber ) throws SQLException{
        
        try {
            PreparedStatement queryStatement = (PreparedStatement) 
                    super.connectionToDatabase.prepareStatement( this.QUERY_SEARCH );
            queryStatement.setString( QueryEnumeration.FIRST_QUERY_VALUE, input_PhoneNumber );
            
            ResultSet resultSet = queryStatement.executeQuery();
            boolean isClientFound = resultSet.last();
            
            String[] clientInformation;
            if ( isClientFound ) {
                clientInformation = this.putClientInformationIntoArray(resultSet);
                
            } else{
                clientInformation = null;
            }
            return clientInformation;
            
        } catch (SQLException ex) {
            throw ex;
        }
    }
    
    private String[] putClientInformationIntoArray(ResultSet input_resultSet) throws SQLException{
        
        String[] clientInformation = 
                new String[input_resultSet.getMetaData().getColumnCount()];

        clientInformation[ this.NAME_COLUMN ] = 
            input_resultSet.getString( this.NAME_COLUMN_NAME );
        clientInformation[ this.PHONENUMBER_COLUMN ] = 
            input_resultSet.getString( this.PHONENUMBER_COLUMN_NAME );
        clientInformation[ this.ADDRESS_COLUMN ] = 
            input_resultSet.getString( this.ADDRESS_COLUMN_NAME );
        clientInformation[ this.REFERENCES_COLUMN ] = 
            input_resultSet.getString( this.REFERENCES_COLUMN_NAME );
        
        return clientInformation;
    }
}
