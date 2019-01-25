package businessLogic.responsabile;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataAccess.storage.bean.Addetto;

public class ResponsabileManager {
	
	private static ResponsabileManager instance;
	private ResponsabileRepository r = new ResponsabileRepository();
	
	public static ResponsabileManager getInstace(){
		if (instance == null){
			instance = new ResponsabileManager();
		}
		return instance;
	}
	
	public ResponsabileManager(){
		r = ResponsabileRepository.getInstance();
	}
	

	public void addResp (Addetto a) throws SQLException{
		if(isEmailRight(a.getEmail()) && isPasswordRight(a.getPassword())){
			r.add(a);
		}
	}
	
	private boolean isPasswordRight(String password) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isEmailRight(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	public void rimuoviResp (Addetto resp) throws SQLException{
		r.delete(resp);
	}
	
	public List<Addetto> getListaResp () throws SQLException{
		List<Addetto> resp = new ArrayList<Addetto>();
		resp = r.query(new ResponsabileList());
		return resp;
	}

}
