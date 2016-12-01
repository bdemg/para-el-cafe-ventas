/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.editors;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import model.ProductQuantityTemplate;

/**
 * This class is used to control the behaviour of a spinner field in a table.
 * @author Jorge A. Cano
 * 
 */
public class JSpinnerTableEditor extends DefaultCellEditor
{
    private final JSpinner spinner;

    public JSpinnerTableEditor(){
        
    	super( new JTextField() );
    	this.spinner = new JSpinner( new ProductQuantityTemplate() );
    	this.spinner.setBorder( null );
    }

    
    @Override
    public Component getTableCellEditorComponent(
    	JTable input_table,
        Object input_value,
        boolean input_isSelected,
        int input_rowIndex,
        int input_columnIndex 
    ) {
        
    	this.spinner.setValue( input_value );
    	return this.spinner;
    }

    
    @Override
    public Object getCellEditorValue(){
        
    	return this.spinner.getValue();
    }
}

