package businessLogic.responsabile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.storage.Connessione;
import dataAccess.storage.Specification;
import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Addetto;

public class ResponsabileRepository {
	private static ResponsabileRepository instance;

    public static ResponsabileRepository getInstance() {
        if (instance == null) {
            instance = new ResponsabileRepository();
        }
        return instance;
    }

    public static final String TABLE_NAME = "responsabile";
    
    public ResponsabileRepository(){
    	
    }
    
    public void add(Addetto a) throws SQLException{
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	
    	String insertSQL = "INSERT INTO " + TABLE_NAME
    			+ " (email, password, name, surname) VALUES (?, ?, ?, ?)";
    	
    	try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, a.getEmail());
			preparedStatement.setString(2, a.getPassword());
			preparedStatement.setString(3, a.getName());
			preparedStatement.setString(4, a.getSurname());
			
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
    
    public void delete(Addetto a) throws SQLException {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	
    	String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE email = ?";
    	
    	try{
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, a.getEmail());

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
    	
    	public void update(Addetto a) throws SQLException{
    		
    	}
    	
    public Addetto findItemByQuery(Specification specification) throws SQLException{
    	
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        String selectSQL= sqlSpecification.toSqlQuery();
        Addetto a = new Addetto();
        try{
        	connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
			
            while (rs.next()) {
                a.setEmail(rs.getString("email"));
				a.setPassword(rs.getString("password"));
				a.setName(rs.getString("nome"));
                a.setSurname(rs.getString("cognome"));
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
        
        return a;
    }
    
    public List<Addetto> query(Specification specification) throws SQLException{
    	
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        List<Addetto> responsabili = new ArrayList<>();
        String selectSQL= sqlSpecification.toSqlQuery();

        try{
            connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Addetto a = new Addetto();
                a.setEmail(rs.getString("email"));
				a.setPassword(rs.getString("password"));
				a.setName(rs.getString("nome"));
                a.setSurname(rs.getString("cognome"));

				responsabili.add(a);
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

        return responsabili;
    }
    
}
