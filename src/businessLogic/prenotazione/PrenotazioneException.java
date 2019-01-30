package businessLogic.prenotazione;

public class PrenotazioneException extends RuntimeException {

	public PrenotazioneException(){
		super("Problemi con le funzionalita' relative alla gestione delle prenotazioni");
	}
	
	public PrenotazioneException(String msg){
		super(msg);
	}
}
