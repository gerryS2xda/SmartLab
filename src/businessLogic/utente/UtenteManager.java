package businessLogic.utente;

import dataAccess.storage.bean.Sospensione;
import dataAccess.storage.bean.Studente;
import java.sql.SQLException;
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
		
		/** DA COMPLETARE **/
	}

	public Studente effettuaAutenticazione (String email, String password){
		Studente s = new Studente();
		try{
			s = studente.findItemByQuery(new StudenteLoginSQL(email, password));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return s;
	}
	
	public Sospensione effettuaSospensione (Studente s, String motivazione) throws SQLException{
		Sospensione v = new Sospensione();
		v.setStudente(s);
		v.setMotivazione(motivazione);
		s.setStato(true);
		sospensione.add(v);
		return v;
	}
	

	public boolean isStudentPresente(String email){
		boolean b = false;
		Studente s = new Studente();
		try{
			s = studente.findItemByQuery(new StudenteSQL(email));
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		if(!s.getEmail().equals("")){
			b = true;
		}
		
		return b;
	}
	
	public List<Studente> getStudentList() throws SQLException{
		return studente.query(new StudentList());
	}
	
	public void editPassword(String email, String newPassword)throws SQLException{
		
		Studente stud = new Studente();
		
		stud = studente.findItemByQuery(new StudenteSQL(email));
		stud.setPassword(newPassword);
		studente.update(stud);
	}
}
