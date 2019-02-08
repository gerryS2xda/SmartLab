package businessLogic.comunicazione;

import java.sql.Connection;
import java.sql.Date;
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
import dataAccess.storage.bean.Avviso;

public class AvvisoRepository implements Repository<Avviso> {
	
	public static final String TABLE_NAME = "avviso";
	public static final String[] COLUMN_NAME = {"titolo", "messaggio", "data", "addetto"};
	
	private static AvvisoRepository instance;
	
	public static AvvisoRepository getInstance(){

		instance = new AvvisoRepository();
		return instance;
	}
	
	@Override
	public void add(Avviso avviso) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String addAvviso = "INSERT INTO " + Utils.getRelazioneTable(TABLE_NAME, COLUMN_NAME) + " VALUES(?,?,?,?)";
		try{
			con = Connessione.getConnection();
			ps = con.prepareStatement(addAvviso);
			ps.setString(1, avviso.getTitolo());
			ps.setString(2, avviso.getMessaggio());
			ps.setDate(3, (Date) avviso.getData());
			ps.setString(4, avviso.getAddetto());
			ps.executeUpdate();
			
		}finally{
			con.close();
			ps.close();
		}
	}

	@Override
	public void delete(Avviso avviso) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String delAvviso = "DELETE FROM " + TABLE_NAME + " WHERE idAvviso = ?";
		try{
			con = Connessione.getConnection();
			ps = con.prepareStatement(delAvviso);
			ps.setInt(1, avviso.getId());
			ps.executeUpdate();
			
		}finally{
			ps.close();
			con.close();
		}
	}

	@Override
	public void update(Avviso item) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		String updateSQL = "update " + TABLE_NAME + " set titolo = ?, messaggio = ?, data = ?, "
				+ "addetto = ? where idAvviso = " + item.getId();
		
		try{
			conn = Connessione.getConnection();
			ps = conn.prepareStatement(updateSQL);
				
			ps.setString(1, item.getTitolo());
			ps.setString(2, item.getMessaggio());
			ps.setDate(3, (Date) item.getData());
			ps.setString(4, item.getAddetto());
				
			ps.executeUpdate();
		}finally{
			if(ps != null) {
				ps.close();
			}
			Connessione.releaseConnection(conn); //rilascia la connessione e la rende nuovamente disponibile
		}
		
	}

	@Override
	public Avviso findItemByQuery(Specification spec) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		SqlSpecification sql = (SqlSpecification) spec;
		String query = sql.toSqlQuery();
		Avviso item = new Avviso();
		try{
			con = Connessione.getConnection();
			ps = con.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			while(res.next()){
				item.setId(res.getInt("idAvviso"));
				item.setTitolo(res.getString("titolo"));
				item.setMessaggio(res.getString("messaggio"));
				item.setData(res.getDate("data"));
				item.setAddetto(res.getString("addetto"));
			}
			
		}finally{
			con.close();
			ps.close();
		}
		return item;
	}

	@Override
	public List<Avviso> query(Specification spec) throws SQLException {
		int id;
		String tit, msg, ad;
		Date data;
		Connection con = null;
		PreparedStatement ps = null;
		List<Avviso> list = new ArrayList<Avviso>();
		SqlSpecification sql = (SqlSpecification) spec;
		String query = sql.toSqlQuery();
		try{
			con = Connessione.getConnection();
			ps = con.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			while(res.next()){
				id = res.getInt("idAvviso");
				tit = res.getString("titolo");
				msg = res.getString("messaggio");
				data = res.getDate("data");
				ad = res.getString("addetto");
				Avviso tmp = new Avviso(id, tit, msg, data, ad);
				list.add(tmp);
			}
		}finally{
			con.close();
			ps.close();
		}
		return list;
	}
	
}
