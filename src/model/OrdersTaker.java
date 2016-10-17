/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jorge A. Cano
 */
public class OrdersTaker extends DefaultTableModel {
    
    public static final int PRODUCT_NUMBER = 0;
    public static final int PRODUCT_NAME = 1;
    public static final int PRODUCT_QUANTITY = 2;
    public static final int PRODUCT_PRICE = 3;
    
    
    private static final String[] COLUMN_TITLES = { "#", "Producto",
        "Cantidad", "Costo" };
    
    public OrdersTaker(int inputRowCount) {
        
        super(OrdersTaker.COLUMN_TITLES, inputRowCount); 
    }

    
    public OrdersTaker(Object[][] data) {
        
        super(data, OrdersTaker.COLUMN_TITLES);
    }

    @Override
    public Class<?> getColumnClass(int inputColumnIndex) {
        Class columnClass = String.class;
        switch ( inputColumnIndex ) {
            
            case OrdersTaker.PRODUCT_NUMBER:
                columnClass = Integer.class;
                break;
                
            case OrdersTaker.PRODUCT_NAME:
                columnClass = JComboBox.class;
                break;
                
            case OrdersTaker.PRODUCT_QUANTITY:
                columnClass = Integer.class;
                break;
                
            case OrdersTaker.PRODUCT_PRICE:
                columnClass = Double.class;
                break;
                
            default:
                break;
        }
        return columnClass;
    }
    
    
    @Override
    public Object getValueAt( int inputRow, int inputColumn ) {
        if(inputColumn == 0) {
            
            return inputRow + 1; 
        } else{
            
            return super.getValueAt( inputRow, inputColumn );
        }
    }
}
