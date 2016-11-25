/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.renders;

import java.awt.Component;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This class is used to indicate that certian cells in a table are going to look like
 * a spinner field.
 * This class is based on external code that can be found in it's original form here
 * http://stackoverflow.com/questions/5064181/how-to-use-jlists-in-jtable-cells
 * @author Jorge A. Cano
 */
public class JSpinnerTableRenderer extends DefaultTableCellRenderer {

    JSpinner spinner = new JSpinner();

    
    public JSpinnerTableRenderer() {
        super();
    }

    
    @Override
    public Component getTableCellRendererComponent(
        JTable input_table,
        Object input_value,
        boolean input_isSelected,
        boolean input_hasFocus,
        int input_rowIndex,
        int input_columnIndex 
    ) {
        
        this.spinner.setValue( input_value );
        return this.spinner;
    }
}
