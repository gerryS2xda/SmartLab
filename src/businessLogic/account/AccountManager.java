package businessLogic.account;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import businessLogic.account.AccountRepository;
import dataAccess.storage.bean.Sospensione;
import dataAccess.storage.bean.Studente;
import dataAccess.storage.bean.Utente;

public class AccountManager {
	
	private static AccountManager instance;

	private AccountRepository r;
	
	public static AccountManager getInstace(){
		if (instance == null){
			instance = new AccountManager();
		}
		return instance;
	}
	
	public AccountManager(){
		r = AccountRepository.getInstance();
	}
	
	public void effettuaRegistrazione (Utente u) throws SQLException{
		if (isEmailRight(u.getEmail()) && isPasswordRight(u.getPassword())){
			r.add(u);
		}
	}
	
	private boolean isEmailRight(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	private boolean isPasswordRight(String password) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean effettuaAutenticazione (String email, String password) throws SQLException{
		List<Utente> list = getAccountList();
		for(Utente u: list){
			if ((u.getEmail().equals(email)) && (u.getPassword().equals(password))){
				return true;
			}
		}
		return false;
	}
	
	public Sospensione effettuaSospensione (Studente s){
		Sospensione v = new Sospensione();
		v.setStudente(s);
		s.setStato(true);
		return v;
	}
	
	public List<Utente> getAccountList() throws SQLException{
		List<Utente> accountList = new ArrayList<Utente>();
		accountList = r.query(new AccountList());
		return accountList;
	}
	
}
