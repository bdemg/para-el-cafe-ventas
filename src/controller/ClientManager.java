/**
 * File: ClientManager.java
 * This file represents the ClientManager class who extends from Controller
 * This class is used to manage and register the client information that the
 * view ClientForm receives, into the Database using ClientDAO
 * Methods: validateClientRegistration(), registerClientInformation().
 */
package controller;

// (c) Copyright 2016 José A. Soto. All Rights Reserved.
import java.awt.event.ActionEvent;

import daos.ClientsDAO;
import java.sql.SQLException;
import model.ErrorMessager;
import model.Keywords;
import view.ClientForm;

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
    
    private void validateClientRegistration(){
        
        boolean isClientRegistered = this.isClientRegistered();
        boolean areFormFieldsEmpty = this.areFormFieldsEmpty();
        
        if( !areFormFieldsEmpty && !isClientRegistered ){
            this.storeClientInformation();
            
        } else if( areFormFieldsEmpty ){
            ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
            errorMessager.showErrorMessage( ErrorMessager.EMPTY_FIELDS );
            
        } else if( isClientRegistered ){
            ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
            errorMessager.showErrorMessage( ErrorMessager.CLIENT_REPETITION );
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
        ClientsDAO clientTableDAO = ClientsDAO.getClientsDAO();
        boolean isClientRegistered = clientTableDAO.searchClientPhoneNumber(phone_number);
        return isClientRegistered;
    }
    
    private void storeClientInformation(){
        
        String name = this.clientForm.getClientName().getText();
        String phone_number = this.clientForm.getClientPhoneNumber().getText();
        String address = this.clientForm.getClientAddress().getText();
        String references = this.clientForm.getClientAddressReferences().getText();
        
        ClientsDAO clientTableDAO = ClientsDAO.getClientsDAO();
        clientTableDAO.insertClientInformation(name, phone_number, address, references);
    }
    
    private void cleanClientForm(){
        
        this.clientForm.getClientName().setText( Keywords.EMPTY );
        this.clientForm.getClientPhoneNumber().setText( Keywords.EMPTY );
        this.clientForm.getClientAddress().setText( Keywords.EMPTY );
        this.clientForm.getClientAddressReferences().setText( Keywords.EMPTY );
    }
    
    
    protected static String[] searchForClientInfo( String input_clientPhonenumber ) throws SQLException{
        return ClientsDAO.getClientsDAO().getClientInfo( input_clientPhonenumber );
    }
}
