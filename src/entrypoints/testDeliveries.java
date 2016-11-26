/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrypoints;

import daos.DeliveriesDAO;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import view.DeliveriesBoard;

/**
 *
 * @author Jorge A. Cano
 */
public class testDeliveries {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Object[][] deliveries = DeliveriesDAO.getDeliveriesDAO().getTodaysDeliveries();
        
        DeliveriesBoard DB = new DeliveriesBoard();
        String[] columnNames = {"name", "phoneNumber", "address", "product_name", "quantity", "subtotal", "date"};
        DB.setDeliveriesList(new DefaultTableModel(deliveries, columnNames));
    }
    
}
