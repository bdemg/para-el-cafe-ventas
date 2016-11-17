package model;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * This class handles the program's errors.
 * @author (c) Copyright 2016 José A. Soto. All Rights Reserved.
 */
public class ErrorMessager {
    
    private static final ErrorMessager errorMessager = new ErrorMessager();    
    private final String ERROR_TITLE = "¡Error!";
    
    // Types of errors.
    public static final String CLIENT_NOT_FOUND = 
            "No se ha encontrado un cliente con el teléfono ingresado.";
    public static final String NOT_A_NUMBER = 
            "No ha ingresado un valor válido.";
    public static final String PRODUCT_NOT_FOUND = 
            "El numero ingresado no corresponde a ningún producto.";
    public static final String EMPTY_FIELDS = 
            "Asegurese de llenar todos los campos.";
    public static final String CLIENT_REPETITION = 
            "El cliente ya existe en la base de datos.";
    public static final String DATABASE_ERROR = 
            "No se puede conectar con la base de datos. Consulte con el Ingeniero.";
    
    private ErrorMessager(){
        ;
    }
    
    // Returns the instance of this class for use in other classes.
    public static ErrorMessager callErrorMessager(){
        
        return errorMessager;
    }
    
    // Shows the error message to the user on screen.
    public void showErrorMessage(String input_errorMessage){
        
        JFrame errorFrame = new JFrame();
        JOptionPane.showMessageDialog(
            errorFrame,
            input_errorMessage,
            this.ERROR_TITLE,
            JOptionPane.ERROR_MESSAGE
        );
    }
    
}
