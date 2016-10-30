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
    
    public void calculatePartialCosts( OrdersList mod_ordersList ){
        
        for(int productCount = 0; productCount < mod_ordersList.getRowCount(); productCount++){
        
            ProductsList productList = (ProductsList) mod_ordersList.
                getValueAt( productCount, OrdersList.PRODUCT_NAME );
            String productName = (String) productList.getSelectedItem();
            double unitaryPrice = PricesDAO.getPricesDAO().getPrice( productName );
            
            double adjustedPrice = unitaryPrice * 
                (double) mod_ordersList.getValueAt( productCount, OrdersList.PRODUCT_QUANTITY );
            
            mod_ordersList.setValueAt( adjustedPrice, productCount, OrdersList.PRODUCT_PRICE );
        }
    }
    
    public double saleTotal( OrdersList input_ordersList ){
        
        double totalCost = 0;
        for(int productCount = 0; productCount < input_ordersList.getRowCount(); productCount++){
            
            totalCost = totalCost + 
                    (double) input_ordersList.getValueAt( productCount, OrdersList.PRODUCT_PRICE );
        }
        
        return totalCost;
    }
}
