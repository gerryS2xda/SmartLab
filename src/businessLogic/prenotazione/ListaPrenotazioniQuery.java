package businessLogic.prenotazione;

import java.sql.SQLException;

import dataAccess.storage.SqlSpecification;

/**
 * Un oggetto ListaPrenotazioniQuery definisce un comportamento che specifica una query da eseguire per
 * ottenere i dati di una o più prenotazioni presenti nella table di 'Prenotazione' (tupla/e)
 * @author gerardo michele laucella
*/
public class ListaPrenotazioniQuery implements SqlSpecification{

	//constructor
	public ListaPrenotazioniQuery(){
		//vuoto
	}
	
	/**
	 * Query che permette di ottenere tutte le prenotazioni presenti nel DB
	 * @post stringa che rappresenta una query da eseguire 
	 */
	public String toSqlQuery(){
		String str = String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME);
		return str;
	}
}
