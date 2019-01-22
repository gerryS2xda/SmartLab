package businessLogic.Postazione;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import dataAccess.storage.Connessione;
import dataAccess.storage.bean.Intervento;
import dataAccess.storage.bean.Postazione;

public class InterventoRepository {
	
	private static InterventoRepository instance;

    public static InterventoRepository getInstance() 
    {

        if (instance == null) {
            instance = new InterventoRepository();
        }
        return instance;

    }
    
    public static final String TABLE_NAME = "intervento";
    
    public void add(Intervento intervento) throws SQLException 
    {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (idintervento,descrizione,data,postazione,laboratorio,addetto) VALUES (?, ?, ?, ?,?,?)";
		
		try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, intervento.getIdIntervento());
			preparedStatement.setString(2, intervento.getDescrizione());
			preparedStatement.setDate(3, (Date) intervento.getData());
			preparedStatement.setInt(4, intervento.getPostazione());
			preparedStatement.setInt(5, intervento.getLaboratorio());
			preparedStatement.setString(6, intervento.getAddetto());

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
    
    public void delete(Intervento intervento) throws SQLException 
    {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE idintervento=?";
		
		try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, intervento.getIdIntervento());

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

}