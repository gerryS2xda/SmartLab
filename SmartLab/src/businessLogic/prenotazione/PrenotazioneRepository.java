package businessLogic.prenotazione;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.project.utils.Utils;
import dataAccess.storage.*;
import dataAccess.storage.bean.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Statement;

public class PrenotazioneRepository implements Repository<Prenotazione>{

	//variabili di classe
	public static final String TABLE_NAME = "Prenotazione";
	/* 
	 * In aggiunta e' presente la colonna 'IDprenotazione' che non e' stata inserita nella lista poiche'
	 * l'id viene aggiunto in maniera automatica (auto_increment)
	 */
	public static final String[] COLUMN_NAME = {"data", "fascia_oraria", "stato", "studente", "postazione", "laboratorio"};
	
	
	
	//variabili di istanza
	
			
	//costruttore
	public PrenotazioneRepository(){
		//connessione con il DB
	}
	
	//public methods
	public void add(Prenotazione item)throws SQLException{
		
		PreparedStatement ps = null;
		
		String insertSQL = "insert into " + Utils.getRelazioneTable(TABLE_NAME, COLUMN_NAME)
			+ " VALUES(?,?,?,?,?,?)";
		
		ps.setDate(1, Date.valueOf(item.getData()));
		ps.setTime(2, Time.valueOf(item.getFasciaOraria()));
		ps.setBoolean(3, item.isPrenotazioneActive());
		ps.setString(4, item.getStudente());
		ps.setInt(5, item.getPostazione());
		ps.setInt(6, item.getLaboratorio());
		
		ps.executeUpdate(insertSQL);
		
	}
	
	public void delete(Prenotazione item)throws SQLException{
		
		PreparedStatement ps = null;
		
		String delSQL = "delete from " + TABLE_NAME + " where IDprenotazione = " + item.getId();
		ps.executeUpdate(delSQL);
		
	}
	
	public void update(Prenotazione item)throws SQLException{
		
		PreparedStatement ps = null;
		
		String updateSQL = "update " + TABLE_NAME + " set data = ?, fascia_oraria = ?, "
				+ "stato = ?, studente = ?, laboratorio = ? where IDprenotazione = " + item.getId();
		
		ps.setDate(1, Date.valueOf(item.getData()));
		ps.setTime(2, Time.valueOf(item.getFasciaOraria()));
		ps.setBoolean(3, item.isPrenotazioneActive());
		ps.setString(4, item.getStudente());
		ps.setInt(5, item.getPostazione());
		ps.setInt(6, item.getLaboratorio());
		
		ps.executeUpdate(updateSQL);
		
	}
	
	public Prenotazione findItemByQuery(Specification spec)throws SQLException{
		
		PrenotazioneSqlSpecification sqlSpec = (PrenotazioneSqlSpecification) spec;
		Statement stmt = null;
		
		ResultSet res = stmt.executeQuery(sqlSpec.toSqlQuery());
		Prenotazione pr = new Prenotazione();
		
		pr.setID(res.getInt(1));
		pr.setData(res.getDate(2).toLocalDate());
		pr.setFasciaOraria(res.getTime(3).toString());
		pr.setStatus(res.getBoolean(4));
		pr.setStudente(res.getString(5));
		pr.setPostazione(res.getInt(6));
		pr.setLaboratorio(res.getInt(7));
		
		return pr;
	}
	
	public List<Prenotazione> query(Specification spec)throws SQLException{
		
		List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		PrenotazioneSqlSpecification sqlSpec = (PrenotazioneSqlSpecification) spec;
		Statement stmt = null;
		
		ResultSet res = stmt.executeQuery(sqlSpec.toSqlQuery());
		
		while(res.next()){
			Prenotazione pr = new Prenotazione();
			pr.setID(res.getInt(1));
			pr.setData(res.getDate(2).toLocalDate());
			pr.setFasciaOraria(res.getTime(3).toString());
			pr.setStatus(res.getBoolean(4));
			pr.setStudente(res.getString(5));
			pr.setPostazione(res.getInt(6));
			pr.setLaboratorio(res.getInt(7));
			prenotazioni.add(pr);
		}
		
		
		return prenotazioni;
	}
	
	public void closeConnection(){
		//da implementare
	}
}
