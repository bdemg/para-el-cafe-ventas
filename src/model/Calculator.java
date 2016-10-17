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
public class Calculator {
    
    private static final Calculator calculator = new Calculator();
    
    private Calculator(){
        ;
    }
    
    public static Calculator getCalculator(){
        
        return calculator;
    }
    
    public void calculatePartialCosts( OrdersTaker modOrdersTaker ){
        
        for(int productCount = 0; productCount < modOrdersTaker.getRowCount(); productCount++){
        
            ProductsList productList = (ProductsList) modOrdersTaker.
                getValueAt( productCount, OrdersTaker.PRODUCT_NAME );
            String productName = (String) productList.getSelectedItem();
            double unitaryPrice = PricesDAO.getPricesDAO().getPrice( productName );
            
            double adjustedPrice = unitaryPrice * 
                (double) modOrdersTaker.getValueAt( productCount, OrdersTaker.PRODUCT_QUANTITY );
            
            modOrdersTaker.setValueAt( adjustedPrice, productCount, OrdersTaker.PRODUCT_PRICE );
        }
    }
    
    public double saleTotal( OrdersTaker inputOrdersTaker ){
        
        double totalCost = 0;
        for(int productCount = 0; productCount < inputOrdersTaker.getRowCount(); productCount++){
            
            totalCost = totalCost + 
                    (double) inputOrdersTaker.getValueAt( productCount, OrdersTaker.PRODUCT_PRICE );
        }
        
        return totalCost;
    }
}
