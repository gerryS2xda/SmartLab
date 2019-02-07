package businessLogic.Postazione;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dataAccess.storage.Connessione;
import dataAccess.storage.Specification;
import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Intervento;

public class InterventoRepository {
	
	private static InterventoRepository instance;

    public static InterventoRepository getInstance() {
        instance = new InterventoRepository();
        return instance;

    }
    
    public static final String TABLE_NAME = "intervento";
    
    public void add(Intervento intervento) throws SQLException{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (descrizione,data,postazione,laboratorio,addetto) VALUES (?, ?, ?,?,?)";
		
		
		try {
			connection = Connessione.getConnection();
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, intervento.getDescrizione());
			preparedStatement.setDate(2, Date.valueOf(intervento.getData()));
			preparedStatement.setInt(3, intervento.getPostazione());
			preparedStatement.setString(4, intervento.getLaboratorio());
			preparedStatement.setString(5, intervento.getAddetto());

			preparedStatement.executeUpdate();
		}finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}
	}
    
    public void delete(Intervento intervento) throws SQLException{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE IDintervento=?";
		try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, intervento.getIdIntervento());
			preparedStatement.executeUpdate();
		}finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}
	}

    

	public Intervento findItemByQuery(Specification specification) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	    SqlSpecification sqlSpecification = (SqlSpecification) specification;
	    String selectSQL= sqlSpecification.toSqlQuery();
	    Intervento in= new Intervento();
	    
	    
	    try{
	        connection = Connessione.getConnection();
	        preparedStatement = connection.prepareStatement(selectSQL);
	        ResultSet rs = preparedStatement.executeQuery();
	
			while (rs.next()) {
				
				in.setAddetto(rs.getString("addetto"));
				in.setDescrizione(rs.getString("descrizione"));
				in.setIdIntervento(rs.getInt("IDintervento"));
				in.setLaboratorio(rs.getString("laboratorio"));
				in.setPostazione(rs.getInt("postazione"));
				in.setData(rs.getDate("data").toLocalDate());
	           
			}
	
		}finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}
	
	    return in;
	}
}

