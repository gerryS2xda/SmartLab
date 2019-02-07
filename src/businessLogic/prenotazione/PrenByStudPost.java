package businessLogic.prenotazione;

import dataAccess.storage.SqlSpecification;

/**
 * Un oggetto PrenByStudPost definisce un comportamento che specifica una query da eseguire per
 * ottenere i dati di una o più prenotazioni presenti nella table di 'Prenotazione' (tupla/e)
 * @author gerardo michele laucella
*/
public class PrenByStudPost implements SqlSpecification{

	//instance field
	private String email;
	private String numeroPost;
	private String idLab;
		
	//constructor
	/**
	 * Costruisci e inizializza un oggetto PrenByStudPost passando come input: email dello studente, numero della posazione e id laboratorio 
	 * @param email rappresenta l'email dello studente
	 * @param numeroPost rappresenta il numero della postazione
	 * @param idLab rappresenta l'ID del laboratorio in cui e' presente numeroPost
	 */
	public PrenByStudPost(String email, String numeroPost, String idLab) {
		this.email = email;
		this.numeroPost = numeroPost;
		this.idLab = idLab;
	}
		
	/**
	 * Query che permette di ottenere tutte le prenotazioni aventi: 
	 * Studente == email, Postazione == numeroPost e Laboratorio == idLab. 
	 * @post stringa che rappresenta una query da eseguire 
	 * @return stringa che rappresenta una query
	 */	
	public String toSqlQuery() {
		String str = String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME 
				+ " WHERE Studente = '%s' AND Postazione = %s AND Laboratorio = %s"  , email, numeroPost, idLab);
		return str;
	}
}
