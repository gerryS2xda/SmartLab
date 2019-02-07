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
import dataAccess.storage.bean.Avviso;

public class AvvisoRepository implements Repository<Avviso> {
	
	private String table = "avviso";
	private static AvvisoRepository instance;
	
	public static AvvisoRepository getInstance(){

		instance = new AvvisoRepository();
		return instance;
	}
	
	@Override
	public void add(Avviso avviso) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;
		String addAvviso = "INSERT INTO " + table + " VALUES(?,?,?,?,?)";
		try{
			con = Connessione.getConnection();
			ps = con.prepareStatement(addAvviso);
			ps.setInt(1, avviso.getId());
			ps.setString(2, avviso.getTitolo());
			ps.setString(3, avviso.getMessaggio());
			ps.setDate(4, (Date) avviso.getData());
			ps.setString(5, avviso.getAddetto());
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
		String delAvviso = "DELETE FROM " + table + " WHERE idAvviso = ?";
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public Avviso findItemByQuery(Specification spec) throws SQLException {
		int id;
		String tit, msg, ad;
		Date data;
		Connection con = null;
		PreparedStatement ps = null;
		SqlSpecification sql = (SqlSpecification) spec;
		String query = sql.toSqlQuery();
		Avviso item;
		try{
			con = Connessione.getConnection();
			ps = con.prepareStatement(query);
			ResultSet res = ps.executeQuery();
			id = res.getInt("idAvviso");
			tit = res.getString("titolo");
			msg = res.getString("messaggio");
			data = res.getDate("data");
			ad = res.getString("addetto");
			item = new Avviso(id, tit, msg, data, ad);
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
