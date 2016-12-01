package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class sets up the connection to the database.
 * @author (c) Copyright 2016 José A. Soto. All Rights Reserved.
 */
public class DAO {
    
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String HOST = "jdbc:mysql://localhost/DBCafe?autoReconnect=true&useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "rootluigi44_44";
    
    protected Connection connectionToDatabase = null;
    
    public DAO() throws SQLException{
        
        try {
            Class.forName( this.DRIVER );
            this.connectionToDatabase = DriverManager.
                getConnection( this.HOST, this.USER, this.PASSWORD );

        } catch (SQLException ex) {
            throw ex;
        } catch (ClassNotFoundException ex) {
            throw new SQLException();
        }
    }
}
