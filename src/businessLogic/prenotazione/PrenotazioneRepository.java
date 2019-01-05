package businessLogic.prenotazione;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.project.utils.Utils;
import dataAccess.storage.*;
import dataAccess.storage.bean.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;

public class PrenotazioneRepository implements Repository<Prenotazione>{

	//variabili di classe
	public static final String TABLE_NAME = "Prenotazione";
	/* 
	 * In aggiunta e' presente la colonna 'IDprenotazione' che non e' stata inserita nella lista poiche'
	 * l'id viene aggiunto in maniera automatica (auto_increment)
	 */
	public static final String[] COLUMN_NAME = {"data", "fascia_oraria", "stato", "studente", "postazione", "laboratorio"};
	public static final String[] COLUMN_NAME_WITH_ID = {"IDprenotazione", "data", "fascia_oraria", "stato", "studente", "postazione", "laboratorio"};
	public static PrenotazioneRepository instance;
	
	public static PrenotazioneRepository getInstance(){
		if(instance == null){
			instance = new PrenotazioneRepository();
		}
		return instance;
	}
	
	
	//costruttore (uso del singleton design pattern)
	private PrenotazioneRepository(){
		//vuoto
	}
	
	//public methods
	public void add(Prenotazione item)throws SQLException{
		
		Connection conn = null;
		PreparedStatement ps = null;
				
		String insertSQL = "insert into " + Utils.getRelazioneTable(TABLE_NAME, COLUMN_NAME)
			+ " VALUES(?,?,?,?,?,?)";
		
		
		conn = Connessione.getConnection();
		
		ps = conn.prepareStatement(insertSQL);
		
		ps.setDate(1, Date.valueOf(item.getData()));
		ps.setString(2, item.getFasciaOraria());
		ps.setBoolean(3, item.isPrenotazioneActive());
		ps.setString(4, item.getStudente());
		ps.setInt(5, item.getPostazione());
		ps.setInt(6, item.getLaboratorio());
		
		ps.executeUpdate();	
		
		if(Utils.isDriverManagerEnabled){
			DMConnectionPool.releaseConnection(conn);
		}
	}
	
	public void delete(Prenotazione item)throws SQLException{
		
		Connection conn = null;
		Statement stmt = null;
		
		String delSQL = "delete from " + TABLE_NAME + " where IDprenotazione = " + item.getId();
		
		conn = Connessione.getConnection();
		stmt = conn.createStatement();
				
		stmt.executeUpdate(delSQL);
		
		if(Utils.isDriverManagerEnabled){
			DMConnectionPool.releaseConnection(conn);
		}
	}
	
	public void update(Prenotazione item)throws SQLException{
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		String updateSQL = "update " + TABLE_NAME + " set data = ?, fascia_oraria = ?, "
				+ "stato = ?, studente = ?, postazione = ?, laboratorio = ? where IDprenotazione = " + item.getId();
		
		conn = Connessione.getConnection();
		ps = conn.prepareStatement(updateSQL);
			
		ps.setDate(1, Date.valueOf(item.getData()));
		ps.setString(2, item.getFasciaOraria());
		ps.setBoolean(3, item.isPrenotazioneActive());
		ps.setString(4, item.getStudente());
		ps.setInt(5, item.getPostazione());
		ps.setInt(6, item.getLaboratorio());
			
		ps.executeUpdate();
		
		if(Utils.isDriverManagerEnabled){
			DMConnectionPool.releaseConnection(conn);
		}
	}
	
	public Prenotazione findItemByQuery(Specification spec)throws SQLException{
		
		Connection conn = null;
		SqlSpecification sqlSpec = (SqlSpecification) spec;
		Statement stmt = null;
		Prenotazione pr = null;
		
		conn = Connessione.getConnection();
		stmt = conn.createStatement();
		
		ResultSet res = stmt.executeQuery(sqlSpec.toSqlQuery());
		
		while(res.next()){
			pr = new Prenotazione();
			pr.setID(res.getInt(1));
			pr.setData(res.getDate(2).toLocalDate().toString());
			pr.setFasciaOraria(res.getString(3));
			pr.setStatus(res.getBoolean(4));
			pr.setStudente(res.getString(5));
			pr.setPostazione(res.getInt(6));
			pr.setLaboratorio(res.getInt(7));
			
		}
		
		if(Utils.isDriverManagerEnabled){
			DMConnectionPool.releaseConnection(conn);
		}
		
		return pr;
	}
	
	public List<Prenotazione> query(Specification spec)throws SQLException{
		
		Connection conn = null;
		List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		SqlSpecification sqlSpec = (SqlSpecification) spec;
		Statement stmt = null;
		
		
		conn = Connessione.getConnection();
		stmt = conn.createStatement();
		ResultSet res = stmt.executeQuery(sqlSpec.toSqlQuery());
			
		while(res.next()){
			Prenotazione pr = new Prenotazione();
			pr.setID(res.getInt(1));
			pr.setData(res.getDate(2).toLocalDate().toString());
			pr.setFasciaOraria(res.getString(3));
			pr.setStatus(res.getBoolean(4));
			pr.setStudente(res.getString(5));
			pr.setPostazione(res.getInt(6));
			pr.setLaboratorio(res.getInt(7));
			prenotazioni.add(pr);
		}
		
		if(Utils.isDriverManagerEnabled){
			DMConnectionPool.releaseConnection(conn);
		}
		
		return prenotazioni;
	}
	
}
