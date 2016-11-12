/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Jorge A. Cano
 */
public class PricesDAO extends DatabaseDAO{
    private static final PricesDAO pricesDAO = new PricesDAO();
    
    private final String PRICE_QUERY = "select * from product where name=?";
    
    private final int PRODUCT_NAME_COLUMN = 1;
    
    private final String PRICE_COLUMN_NAME = "price";
    
    
    public static PricesDAO getPricesDAO(){
        return pricesDAO;
    }
    
    
    private PricesDAO(){
        
        super();
    }
    
    
    public double getProductPrice(String input_ProductName) throws SQLException{
        
        try {
            PreparedStatement preparedStatement = ( PreparedStatement ) super.connectionToDatabase
                .prepareStatement( this.PRICE_QUERY );
            
            preparedStatement.setString( this.PRODUCT_NAME_COLUMN, input_ProductName );
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            resultSet.first();
            double productPrice = resultSet.getDouble( this.PRICE_COLUMN_NAME );
            
            return productPrice;
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
