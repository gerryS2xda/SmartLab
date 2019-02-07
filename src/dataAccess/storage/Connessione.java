package dataAccess.storage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class Connessione  {

	private static List<Connection> freeDbConnections;

	static {
		freeDbConnections = new LinkedList<Connection>();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("DB driver not found:"+ e.getMessage());
		} 
	}
	
	private static synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;
//		String ip = "192.168.1.125";
		String ip = "localhost";
		String port = "3306";
		String db = "smartlab";

		String username = "root";
		String password = "asd456JKL";
		
		newConnection = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+db, username, password);

/** NON TI AZZARDARE A TOCCARE QUESTA RIGA ALTRIMENTI TI AMMAZZO - ROCCO :) 
 *		newConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/smartlab?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", username, password);
**/	
		newConnection.setAutoCommit(true);

		return newConnection;
	}


	public static synchronized Connection getConnection() throws SQLException {
		Connection connection;

		if (!freeDbConnections.isEmpty()) {
			connection = (Connection) freeDbConnections.get(0);
			freeDbConnections.remove(0);

			try {
				if (connection.isClosed())
					connection = getConnection();
			} catch (SQLException e) {
				connection.close();
				connection = getConnection();
			}
		} else {
			connection = createDBConnection();		
		}

		return connection;
	}

	public static synchronized void releaseConnection(Connection connection) throws SQLException {
		if(connection != null) freeDbConnections.add(connection);
	}
}
