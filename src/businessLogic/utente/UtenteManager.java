package businessLogic.utente;

import businessLogic.addetto.AddettoRepository;
import dataAccess.storage.bean.Sospensione;
import dataAccess.storage.bean.Studente;
import dataAccess.storage.bean.Utente;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UtenteManager {
	
	private static UtenteManager instance;
	private StudenteRepository studente = new StudenteRepository();
	private SospensioneRepository sospensione = new SospensioneRepository();
	
	public static UtenteManager getInstace(){
		instance = new UtenteManager();
		return instance;
	}
	
	public UtenteManager(){
		studente = StudenteRepository.getInstance();
		sospensione = SospensioneRepository.getInstance();
	}
	
	public void registraStudente (Studente s) throws SQLException{
		studente.add(s);
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
	

	public boolean isStudentPresent(String email){
		boolean val = false;
		Studente stud = new Studente();
		try{
			stud = studente.findItemByQuery(new StudenteSQL(email));
		}catch(SQLException e){
			System.out.println("Errore: problema nell'eseguire la findItemByQuery() di StudenteRepository");
		}
		
		if(!stud.getEmail().equals("")){	//se email e' riempito --> studente e' presente
			val = true;
		}
		return val;
	}
	
	public List<Studente> getStudentList() throws SQLException{
		return studente.query(new StudentList());
	}
	
}
