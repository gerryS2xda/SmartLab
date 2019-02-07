package dataAccess.storage.bean;

import java.time.LocalTime;

/**
 * Un oggetto Prenotazione che modella i dati relativi ad una prenotazione
 * @author gerardo michele laucella
*/
public class Prenotazione {

	//instance field
	private int id;
	private String data;
	private LocalTime oraInizio; //l'ora in cui inizia la prenotazione della postazione
	private LocalTime oraFine; //l'ora in cui termina la prenotazione della postazione
	private boolean stato; 
	private Studente studente; //sostituire con oggetto studente
	private Postazione postazione;  //sostituire con oggetto postazione
	private Laboratorio laboratorio; //sostituire con oggetto Laboratorio
	
	//constructors
	/**
	 * Costruisce una Prenotazione senza definire il suo stato (viene assegnato di default)
	 */
	public Prenotazione(){
		//vuoto
	}
	
	/**
	 * Costruisce e inizializza un oggetto Prenotazione definendo il suo stato
	 * @param data rappresenta la data in cui si occupa una postazione
	 * @param oraInizio rappresenta l'ora in cui inizia l'occupazione di una postazione
	 * @param oraFine rappresenta l'ora in cui inizia l'occupazione di una postazione
	 * @param studente rappresenta un oggetto Studente (come stato ha l'email settata)
	 * @param postazione rappresenta un oggetto Postazione (come stato ha il numero di postazione settato)
	 * @param lab rappresenta un oggetto Laboratorio (che ha l'identificativo del laboratorio settato)
	 */
	public Prenotazione(String data, LocalTime oraInizio, LocalTime oraFine, Studente studente, Postazione postazione, Laboratorio lab){
		this.data= data;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.studente = studente;
		this.postazione = postazione;
		this.laboratorio = lab;
	}
	
	//getter and setter
	/**
	 * Restituisce l'ID associato a questa prenotazione
	 * @return ID, un intero
	 */
	public int getId() {
		return id;
	}

	/**
	 * Restituisce la data in cui si occupa una postazione
	 * @return una data sottoforma di stringa
	 */
	public String getData() {
		return data;
	}

	/**
	 * Restituisce un oggetto Studente che modella lo studente che ha effettuato una prenotazione
	 * @return un oggetto Studente
	 */
	public Studente getStudente() {
		return studente;
	}

	/**
	 * Restituisce un oggetto Postazione che modella la postazione prenotata
	 * @return un oggetto Postazione
	 */
	public Postazione getPostazione() {
		return postazione;
	}

	/**
	 * Restituisce un oggetto Laboratorio che modella un laboratorio associato a questa prenotazione (al numero della postazione)
	 * @return un oggetto Laboratorio
	 */
	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	/**
	 * Restituisce lo stato di una prenotazione: active (true), scaduta (false)
	 * @return status della prenotazione
	 */
	public boolean isPrenotazioneActive(){
		return stato;
	}
	
	/**
	 * Restituisce un oggetto LocalTime che modella l'orario di inizio della occupazione di una postazione
	 * @return un oggetto LocalTime
	 */
	public LocalTime getOraInizio() {
		return oraInizio;
	}
	
	/**
	 * Restituisce un oggetto LocalTime che modella l'orario di fine della occupazione di una postazione
	 * @return un oggetto LocalTime
	 */
	public LocalTime getOraFine() {
		return oraFine;
	}
	
	/**
	 * Setta l'ID di questa prenotazione in base al valore fornito in input
	 * @param id rappresenta l'ID (un intero) 
	 */
	public void setID(int id){
		this.id = id;
	}
	
	/**
	 * Setta la data di questa prenotazione in base al valore fornito in input
	 * @param data rappresenta la data associata a questa prenotazione
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * Setta l'oggetto Studnete a questa prenotazione in base al valore fornito in input
	 * @param studente rappresenta l'oggetto Studente che modella i dati di uno studente
	 */
	public void setStudente(Studente studente) {
		this.studente = studente;
	}

	/**
	 * Setta l'oggetto Postazione a questa prenotazione in base al valore fornito in input
	 * @param postazione rappresenta l'oggetto Postazione che modella i dati di una postazione
	 */
	public void setPostazione(Postazione postazione) {
		this.postazione = postazione;
	}

	/**
	 * Setta l'oggetto Laboratorio a questa prenotazione in base al valore fornito in input
	 * @param laboratorio rappresenta l'oggetto Laboratorio che modella i dati di un laboratorio
	 */
	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}
	
	/**
	 * Setta lo stato a questa Prenotazione (active (true), scaduta (false))
	 * @param s -- rappresenta il nuovo stato da impostare a questa prenotazione
	 */
	public void setStatus(boolean s){
		stato = s;
	}
	
	/**
	 * Setta l'oggetto LocalTime a questa prenotazione in base al valore fornito in input
	 * @param oraInizio rappresenta l'oggetto LocalTime
	 */
	public void setOraInizio(LocalTime oraInizio) {
		this.oraInizio = oraInizio;
	}
	
	/**
	 * Setta l'oggetto LocalTime a questa prenotazione in base al valore fornito in input
	 * @param oraFine rappresenta l'oggetto LocalTime
	 */
	public void setOraFine(LocalTime oraFine) {
		this.oraFine = oraFine;
	}
	
	//other method
	/**
	 * Restituisce una stringa che rappesenta l'oggetto Prenotazione e i suoi valori.
	 * La string e' stata adattata per essere usata come stringa JSON
	 * @return stringa in formato JSON
	 */
	public String toString(){	//adattato per costruzione della stringa JSON
		String str = "{\"id\":" + id + ", \"data\": \"" + data + "\", \"oraInizio\": \"" + getOraInizio().toString() + "\", "
				+ "\"oraFine\": \"" + getOraFine().toString() + "\",  \"email\": \"" + getStudente().getEmail() + "\", \"postazione\": " + getPostazione().getNumero() + ", "  
				+ "\"laboratorio\": \"" + getLaboratorio().getNome() + "\", \"stato\": " + stato + " }";
		return str;
	}

	/**
	 * Determina se l'oggetto otherObject e' uguale a questo oggetto Prenotazione
	 * @param otherObject rappresenta un oggetto che deve essere comparato con questo
	 * @return true se l'oggetto e' istanza di Prenotazione; false altrimenti
	 */
	public boolean equals(Object otherObject){
		boolean val = false;
		if(otherObject instanceof Prenotazione){
			Prenotazione pren = (Prenotazione) otherObject;
			if(getData().equals(pren.getData()) && getOraInizio().compareTo(pren.getOraInizio()) == 0 
					&& getOraFine().compareTo(pren.getOraFine()) == 0 && getStudente().getEmail().equals(pren.getStudente().getEmail()) 
					&& getPostazione().getNumero() == getPostazione().getNumero() && getLaboratorio().getIDlaboratorio().equals(pren.getLaboratorio().getIDlaboratorio())){
				val = true;
			}
		}
		return val;
	}
}
