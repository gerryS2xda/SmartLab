package businessLogic.account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.storage.Connessione;
import dataAccess.storage.Repository;
import dataAccess.storage.Specification;
import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Sospensione;
import dataAccess.storage.bean.Utente;

public class SospensioneRepository implements Repository<Sospensione>{
	
	private static SospensioneRepository instance;

    public static SospensioneRepository getInstance() {
        if (instance == null) {
            instance = new SospensioneRepository();
        }
        return instance;
    }

    public static final String TABLE_NAME = "sospensione";
    
    public SospensioneRepository(){
    	
    }
    
    public void add(Sospensione s) throws SQLException{
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	
    	String insertSQL = "INSERT INTO " + TABLE_NAME
    			+ " (id, durata, data, motivazione, studente, addetto) VALUES (?, ?, ?, ?, ?, ?)";
    	
    	try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, (s.getID()));
			preparedStatement.setInt(2, (s.getDurata()));
			preparedStatement.setDate(3, s.getData()); //????????
			preparedStatement.setString(4, s.getMotivazione());
			preparedStatement.setString(5, s.getStudente());
			preparedStatement.setString(6, s.getAddetto());
			
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
    
    public void delete(Sospensione s) throws SQLException {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	
    	String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
    	
    	try{
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, s.getID());

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
    	
    	public void update(Sospensione s) throws SQLException{
    		
    	}
    	
    public Sospensione findItemByQuery(Specification specification) throws SQLException{
    	
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        String selectSQL= sqlSpecification.toSqlQuery();
        Sospensione s = new Sospensione();
        try{
        	connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
			
            while (rs.next()) {
                s.setID(rs.getInt("id"));
				s.setDurata(rs.getInt("durata"));
				s.setData(rs.getDate("data"));
				s.setMotivazione(rs.getString("motivazione"));
                s.setStudente(rs.getString("studente"));
                s.setAddetto(rs.getString("addetto"));
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
    
    public List<Sospensione> query(Specification specification) throws SQLException{
    	
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        List<Sospensione> sospesi = new ArrayList<>();
        String selectSQL= sqlSpecification.toSqlQuery();

        try{
            connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Sospensione s = new Sospensione();
                s.setID(rs.getInt("id"));
				s.setDurata(rs.getInt("durata"));
				s.setData(rs.getDate("data"));
				s.setMotivazione(rs.getString("motivazione"));
                s.setStudente(rs.getString("studente"));
                s.setAddetto(rs.getString("addetto"));
				sospesi.add(s);
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

        return sospesi;
    }
    
}
