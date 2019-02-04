package businessLogic.comunicazione;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.storage.Connessione;
import dataAccess.storage.Repository;
import dataAccess.storage.Specification;
import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Segnalazione;

public class SegnalazioneRepository implements Repository<Segnalazione> {
	
	private String table = "segnalazione";
	
	public void add(Segnalazione segnalazione) throws SQLException{
		Connection con = null;
		PreparedStatement ps = null;
		String addSegnalazione = "INSERT INTO " + table + " VALUES(?,?,?,?,?,?,?)";
		try{
			con = Connessione.getConnection();
			ps = con.prepareStatement(addSegnalazione);
			ps.setInt(1, segnalazione.getId());
			ps.setString(2, segnalazione.getOggetto());
			ps.setString(3, segnalazione.getDescrizione());
			ps.setDate(4, segnalazione.getData());
			ps.setString(5, segnalazione.getStudente());
			ps.setString(6, segnalazione.getLaboratorio());
			ps.setInt(7, segnalazione.getPostazione());
			ps.executeUpdate();
			
		}finally{
			con.close();
			ps.close();
		}
	}

	public void delete(Segnalazione segnalazione) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String delSegnalazione = "DELETE FROM " + table + " WHERE idSegnalazione = ?";
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
		// TODO Auto-generated method stub
		
	}

	public Segnalazione findItemByQuery(Specification spec) throws SQLException {
		int id, pos;
		String og, des, lab, stud;
		Date data;
		Connection con = null;
		PreparedStatement ps = null;
		SqlSpecification sql = (SqlSpecification) spec;
		String query = sql.toSqlQuery();
		Segnalazione item;
		try{
			con = Connessione.getConnection();
			ps = con.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			id = res.getInt("idSegnalazione");
			og = res.getString("oggetto");
			des = res.getString("descrizione");
			data = res.getDate("data");
			stud = res.getString("studente");
			lab = res.getString("laboratorio");
			pos = res.getInt("postazione");
			item = new Segnalazione(id, og, des, data, stud, lab, pos);
		}finally{
			con.close();
			ps.close();
		}
		return item;
	}

	public List<Segnalazione> query(Specification spec) throws SQLException {
		int id, pos;
		String og, des, lab, stud;
		Date data;
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
				id = res.getInt("idSegnalazione");
				og = res.getString("oggetto");
				des = res.getString("descrizione");
				data = res.getDate("data");
				stud = res.getString("studente");
				lab = res.getString("laboratorio");
				pos = res.getInt("postazione");
				Segnalazione temp = new Segnalazione(id, og, des, data, stud, lab, pos);
				list.add(temp);
			}
		}finally{
			con.close();
		}
		return list;
	}
}
