package businessLogic.utente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dataAccess.storage.Connessione;
import dataAccess.storage.Repository;
import dataAccess.storage.Specification;
import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Studente;

public class StudenteRepository implements Repository<Studente> {
	
	private static StudenteRepository instance;
	
	public static StudenteRepository getInstance(){
		
		instance = new StudenteRepository();
		return instance;
	}
	
	public static final String TABLE_NAME = "studente";
	public static final String TABLE_NAME_UTENTE = "utente";
	
	public StudenteRepository(){
		
	}
	
	public void add(Studente s) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQLUtente = "INSERT INTO " + TABLE_NAME_UTENTE
    			+ " (email, password, nome, cognome) VALUES (?, ?, ?, ?)";
		
		String insertSQLStudente = "INSERT INTO " + TABLE_NAME
				+ " (email, stato) VALUES (?, ?)";
		
		try{
			connection = Connessione.getConnection();
			
			preparedStatement = connection.prepareStatement(insertSQLUtente);
			preparedStatement.setString(1, s.getEmail());
			preparedStatement.setString(2, s.getPassword());
			preparedStatement.setString(3, s.getName());
			preparedStatement.setString(4, s.getSurname());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(insertSQLStudente);
			preparedStatement.setString(1, s.getEmail());
			preparedStatement.setBoolean(2, s.getStato());
			
			preparedStatement.executeUpdate();
		}finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}
	}
	
	public void delete(Studente s) throws SQLException{
		Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	
    	String deleteSQL = "DELETE FROM " + TABLE_NAME_UTENTE + " WHERE email = ?";	
    	
    	try{
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, s.getEmail());

			preparedStatement.executeUpdate();
		}finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}
	}
	
	public void update(Studente s) throws SQLException{
    	Connection connection = null;
    	PreparedStatement ps = null;
    	
    	String updateSQLUtente = "update " + TABLE_NAME_UTENTE + " set email = ?, password = ?, nome = ?, "
    			+ "cognome = ? where email = '" + s.getEmail() + "'";
    	String updateSQLStudente = "update " + TABLE_NAME + " set stato = ? where email = '" + s.getEmail() + "'";
    	
    	try{
    		connection = Connessione.getConnection();
    		
    		ps = connection.prepareStatement(updateSQLUtente);
    		ps.setString(1, s.getEmail());
    		ps.setString(2, s.getPassword());
    		ps.setString(3, s.getName());
    		ps.setString(4, s.getSurname());
    		
    		ps.executeUpdate();
    		ps.close();
    		
    		ps = connection.prepareStatement(updateSQLStudente);
    		ps.setBoolean(1, s.getStato());
    		ps.executeUpdate();
    	}finally{
			if(ps != null) { 
				ps.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}
    }
	
    public Studente findItemByQuery(Specification specification) throws SQLException{
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        String selectSQL= sqlSpecification.toSqlQuery();
        Studente s = new Studente();
        try{
        	connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
			
            while (rs.next()) {
            	s.setName(rs.getString(1));
				s.setSurname(rs.getString(2));
                s.setEmail(rs.getString(3));
				s.setStato(rs.getBoolean(4));
				s.setPassword(rs.getString(5));
			}
		}finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}
        
        return s;
    }
    
    public List<Studente> query(Specification specification) throws SQLException{
     	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        List<Studente> studenti = new ArrayList<>();
        String selectSQL= sqlSpecification.toSqlQuery();

        try{
            connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Studente s = new Studente();
				s.setName(rs.getString(1));
				s.setSurname(rs.getString(2));
                s.setEmail(rs.getString(3));
				s.setStato(rs.getBoolean(4));
				s.setPassword(rs.getString(5));
				studenti.add(s);
			}

		} finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}

        return studenti;
    }
}
