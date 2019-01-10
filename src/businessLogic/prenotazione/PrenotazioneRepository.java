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
import java.sql.Time;

public class PrenotazioneRepository implements Repository<Prenotazione>{

	//variabili di classe
	public static final String TABLE_NAME = "Prenotazione";
	/* 
	 * In aggiunta e' presente la colonna 'IDprenotazione' che non e' stata inserita nella lista poiche'
	 * l'id viene aggiunto in maniera automatica (auto_increment)
	 */
	public static final String[] COLUMN_NAME = {"data", "ora_inizio", "ora_fine", "stato", "studente", "postazione", "laboratorio"};
	public static final String[] COLUMN_NAME_WITH_ID = {"IDprenotazione", "data", "ora_inizio", "ora_fine", "stato", "studente", "postazione", "laboratorio"};
	private static PrenotazioneRepository instance;
	
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
			+ " VALUES(?,?,?,?,?,?,?)";
		
		try{
			conn = Connessione.getConnection();
			
			ps = conn.prepareStatement(insertSQL);
			
			ps.setDate(1, Date.valueOf(item.getData()));
			ps.setTime(2, Time.valueOf(item.getOraInizio()));
			ps.setTime(3, Time.valueOf(item.getOraFine()));
			ps.setBoolean(4, item.isPrenotazioneActive());
			ps.setString(5, item.getStudente());
			ps.setInt(6, item.getPostazione());
			ps.setInt(7, item.getLaboratorio());
			
			ps.executeUpdate();	
		}finally{
			if(ps != null) 
				ps.close();
			Connessione.releaseConnection(conn); //rilascia la connessione e la rende nuovamente disponibile
		}
	}
	
	public void delete(Prenotazione item)throws SQLException{
		
		Connection conn = null;
		Statement stmt = null;
		
		String delSQL = "delete from " + TABLE_NAME + " where IDprenotazione = " + item.getId();
		
		try{
			conn = Connessione.getConnection();
			stmt = conn.createStatement();
					
			stmt.executeUpdate(delSQL);
		}finally{
			if(stmt != null) 
				stmt.close();
			Connessione.releaseConnection(conn); //rilascia la connessione e la rende nuovamente disponibile
		}
	}
	
	public void update(Prenotazione item)throws SQLException{
		
		Connection conn = null;
		PreparedStatement ps = null;
		
		String updateSQL = "update " + TABLE_NAME + " set data = ?, ora_inizio = ?, ora_fine = ?, "
				+ "stato = ?, studente = ?, postazione = ?, laboratorio = ? where IDprenotazione = " + item.getId();
		
		try{
			conn = Connessione.getConnection();
			ps = conn.prepareStatement(updateSQL);
				
			ps.setDate(1, Date.valueOf(item.getData()));
			ps.setTime(2, Time.valueOf(item.getOraInizio()));
			ps.setTime(3, Time.valueOf(item.getOraFine()));
			ps.setBoolean(4, item.isPrenotazioneActive());
			ps.setString(5, item.getStudente());
			ps.setInt(6, item.getPostazione());
			ps.setInt(7, item.getLaboratorio());
				
			ps.executeUpdate();
		}finally{
			if(ps != null) 
				ps.close();
			Connessione.releaseConnection(conn); //rilascia la connessione e la rende nuovamente disponibile
		}
	}
	
	public Prenotazione findItemByQuery(Specification spec)throws SQLException{
		
		Connection conn = null;
		SqlSpecification sqlSpec = (SqlSpecification) spec;
		Statement stmt = null;
		Prenotazione pr = null;
		
		try{
			conn = Connessione.getConnection();
			stmt = conn.createStatement();
			
			ResultSet res = stmt.executeQuery(sqlSpec.toSqlQuery());
			
			while(res.next()){
				pr = new Prenotazione();
				pr.setID(res.getInt(1));
				pr.setData(res.getDate(2).toLocalDate().toString());
				pr.setOraInizio(res.getTime(3).toLocalTime());
				pr.setOraFine(res.getTime(4).toLocalTime());
				pr.setStatus(res.getBoolean(5));
				pr.setStudente(res.getString(6));
				pr.setPostazione(res.getInt(7));
				pr.setLaboratorio(res.getInt(8));
				
			}
		}finally{
			if(stmt != null) 
				stmt.close();
			Connessione.releaseConnection(conn); //rilascia la connessione e la rende nuovamente disponibile
		}
		
		return pr;
	}
	
	public List<Prenotazione> query(Specification spec)throws SQLException{
		
		Connection conn = null;
		List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		SqlSpecification sqlSpec = (SqlSpecification) spec;
		Statement stmt = null;
		
		try{
			conn = Connessione.getConnection();
			stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(sqlSpec.toSqlQuery());
				
			while(res.next()){
				Prenotazione pr = new Prenotazione();
				pr.setID(res.getInt(1));
				pr.setData(res.getDate(2).toLocalDate().toString());
				pr.setOraInizio(res.getTime(3).toLocalTime());
				pr.setOraFine(res.getTime(4).toLocalTime());
				pr.setStatus(res.getBoolean(5));
				pr.setStudente(res.getString(6));
				pr.setPostazione(res.getInt(7));
				pr.setLaboratorio(res.getInt(8));
				prenotazioni.add(pr);
			}
		}finally{
			if(stmt != null) 
				stmt.close();
			Connessione.releaseConnection(conn); //rilascia la connessione e la rende nuovamente disponibile
		}
		
		return prenotazioni;
	}
	
}
