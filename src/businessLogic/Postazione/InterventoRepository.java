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
import dataAccess.storage.bean.Postazione;

public class InterventoRepository {
	
	private static InterventoRepository instance;

    public static InterventoRepository getInstance() 
    {
            instance = new InterventoRepository();
        return instance;

    }
    
    public static final String TABLE_NAME = "intervento";
    
    public void add(Intervento intervento)  
    {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (IDintervento,descrizione,data,postazione,laboratorio,addetto) VALUES (?, ?, ?, ?,?,?)";
		
		
		try {
				connection = Connessione.getConnection();
			
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, intervento.getIdIntervento());
			preparedStatement.setString(2, intervento.getDescrizione());
			preparedStatement.setDate(3, Date.valueOf(intervento.getData()));
			preparedStatement.setInt(4, intervento.getPostazione());
			preparedStatement.setString(5, intervento.getLaboratorio());
			preparedStatement.setString(6, intervento.getAddetto());

			preparedStatement.executeUpdate();

			connection.commit();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		} finally 
			{
			
				if (preparedStatement != null)
					try {
						preparedStatement.close();
						if (connection != null)
							Connessione.releaseConnection(connection);
					} catch (SQLException e) {
						
						e.printStackTrace();
					}
			
		}
	}
    
    public void delete(Intervento intervento) throws SQLException 
    {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE IDintervento=?";
		
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
    
public int trovaUltimoInter(Specification specification) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        String selectSQL= sqlSpecification.toSqlQuery();
        Intervento inter= new Intervento();
        int n=0;
        
        try{
            connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

            rs.last();
            n=rs.getRow();
            n++;
            

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					Connessione.releaseConnection(connection);
			}
		}

        return n;

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

	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} finally {
			if (connection != null)
				Connessione.releaseConnection(connection);
		}
	}

    return in;

}

		

}
