package businessLogic.addetto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import businessLogic.utente.StudenteSQL;
import dataAccess.storage.bean.Addetto;
import dataAccess.storage.bean.Studente;
import dataAccess.storage.bean.Utente;

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
	

	public boolean addResp (Addetto a) throws SQLException{
		if(isEmailRight(a.getEmail()) && isPasswordRight(a.getPassword())){
			r.add(a);
			return true;
		} else return false;
	}
	
	private boolean isPasswordRight(String password) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isEmailRight(String email) {
		char c;
		String str;
		for(int i=0; i<email.length(); i++){
			c = email.charAt(i);
			if(c=='@'){
			str = email.substring(i+1, email.length()-1);
			if(str.compareTo("unisa.it") == 0)
				return true;
			}
		}
		return false;
	}
	
	public void rimuoviResp (Addetto resp) throws SQLException{
		r.delete(resp);
	}
	
	public List<Addetto> getListaResp () throws SQLException{
		List<Addetto> resp = new ArrayList<Addetto>();
		resp = r.query(new AddettoList());
		return resp;
	}

	public Addetto effettuaAutenticazione (String email, String password){
		Addetto addetto = new Addetto();
		try{
			addetto = r.findItemByQuery(new AddettoLoginSQL(email, password));
		}catch(SQLException e){
			//error msg
		}
		return addetto;
	}
}
