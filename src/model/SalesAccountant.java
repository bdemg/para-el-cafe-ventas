package model;

import daos.PricesDAO;
import java.sql.SQLException;

/**
 * This class is used to calculate partial and total costs for the products
 * contained in an order list. 
 * @author: Jorge A. Cano
 */
public class SalesAccountant {

    private static final SalesAccountant salesAccountant = new SalesAccountant();

    private SalesAccountant() {
        ;
    }

    public static SalesAccountant getSalesAccountant() {

        return salesAccountant;
    }

    //calculates the price of every product based on a stored price and the product quantity
    public void calculatePartialCosts(
        OrdersList mod_ordersList //list of the current ordered products. Calculated prices will be stored here 
    ) throws SQLException {
        
        //cycle through the whole list and calculate each price
        for ( int productCount = 0; productCount < mod_ordersList.getRowCount(); productCount++ ) {

            //obtain the unitary price of the current product
            ProductsList productList = ( ProductsList ) mod_ordersList.
                getValueAt( productCount, OrdersList.PRODUCT_NAME );
            String productName = ( String ) productList.getSelectedItem();
            
            double unitaryPrice = PricesDAO.getPricesDAO().getProductPrice( productName );

            //adjust the price based on the product quantity
            double adjustedPrice = unitaryPrice *
                (int) mod_ordersList.getValueAt( productCount, OrdersList.PRODUCT_QUANTITY );

            //write the adjusted price in the orders list
            mod_ordersList.setValueAt( adjustedPrice, productCount, OrdersList.PRODUCT_PRICE );
        }
    }


//calculate and return the sale total based on the partial costs
public double totalPriceOfSale( 
        OrdersList input_ordersList //list of the current ordered products with partial costs already calculated
    ){
        
        double totalCost = 0; //the acumulated prices of all products in the order
        
        //cycle through the whole list and add the price of a product to the total
        for( int productCount = 0; productCount < input_ordersList.getRowCount(); productCount++ ){
            
            totalCost = totalCost + 
                    (double) input_ordersList.getValueAt( productCount, OrdersList.PRODUCT_PRICE );
            
        }
        
        return totalCost;
    
    }

}
