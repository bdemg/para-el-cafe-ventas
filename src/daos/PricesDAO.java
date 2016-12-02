/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class provides an interface for the prices information stored in the database
 * @author Jorge A. Cano
 */
public class PricesDAO extends DatabaseDAO{
    
    private static PricesDAO pricesDAO;
    
    private final String GET_PRICE_QUERY = "select * from product where name=?";
    private final String UPDATE_PRICE_QUERY = "UPDATE product SET unitPrice=? WHERE name=? ";
    
    private final String PRICE_COLUMN_NAME = "unitPrice";
    
    
    public static PricesDAO getPricesDAO() throws SQLException{
        
        if( PricesDAO.pricesDAO == null ){
            
            PricesDAO.pricesDAO = new PricesDAO();
        }
        
        return PricesDAO.pricesDAO;
    }
    
    
    private PricesDAO() throws SQLException{
        
        super();
    }
    
    
    //obtain the price of a given product from the database
    public double getProductPrice( String input_productName ) throws SQLException{
            
        PreparedStatement preparedStatement = ( PreparedStatement ) 
            super.connectionToDatabase.prepareStatement( this.GET_PRICE_QUERY );

        //add the values into the price obtaining query
        preparedStatement.setString( QueryEnumeration.FIRST_QUERY_VALUE, input_productName );

        ResultSet resultSet = preparedStatement.executeQuery();

        resultSet.first();
        double productPrice = resultSet.getDouble( this.PRICE_COLUMN_NAME );

        return productPrice;
    }
    
    
    //update the price of a given product in the database with a new price
    public void updateProductPrice(String input_productName, double input_productPrice) throws SQLException{
            
        PreparedStatement preparedStatement = ( PreparedStatement )
                super.connectionToDatabase.prepareStatement( this.UPDATE_PRICE_QUERY );

        //add the values into the price updating query
        preparedStatement.setDouble( QueryEnumeration.FIRST_QUERY_VALUE, input_productPrice );
        preparedStatement.setString( QueryEnumeration.SECOND_QUERY_VALUE, input_productName );

        preparedStatement.execute();
    }
}
