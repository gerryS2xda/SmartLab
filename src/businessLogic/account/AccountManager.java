package businessLogic.account;

import java.util.ArrayList;

import dataAccess.storage.bean.Sospensione;
import dataAccess.storage.bean.Studente;
import dataAccess.storage.bean.Utente;

public class AccountManager {
	
	private static AccountManager instance;
	private ArrayList<Utente> accountList = new ArrayList<Utente>();
	
	public static AccountManager getInstace(){
		if (instance == null){
			instance = new AccountManager();
		}
		return instance;
	}
	
	public void effettuaRegistrazione (String email, String password, String name, String surname){
		if (isEmailRight(email) && isPasswordRight(password)){ //questi metodi li implemento in questa classe?
			Utente u = new Utente(email, password, name, surname); //come risolvo?
			accountList.add(u);
		}
	}
	
	public boolean effettuaAutenticazione (String email, String password){
		for(Utente u: accountList){
			if ((u.getEmail().equals(email)) && (u.getPassword().equals(password))){
				return true;
			}
		}
		return false;
	}
	
	public Sospensione effettuaSospensione (Studente s){
		Sospensione v = new Sospensione();
		v.setStudente(v, s); //questi metodi li implemento in questa classe?
		s.changeStatusStudente(s); //questi metodi li implemento in questa classe?
		return v;
	}
	
	public ArrayList<Utente> getAccountList(){
		return accountList;
	}
	
}
