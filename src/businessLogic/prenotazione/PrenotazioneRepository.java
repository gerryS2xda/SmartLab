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
	
	//usato per il testing
	public static PrenotazioneRepository getInstance(){
		
		return new PrenotazioneRepository();
	}
	
	
	//costruttore (uso del singleton design pattern)
	public PrenotazioneRepository(){
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
			ps.setString(5, item.getStudente().getEmail());
			ps.setInt(6, item.getPostazione().getNumero());
			ps.setString(7, item.getLaboratorio().getIDlaboratorio());
			
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
			ps.setString(5, item.getStudente().getEmail());
			ps.setInt(6, item.getPostazione().getNumero());
			ps.setString(7, item.getLaboratorio().getIDlaboratorio());
				
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
		Postazione post = new Postazione();
		Laboratorio lab = new Laboratorio();
		Studente stud = new Studente();
		
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
				
				//setta l'email studente presa dal DB -- servira' per ottenere i dati di studente in seguito
				stud.setEmail(res.getString(6));
				pr.setStudente(stud);
				
				//setta il numero di postazione preso dal DB -- servira' per ottenere i dati di postazione in seguito
				post.setNumero(res.getInt(7));
				post.setLaboratorio(res.getString(8)); //postazione viene identificata anche tramite l'ID del laboratorio
				
				//setta ID laboratorio preso dal DB -- servira' per ottenere i dati del laboratorio in seguito
				lab.setIDlaboratorio(res.getString(8));
				
				pr.setPostazione(post);
				pr.setLaboratorio(lab);
				
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
		Postazione post = new Postazione();
		Laboratorio lab = new Laboratorio();
		Studente stud = new Studente();
		
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
				
				//setta l'email studente presa dal DB -- servira' per ottenere i dati di studente in seguito
				stud.setEmail(res.getString(6));
				pr.setStudente(stud);
				
				//setta il numero di postazione preso dal DB -- servira' per ottenere i dati di postazione in seguito
				post.setNumero(res.getInt(7));
				post.setLaboratorio(res.getString(8)); //postazione viene identificata anche tramite l'ID del laboratorio
				
				//setta ID laboratorio preso dal DB -- servira' per ottenere i dati del laboratorio in seguito
				lab.setIDlaboratorio(res.getString(8));
				
				pr.setPostazione(post);
				pr.setLaboratorio(lab);
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
