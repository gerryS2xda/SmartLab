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

public class StudenteRepository implements Repository<Studente>{
	
	private static StudenteRepository instance;
	
	public static StudenteRepository getInstance(){
		if (instance == null){
			instance = new StudenteRepository();
		}
		return instance;
	}
	
	public static final String TABLE_NAME = "studente";
	
	public StudenteRepository(){
		
	}
	
	public void add(Studente s) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (email, stato) VALUES (?, ?)";
		
		try{
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, s.getEmail());
			preparedStatement.setBoolean(2, s.getStato());
			
			preparedStatement.executeUpdate();
			connection.commit();
		} finally {
			try{
				if(preparedStatement != null)
					preparedStatement.close();
			} finally {
				if(connection != null)
					Connessione.releaseConnection(connection);
			}
		}
	}
	
	public void delete(Studente s) throws SQLException{
		Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	
    	String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE email = ?";
    	
    	try{
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, s.getEmail());

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
	
	public void update(Studente s) throws SQLException{
    	Connection connection = null;
    	PreparedStatement ps = null;
    	
    	String updateSQL = "update " + TABLE_NAME + " set email = ?, stato = ? where email = " + s.getEmail();
    	
    	try{
    		connection = Connessione.getConnection();
    		ps = connection.prepareStatement(updateSQL);
    		ps.setString(1, s.getEmail());
    		ps.setBoolean(2, s.getStato());
    		
    		ps.executeUpdate();
    	} finally {
    		if(ps != null)
    			ps.close();
    		Connessione.releaseConnection(connection);
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
                s.setEmail(rs.getString("email"));
				s.setStato(rs.getBoolean("stato"));
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
                s.setEmail(rs.getString("email"));
				s.setStato(rs.getBoolean("stato"));

				studenti.add(s);
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

        return studenti;
    }
}
