/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge A. Cano
 */
public class DeliveriesList extends DefaultTableModel {
    
    public static final int CLIENT_NAME = 0;
    public static final int TELEPHONE = 1;
    public static final int ADDRESS = 2;
    public static final int PRODUCT_NAME = 3;
    public static final int PRODUCT_QUANTITY = 4;
    public static final int PRODUCT_PRICE = 5;
    public static final int DELIVERY_DATE = 6;
    
    
    //the fields of the orders list
    private static final String[] FIELD_NAMES = {"Cliente", "Teléfono",
        "Dirección", "Producto", "Cantidad", "Precio", "Fecha"};
    
    public DeliveriesList( int input_rowCount ) {
        
        super( DeliveriesList.FIELD_NAMES, input_rowCount ); 
    }

    
    public DeliveriesList( Object[][] input_data ) {
        
        super( input_data, DeliveriesList.FIELD_NAMES );
    }

    //the diferent types of values that are can be written to the orders list
    @Override
    public Class<?> getColumnClass( int input_columnIndex ) {
        
        Class columnClass = String.class;
        switch ( input_columnIndex ) {
            
            case DeliveriesList.CLIENT_NAME:
                columnClass = String.class;
                break;
                
            case DeliveriesList.TELEPHONE:
                columnClass = String.class;
                break;
                
            case DeliveriesList.ADDRESS:
                columnClass = String.class;
                break;
                
            case DeliveriesList.PRODUCT_NAME:
                columnClass = String.class;
                break;
                
            case DeliveriesList.PRODUCT_QUANTITY:
                columnClass = Integer.class;
                break;
                
            case DeliveriesList.PRODUCT_PRICE:
                columnClass = Double.class;
                break;
                
            case DeliveriesList.DELIVERY_DATE:
                columnClass = String.class;
                break;
            
            default:
                break;
        }
        return columnClass;
    }
    
    
    @Override
    public boolean isCellEditable(int input_row, int input_column) {
        
        return false;
    }
    
}
