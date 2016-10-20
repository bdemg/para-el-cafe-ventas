/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import daos.PricesDAO;

/**
 *
 * @author Jorge A. Cano
 */
public class SalesCalculator {
    
    private static final SalesCalculator SalesCalculator = new SalesCalculator();
    
    private SalesCalculator(){
        ;
    }
    
    public static SalesCalculator getCalculator(){
        
        return SalesCalculator;
    }
    
    public void calculatePartialCosts( OrdersList modOrdersList ){
        
        for(int productCount = 0; productCount < modOrdersList.getRowCount(); productCount++){
        
            ProductsList productList = (ProductsList) modOrdersList.
                getValueAt( productCount, OrdersList.PRODUCT_NAME );
            String productName = (String) productList.getSelectedItem();
            double unitaryPrice = PricesDAO.getPricesDAO().getPrice( productName );
            
            double adjustedPrice = unitaryPrice * 
                (double) modOrdersList.getValueAt( productCount, OrdersList.PRODUCT_QUANTITY );
            
            modOrdersList.setValueAt( adjustedPrice, productCount, OrdersList.PRODUCT_PRICE );
        }
    }
    
    public double saleTotal( OrdersList inputOrdersList ){
        
        double totalCost = 0;
        for(int productCount = 0; productCount < inputOrdersList.getRowCount(); productCount++){
            
            totalCost = totalCost + 
                    (double) inputOrdersList.getValueAt( productCount, OrdersList.PRODUCT_PRICE );
        }
        
        return totalCost;
    }
}
