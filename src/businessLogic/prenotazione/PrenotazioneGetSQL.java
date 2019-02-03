package businessLogic.prenotazione;

import dataAccess.storage.SqlSpecification;

public class PrenotazioneGetSQL implements SqlSpecification{
	
	//instance field
	private String oraInizio;
	private String oraFine;
	private String postazione;
	private String idLab;
			
	//constructor
	public PrenotazioneGetSQL(String oraInizio){
		this.oraInizio = oraInizio;
		this.oraFine = "";
		this.postazione = "";
		this.idLab = "";
	}
	
	public PrenotazioneGetSQL(String oraInizio, String oraFine, String postazione, String idLab){
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.postazione = postazione;
		this.idLab = idLab;
	}
			
			
	public String toSqlQuery(){
		String str = "";
		if(oraFine.equals("") && postazione.equals("") && idLab.equals("")){	//query della prenotazione by oraInizio
			str += String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME 
					+ " WHERE ora_inizio = '%s'", oraInizio);
		}else{
			str += String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME 
					+ " WHERE ora_inizio = '%s' AND ora_fine = '%s' AND postazione = %s AND Laboratorio = %s", oraInizio, oraFine, postazione, idLab);
		}
		return str;
	}

}
