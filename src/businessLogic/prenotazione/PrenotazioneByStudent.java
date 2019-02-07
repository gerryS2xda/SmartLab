package businessLogic.prenotazione;

import dataAccess.storage.SqlSpecification;

/**
 * Un oggetto PrenotazioneByStudent definisce un comportamento che specifica una query da eseguire per
 * ottenere i dati di una o più prenotazioni presenti nella table di 'Prenotazione' (tupla/e)
 * @author gerardo michele laucella
*/
public class PrenotazioneByStudent implements SqlSpecification{

	//instance field
	private String stud;	//sostituire con oggetto Studente
	
	//constructor
	/**
	 * Costruisce e inizializza un oggetto PrenotazioneByStudent passando come input l'email dello studebte
	 * @param stud rappresenta l'email dello studente da usare nella query
	 */
	public PrenotazioneByStudent(String stud){
		this.stud = stud;
	}
	
	/**
	 * Query che permette di ottenere una lista di prenotazioni che effettuato uno studente in base all'email
	 * @post stringa che rappresenta una query da eseguire 
	 * @return stringa che rappresenta una query da eseguire
	 */	
	public String toSqlQuery(){
		String str = String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME 
				+ " WHERE Studente = '%s'", stud);
		return str;
	}
}
