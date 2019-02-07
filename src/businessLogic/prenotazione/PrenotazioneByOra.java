package businessLogic.prenotazione;

import dataAccess.storage.SqlSpecification;

/**
 * Un oggetto PrenotazioneByOra definisce un comportamento che specifica una query da eseguire per
 * ottenere i dati di una o più prenotazioni presenti nella table di 'Prenotazione' (tupla/e)
 * @author gerardo michele laucella
*/
public class PrenotazioneByOra implements SqlSpecification{
	
	//instance field
	private String oraInizio;
	private String oraFine;
	private String idLab;
			
	//constructor
	/**
	 * Costruisce e inizializza un oggetto PrenotazioneByOra passando come input: ora di inizio e fine occupazione della postazione e id del laboratorio
	 * @param oraInizio rappresenta l'ora in cui inizia l'occupazione di una postazione
	 * @param oraFine rappresenta l'ora in cui inizia l'occupazione di una postazione
	 * @param idLab rappresenta l'identificativo del laboratorio in cui si trova una postazione
	 */
	public PrenotazioneByOra(String oraInizio, String oraFine, String idLab){
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.idLab = idLab;
	}
			
	/**
	 * Query che permette di ottenere una lista di prenotazioni in base all'ora di inizio, fine e id del laboratorio
	 * @post stringa che rappresenta una query da eseguire 
	 */	
	public String toSqlQuery(){
		String str = String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME 
				+ " WHERE ora_inizio = '%s' AND ora_fine = '%s' AND Laboratorio = %s"  , oraInizio, oraFine, idLab);
		return str;
	}
}
