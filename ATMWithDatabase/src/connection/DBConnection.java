package connection; //Name of the package

//Use to connect to the database
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection //Name of the class
{
	public static Connection prepareConnection() throws SQLException, ClassNotFoundException
	{
		//database URL
		// 3306 is the default port for mysql
		String connectionURL = "jdbc:mysql://localhost:3306/clt6"; // core is database name
		
		// database credentials
		String uname = "root";
		String pwd = "root";
		
		//Register JDBC DRIVER
		Class.forName("com.mysql.jdbc.Driver");
		
		//Open a connection
		Connection ref = DriverManager.getConnection(connectionURL,uname,pwd);
		return ref;
	} //end prepareConnection
} //end DBConnection