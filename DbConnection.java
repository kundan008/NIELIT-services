package nielit.webService;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DbConnection {
    public  Connection getConnection() throws SQLException {
        Connection con = null;
        String ConnectionURL="jdbc:oracle:thin:system/oracle@localhost";
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(ConnectionURL);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } 
	return con;
    }
}