/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.editors;

import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

/**
 * This class is based on external code that can be found in it's original form here
 * http://stackoverflow.com/questions/5064181/how-to-use-jlists-in-jtable-cells
 * @author Jorge A. Cano
 */
public class JComboBoxTableEditor extends AbstractCellEditor implements TableCellEditor {

    JComboBox component = new JComboBox();

    @Override
    public Component getTableCellEditorComponent(
        JTable table,
        Object value,
        boolean isSelected,
        int rowIndex,
        int vColIndex 
    ) {
        component = ( ( JComboBox ) value );
        return ( ( JComboBox ) value );
    }

    @Override
    public Object getCellEditorValue() {
        
        return component;
    }

}
