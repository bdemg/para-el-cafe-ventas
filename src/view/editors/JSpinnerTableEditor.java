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
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author Jorge A. Cano
 */
public class JSpinnerTableEditor extends DefaultCellEditor
{
    private final JSpinner spinner;
    
    private final int STARTING_VALUE = 1;
    private final int MINUMUM = 1;
    private final int MAXIMUM = 100;
    private final int STEP_BY = 1;

    public JSpinnerTableEditor()
    {
    	super( new JTextField() );
        
    	spinner = new JSpinner( 
            new SpinnerNumberModel(
                this.STARTING_VALUE,
                this.MINUMUM,
                this.MAXIMUM,
                this.STEP_BY
            )
        );
        
    	spinner.setBorder( null );
    }

    @Override
    public Component getTableCellEditorComponent(
    	JTable table,
        Object value,
        boolean isSelected,
        int row,
        int column 
    ) {
        
    	spinner.setValue( value );
    	return spinner;
    }

    @Override
    public Object getCellEditorValue()
    {
    	return spinner.getValue();
    }
}

