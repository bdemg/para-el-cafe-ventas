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
        
        if(!this.areTextFieldsEmpty() && !this.isPhoneNumberAlreadyInDataBase()){
            this.registerClientInformation();
            
        } else if(this.areTextFieldsEmpty()){
            ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
            errorMessager.showErrorMessage( ErrorMessager.EMPTY_FIELDS );
            
        } else if(this.isPhoneNumberAlreadyInDataBase()){
            ErrorMessager errorMessager = ErrorMessager.callErrorMessager();
            errorMessager.showErrorMessage( ErrorMessager.CLIENT_REPETITION );
        }
    }
    
    private boolean areTextFieldsEmpty(){
        boolean areTextFieldsEmpty = (this.clientForm.getNameTextField().getText().equals(this.EMPTY)
                || this.clientForm.getPhoneNumberTextField().getText().equals(this.EMPTY)
                || this.clientForm.getAddressTextField().getText().equals(this.EMPTY)
                || this.clientForm.getReferencesTextArea().getText().equals(this.EMPTY)
        );
        return areTextFieldsEmpty;
    }
    
    private boolean isPhoneNumberAlreadyInDataBase(){
        
        String phone_number = this.clientForm.getPhoneNumberTextField().getText();
        ClientTableDAO clientTableDAO = ClientTableDAO.getClientTableDAO();
        boolean isPhoneNumberAlreadyInDataBase = clientTableDAO.searchClientPhoneNumber(phone_number);
        return isPhoneNumberAlreadyInDataBase;
    }
    
    private void registerClientInformation(){
        
        String name = this.clientForm.getNameTextField().getText();
        String phone_number = this.clientForm.getPhoneNumberTextField().getText();
        String address = this.clientForm.getAddressTextField().getText();
        String references = this.clientForm.getReferencesTextArea().getText();
        
        ClientTableDAO clientTableDAO = ClientTableDAO.getClientTableDAO();
        clientTableDAO.insertClientInformation(name, phone_number, address, references);
    }
}
