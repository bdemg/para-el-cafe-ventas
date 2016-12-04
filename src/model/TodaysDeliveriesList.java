/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.table.DefaultTableModel;

/**
 * This class represents the list of deliveries that are due for today
 * @author Jorge A. Cano
 */
public class TodaysDeliveriesList extends DefaultTableModel {
    
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
    
    public TodaysDeliveriesList( int input_rowCount ) {
        
        super(TodaysDeliveriesList.FIELD_NAMES, input_rowCount ); 
    }

    
    public TodaysDeliveriesList( Object[][] input_data ) {
        
        super(input_data, TodaysDeliveriesList.FIELD_NAMES );
    }

    //the diferent types of values that are can be written to the orders list
    @Override
    public Class<?> getColumnClass( int input_columnIndex ) {
        
        Class columnClass = String.class;
        switch ( input_columnIndex ) {
            
            case TodaysDeliveriesList.CLIENT_NAME:
                columnClass = String.class;
                break;
                
            case TodaysDeliveriesList.TELEPHONE:
                columnClass = String.class;
                break;
                
            case TodaysDeliveriesList.ADDRESS:
                columnClass = String.class;
                break;
                
            case TodaysDeliveriesList.PRODUCT_NAME:
                columnClass = String.class;
                break;
                
            case TodaysDeliveriesList.PRODUCT_QUANTITY:
                columnClass = Integer.class;
                break;
                
            case TodaysDeliveriesList.PRODUCT_PRICE:
                columnClass = Double.class;
                break;
                
            case TodaysDeliveriesList.DELIVERY_DATE:
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
