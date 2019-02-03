package businessLogic.prenotazione;

import dataAccess.storage.SqlSpecification;

public class ListaPrenotazioniQuery implements SqlSpecification{

	//constructor
	public ListaPrenotazioniQuery(){
		//vuoto
	}
	
	public String toSqlQuery(){
		String str = String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME);
		return str;
	}
}
