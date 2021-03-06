package businessLogic.Postazione;

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
import dataAccess.storage.bean.Postazione;

public class PostazioneRepository implements Repository<Postazione> {
	
	private static PostazioneRepository instance;

    public static PostazioneRepository getInstance() {
        instance = new PostazioneRepository();
        return instance;
    }

	public static final String TABLE_NAME = "postazione";
	 
	@Override
	public void add(Postazione pos) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (numero, laboratorio, stato) VALUES (?, ?, ?)";
		
		try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, pos.getNumero());
			preparedStatement.setString(2, pos.getLaboratorio());
			preparedStatement.setBoolean(3, pos.isStato());
			preparedStatement.executeUpdate();
		}finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}

	}

	@Override
	public void delete(Postazione pos){
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE numero = ? AND laboratorio = ?";

		try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, pos.getNumero());
			preparedStatement.setString(2, pos.getLaboratorio());
			
			preparedStatement.executeUpdate();
				
			if (preparedStatement != null) {
				preparedStatement.close();
			}	
			Connessione.releaseConnection(connection);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public void update(Postazione item) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		

		String updateSQL = "UPDATE " + TABLE_NAME + " SET stato = ? WHERE numero = ? AND laboratorio = ? ;";

		try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setBoolean(1, item.isStato());
			preparedStatement.setInt(2, item.getNumero());
			preparedStatement.setString(3, item.getLaboratorio());

			preparedStatement.executeUpdate();
		

		}finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}
	}

	@Override
	public Postazione findItemByQuery(Specification specification) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        String selectSQL= sqlSpecification.toSqlQuery();
        Postazione pos= new Postazione();

        try{
            connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
                pos.setNumero(rs.getInt("numero"));
                pos.setLaboratorio(rs.getString("laboratorio"));	                
				pos.setStato(rs.getBoolean("stato"));
			}

		}finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}

        return pos;

	}

	@Override
	public List<Postazione> query(Specification specification) throws SQLException {
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        String selectSQL= sqlSpecification.toSqlQuery();
        List<Postazione> postazioni= new ArrayList<>();
        
        try{
            connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Postazione pos=new Postazione();
                pos.setNumero(rs.getInt("numero"));
                pos.setLaboratorio(rs.getString("laboratorio"));
				pos.setStato(rs.getBoolean("stato"));
				postazioni.add(pos);
			}

		}finally{
			if(preparedStatement != null) { 
				preparedStatement.close();
			}
			Connessione.releaseConnection(connection); //rilascia la connessione e la rende nuovamente disponibile
		}

        return postazioni;

	}
}
