package daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Antonio Soto
 */
public class DatabaseDAO {
    
    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String HOST = "jdbc:mysql://localhost/DBCafe?autoReconnect=true&useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "rootluigi44_44";
    
    protected Connection connectionToDatabase = null;
    
    public DatabaseDAO(){
        
        try {
            Class.forName(this.DRIVER);
            this.connectionToDatabase = DriverManager.getConnection(this.HOST, this.USER, this.PASSWORD);

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
}
