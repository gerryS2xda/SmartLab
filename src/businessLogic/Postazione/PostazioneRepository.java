package businessLogic.Postazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import businessLogic.laboratorio.LaboratorioRepository;
import dataAccess.storage.Connessione;
import dataAccess.storage.Repository;
import dataAccess.storage.Specification;
import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Laboratorio;
import dataAccess.storage.bean.Postazione;

public class PostazioneRepository implements Repository<Postazione>{
	
	private static PostazioneRepository instance;

    public static PostazioneRepository getInstance() 
    {

        if (instance == null) {
            instance = new PostazioneRepository();
        }
        return instance;

    }

	 public static final String TABLE_NAME = "postazione";
	 
	@Override
	public void add(Postazione pos) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (nome, laboratorio, stato) VALUES (?, ?, ?)";
		
		Laboratorio lab=pos.getLaboratorio();
		String id=lab.getIDlaboratorio();
		
		try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, pos.getNumero());
			preparedStatement.setString(2, id);
			preparedStatement.setBoolean(3, pos.isStato());

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

	@Override
	public void delete(Postazione pos) throws SQLException
	{
		
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			Laboratorio lab=pos.getLaboratorio();
			String id=lab.getIDlaboratorio();

			String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE numero = ? && laboratorio=?";

			try {
				connection = Connessione.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, pos.getNumero());
				preparedStatement.setString(2, id);

				preparedStatement.executeUpdate();
				

				} finally 
				{
				try
				{
					if (preparedStatement != null)
						preparedStatement.close();
				} finally 
				{
					if (connection != null)
						Connessione.releaseConnection(connection);
				}
	}
	
}

	@Override
	public void update(Postazione item) throws SQLException {
		// TODO Auto-generated method stub
		
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
				pos.setLaboratorio((Laboratorio) rs.getObject("laboratorio"));
				pos.setStato(rs.getBoolean("stato"));
              

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

        return pos;

	}

	@Override
	public List<Postazione> query(Specification specification) throws SQLException {
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        String selectSQL= sqlSpecification.toSqlQuery();
        List <Postazione> postazioni= new ArrayList<>();
        
        try{
            connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Postazione pos=new Postazione();
                pos.setNumero(rs.getInt("numero"));
                pos.setLaboratorio((Laboratorio) rs.getObject("laboratorio"));
				pos.setStato(rs.getBoolean("stato"));
              
				postazioni.add(pos);
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

        return postazioni;

		
	}


}
