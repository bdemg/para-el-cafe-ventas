/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.renders;

import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This class is used to indicate that certian cells in a table are going to look like
 * a combo box.
 * @author Jorge A. Cano
 */
public class JComboBoxTableRenderer extends DefaultTableCellRenderer {

    JComboBox comboBox = new JComboBox();

    
    public JComboBoxTableRenderer() {
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
        
        this.comboBox = ( JComboBox ) input_value;
        return this.comboBox;
    }
}
