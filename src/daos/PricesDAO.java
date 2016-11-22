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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge A. Cano
 */
public class PricesDAO extends DAO{
    private static final PricesDAO pricesDAO = new PricesDAO();
    
    private final String GET_PRICE_QUERY = "select * from product where name=?";
    private final String UPDATE_PRICE_QUERY = "UPDATE product" +
        "SET unitPrice=?" +
        "WHERE name=?";
    
    private final String PRICE_COLUMN_NAME = "unitPrice";
    
    
    public static PricesDAO getPricesDAO(){
        return pricesDAO;
    }
    
    
    private PricesDAO(){
        
        super();
    }
    
    
    public double getProductPrice( String input_productName ) throws SQLException{
        
        try {
            PreparedStatement preparedStatement = ( PreparedStatement ) 
                super.connectionToDatabase.prepareStatement( this.GET_PRICE_QUERY );
            
            preparedStatement.setString( QueryEnumeration.FIRST_QUERY_VALUE, input_productName );
            
            ResultSet resultSet = preparedStatement.executeQuery();
            
            resultSet.first();
            double productPrice = resultSet.getDouble( this.PRICE_COLUMN_NAME );
            
            return productPrice;
        } catch ( SQLException ex ) {
            throw ex;
        }
    }
    
    public void updateProductPice(String input_productName, double input_productPrice) throws SQLException{
        
        try {
            PreparedStatement preparedStatement = ( PreparedStatement )
                    super.connectionToDatabase.prepareStatement(this.UPDATE_PRICE_QUERY);
            
            preparedStatement.setDouble(QueryEnumeration.FIRST_QUERY_VALUE, input_productPrice);
            preparedStatement.setString(QueryEnumeration.SECOND_QUERY_VALUE, input_productName);
            
            preparedStatement.execute();
        } catch (SQLException ex) {
            throw ex;
        }
    }
}
