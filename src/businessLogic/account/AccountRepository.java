package businessLogic.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import dataAccess.storage.Repository;
import dataAccess.storage.Connessione;
import dataAccess.storage.Specification;
import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Studente;
import dataAccess.storage.bean.Utente;

public class AccountRepository implements Repository<Utente>{
		
		private static AccountRepository instance;

	    public static AccountRepository getInstance() {
	        if (instance == null) {
	            instance = new AccountRepository();
	        }
	        return instance;
	    }

	    public static final String TABLE_NAME = "account";
	    
	    public AccountRepository(){
	    	
	    }
	    
	    public void add(Utente u) throws SQLException{
	    	Connection connection = null;
	    	PreparedStatement preparedStatement = null;
	    	
	    	String insertSQL = "INSERT INTO " + TABLE_NAME
	    			+ " (email, password, nome, cognome) VALUES (?, ?, ?, ?)";
	    	
	    	try {
				connection = Connessione.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setString(1, (u.getEmail()));
				preparedStatement.setString(2, (u.getPassword()));
				preparedStatement.setString(3, u.getName());
				preparedStatement.setString(4, u.getSurname());
				
				preparedStatement.executeUpdate();
				connection.commit();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						Connessione.releaseConnection(connection);
				}
			}  	
	    }
	    
	    public void delete(Utente u) throws SQLException {
	    	Connection connection = null;
	    	PreparedStatement preparedStatement = null;
	    	
	    	String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE email = ?";
	    	
	    	try{
				connection = Connessione.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setString(1, u.getEmail());

				preparedStatement.executeUpdate();
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						Connessione.releaseConnection(connection);
				}
			}
	    }
	    	
	    public void update(Utente u) throws SQLException{
	    	Connection connection = null;
	    	PreparedStatement ps = null;
	    	
	    	String updateSQL = "update " + TABLE_NAME + " set email = ?, password = ?, nome = ?, "
	    			+ "cognome = ? where email = " + u.getEmail();
	    	
	    	try{
	    		connection = Connessione.getConnection();
	    		ps = connection.prepareStatement(updateSQL);
	    		
	    		ps.setString(1, u.getEmail());
	    		ps.setString(2, u.getPassword());
	    		ps.setString(3, u.getName());
	    		ps.setString(4, u.getSurname());
	    		
	    		ps.executeUpdate();
	    	} finally {
	    		if(ps != null)
	    			ps.close();
	    		Connessione.releaseConnection(connection);
	    	}
	    }
	    	
	    public Utente findItemByQuery(Specification specification) throws SQLException{
	    	
	    	Connection connection = null;
			PreparedStatement preparedStatement = null;
	        SqlSpecification sqlSpecification = (SqlSpecification) specification;
	        String selectSQL= sqlSpecification.toSqlQuery();
	        Utente u = new Studente();
	        try{
	        	connection = Connessione.getConnection();
	            preparedStatement = connection.prepareStatement(selectSQL);
	            ResultSet rs = preparedStatement.executeQuery();
				
	            while (rs.next()) {
	                u.setEmail(rs.getString("email"));
					u.setPassword(rs.getString("password"));
					u.setName(rs.getString("nome"));
	                u.setSurname(rs.getString("cognome"));
				}
			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						Connessione.releaseConnection(connection);
				}
			}
	        
	        return u;
	    }
	    
	    public List<Utente> query(Specification specification) throws SQLException{
	    	
	    	Connection connection = null;
			PreparedStatement preparedStatement = null;
	        SqlSpecification sqlSpecification = (SqlSpecification) specification;
	        List<Utente> utenti = new ArrayList<>();
	        String selectSQL= sqlSpecification.toSqlQuery();

	        try{
	            connection = Connessione.getConnection();
	            preparedStatement = connection.prepareStatement(selectSQL);
	            ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					Utente u = new Studente();
	                u.setEmail(rs.getString("email"));
					u.setPassword(rs.getString("password"));
					u.setName(rs.getString("nome"));
	                u.setSurname(rs.getString("cognome"));

					utenti.add(u);
				}

			} finally {
				try {
					if (preparedStatement != null)
						preparedStatement.close();
				} finally {
					if (connection != null)
						Connessione.releaseConnection(connection);
				}
			}

	        return utenti;
	    }
	    
}
