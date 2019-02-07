package businessLogic.addetto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.storage.bean.Addetto;

public class AddettoManager {
	
	private static AddettoManager instance;
	private AddettoRepository r = new AddettoRepository();
	
	public static AddettoManager getInstace(){
		instance = new AddettoManager();
		return instance;
	}

	public AddettoManager(){
		r = AddettoRepository.getInstance();
	}


	public boolean addResp(Addetto a) throws SQLException {
		boolean b = false;

		try {
			r.add(a);
			b = true;
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}

		return b;	
	}
	
	public boolean rimuoviResp(Addetto resp){
		boolean b = false;
		
		try {
			r.delete(resp);
			b = true;
		} catch (SQLException e) {
			b = false;
			e.printStackTrace();
		}
		
		return b;
	}
	
	public List<Addetto> getListaResp() throws SQLException{
		List<Addetto> resp = new ArrayList<Addetto>();
		resp = r.query(new AddettoList());
		return resp;
	}

	public Addetto effettuaAutenticazione(String email, String password){
		Addetto addetto = new Addetto();
		try{
		 addetto = r.findItemByQuery(new AddettoLoginSQL(email, password));
		}catch(SQLException e){
		 e.printStackTrace();
		}
		return addetto;
	}
}
