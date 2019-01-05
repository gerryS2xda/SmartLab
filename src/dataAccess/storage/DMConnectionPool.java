package dataAccess.storage;

import java.sql.*;
import java.util.ArrayList;

public class DMConnectionPool {

	//instance field
	private static ArrayList<Connection> freeDBConnections; //lista di connessioni disponibili
	
	//parametri di configurazione connessione DB
	private static final String username = "root";
	private static final String password = "0582";
	private static final String host = "localhost";
	private static final String port = "3306";
	private static final String nameDB = "smartlab";
	
	static{
		freeDBConnections = new ArrayList<Connection>();
		//caricamento del driver
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println("Error load driver jdbc!!");
			e.printStackTrace();
		}
		
	}
	
	private static synchronized Connection makeNewConnection() throws SQLException{
		Connection conn = null;
		
		String url = "jdbc:mysql://" + host + ":" + port + "/" + nameDB;
		conn = DriverManager.getConnection(url, username, password);
		
		return conn;
	}
	
	public static synchronized Connection getConnection()throws SQLException{
		Connection conn = null;
		if(!freeDBConnections.isEmpty()){	//se ci sono connessione disponibili
			conn = freeDBConnections.get(0);	//prendi la connessione in testa alla lista per usarla
			freeDBConnections.remove(0); //rimuovi conn. da lista dato che una conn. e' stata impiegata
			
			try{
				if(conn.isClosed()){
					conn = getConnection();	//se non � disponbile, prendine un'altra
				}
			}catch(SQLException e){
				conn.close();
				conn = getConnection();
			}
		}else{
			conn = makeNewConnection(); //si crea una nuova connessione se non ci sono connessioni disponibili
		}
		return conn;
	}
	
	public static synchronized void releaseConnection(Connection c){
		if(c != null){	//se la conn. non � stata chiusa
			freeDBConnections.add(c); //viene aggunta alla lista della conn. disponibili
		}
	}
}
