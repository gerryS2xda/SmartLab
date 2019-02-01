package businessLogic.prenotazione;

import dataAccess.storage.SqlSpecification;

public class PrenotazioneByOra implements SqlSpecification{
	
	//instance field
	private String oraInizio;
	private String oraFine;
	private String idLab;
			
	//constructor
	public PrenotazioneByOra(String oraInizio, String oraFine, String idLab){
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.idLab = idLab;
	}
			
			
	public String toSqlQuery(){
		String str = String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME 
				+ " WHERE OraInizio = '%s' AND OraFine = '%s' AND Laboratorio = %s"  , oraInizio, oraFine, idLab);
		return str;
	}
}
