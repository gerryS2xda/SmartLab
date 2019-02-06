package businessLogic.prenotazione;

/**
 * Un oggetto PrenotazioneException che definisce un'eccezione che viene lanciata quando 
 * si verificano alcuni comportamenti non ammessi che hanno a che fare con le prenotazioni
 * @author gerardo michele laucella
*/
public class PrenotazioneException extends Exception {

	/**
	 * Costruisci un PrenotazioneException senza fornire in input un messaggio
	 */
	public PrenotazioneException(){
		super("Problemi con le funzionalita' relative alla gestione delle prenotazioni");
	}
	
	/**
	 * Costruisci un PrenotazioneException fornendo in input un messaggio specifico che descrive l'eccezione
	 * @param msg rappresenta il messaggio
	 */
	public PrenotazioneException(String msg){
		super(msg);
	}
}
