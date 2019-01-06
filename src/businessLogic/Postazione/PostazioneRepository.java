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

public class PostazioneRepository extends Connessione implements Repository<Postazione>{
	
	private static PostazioneRepository instance;

    public static PostazioneRepository getInstance() {

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
				+ " (nome, posti, stato,fascia_oraria_apertura) VALUES (?, ?, ?, ?)";
		
		try {
			connection = super.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, pos.getNumero());
			preparedStatement.setString(2, pos.getLaboratorio());
			preparedStatement.setBoolean(3, pos.isStato());

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

	@Override
	public void delete(Postazione pos) throws SQLException
	{
		
			Connection connection = null;
			PreparedStatement preparedStatement = null;

			String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE numero = ? && IDlaboratorio=?";

			try {
				connection = super.getConnection();
				preparedStatement = connection.prepareStatement(deleteSQL);
				preparedStatement.setInt(1, pos.getNumero());
				preparedStatement.setString(2, pos.getLaboratorio());

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
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

                pos.setNumero(rs.getInt("numero"));
				pos.setLaboratorio(rs.getString("laboratorio"));
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
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Postazione pos=new Postazione();
                pos.setNumero(rs.getInt("IDlaboratorio"));
				pos.setLaboratorio(rs.getString("nome"));
				pos.setStato(rs.getBoolean("posti"));
              
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
