package businessLogic.prenotazione;

import dataAccess.storage.SqlSpecification;

/**
 * Un oggetto PrenotazioneById definisce un comportamento che specifica una query da eseguire per
 * ottenere i dati di una o più prenotazioni presenti nella table di 'Prenotazione' (tupla/e)
 * @author gerardo michele laucella
*/
public class PrenotazioneById implements SqlSpecification{

	//instance field
	private int id;
	
	//constructor
	/**
	 * Costruisce e inizializza un oggetto PrenotazioneById passando come input l'id della prenotazione 
	 * @param id rappresenta l'id della prenotazione
	 */
	public PrenotazioneById(int id){
		this.id = id;
	}
	
	/**
	 * Query che permette di ottenere una prenotazione avente IDprenotazione = id
	 * @post stringa che rappresenta una query da eseguire 
	 */	
	public String toSqlQuery(){
		String str = String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME 
				+ " WHERE IDprenotazione = %d", id);
		return str;
	}
}
