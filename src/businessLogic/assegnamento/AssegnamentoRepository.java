package businessLogic.assegnamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import businessLogic.laboratorio.LaboratorioRepository;
import dataAccess.storage.Connessione;
import dataAccess.storage.Specification;
import dataAccess.storage.Repository;
import dataAccess.storage.SqlSpecification;

import dataAccess.storage.bean.Assegnamento;
public class AssegnamentoRepository extends Connessione implements Repository<Assegnamento>{
	
	private static AssegnamentoRepository instance;

    public static AssegnamentoRepository getInstance() {

        if (instance == null) {
            instance = new AssegnamentoRepository();
        }
        return instance;

    }
	
    public static final String TABLE_NAME = "assegnamento";//nome della tabella su cui saranno effettuate le operazioni


    public AssegnamentoRepository(){
		super();
	}

    public void add(Assegnamento ass) throws SQLException{

        Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + TABLE_NAME
				+ " (laboratorio,responsabile) VALUES (?, ?)";

        try {
			connection = super.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, ass.getLaboratorio());
			preparedStatement.setString(2, ass.getResponsabile());

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

    public void delete(Assegnamento ass) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM " + TABLE_NAME + " WHERE laboratorio = ? && addetto=?";

		try {
			connection = super.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, ass.getLaboratorio());
			preparedStatement.setString(2, ass.getResponsabile());

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


    public void update(Assegnamento ass)throws SQLException{

    }

    public Assegnamento findItemByQuery(Specification specification)throws SQLException{
    	
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        String selectSQL= sqlSpecification.toSqlQuery();
        Assegnamento ass=new Assegnamento();
        
        try{
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
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
					connection.close();
			}
		}

        return ass;
    }

    public List<Assegnamento> query(Specification specification)throws SQLException{
    	
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        List<Assegnamento> assegnamenti = new ArrayList<>();
        String selectSQL= sqlSpecification.toSqlQuery();

        try{
            connection = super.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Assegnamento ass=new Assegnamento();

                ass.setLaboratorio(rs.getString("laboratorio"));
				ass.setResponsabile(rs.getString("responsabile"));

				assegnamenti.add(ass);
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

        return assegnamenti;

    }


}