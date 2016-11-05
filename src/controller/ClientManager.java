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

import daos.ClientTableDAO;
import model.ErrorMessager;
import view.ClientForm;

public class ClientManager extends Controller{
    
    private ClientForm clientForm;
    
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
    public void actionPerformed(ActionEvent event) {
        
        Object eventSource = event.getSource();
        
        if(eventSource == this.clientForm.getRegisterButton()){
            this.validateClientRegistration();
        }
    }
    
    private void validateClientRegistration(){
        
        boolean isClientRegistered = this.isClientRegistered();
        if(!this.areFormFieldsEmpty() && !isClientRegistered){
            this.storeClientInformation();
            
        } else if(this.areFormFieldsEmpty()){
            ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
            errorMessager.showErrorMessage( ErrorMessager.EMPTY_FIELDS );
            
        } else if( isClientRegistered ){
            ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
            errorMessager.showErrorMessage( ErrorMessager.CLIENT_REPETITION );
        }
    }
    
    private boolean areFormFieldsEmpty(){
        boolean areFormFieldsEmpty = (this.clientForm.getClientName().getText().equals(this.EMPTY)
                || this.clientForm.getClientPhoneNumber().getText().equals(this.EMPTY)
                || this.clientForm.getClientAddress().getText().equals(this.EMPTY)
                || this.clientForm.getClientAddressReferences().getText().equals(this.EMPTY)
        );
        return areFormFieldsEmpty;
    }
    
    private boolean isClientRegistered(){
        
        String phone_number = this.clientForm.getClientPhoneNumber().getText();
        ClientTableDAO clientTableDAO = ClientTableDAO.getClientTableDAO();
        boolean isClientRegistered = clientTableDAO.searchClientPhoneNumber(phone_number);
        return isClientRegistered;
    }
    
    private void storeClientInformation(){
        
        String name = this.clientForm.getClientName().getText();
        String phone_number = this.clientForm.getClientPhoneNumber().getText();
        String address = this.clientForm.getClientAddress().getText();
        String references = this.clientForm.getClientAddressReferences().getText();
        
        ClientTableDAO clientTableDAO = ClientTableDAO.getClientTableDAO();
        clientTableDAO.insertClientInformation(name, phone_number, address, references);
    }
}
