package businessLogic.utente;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import businessLogic.addetto.AddettoRepository;
import businessLogic.utente.UtenteRepository;
import dataAccess.storage.bean.Sospensione;
import dataAccess.storage.bean.Studente;
import dataAccess.storage.bean.Utente;

public class UtenteManager {
	
	private static UtenteManager instance;

	private UtenteRepository utente = new UtenteRepository();
	private StudenteRepository studente = new StudenteRepository();
	private SospensioneRepository sospensione = new SospensioneRepository();
	
	public static UtenteManager getInstace(){
		if (instance == null){
			instance = new UtenteManager();
		}
		return instance;
	}
	
	public UtenteManager(){
		utente = UtenteRepository.getInstance();
		studente = StudenteRepository.getInstance();
		sospensione = SospensioneRepository.getInstance();
		addetto = AddettoRepository.getInstance();
	}
	
	public boolean registraStudente (Studente s) throws SQLException{
		if(isEmailRight(s.getEmail()) && isPasswordRight(s.getPassword())){
			studente.add(s);
			return true;
		}
		else return false;
	}
	
	private boolean isEmailRight(String email) {
		char c;
		String str;
		for(int i=0; i<email.length(); i++){
			c = email.charAt(i);
			if(c=='@'){
			str = email.substring(i+1, email.length()-1);
			if(str.compareTo("studenti.unisa.it") == 0 || str.compareTo("unisa.it") == 0)
				return true;
			}
		}
		return false;
	}

	private boolean isPasswordRight(String password) {
		if(password.length()<8 || password.length()>16) return false;
		else return true;
		
		/** DA COMPLETARE? **/
	}

	public Studente effettuaAutenticazione (String email, String password){
		Studente s = new Studente();
		try{
			s = studente.findItemByQuery(new StudenteLoginSQL(email, password));
		}catch(SQLException e){
			//error msg
		}
		return s;
	}
	
	public Sospensione effettuaSospensione (Studente s) throws SQLException{
		Sospensione v = new Sospensione();
		v.setStudente(s);
		s.setStato(true);
		sospensione.add(v);
		return v;
	}
	
	//Metodo Sbagliato
	public List<Utente> getAccountList() throws SQLException{
		return utente.query(new UtenteList()); //deve essere un oggetto Specification
	}
	
}
