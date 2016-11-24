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
 * This class is based on external code that can be found in it's original form here
 * http://stackoverflow.com/questions/5064181/how-to-use-jlists-in-jtable-cells
 * @author Jorge A. Cano
 */
public class JSpinnerTableRenderer extends DefaultTableCellRenderer {

    JSpinner pane = new JSpinner();

    public JSpinnerTableRenderer() {
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
        
        pane.setValue( value );
        return pane;
    }
}
