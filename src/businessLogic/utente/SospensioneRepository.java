package businessLogic.utente;

import java.sql.Connection;
import java.sql.Date;
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

public class SospensioneRepository implements Repository<Sospensione> {
	
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
    			+ " (durata, data, motivazione, studente, addetto) VALUES (?, ?, ?, ?, ?)";
    	String insertSQLStudente = "update " + TABLE_NAME_STUDENTE + " set stato = ? where email = " + "'"+s.getStudente()+"'";
    	
    	try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(insertSQLSospensione);
			preparedStatement.setInt(1, s.getDurata());
			preparedStatement.setDate(2, Date.valueOf(s.getData()));
			preparedStatement.setString(3, s.getMotivazione());
			preparedStatement.setString(4, s.getStudente());
			preparedStatement.setString(5, s.getAddetto());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(insertSQLStudente);
			preparedStatement.setBoolean(1, false);
			preparedStatement.executeUpdate();
			
		}finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}	
    }
    
    public void delete(Sospensione s) throws SQLException {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	
    	String deleteSQLSospensione = "DELETE FROM " + TABLE_NAME
    			+ " WHERE IDsospensione = ?";
    	String deleteSQLStudente = "update " + TABLE_NAME_STUDENTE + " set stato = ? where email = " + "'"+s.getStudente()+"'";
    	
    	try{
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQLSospensione);
			preparedStatement.setInt(1, s.getID());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(deleteSQLStudente);
			preparedStatement.setBoolean(1, true);
			preparedStatement.executeUpdate();
			
		}finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}
    }
    	
    public void update(Sospensione s) throws SQLException{
    	Connection connection = null;
    	PreparedStatement ps = null;
    	
    	String updateSQLSospensione = "update " + TABLE_NAME + " set durata = ?, data = ?, "
    			+ " motivazione = ?, studente = ?, addetto = ? where IDsospensione = " + s.getID();
    	
    	String updateSQLStudente = "update " + TABLE_NAME_STUDENTE + " set stato = ? where email = " + "'"+s.getStudente()+"'";
    	
    	try{
    		connection = Connessione.getConnection();
    		ps = connection.prepareStatement(updateSQLSospensione);
    		
    		ps.setInt(1, s.getDurata());
    		ps.setDate(2, Date.valueOf(s.getData()));
    		ps.setString(3, s.getMotivazione());
    		ps.setString(4, s.getStudente());
    		ps.setString(5, s.getAddetto());
    		ps.executeUpdate();
       		ps.close();
    
       		ps = connection.prepareStatement(updateSQLStudente);
       		ps.setBoolean(1, true); 
       		ps.executeUpdate();       		
    	
    	}finally{
			if(ps != null) { 
				ps.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
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
                s.setDurata(rs.getInt("durata"));
                s.setData(rs.getDate("data").toLocalDate().toString());
				s.setMotivazione(rs.getString("motivazione"));
                s.setStudente(rs.getString("studente"));
                s.setAddetto(rs.getString("addetto"));
			}
		}finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
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
                s.setDurata(rs.getInt("durata"));
                s.setData(rs.getDate("data").toLocalDate().toString());
				s.setMotivazione(rs.getString("motivazione"));
                s.setStudente(rs.getString("studente"));
                s.setAddetto(rs.getString("addetto"));
				sospesi.add(s);
			}

		} finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}

        return sospesi;
    }
    
}
