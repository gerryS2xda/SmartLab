package businessLogic.utente;

import dataAccess.storage.bean.Sospensione;
import dataAccess.storage.bean.Studente;
import java.sql.SQLException;
import java.util.List;

public class UtenteManager {
	
	private static UtenteManager instance;
	private StudenteRepository studente = new StudenteRepository();
	private SospensioneRepository sospensione = new SospensioneRepository();
	private int countID=0;
	
	public static UtenteManager getInstace(){
		instance = new UtenteManager();
		return instance;
	}
	
	public UtenteManager(){
		studente = StudenteRepository.getInstance();
		sospensione = SospensioneRepository.getInstance();
	}
	
	public void registraStudente(Studente s) throws SQLException{
		studente.add(s);
	}
	

	public Studente effettuaAutenticazione(String email, String password){
		Studente s = new Studente();
		try{
			s = studente.findItemByQuery(new StudenteLoginSQL(email, password));
		}catch(SQLException e){
			e.printStackTrace();
		}
		return s;
	}
	
	public Sospensione effettuaSospensione(Studente s, String motivazione) throws SQLException{
		Sospensione sos = new Sospensione();
		sos.setStudente(s.getEmail());
		sos.setMotivazione(motivazione);
		sos.setID(countID);
		s.setStato(true);
		sospensione.add(sos);
		return sos;
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
