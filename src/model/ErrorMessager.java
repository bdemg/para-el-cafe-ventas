package model;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Antonio Soto
 */
public class ErrorMessager {
    
    private static final ErrorMessager errorMessager = new ErrorMessager();    
    private final String ERROR_TITLE = "¡Error!";
    
    public static final String INPUT_PASSWORD_ERROR = "Contraseña inválida.";
    public static final String FILE_ERROR = "No se encontró el archivo. Consulte con el Ingeniero.";
    public static final String SECURITY_QUESTION_ERROR = "Respuesta incorrecta.";
    public static final String CLIENT_NOT_FOUND = "No se ha encontrado un cliente con el teléfono ingresado.";
    public static final String NOT_A_NUMBER = "No ha ingresado un valor válido.";
    public static final String PRODUCT_NOT_FOUND = "El numero ingresado no corresponde a ningún producto.";
    public static final String EMPTY_FIELDS = "Asegurese de llenar todos los campos.";
    public static final String CLIENT_REPETITION = "El cliente ya existe en la base de datos.";
    public static final String DATABASE_ERROR = "No se puede conectar con la base de datos. Cosnulte con el ingeniero";
    
    private ErrorMessager(){
        ;
    }
    
    public static ErrorMessager callErrorMessager(){
        
        return errorMessager;
    }
    
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
