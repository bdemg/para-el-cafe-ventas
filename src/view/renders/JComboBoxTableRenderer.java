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
 *
 * @author Jorge A. Cano
 */
public class JComboBoxTableRenderer extends DefaultTableCellRenderer {

    JComboBox pane = new JComboBox();

    public JComboBoxTableRenderer() {
        super();
    }

    @Override
    public Component getTableCellRendererComponent( 
        JTable table,
        Object value,
        boolean isSelected,
        boolean hasFocus,
        int row,
        int column 
    ) {
        
        pane = ( JComboBox ) value;
        return pane;
    }
}
