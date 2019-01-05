package dataAccess.storage;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.project.utils.Utils;

public class Connessione {
	private static List<Connection> freeDbConnections;
	private static DataSource ds;
	static {
		try{
			if(!Utils.isDriverManagerEnabled){
				//contesto iniziale JNDI
				Context initCtx=new InitialContext();
				Context envCtx=(Context) initCtx.lookup("java:comp/env");
				//look up del data source
				ds=(DataSource)envCtx.lookup("jdbc/smartlab");
			}
		}catch(NamingException e){
			System.out.println("Error:asd" + e.getMessage());
		}
	}
	
	
	public static synchronized Connection getConnection() throws SQLException {
		
		Connection newConnection = null;
		if(Utils.isDriverManagerEnabled){
			newConnection = DMConnectionPool.getConnection();
		}else{
			newConnection = ds.getConnection();
		}
		return newConnection;
	
	}
	
	
}
