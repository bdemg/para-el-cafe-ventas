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

import daos.ClientDAO;
import model.ErrorMessager;
import view.ClientForm;

public class ClientManager extends Controller{
    
    private final ClientForm clientForm;
    
    private final String EMPTY = "";
    
    public ClientManager(){
        
        this.clientForm = new ClientForm();
        this.clientForm.setVisible(true);
        this.clientForm.setResizable(false);
        this.clientForm.setLocationRelativeTo(null);
        
        this.addActionListeners();
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
            this.clientForm.getClientName().getText().equals(this.EMPTY)
            || this.clientForm.getClientPhoneNumber().getText().equals(this.EMPTY)
            || this.clientForm.getClientAddress().getText().equals(this.EMPTY)
            || this.clientForm.getClientAddressReferences().getText().equals(this.EMPTY)
        );
        return areFormFieldsEmpty;
    }
    
    private boolean isClientRegistered(){
        
        String phone_number = this.clientForm.getClientPhoneNumber().getText();
        ClientDAO clientTableDAO = ClientDAO.getClientTableDAO();
        boolean isClientRegistered = clientTableDAO.searchClientPhoneNumber(phone_number);
        return isClientRegistered;
    }
    
    private void storeClientInformation(){
        
        String name = this.clientForm.getClientName().getText();
        String phone_number = this.clientForm.getClientPhoneNumber().getText();
        String address = this.clientForm.getClientAddress().getText();
        String references = this.clientForm.getClientAddressReferences().getText();
        
        ClientDAO clientTableDAO = ClientDAO.getClientTableDAO();
        clientTableDAO.insertClientInformation(name, phone_number, address, references);
    }
}
