/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entrypoints;

import daos.DeliveriesDAO;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import model.DeliveriesList;
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
        DB.setDeliveriesList(new DeliveriesList(deliveries));
    }
    
}
