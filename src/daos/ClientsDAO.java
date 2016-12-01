package daos;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Client;

/**
 * This class manages the clients data with the database.
 * @author (c) Copyright 2016 José A. Soto. All Rights Reserved.
 */
public class ClientsDAO extends DAO {

    private static ClientsDAO clientsDAO;

    private final String INSERT_CLIENT = "INSERT INTO client VALUES (?, ?, ?, ?)";
    private final String DELETE_CLIENT = "DELETE FROM client WHERE phoneNumber=?";
    private final String SEARCH_CLIENT = "SELECT * FROM client WHERE phoneNumber=?";

    private final String NAME_COLUMN_NAME = "name";
    private final String PHONENUMBER_COLUMN_NAME = "phoneNumber";
    private final String ADDRESS_COLUMN_NAME = "address";
    private final String REFERENCES_COLUMN_NAME = "locationReferences";

    private ClientsDAO() throws SQLException {

        super();
    }

    public static ClientsDAO getClientsDAO() throws SQLException {
        
        if(clientsDAO == null){
            
            clientsDAO = new ClientsDAO();
        }

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

        } catch ( SQLException ex ) {
            ex.printStackTrace();
        }
    }
    
    // Deletes client information of a single client.
    public void deleteClient( String input_PhoneNumber ){
        
        try {
            PreparedStatement queryStatement = (PreparedStatement)
                    super.connectionToDatabase.prepareStatement( this.DELETE_CLIENT );
            queryStatement.setString( QueryEnumeration.FIFTH_QUERY_VALUE, input_PhoneNumber );
            
            queryStatement.executeQuery();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Searches the existence of a single client based on his phone number.
    public boolean findClient( String input_PhoneNumber ) {

        try {
            PreparedStatement queryStatement = (PreparedStatement)
                    super.connectionToDatabase.prepareStatement( this.SEARCH_CLIENT );
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
    public Client getClientInfo(String input_PhoneNumber) throws SQLException {

        try {
            
            PreparedStatement queryStatement = (PreparedStatement) 
                    super.connectionToDatabase.prepareStatement( this.SEARCH_CLIENT );
            queryStatement.setString( QueryEnumeration.FIRST_QUERY_VALUE, input_PhoneNumber );

            ResultSet resultSet = queryStatement.executeQuery();
            boolean isClientFound = resultSet.last();

            Client client;
            if (isClientFound) {
                
                client = new Client(
                    resultSet.getString(this.PHONENUMBER_COLUMN_NAME),
                    resultSet.getString(this.NAME_COLUMN_NAME),
                    resultSet.getString(this.ADDRESS_COLUMN_NAME),
                    resultSet.getString(this.REFERENCES_COLUMN_NAME)
                );
                
            } else {
                client = null;
            }
            return client;

        } catch (SQLException ex) {
            throw ex;
        }
    }
}
