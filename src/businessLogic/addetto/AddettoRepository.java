package businessLogic.addetto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.storage.Connessione;
import dataAccess.storage.Specification;
import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Addetto;

public class AddettoRepository {
	private static AddettoRepository instance;

    public static AddettoRepository getInstance() {
        instance = new AddettoRepository();
        return instance;
    }

    public static final String TABLE_NAME = "addetto";
    public static final String TABLE_NAME_UTENTE = "utente";
    
    public AddettoRepository(){
    	
    }
    
    public void add(Addetto a) throws SQLException{
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	
    	String insertSQLUtente = "INSERT INTO " + TABLE_NAME_UTENTE
    			+ " (email, password, nome, cognome) VALUES (?, ?, ?, ?)";
    	String insertSQLAddetto = "INSERT INTO " + TABLE_NAME
    			+ " (email, tipo) VALUES (?, ?)";
    	
    	try {
			connection = Connessione.getConnection();
			
			preparedStatement = connection.prepareStatement(insertSQLUtente);
			preparedStatement.setString(1, a.getEmail());
			preparedStatement.setString(2, a.getPassword());
			preparedStatement.setString(3, a.getName());
			preparedStatement.setString(4, a.getSurname());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			
			preparedStatement = connection.prepareStatement(insertSQLAddetto);
			preparedStatement.setString(1, a.getEmail());
			preparedStatement.setBoolean(2, a.getTipo());
			
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
    
    public void delete(Addetto a) throws SQLException {
    	Connection connection = null;
    	PreparedStatement preparedStatement = null;
    	
    	String deleteSQL = "DELETE FROM " + TABLE_NAME_UTENTE + " WHERE email = ?";
    	
    	try{
			connection = Connessione.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, a.getEmail());

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
    	
    public void update(Addetto a) throws SQLException{
    	Connection connection = null;
    	PreparedStatement ps = null;
    	
    	String updateSQLUtente = "update " + TABLE_NAME_UTENTE + " set email = ?, password = ?, nome = ?, "
    			+ "cognome = ? where email = " + a.getEmail();
    	String updateSQLAddetto = "update " + TABLE_NAME + " set tipo = ? where email = " + a.getEmail();
    	
    	try{
    		connection = Connessione.getConnection();
    		ps = connection.prepareStatement(updateSQLUtente);
    		ps.setString(1, a.getEmail());
    		ps.setString(2, a.getPassword());
    		ps.setString(3, a.getName());
    		ps.setString(4, a.getSurname());
    		
    		ps.executeUpdate();
    		ps.close();
    		
    		ps = connection.prepareStatement(updateSQLAddetto);
    		ps.setBoolean(1, a.getTipo());
    		
    		ps.executeUpdate();
    	} finally {
    		if(ps != null)
    			ps.close();
    		Connessione.releaseConnection(connection);
    	}	
    }
    	
    public Addetto findItemByQuery(Specification specification) throws SQLException{
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        String selectSQL= sqlSpecification.toSqlQuery();
        Addetto a = new Addetto();
        try{
        	connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
			
            while (rs.next()) {
            	a.setName(rs.getString(1));
				a.setSurname(rs.getString(2));
                a.setEmail(rs.getString(3));
				a.setTipo(rs.getBoolean(4));
				a.setPassword(rs.getString(5));
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
        
        return a;
    }
    
    public List<Addetto> query(Specification specification) throws SQLException{    	
    	Connection connection = null;
		PreparedStatement preparedStatement = null;
        SqlSpecification sqlSpecification = (SqlSpecification) specification;
        List<Addetto> addetti = new ArrayList<>();
        String selectSQL = sqlSpecification.toSqlQuery();

        try{
            connection = Connessione.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            ResultSet rs = preparedStatement.executeQuery();
            
            //System.out.println(preparedStatement);
			while (rs.next()) {
				Addetto a = new Addetto();
				
				a.setEmail(rs.getString("email"));
				a.setPassword(rs.getString("password"));
				a.setSurname(rs.getString("cognome"));
                a.setName(rs.getString("nome"));
				a.setTipo(rs.getBoolean("tipo"));
				addetti.add(a);
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

        return addetti;
    }
    
}
