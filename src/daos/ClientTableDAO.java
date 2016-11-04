package daos;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Antonio Soto
 */
public class ClientTableDAO {

    private static final ClientTableDAO clientTableDAO = new ClientTableDAO();

    private final String DRIVER = "com.mysql.jdbc.Driver";
    private final String HOST = "jdbc:mysql://localhost/DBCafe?autoReconnect=true&useSSL=false";
    private final String USER = "root";
    private final String PASSWORD = "rootluigi44_44";

    private final String QUERY_INSERT = "INSERT INTO client VALUES (?, ?, ?, ?)";

    private final String QUERY_SEARCH = "SELECT * FROM client WHERE phone_number='";
    private final String QUERY_SEARCH_ENDING = "';";

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    private ClientTableDAO() {
        try {
            Class.forName(this.DRIVER);
            this.connection = DriverManager.getConnection(this.HOST, this.USER, this.PASSWORD);

            this.statement = this.connection.createStatement();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static ClientTableDAO getClientTableDAO() {
        return clientTableDAO;
    }

    public void insertClientInformation(
        String name,
        String phone_number,
        String address,
        String location_references
    ) {
        try {
            this.preparedStatement = (PreparedStatement) this.connection.prepareStatement( this.QUERY_INSERT );
            this.preparedStatement.setString(1, name);
            this.preparedStatement.setString(2, phone_number);
            this.preparedStatement.setString(3, address);
            this.preparedStatement.setString(4, location_references);
            this.preparedStatement.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
            
        } finally {
            this.closeResultSet();
            this.closeStatement();
            this.closeConnection();
        }
    }

    public boolean searchClientPhoneNumber(String input_PhoneNumber) {
        try {
            this.resultSet = this.statement.executeQuery(
                    this.QUERY_SEARCH
                    + input_PhoneNumber
                    + this.QUERY_SEARCH_ENDING
            );
            if (this.resultSet.last()) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;

        } finally {
            //this.close();
        }
        return false;
    }

    private void closeResultSet() {

        try {
            if (this.resultSet != null) {
                this.resultSet.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void closeStatement() {

        try {
            if (this.statement != null) {
                this.statement.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    private void closeConnection() {

        try {
            if (this.connection != null) {
                this.connection.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
