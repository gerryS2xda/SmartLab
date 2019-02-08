package businessLogic.comunicazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.project.utils.Utils;
import dataAccess.storage.Connessione;
import dataAccess.storage.Repository;
import dataAccess.storage.Specification;
import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Segnalazione;

public class SegnalazioneRepository implements Repository<Segnalazione> {
	
	public static final String TABLE_NAME = "segnalazione";
	public static final String[] COLUMN_NAME = {"oggetto", "descrizione", "data", "postazione", "laboratorio", "studente"};
	
	public void add(Segnalazione segnalazione) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		String addSegnalazione = "INSERT INTO " + Utils.getRelazioneTable(TABLE_NAME, COLUMN_NAME) + " VALUES(?, ?, ?, ?, ?, ?)";
		try{
			con = Connessione.getConnection();
			ps = con.prepareStatement(addSegnalazione);
			ps.setString(1, segnalazione.getOggetto());
			ps.setString(2, segnalazione.getDescrizione());
			ps.setDate(3, segnalazione.getData());
			ps.setInt(4, segnalazione.getPostazione());
			ps.setString(5, segnalazione.getLaboratorio());
			ps.setString(6, segnalazione.getStudente());
			ps.executeUpdate();
		}finally{
			if(con != null) {
				con.close();
			}
			if(ps != null) {
				ps.close();
			}
		}
	}

	public void delete(Segnalazione segnalazione) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String delSegnalazione = "DELETE FROM " + TABLE_NAME + " WHERE idSegnalazione = ?";
		try{
			con = Connessione.getConnection();
			ps = con.prepareStatement(delSegnalazione);
			ps.setInt(1, segnalazione.getId());
			ps.executeUpdate();
			
		}finally{
			ps.close();
			con.close();
		}
	}

	public void update(Segnalazione item) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		String updateSQL = "update " + TABLE_NAME + " set oggetto = ?, descrizione = ?, data = ?, "
				+ "postazione = ?, laboratorio = ?, studente = ? where idSegnalazione = " + item.getId();
		
		try{
			conn = Connessione.getConnection();
			ps = conn.prepareStatement(updateSQL);
				
			ps.setString(1, item.getOggetto());
			ps.setString(2, item.getDescrizione());
			ps.setDate(3, item.getData());
			ps.setInt(4, item.getPostazione());
			ps.setString(5, item.getLaboratorio());
			ps.setString(6, item.getStudente());
				
			ps.executeUpdate();
		}finally{
			if(ps != null) {
				ps.close();
			}
			Connessione.releaseConnection(conn); //rilascia la connessione e la rende nuovamente disponibile
		}
		
	}

	public Segnalazione findItemByQuery(Specification spec) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		SqlSpecification sql = (SqlSpecification) spec;
		String query = sql.toSqlQuery();
		Segnalazione item = new Segnalazione();
		
		try{
			con = Connessione.getConnection();
			ps = con.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			
			while(res.next()){
				item.setId(res.getInt("idSegnalazione"));
				item.setOggetto(res.getString("oggetto"));
				item.setDescrizione(res.getString("descrizione"));
				item.setData(res.getDate("data"));
				item.setStudente(res.getString("studente"));
				item.setLaboratorio(res.getString("laboratorio"));
				item.setPostazione(res.getInt("postazione"));
			}
			
		}finally{
			con.close();
			ps.close();
		}
		return item;
	}

	public List<Segnalazione> query(Specification spec) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		List<Segnalazione> list = new ArrayList<Segnalazione>();
		SqlSpecification sql = (SqlSpecification) spec;
		String query = sql.toSqlQuery();
		try{
			con = Connessione.getConnection();
			ps = con.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			while(res.next()){
				Segnalazione item = new Segnalazione();
				item.setId(res.getInt("idSegnalazione"));
				item.setOggetto(res.getString("oggetto"));
				item.setDescrizione(res.getString("descrizione"));
				item.setData(res.getDate("data"));
				item.setStudente(res.getString("studente"));
				item.setLaboratorio(res.getString("laboratorio"));
				item.setPostazione(res.getInt("postazione"));
				list.add(item);
			}
		}finally{
			con.close();
			ps.close();
		}
		return list;
	}
}
