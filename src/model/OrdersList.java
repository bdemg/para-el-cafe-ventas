/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *This class is where the client's orders are written 
 * @author Jorge A. Cano
 */
public class OrdersList extends DefaultTableModel {
    
    public static final int PRODUCT_NUMBER = 0;
    public static final int PRODUCT_NAME = 1;
    public static final int PRODUCT_QUANTITY = 2;
    public static final int PRODUCT_PRICE = 3;
    
    
    //the fields of the orders list
    private static final String[] FIELD_NAMES = { "#", "Producto",
        "Cantidad", "Costo" };
    
    public OrdersList(int input_rowCount) {
        
        super(OrdersList.FIELD_NAMES, input_rowCount); 
    }

    
    public OrdersList(Object[][] input_data) {
        
        super(input_data, OrdersList.FIELD_NAMES);
    }

    @Override
    public Class<?> getColumnClass(int input_columnIndex) {
        Class columnClass = String.class;
        switch ( input_columnIndex ) {
            
            case OrdersList.PRODUCT_NUMBER:
                columnClass = Integer.class;
                break;
                
            case OrdersList.PRODUCT_NAME:
                columnClass = JComboBox.class;
                break;
                
            case OrdersList.PRODUCT_QUANTITY:
                columnClass = Integer.class;
                break;
                
            case OrdersList.PRODUCT_PRICE:
                columnClass = Double.class;
                break;
                
            default:
                break;
        }
        return columnClass;
    }
    
    
    @Override
    public Object getValueAt( int input_row, int input_column ) {
        if(input_column == 0) {
            
            return input_row + 1; 
        } else{
            
            return super.getValueAt( input_row, input_column );
        }
    }
    
    
    //obtain the list of product numbers that currently are in the order list
    public Object[] getProductNumberList(){
        
        Object[] productNumbers = new Object[this.getRowCount()];
		
        //cycle through the whole orders list and make the product numbers list
        for( int productCount = 0; productCount < this.getRowCount(); productCount++ ){
            
            productNumbers[ productCount ] = productCount + 1;
        }
        
        return productNumbers;
    }
}
