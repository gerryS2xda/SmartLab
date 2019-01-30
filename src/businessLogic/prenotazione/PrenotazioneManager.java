package businessLogic.prenotazione;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dataAccess.storage.bean.Prenotazione;

/**
 * Un oggetto PrenotazioneManager contiene tutti i comportamenti che permettono di poter gestire le  
 * prenotazioni che vengono effettuate dagli studenti
 * @author gerardo michele laucella
*/
public class PrenotazioneManager {
	
	//instance field
	private PrenotazioneRepository repository;
	
	//static methods
	//usato per il testing
	public static PrenotazioneManager getInstance(){ 
		
		return new PrenotazioneManager();
		
	}
	
	public PrenotazioneManager(){
		repository = PrenotazioneRepository.getInstance();
	}

	/**
	 * Restituisce la prenotazione che e' stata effettuata in base ai dati passati in input.
	 * Si assume che la postazione sia disponibile e che lo studente ha effettuato < 3 prenotazioni
	 * @param s studente che sta effettuando la prenotazione
	 * @param p postazione che si vuole prenotare
	 * @param fasciaOraria indica la fascia oraria della prenotazione
	 * @return prenotazione che e' stata effettuata
	 * @pre isPostazioneDisponibile(p)
	 * @pre getNumPrenotazioniEffettuateOggi(s) < 3
	 * @post  getNumPrenotazioniEffettuate(s) = @pre.getNumPrenotazioniEffettuate(s)+1
	 * @post  getNumPrenotazioniEffettuateOggi(s) = @pre.getNumPrenotazioniEffettuateOggi(s)+1
	 */
	public Prenotazione effettuaPrenotazione(String stud, int post, String oraInizio, String oraFine)throws PrenotazioneException{
		
		Prenotazione pr = new Prenotazione();
		pr.setData(LocalDate.now().toString());
		pr.setOraInizio(LocalTime.parse(oraInizio));
		pr.setOraFine(LocalTime.parse(oraFine));
		pr.setPostazione(post);
		pr.setStudente(stud);
		
		//aggiungere il controllo della postazione
		if(getNumPrenotazioniEffettuateOggi(stud) < 3){
			pr.setStatus(true); //se i controlli sono rispettati
		}else{
			throw new PrenotazioneException("Lo studente ha gia' effettuato 2 prenotazioni!! Riprova domani");
		}
			
		try{
			repository.add(pr); 
		}catch(SQLException e){
			System.out.println("Errore: problema nell'aggiungere la prenotazione al DB!!");
		}
		
		return pr;
	}
	
	/**
	 * Annulla una prenotazione effettuata, si assume che ci sia almeno una prenotazione da annullare
	 * @param pr indica la prenotazione da annullare
	 * @precondition isPrenotazioneActive(pr) && ora attuale < pr.getOraInizio().getHour() - 2
	 * @post  getNumPrenotazioniEffettuate(s) = @pre.getNumPrenotazioniEffettuate(s)-1
	 * @post  getNumPrenotazioniEffettuateOggi(s) = @pre.getNumPrenotazioniEffettuateOggi(s)-1
	 */
	public void annullaPrenotazione(Prenotazione pr)throws PrenotazioneException{

		//controlli precondizione
		if(!isPrenotazioneActive(pr)){
			throw new PrenotazioneException("Prenotazione gia' scaduta!!");
		}
		
		int oraAttuale = LocalTime.now().getHour();
		int oraInizio = pr.getOraInizio().getHour() - 2; //puoi annullare almeno 2 ore prima dell'inizio della prenotazione
		if(oraAttuale < oraInizio){
			try{
				repository.delete(pr);
			}catch(SQLException e){
				System.out.println("Errore: problema nella rimozione della prenotazione dal DB");
			}	
		}else{
			throw new PrenotazioneException("La prenotazione non puo' essere piu' annullata");
		}
		
	}
	
	/**
	 * Restituisce una prenotazione a cui e' associata l'id dato in input
	 * @param id indica il valore per ricercare la postazione
	 * @return prenotazione che ha l'id associato a quello dato in input
	 * @pre id > 0
	 */
	public Prenotazione findPrenotazioneById(int id)throws PrenotazioneException{
		
		if(id < 0){
			throw new PrenotazioneException("ID della prenotazione non valido!! ID deve essere > 0");
		}
		
		Prenotazione pr = new Prenotazione();	//da decidere se oggetto vuoto oppure null (uso di eccezione customizzata)
		try{
			pr = repository.findItemByQuery(new PrenotazioneById(id));
		}catch(SQLException e){
			System.out.println("Prenotazione non trovata!!");
		}
		return pr;
		
	}
	
	/**
	 * Aggiorna le informazioni memorizzate di una prenotazione presente nel DB
	 * @param pr indica la nuova prenotazione da memorizzare
	 */
	public void updatePrenotazione(Prenotazione pr){
		
		try{
			repository.update(pr);
		}catch(SQLException e){
			System.out.println("Errore: problema nell'aggiornare una prenotazione dal DB");
		}
	}
	
	/**
	 * Restituisce una lista di prenotazioni effettuate da uno studente
	 * @param s studente per ricercare le prenotazioni
	 * @return lista prenotazioni
	 */
	public List<Prenotazione> getListPrenotazioniByStudent(String stud){
		
		List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		
		try{
			prenotazioni = repository.query(new PrenotazioneByStudent(stud));
		}catch(SQLException e){
			System.out.println("Errore: lo studente non ha effettuato prenotazioni");
		}
		return prenotazioni;
	} 
	
	/**
	 * Verifica se una prenotazione e' ancora attiva in base alla data corrente
	 * @param pr indica la prenotazione da controllare
	 * @return esito della verifica
	 */
	public boolean isPrenotazioneActive(Prenotazione pr){
		boolean val = pr.isPrenotazioneActive();
		if(val){	//si verifica se e' ancora attiva
			LocalDate dataCorrente = LocalDate.now(); //dammi la data corrente
			LocalDate dataPrenotazione = LocalDate.parse(pr.getData()); //ottieni data da prenotazione
			if(dataPrenotazione.isAfter(dataCorrente)){
				val = false;	
			}
		}
		return val;	//restituendo false, la servlet provvede a modificare il suo stato
	}
	
	/**
	 * Restituisce il numero di prenotazioni che sono state effettuate da uno 
	 * studente nella data odierna
	 * @param s studente per ricercare le prenotazioni
	 * @return numero prenotazioni effettuate
	 * @pre getNumPrenotazioniEffettuate(s) > 0
	 */
	public int getNumPrenotazioniEffettuateOggi(String stud){
		if(getNumPrenotazioniEffettuate(stud) <= 0) return 0;
		
		//potrebbe essere ottenuto da una query 
		List<Prenotazione> prenotazioni = getListPrenotazioniByStudent(stud);
		LocalDate today = LocalDate.now();
		int count = 0;
		for(Prenotazione p : prenotazioni){
			if(p.getData().toString().equals(today.toString())){
				count++;
			}
		}
		return count;
	}
	
	/**
	 * Restituisce il numero totale di prenotazioni che sono state effettuate da uno studente
	 * @param s studente per calcolare questo valore
	 * @return numero totale di prenotazioni effettuate
	 */
	public int getNumPrenotazioniEffettuate(String stud){
		List<Prenotazione> prenotazioni = getListPrenotazioniByStudent(stud);
		return prenotazioni.size();
	}

}



