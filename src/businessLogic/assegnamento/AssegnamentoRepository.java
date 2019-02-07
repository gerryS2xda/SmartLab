package businessLogic.assegnamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dataAccess.storage.Connessione;
import dataAccess.storage.Specification;
import dataAccess.storage.SqlSpecification;
import dataAccess.storage.Repository;
import dataAccess.storage.bean.Assegnamento;

public class AssegnamentoRepository implements Repository<Assegnamento>{
	
	private static AssegnamentoRepository instance;

    public static AssegnamentoRepository getInstance() {

        if (instance == null) {
            instance = new AssegnamentoRepository();
        }
        return instance;

    }
	
    public static final String TABLE_NAME = "assegnamento";//nome della tabella su cui saranno effettuate le operazioni


    public AssegnamentoRepository(){
		
	}

    public void add(Assegnamento ass) throws SQLException{

        Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (laboratorio,responsabile) VALUES (?, ?)";

        try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, ass.getLaboratorio());
			preparedStatement.setString(2, ass.getResponsabile());
			System.out.println(preparedStatement);
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

    public void delete(Assegnamento ass) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE laboratorio = ? && responsabile=?";

		try {
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, ass.getLaboratorio());
			preparedStatement.setString(2, ass.getResponsabile());
			System.out.println(preparedStatement);
			
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


    public void update(Assegnamento ass)throws SQLException{
    	
    }

    public Assegnamento findItemByQuery(Specification specification) throws SQLException{
    	
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        String selectSQL= sqlSpecification.toSqlQuery();
        Assegnamento ass=new Assegnamento();
        try{
            connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				ass.setLaboratorio(rs.getString("laboratorio"));
				ass.setResponsabile(rs.getString("responsabile"));
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

        return ass;

    }

    public List<Assegnamento> query(Specification specification){
    	
    
        return null;

    }



}