package businessLogic.prenotazione;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dataAccess.storage.bean.Prenotazione;

/**
 * Un oggetto PrenotazioneManager contiene tutti i comportamenti che permettono di poter gestire le  
 * prenotazioni che vengono effettuate dagli studenti
 * @author gerardo michele laucella
*/
public class PrenotazioneManager {

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
	public Prenotazione effettuaPrenotazione(String stud, int post, String fasciaOraria){
		
		Prenotazione pr = new Prenotazione(fasciaOraria, stud, post);
		
		//aggiungere il controllo della postazione
		if(getNumPrenotazioniEffettuateOggi(stud) < 3){
			pr.setStatus(true); //se i controlli sono rispettati
		}
			
		PrenotazioneRepository rep = new PrenotazioneRepository();
		try{
			rep.add(pr); 
		}catch(SQLException e){
			System.out.println("Errore: problema nell'aggiungere la prenotazione al DB!!");
		}finally{
			rep.closeConnection();
		}
		
		return pr;
	}
	
	/**
	 * Annulla una prenotazione effettuata, si assume che ci sia almeno una prenotazione da annullare
	 * @param pr indica la prenotazione da annullare
	 * @precondition getListPrenotazioniByStudent(s).size() > 0
	 * @pre isPrenotazioneActive(pr)
	 * @post  getNumPrenotazioniEffettuate(s) = @pre.getNumPrenotazioniEffettuate(s)-1
	 * @post  getNumPrenotazioniEffettuateOggi(s) = @pre.getNumPrenotazioniEffettuateOggi(s)-1
	 */
	public void annullaPrenotazione(Prenotazione pr){

		
		PrenotazioneRepository rep = new PrenotazioneRepository();
		try{
			rep.delete(pr);
		}catch(SQLException e){
			System.out.println("Errore: problema nella rimozione della prenotazione dal DB");
		}finally{
			rep.closeConnection();
		}
	}
	
	/**
	 * Restituisce una prenotazione a cui e' associata l'id dato in input
	 * @param id indica il valore per ricercare la postazione
	 * @return prenotazione che ha l'id associato a quello dato in input
	 * @pre id > 0
	 */
	public Prenotazione findPrenotazioneById(int id){
		
		if(id < 0) return null;
		PrenotazioneRepository rep = new PrenotazioneRepository();
		Prenotazione pr = new Prenotazione();	//da decidere se oggetto vuoto oppure null (uso di eccezione customizzata)
		try{
			pr = rep.findItemByQuery(new PrenotazioneById(id));
		}catch(SQLException e){
			System.out.println("Prenotazione non trovata!!");
		}finally{
			rep.closeConnection();
		}
		return pr;
		
	}
	
	/**
	 * Aggiorna le informazioni memorizzate di una prenotazione presente nel DB
	 * @param pr indica la nuova prenotazione da memorizzare
	 */
	public void updatePrenotazione(Prenotazione pr){
		
		PrenotazioneRepository rep = new PrenotazioneRepository();
		try{
			rep.update(pr);
		}catch(SQLException e){
			System.out.println("Errore: problema nell'aggiornare una prenotazione dal DB");
		}finally{
			rep.closeConnection();
		}
	}
	
	/**
	 * Restituisce una lista di prenotazioni effettuate da uno studente
	 * @param s studente per ricercare le prenotazioni
	 * @return lista prenotazioni
	 */
	public List<Prenotazione> getListPrenotazioniByStudent(String stud){
		
		List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		PrenotazioneRepository rep = new PrenotazioneRepository();
		try{
			prenotazioni = rep.query(new PrenotazioneByStudent(stud));
		}catch(SQLException e){
			System.out.println("Errore: lo studente non ha effettuato prenotazioni");
		}finally{
			rep.closeConnection();
		}
		return prenotazioni;
	} 
	
	/**
	 * Verifica se una prenotazione e' attiva in base al suo stato
	 * @param pr indica la prenotazione da controllare
	 * @return esito della verifica
	 */
	public boolean isPrenotazioneActive(Prenotazione pr){
		return pr.isPrenotazioneActive();
	}
	
	/**
	 * Restituisce il numero di prenotazioni che sono state effettuate da uno 
	 * studente nella data odierna
	 * @param s studente per ricercare le prenotazioni
	 * @return numero prenotazioni effettuate
	 * @pre getNumPrenotazioniEffettuate(s) > 0
	 */
	public int getNumPrenotazioniEffettuateOggi(String stud){
		if(getNumPrenotazioniEffettuate(stud) < 0) return 0;
		
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


