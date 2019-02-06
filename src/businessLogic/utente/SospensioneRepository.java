package businessLogic.utente;

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

public class SospensioneRepository implements Repository<Sospensione>{
	
	private static SospensioneRepository instance;

    public static SospensioneRepository getInstance() {
        if (instance == null) {
            instance = new SospensioneRepository();
        }
        return instance;
    }

    public static final String TABLE_NAME = "sospensione";
    public static final String TABLE_NAME_STUDENTE = "studente";
    
    public SospensioneRepository(){
    	
    }
    
    public void add(Sospensione s) throws SQLException{
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	
    	String insertSQLSospensione = "INSERT INTO " + TABLE_NAME
    			+ " (IDsospensione, motivazione, studente) VALUES (?, ?, ?)";
    	String insertSQLStudente = "update " + TABLE_NAME_STUDENTE + " set stato = ? where email = " + "'"+s.getStudente()+"'";
    	
    	try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(insertSQLSospensione);
			preparedStatement.setInt(1, s.getID());
			preparedStatement.setString(2, s.getMotivazione());
			preparedStatement.setString(3, s.getStudente());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(insertSQLStudente);
			preparedStatement.setBoolean(1, true);
			preparedStatement.executeUpdate();
			
		//	connection.commit();
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
    	
    	String deleteSQLSospensione = "DELETE FROM " + TABLE_NAME
    			+ " WHERE id = ?";
    	String deleteSQLStudente = "update " + TABLE_NAME_STUDENTE + " set stato = ? where email = " + "'"+s.getStudente()+"'";
    	
    	try{
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQLSospensione);
			preparedStatement.setInt(1, s.getID());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(deleteSQLStudente);
			preparedStatement.setBoolean(1, false);
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
    	Connection connection = null;
    	PreparedStatement ps = null;
    	
    	String updateSQLSospensione = "update " + TABLE_NAME + " set IDsospensione = ?,"
    			+ "motivazione = ?, studente = ? where IDsospensione = " + s.getID();
    	
    	String updateSQLStudente = "update " + TABLE_NAME_STUDENTE + " set stato = ? where email = " + "'"+s.getStudente()+"'";
    	
    	try{
    		connection = Connessione.getConnection();
    		ps = connection.prepareStatement(updateSQLSospensione);
    		
    		ps.setInt(1, s.getID());
    		ps.setString(2, s.getMotivazione());
    		ps.setString(3, s.getStudente());
    		ps.executeUpdate();
       		ps.close();
    
       		ps = connection.prepareStatement(updateSQLStudente);
       		//Come faccio l'upgrade dello stato?
       		ps.setBoolean(1, true); 
       		ps.executeUpdate();       		
    	
    	} finally {
    		if(ps != null)
    			ps.close();
    		Connessione.releaseConnection(connection);
    	}
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
                s.setID(rs.getInt("IDsospensione"));
				s.setMotivazione(rs.getString("motivazione"));
                s.setStudente(rs.getString("studente"));
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
                s.setID(rs.getInt("IDsospensione"));
				s.setMotivazione(rs.getString("motivazione"));
                s.setStudente(rs.getString("studente"));
                
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
