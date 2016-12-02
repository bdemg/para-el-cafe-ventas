/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.DeliveriesDAO;
import java.sql.SQLException;
import model.DeliveriesList;
import view.DeliveriesBoard;

/**
 *
 * @author Evan-Ian-Ray
 */
public class DeliverySecretary {

    public DeliverySecretary() throws SQLException{
        
        getDeliveriesOfTheDay();
    }
    
    private void getDeliveriesOfTheDay() throws SQLException{
         Object[][] deliveries = DeliveriesDAO.getDeliveriesDAO().getTodaysDeliveries();
        
        DeliveriesBoard DB = new DeliveriesBoard();
        DB.setDeliveriesList(new DeliveriesList(deliveries));
    }
    
}
