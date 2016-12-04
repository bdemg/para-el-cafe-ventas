/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import daos.DeliveriesDAO;
import java.sql.SQLException;
import model.TodaysDeliveriesList;
import view.TodaysDeliveriesBoard;

/**
 * This class represents the person who checks what 
 * orders needs to be delivered the current date
 * @author Evan-Ian-Ray
 */
public class DeliverySecretary {

    public DeliverySecretary() throws SQLException{
        
        getDeliveriesOfTheDay();
    }
    
    private void getDeliveriesOfTheDay() throws SQLException{
         Object[][] deliveries = DeliveriesDAO.getDeliveriesDAO().getTodaysDeliveries();
        
        TodaysDeliveriesBoard DB = new TodaysDeliveriesBoard();
        DB.setDeliveriesList(new TodaysDeliveriesList(deliveries));
    }
    
}
