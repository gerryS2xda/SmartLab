package businessLogic.prenotazione;

import dataAccess.storage.SqlSpecification;

public class PrenotazioneByStudent implements SqlSpecification{

	//instance field
	private String stud;	//sostituire con oggetto Studente
	
	//constructor
	public PrenotazioneByStudent(String stud){
		this.stud = stud;
	}
	
	
	public String toSqlQuery(){
		String str = String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME 
				+ " WHERE Studente = '%s'", stud);
		return str;
	}
}
