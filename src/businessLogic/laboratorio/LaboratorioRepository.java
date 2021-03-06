package businessLogic.laboratorio;
import dataAccess.storage.bean.Laboratorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import dataAccess.storage.Connessione;
import dataAccess.storage.Specification;
import dataAccess.storage.Repository;
import dataAccess.storage.SqlSpecification;

public class LaboratorioRepository implements Repository<Laboratorio> {
	
	
	private static LaboratorioRepository instance;

    public static LaboratorioRepository getInstance() {

        if (instance == null) {
            instance = new LaboratorioRepository();
        }
        return instance;

    }

    public static final String TABLE_NAME = "laboratorio";//nome della tabella su cui saranno effettuate le operazioni


    public LaboratorioRepository(){
		
	}

    public void add(Laboratorio lab) throws SQLException{

        Connection connection = null;
		PreparedStatement preparedStatement = null;

        //IDlaboratorio a' auto increment
		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (nome, posti, stato,ora_apertura, ora_chiusura) VALUES (?, ?, ?, ?, ?)";

        try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, lab.getNome());
			preparedStatement.setInt(2, lab.getPosti());
			preparedStatement.setBoolean(3, lab.isStato());
			preparedStatement.setTime(4, Time.valueOf(lab.getApertura()));
			preparedStatement.setTime(5, Time.valueOf(lab.getChiusura()));
			//System.out.println(preparedStatement);
			preparedStatement.executeUpdate();

			//connection.commit();
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				if (connection != null) {
					Connessione.releaseConnection(connection);
				}
			}
		}

    }

    public void delete(Laboratorio lab) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE "+TABLE_NAME+".IDlaboratorio = ?";

		try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, lab.getIDlaboratorio());

			preparedStatement.executeUpdate();
			
			//connection.commit();
			//System.out.println(preparedStatement);

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				if (connection != null) {
					Connessione.releaseConnection(connection);
				}
			}
		}
	}


    public void update(Laboratorio lab)throws SQLException{
    	Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "UPDATE "+TABLE_NAME+" SET nome = ?, posti = ?, stato = ?, ora_apertura = ?, ora_chiusura = ? WHERE IDlaboratorio = ?";

		try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, lab.getNome());
			preparedStatement.setInt(2, lab.getPosti());
			preparedStatement.setBoolean(3, lab.isStato());
			preparedStatement.setTime(4, Time.valueOf(lab.getApertura()));
			preparedStatement.setTime(5, Time.valueOf(lab.getChiusura()));
			preparedStatement.setString(6, lab.getIDlaboratorio());

			preparedStatement.executeUpdate();
			
			//connection.commit();
			//System.out.println(preparedStatement);

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				if (connection != null) {
					Connessione.releaseConnection(connection);
				}
			}
		}
    }

    public Laboratorio findItemByQuery(Specification specification)throws SQLException{
    	
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        String selectSQL= sqlSpecification.toSqlQuery();
        Laboratorio lab=new Laboratorio();
        try{
            connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {

                lab.setIDlaboratorio(rs.getString("IDlaboratorio"));
				lab.setNome(rs.getString("nome"));
				lab.setPosti(rs.getInt("posti"));
                lab.setStato(rs.getBoolean("stato"));
                if(rs.getTime("ora_apertura")!=null){
                	lab.setApertura(rs.getTime("ora_apertura").toLocalTime());
                }
                if(rs.getTime("ora_chiusura")!=null){
                	lab.setChiusura(rs.getTime("ora_chiusura").toLocalTime());
                }

			}

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				if (connection != null) {
					Connessione.releaseConnection(connection);
				}
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
            connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            //System.out.println(preparedStatement);
			while (rs.next()) {
				Laboratorio lab=new Laboratorio();

                lab.setIDlaboratorio(rs.getString("IDlaboratorio"));
				lab.setNome(rs.getString("nome"));
				lab.setPosti(rs.getInt("posti"));
                lab.setStato(rs.getBoolean("stato")); 
                if(rs.getTime("ora_apertura")!=null){
                	lab.setApertura(rs.getTime("ora_apertura").toLocalTime());
                }
                if(rs.getTime("ora_chiusura")!=null){
                	lab.setChiusura(rs.getTime("ora_chiusura").toLocalTime());
                }
 
				laboratori.add(lab);
			}

		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} finally {
				if (connection != null) {
					Connessione.releaseConnection(connection);
				}
			}
		}

        return laboratori;

    }


}