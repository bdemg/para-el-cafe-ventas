/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import daos.SalesDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * This class properly stores sales that have been made by the phone operator
 *
 * @author Jorge A. Cano
 */
public class SalesManager {

    private static final SalesManager salesManager = new SalesManager();

    private SalesManager() {
        ;
    }

    public static SalesManager callSalesManager() {
        return SalesManager.salesManager;
    }

    //store each order of the sale in the database
    public void storeSale(
        OrdersList input_saleOrders,
        String input_phoneNumber,
        int input_dueDay,
        int input_dueMonth,
        int input_dueYear,
        int input_dueHour,
        int input_dueMinute
    ) throws SQLException {

        //cycle through the orders list of the sale and save each one 
        for ( int ordersCount = 0; ordersCount < input_saleOrders.getRowCount(); ordersCount++ ) {

            ProductsList productList = ( ProductsList ) input_saleOrders.getValueAt( ordersCount,
                OrdersList.PRODUCT_NAME );

            SalesDAO.getSalesDAO().saveSale(
                input_phoneNumber,
                (String) productList.getSelectedItem(),
                (int) input_saleOrders.getValueAt(ordersCount, OrdersList.PRODUCT_QUANTITY),
                (double) input_saleOrders.getValueAt(ordersCount, OrdersList.PRODUCT_PRICE),
                this.formatDueDate(
                    input_dueDay,
                    input_dueMonth,
                    input_dueYear,
                    input_dueHour,
                    input_dueMinute
                )
            );
        } //end of for
    }

    //create a properly formatted date that can be saved along the info of each order
    private Date formatDueDate(
        int input_DueDay,
        int input_DueMonth,
        int input_DueYear,
        int input_DueHour,
        int input_DueMinute
    ) {
        Calendar dueDate = Calendar.getInstance();
        
        dueDate.set(
            input_DueYear,
            ( input_DueMonth - 1 ),
            input_DueDay,
            input_DueHour,
            input_DueMinute
        );

        return new Date(dueDate.getTimeInMillis());
    }

}
