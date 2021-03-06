package businessLogic.prenotazione;

import businessLogic.Postazione.PostazioneRepository;
import businessLogic.Postazione.PostazioneSql;
import businessLogic.laboratorio.LaboratorioRepository;
import businessLogic.laboratorio.LaboratorioSql;
import businessLogic.utente.StudenteRepository;
import businessLogic.utente.StudenteSQL;
import dataAccess.storage.bean.Laboratorio;
import dataAccess.storage.bean.Postazione;
import dataAccess.storage.bean.Prenotazione;
import dataAccess.storage.bean.Studente;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Un oggetto PrenotazioneManager contiene tutti i comportamenti che permettono di poter gestire le  
 * prenotazioni che vengono effettuate dagli studenti
 * @author gerardo michele laucella
*/
public class PrenotazioneManager {
	
	//instance field
	private PrenotazioneRepository repository;
	private PostazioneRepository postazioneRep;
	private LaboratorioRepository laboratorioRep;
	private StudenteRepository studenteRep;
	
	//static methods
	//usato per il testing
	public static PrenotazioneManager getInstance(){ 
		
		return new PrenotazioneManager();
		
	}
	
	//constructor
	/**
	 * Costruisce e inizializza un oggetto PrenotazioneManager e inizializza le repository
	 */
	public PrenotazioneManager(){
		repository = PrenotazioneRepository.getInstance();
		postazioneRep = PostazioneRepository.getInstance();
		laboratorioRep = LaboratorioRepository.getInstance();
		studenteRep = StudenteRepository.getInstance();
	}

	//private methods
	//usato per ricreare l'informazione completa di una prenotazione
	private void setAllInformationInPrenotazione(Prenotazione pr){
		if(pr == null){ 
			return; //se null non fare altro.. non ha senso recupare i dati dal DB
		}	
		int post = pr.getPostazione().getNumero();
		String idLab = pr.getLaboratorio().getIDlaboratorio();
		String emailStud = pr.getStudente().getEmail();
		
		//ottiene dati di postazione da repository
		Postazione postazione = new Postazione();
		try{
			postazione = postazioneRep.findItemByQuery(new PostazioneSql(post, idLab));
			pr.setPostazione(postazione);
		}catch(SQLException e){
			System.out.println("Errore: problema nel prendere i dati di postazioni dal DB!!");
		}
				
		//ottieni dati del laboratorio da repository
		try{
			Laboratorio lab = laboratorioRep.findItemByQuery(new LaboratorioSql(idLab));
			pr.setLaboratorio(lab);
		}catch(SQLException e){
			System.out.println("Errore: problema nel prendere i dati di postazioni dal DB!!");
		}
		
		if(emailStud.equals("")){	//significa che le informazioni sullo studente non sono richieste
			//ottieni i dati di studente da repository
			try{
				Studente stud = studenteRep.findItemByQuery(new StudenteSQL(emailStud));
				pr.setStudente(stud);
			}catch(SQLException e){
				System.out.println("Errore: problema nel prendere i dati dello studente dal DB!!");
			}
		}
	}
	
	//public methods
	/**
	 * Restituisce la prenotazione che e' stata effettuata in base ai dati passati in input.
	 * Si assume che la postazione sia disponibile e che lo studente ha effettuato < 3 prenotazioni
	 * @param emailStud l'email dello studente usata per tenere traccia di chi sta prenotando
	 * @param post e' il numero della postazione che si vuole prenotare
	 * @param oraInizio indica l'ora di inizio della occupazione di post
	 * @param oraFine indica l'ora di fine della occupazione di post
	 * @param idLab e' l'identificativo del laboratorio in cui si trova post
	 * @return prenotazione che e' stata effettuata
	 * @precondition isPostazioneDisponibile(post)
	 * @precondition getNumPrenotazioniEffettuateOggi(emailStud) < 3
	 * @post  getNumPrenotazioniEffettuateOggi(emailStud) = @pre.getNumPrenotazioniEffettuateOggi(s)+1
	 * @throws PrenotazioneException se getNumPrenotazioniEffettuateOggi(emailStud) > 2 
	 * @throws SQLException se si ha un problema con l'aggiungere i dati della prenotazione nel DB
	 */
	public Prenotazione effettuaPrenotazione(String emailStud, int post, String oraInizio, String oraFine, String idLab)throws PrenotazioneException, SQLException{
		
		Prenotazione pr = new Prenotazione();
		
		pr.setOraInizio(LocalTime.parse(oraInizio));
		pr.setOraFine(LocalTime.parse(oraFine));
		
		Postazione postazione = new Postazione();
		postazione.setNumero(post);
		pr.setPostazione(postazione);
		
		//ottieni dati del laboratorio da repository
		Laboratorio lab = new Laboratorio();
		lab = laboratorioRep.findItemByQuery(new LaboratorioSql(idLab));
		pr.setLaboratorio(lab);
		
		if(lab.getChiusura() == null){
			throw new SQLException("Errore: problema nel recuperare i dati del laboratorio dal DB!!"); 
		}
		
		//ottieni orario di chisura e se orario corrente > orario di chiusura --> setta le prenotazioni per il giorno seguente
		int oraCorrente = LocalTime.now().getHour();
		int oraChiusura = lab.getChiusura().getHour();
		if(oraCorrente > oraChiusura){
			pr.setData(LocalDate.now().plusDays(1).toString());	//prendi le prenotazioni per il giorno successivo	
		}else{
			pr.setData(LocalDate.now().toString());
		}
		
		Studente stud = new Studente();
		stud.setEmail(emailStud);
		pr.setStudente(stud);
		
		if(getNumPrenotazioniEffettuateOggi(emailStud) < 2){	//< 2 perche': se prenEff= 0; -> add; se prenEff = 1 --> add; se prenEff = 2 --> Stop
			pr.setStatus(true); //se i controlli sono rispettati
			repository.add(pr); 
		}else{
			throw new PrenotazioneException("Lo studente ha gia' effettuato 2 prenotazioni!! Riprova domani");
		}	
		
		return pr;
	}
	
	/**
	 * Annulla una prenotazione effettuata, si assume che ci sia almeno una prenotazione da annullare
	 * @param pr indica la prenotazione da annullare
	 * @precondition isPrenotazioneActive(pr) && oraAttuale < pr.getOraInizio().getHour() - 2
	 * @post  getNumPrenotazioniEffettuateOggi(s) = @pre.getNumPrenotazioniEffettuateOggi(s)-1
	 * @throws PrenotazioneException se la prenotazione non e' piu' annullabile
	 * @throws SQLException se ci sono problemi con la rimozione di pr dalla table 'Prenotazione'
	 */
	public void annullaPrenotazione(Prenotazione pr)throws PrenotazioneException, SQLException{
		
		int oraAttuale = LocalTime.now().getHour();
		
		int oraInizio = pr.getOraInizio().getHour() - 2; //puoi annullare almeno 2 ore prima dell'inizio della prenotazione
		if(oraAttuale < oraInizio){
			repository.delete(pr);		
		}else{
			throw new PrenotazioneException("La prenotazione non puo' essere piu' annullata");
		}
		
	}
	
	/**
	 * Restituisce una prenotazione a cui e' associata l'id dato in input
	 * @param id indica il valore per ricercare la postazione
	 * @return prenotazione che ha l'id associato a quello dato in input
	 * @throws PrenotazioneException se ID < 0
	 * @throws SQLException se ci sono problemi con la ricerca della prenotazione nel DB
	 * @precondition id > 0
	 */
	public Prenotazione findPrenotazioneById(int id)throws PrenotazioneException, SQLException{
		
		if(id < 0){
			throw new PrenotazioneException("ID della prenotazione non valido!! ID deve essere > 0");
		}
		
		Prenotazione pr = new Prenotazione();	//da decidere se oggetto vuoto oppure null (uso di eccezione customizzata)
		pr = repository.findItemByQuery(new PrenotazioneById(id));
		
		//ottieni e setta le informazioni presenti nel DB di studente, postazione e laboratori per i rispettivi oggetti
		setAllInformationInPrenotazione(pr);
		return pr;
		
	}
	
	
	/**
	 * Restituisce una lista di prenotazioni effettuate da uno studente in base all'email
	 * @param stud indica l'email dello studente
	 * @return lista prenotazioni
	 * @throws SQLException se ci sono problemi nell'esecuzione della query
	 * @precondition emailStud != "" && emailStud != null
	 * @post prenotazione[], dove per ogni i > 0, prenotazione.get(i).getEmail().equals(stud)
	 */
	public List<Prenotazione> getListPrenotazioniByStudent(String stud)throws SQLException{
		
		List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		
		prenotazioni = repository.query(new PrenotazioneByStudent(stud));
		
		//ottieni e setta le informazioni presenti nel DB di studente, postazione e laboratori per i rispettivi oggetti
		for(int i = 0; i < prenotazioni.size(); i++){
			setAllInformationInPrenotazione(prenotazioni.get(i));
		}
		return prenotazioni;
	} 
	
	/**
	 * Verifica se una prenotazione e' attiva in base all'ora di fine della prenotazione rispetto all'ora corrente
	 * Se oraAttuale < pr.getOraFine(), allora la prenotazione e' attiva
	 * @param pr indica la prenotazione da controllare
	 * @return lo stato di prenotazione: attiva (true), disattiva (false)
	 * @precondition pr.getOraFine() != null
	 * @post se oraAttuale >= OraFine --> false; se oraAttuale < oraFine --> true
	 */
	public boolean isPrenotazioneActive(Prenotazione pr){
		boolean val = true;
		
		int oraAttuale = LocalTime.now().getHour();
		int oraFine = pr.getOraFine().getHour();
		if(oraAttuale >= oraFine){
			val = false;	
		}
		
		return val;
	}
	
	/**
	 * Cambia lo stato di una prenotazione sulla base del metodo isPrenotazioneActive(pr)
	 * @param pr indica la prenotazione a cui modificare lo stato
	 * @throws SQLException se ci sono stati problemi con la modifica dello stato di prenotazione
	 * @precondition pr.getOraFine() != null
	 * @post se oraAttuale >= OraFine --> false; se oraAttuale < oraFine --> true
	 */
	public void changePrenotazioneStatus(Prenotazione pr)throws SQLException{
		if(isPrenotazioneActive(pr)){
			pr.setStatus(true);
		}else{
			pr.setStatus(false);
		}
		repository.update(pr);
	}
	
	/**
	 * Restituisce il numero di prenotazioni che sono state effettuate da uno studente nella data odierna
	 * @param stud rappresenta l'eamil dello studente per ricercare le prenotazioni
	 * @return numero prenotazioni effettuate
	 * @throws SQLException se ci sono stati problemi nell'ottenere la lista di prenotazioni dal DB
	 * @precondition emailStud != null 
	 * @post prenotazioni[], dove per ogni i > 0, today == dataPrenotazione --> conta la prenotazione
	 */
	public int getNumPrenotazioniEffettuateOggi(String stud)throws SQLException{

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
	 * Restituisce il numero delle postazioni prenotate in base all'ora di inizio e all'ID del laboratorio
	 * @param oraInizio usata per la ricerca
	 * @param idLab rappresenta l'ID del laboratorio
	 * @return numero postazioni prenotate
	 * @throws SQLException problema nell'eseguire la query
	 * @precondition oraInizio != "" && idLab == ""
	 * @post |prenotazioni[]| = numero di postazioni prenotate 
	 */
	public int getNumeroPostazioniPrenotate(String oraInizio, String idLab)throws SQLException{
		
		List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		prenotazioni = repository.query(new PrenotazioneGetSQL(oraInizio, "", 0, idLab));
		
		return prenotazioni.size();
	} 
	
	/**
	 * Restituisce una lista delle prenotazioni in base ai parametri formati in input
	 * @param oraInizio rappresenta l'ora in cui inizia l'occupazione di post
	 * @param oraFine rappresenta l'ora in cui termina l'occupazione di post
	 * @param post rappresenta il numero della postazione
	 * @param idlab rappresenta l'ID del laboratorio
	 * @return numero postazioni prenotate
	 * @throws SQLException problema nell'eseguire la query
	 * @post |prenotazioni[]| >= 0 
	 */
	public List<Prenotazione> getPrenotazioniByQuery(String oraInizio, String oraFine, int post, String idlab)throws SQLException{
		List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
		
		prenotazioni = repository.query(new PrenotazioneGetSQL(oraInizio, oraFine, post, idlab));
		
		//ottieni e setta le informazioni presenti nel DB di studente, postazione e laboratori per i rispettivi oggetti
		for(int i = 0; i < prenotazioni.size(); i++){
			setAllInformationInPrenotazione(prenotazioni.get(i));
		}
		return prenotazioni;
	}
	
	/**
	 * Cancella tutte le prenotazioni che sono presenti nella table 'Prenotazione'
	 * @throws SQLException problema nell'eseguire la query relativa alla 'Delete'
	 * @post|prenotazioni[]| == 0
	 */
	public void deleteAllPrenotazioni()throws SQLException{
		List<Prenotazione> prenotazioni = repository.query(new ListaPrenotazioniQuery());
		for(int i = 0; i < prenotazioni.size(); i++){
			Prenotazione pr = prenotazioni.get(i);
			repository.delete(pr);
		}
	}
	
	/**
	 * Cancella tutte le prenotazioni che sono presenti da diversi giorni
	 * @throws SQLException problema nell'eseguire la query definita in query()
	 * @post se dataCorrente == dataPren --> Rimuovi
	 * @post se dataPren == dataDomani --> Non cancellare
	 */
	public void deleteAllPrenotazioniAfterDays()throws SQLException{
		List<Prenotazione> prenotazioni = repository.query(new ListaPrenotazioniQuery());
		String dataCorrente = LocalDate.now().toString();
		String dataDomani = LocalDate.now().plusDays(1).toString();
		for(int i = 0; i < prenotazioni.size(); i++){
			Prenotazione pr = prenotazioni.get(i);
			if(!pr.getData().equals(dataCorrente) && !pr.getData().equals(dataDomani)){
				repository.delete(pr);
			}
		}
	}
	
	/**
	 * Ottieni tutte le prenotazioni presenti nel DB
	 * @throws SQLException problema nell'eseguire la query
	 * @post |prenotazioni[]| >= 0
	 * @post se dataPren == dataDomani --> Non cancellare
	 * @return una lista di tutte le prenotazioni 
	 */
	public List<Prenotazione> getAllPrenotazioni() throws SQLException{
		List<Prenotazione> prenotazioni = repository.query(new ListaPrenotazioniQuery());
		for(int i = 0; i < prenotazioni.size(); i++){
			setAllInformationInPrenotazione(prenotazioni.get(i));
		}
		return prenotazioni;
	}
}



