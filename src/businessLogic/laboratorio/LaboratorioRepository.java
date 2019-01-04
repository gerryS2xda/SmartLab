package businessLogic.laboratorio;
import dataAccess.storage.bean.Laboratorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.storage.Connessione;
import dataAccess.storage.Specification;
import dataAccess.storage.Repository;
import dataAccess.storage.SqlSpecification;

public class LaboratorioRepository extends Connessione implements Repository<Laboratorio>{
	
	
	private static LaboratorioRepository instance;

    public static LaboratorioRepository getInstance() {

        if (instance == null) {
            instance = new LaboratorioRepository();
        }
        return instance;

    }

    public static final String TABLE_NAME = "laboratorio";//nome della tabella su cui saranno effettuate le operazioni


    public LaboratorioRepository(){
		super();
	}

    public void add(Laboratorio lab) throws SQLException{

        Connection connection = null;
		PreparedStatement preparedStatement = null;

        //IDlaboratorio è auto increment
		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (nome, posti, stato,fascia_oraria_apertura) VALUES (?, ?, ?, ?)";

        try {
			connection = super.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, lab.getNome());
			preparedStatement.setInt(2, lab.getPosti());
			preparedStatement.setBoolean(3, lab.isStato());
			preparedStatement.setTime(4, lab.getApertura());

			preparedStatement.executeUpdate();

			connection.commit();
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}

    }

    public void delete(Laboratorio lab) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE IDlaboratorio = ?";

		try {
			connection = super.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, lab.getIDlaboratorio());

			preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}


    public void update(Laboratorio lab)throws SQLException{

    }

    public Laboratorio findItemByQuery(Specification specification)throws SQLException{
    	
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        String selectSQL= sqlSpecification.toSqlQuery();
        Laboratorio lab=new Laboratorio();
        try{
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

                lab.setIDlaboratorio(rs.getString("IDlaboratorio"));
				lab.setNome(rs.getString("nome"));
				lab.setPosti(rs.getInt("posti"));
                lab.setStato(rs.getBoolean("stato"));
                lab.setApertura(rs.getTime("fascia_oraria_apertura"));

			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}

        return lab;
    }

    public List<Laboratorio> query(Specification specification)throws SQLException{
    	
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        List<Laboratorio> laboratori = new ArrayList<>();
        String selectSQL= sqlSpecification.toSqlQuery();

        try{
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Laboratorio lab=new Laboratorio();

                lab.setIDlaboratorio(rs.getString("IDlaboratorio"));
				lab.setNome(rs.getString("nome"));
				lab.setPosti(rs.getInt("posti"));
                lab.setStato(rs.getBoolean("stato"));
                lab.setApertura(rs.getTime("fascia_oraria_apertura"));

				laboratori.add(lab);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}

        return laboratori;

    }


}