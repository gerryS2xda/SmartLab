package businessLogic.prenotazione;

import dataAccess.storage.SqlSpecification;

/**
 * Un oggetto PrenotazioneGetSQL definisce un comportamento che specifica una query da eseguire per
 * ottenere i dati di una o più prenotazioni presenti nella table di 'Prenotazione' (tupla/e)
 * @author gerardo michele laucella
*/
public class PrenotazioneGetSQL implements SqlSpecification{
	
	//instance field
	private String oraInizio;
	private String oraFine;
	private int postazione;
	private String idLab;
			
	//constructor
	/**
	 * Costruisce e inizializza un oggetto PrenotazioneByOra passando come input alcuni parametri
	 * @param oraInizio rappresenta l'ora in cui inizia l'occupazione di una postazione
	 * @param oraFine rappresenta l'ora in cui inizia l'occupazione di una postazione
	 * @param postazione rappresenta il numero della postazione
	 * @param idLab rappresenta l'identificativo del laboratorio in cui si trova una postazione
	 */
	public PrenotazioneGetSQL(String oraInizio, String oraFine, int postazione, String idLab){
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.postazione = postazione;
		this.idLab = idLab;
	}
			
	/**
	 * Query che permette di ottenere una lista di prenotazioni  
	 * @post stringa che rappresenta una query che permette di ottenere le prenotazioni in base all'ora di inizio
	 * @post stringa che rappresenta una query che permette di ottenere le prenotazioni in base all'ora di inizio e id del laboratorio
	 * @post stringa che rappresenta una query che permette di ottenere le prenotazioni in base all'ora di inizio, fine, numero postazione e id del laboratorio
	 */		
	public String toSqlQuery(){
		String str = "";
		if(oraFine.equals("") && postazione == 0 && idLab.equals("")){	//query della prenotazione by oraInizio
			str += String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME 
					+ " WHERE ora_inizio = '%s'", oraInizio);
		}else if(oraFine.equals("") && postazione == 0){	//seleziona le prenotazioni in base all'ora di inizio e il laboratorio (usato per calcolare le postazioni disponibili)
			str += String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME 
					+ " WHERE ora_inizio = '%s' AND Laboratorio = %s", oraInizio, idLab);
		}else{
			str += String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME 
					+ " WHERE ora_inizio = '%s' AND ora_fine = '%s' AND postazione = %s AND Laboratorio = %s", oraInizio, oraFine, postazione, idLab);
		}
		return str;
	}

}
