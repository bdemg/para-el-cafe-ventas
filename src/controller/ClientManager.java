package controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;

import daos.ClientsDAO;
import model.ErrorMessager;
import model.Keywords;
import view.ClientForm;

/**
 * This class represents the manager that registers new clients.
 * @author (c) Copyright 2016 José A. Soto. All Rights Reserved.
 */
public class ClientManager extends Controller{
    
    private final ClientForm clientForm;
    
    public ClientManager(){
        
        this.clientForm = new ClientForm();
        this.setupClientForm();
        
        this.addActionListeners();
    }
    
    private void setupClientForm(){
        
        this.clientForm.setVisible(true);
        this.clientForm.setResizable(false);
        this.clientForm.setLocationRelativeTo(null);
    }
    
    @Override
    protected void addActionListeners() {
        
        this.clientForm.getRegisterButton().addActionListener(this);
    }

    @Override
    public void actionPerformed( ActionEvent event ) {
        
        Object eventSource = event.getSource();
        
        if( this.isRegisteringClients( eventSource ) ){
            this.validateClientRegistration();
            this.cleanClientForm();
        }
    }
    
    private boolean isRegisteringClients( Object input_eventSource ){
        
        return input_eventSource == this.clientForm.getRegisterButton();
    }
    
    // Validates the storing of the new client.
    private void validateClientRegistration(){
        
        boolean isClientRegistered = this.isClientRegistered();
        boolean areFormFieldsEmpty = this.areFormFieldsEmpty();
        
        if( !areFormFieldsEmpty && !isClientRegistered ){
            this.storeClientInformation();
            
        } else if( areFormFieldsEmpty ){
            this.tellErrorMessagerToShowMessage( ErrorMessager.EMPTY_FIELDS );
            
        } else if( isClientRegistered ){
            this.tellErrorMessagerToShowMessage( ErrorMessager.CLIENT_REPETITION );
        }
    }
    
    private boolean areFormFieldsEmpty(){
        
        boolean areFormFieldsEmpty = (
            this.clientForm.getClientName().getText().equals( Keywords.EMPTY )
            || this.clientForm.getClientPhoneNumber().getText().equals( Keywords.EMPTY )
            || this.clientForm.getClientAddress().getText().equals( Keywords.EMPTY )
            || this.clientForm.getClientAddressReferences().getText().equals( Keywords.EMPTY )
        );
        return areFormFieldsEmpty;
    }
    
    private boolean isClientRegistered(){
        
        String phone_number = this.clientForm.getClientPhoneNumber().getText();
        ClientsDAO clientDAO = ClientsDAO.getClientsDAO();
        boolean isClientRegistered = clientDAO.findClient(phone_number);
        return isClientRegistered;
    }
    
    private void storeClientInformation(){
        
        String name = this.clientForm.getClientName().getText();
        String phone_number = this.clientForm.getClientPhoneNumber().getText();
        String address = this.clientForm.getClientAddress().getText();
        String references = this.clientForm.getClientAddressReferences().getText();
        
        ClientsDAO clientDAO = ClientsDAO.getClientsDAO();
        clientDAO.insertClientInformation(name, phone_number, address, references);
    }
    
    private void cleanClientForm(){
        
        this.clientForm.getClientName().setText( Keywords.EMPTY );
        this.clientForm.getClientPhoneNumber().setText( Keywords.EMPTY );
        this.clientForm.getClientAddress().setText( Keywords.EMPTY );
        this.clientForm.getClientAddressReferences().setText( Keywords.EMPTY );
    }
    
    private void tellErrorMessagerToShowMessage( String input_ErrorMessage ){
        
        ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
        errorMessager.showErrorMessage( input_ErrorMessage );
    }
    
    protected static String[] searchForClientInfo( String input_clientPhonenumber ) throws SQLException{
        return ClientsDAO.getClientsDAO().getClientInfo( input_clientPhonenumber );
    }
}
