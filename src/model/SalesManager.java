/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.BakeryPhoneOperator;
import daos.OrdersDAO;
import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.SalesSheet;



/**
 *
 * @author Jorge A. Cano
 */
public class SalesManager {
    
    private static final SalesManager salesManager = new SalesManager();
    
    private SalesManager(){
        ;
    }
    
    public static SalesManager callSalesManager(){
        return SalesManager.salesManager;
    }
    
    public void storeSale(
        OrdersList input_saleOrders,
        String input_phoneNumber,
        int input_dueDay,
        int input_dueMonth,
        int input_dueYear,
        int input_dueHour,
        int input_dueMinute
    ) {
        
        for( int ordersCount = 0; ordersCount < input_saleOrders.getRowCount(); ordersCount++ ){
            
            ProductsList productList = (ProductsList) input_saleOrders.getValueAt(ordersCount,
                OrdersList.PRODUCT_NAME );
            String productName = (String) productList.getSelectedItem();
            
            int productQuantity = (int) input_saleOrders.getValueAt(ordersCount,
                    OrdersList.PRODUCT_QUANTITY );
            
            double productPrice = (double) input_saleOrders.getValueAt(ordersCount,
                    OrdersList.PRODUCT_PRICE);
            
            try {
                
                OrdersDAO.getOrdersDAO().saveSale(
                        input_phoneNumber,
                        productName,
                        productQuantity,
                        productPrice,
                        input_dueDay,
                        input_dueMonth,
                        input_dueYear,
                        input_dueHour,
                        input_dueMinute);
                
            } catch (Exception ex) {
                Logger.getLogger(BakeryPhoneOperator.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    private Date formatDueDate(
        int inputDueDay,
        int inputDueMonth,
        int inputDueYear,
        int inputDueHour,
        int inputDueMinute
    ) {
        Calendar date = Calendar.getInstance();
        date.set(
            inputDueYear,
            ( inputDueMonth - 1 ), 
            inputDueDay, 
            inputDueDay, 
            inputDueDay
        );
        
        return new Date( date.getTimeInMillis() );
    }
    
}
