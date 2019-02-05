package dataAccess.storage.bean;

import java.time.LocalTime;

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
	public Prenotazione(){
		//vuoto
	}
	
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
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @return the studente
	 */
	public Studente getStudente() {
		return studente;
	}

	/**
	 * @return the postazione
	 */
	public Postazione getPostazione() {
		return postazione;
	}

	/**
	 * @return the laboratorio
	 */
	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public boolean isPrenotazioneActive(){
		return stato;
	}
	
	public LocalTime getOraInizio() {
		return oraInizio;
	}
	public LocalTime getOraFine() {
		return oraFine;
	}
	
	public void setID(int id){
		this.id = id;
	}
	
	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @param studente the studente to set
	 */
	public void setStudente(Studente studente) {
		this.studente = studente;
	}

	/**
	 * @param postazione the postazione to set
	 */
	public void setPostazione(Postazione postazione) {
		this.postazione = postazione;
	}

	/**
	 * @param laboratorio the laboratorio to set
	 */
	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}
	
	public void setStatus(boolean s){
		stato = s;
	}
	
	public void setOraInizio(LocalTime oraInizio) {
		this.oraInizio = oraInizio;
	}
	
	public void setOraFine(LocalTime oraFine) {
		this.oraFine = oraFine;
	}
	
	//other method
	public String toString(){	//adattato per costruzione della stringa JSON
		String str = "{\"id\":" + id + ", \"data\": \"" + data + "\", \"oraInizio\": \"" + getOraInizio().toString() + "\", "
				+ "\"oraFine\": \"" + getOraFine().toString() + "\",  \"email\": \"" + getStudente().getEmail() + "\", \"postazione\": " + getPostazione().getNumero() + ", "  
				+ "\"laboratorio\": \"" + getLaboratorio().getNome() + "\", \"stato\": " + stato + " }";
		return str;
	}

	public boolean equals(Object otherObject){
		boolean val = false;
		if(otherObject instanceof Prenotazione){
			Prenotazione pren = (Prenotazione) otherObject;
			if(getData().equals(pren.getData()) && getOraInizio().compareTo(pren.getOraInizio()) == 0 && 
					getOraFine().compareTo(pren.getOraFine()) == 0 && getStudente().getEmail().equals(pren.getStudente().getEmail()) && 
					getPostazione().equals(pren.getPostazione()) && getLaboratorio().getIDlaboratorio().equals(pren.getLaboratorio().getIDlaboratorio())){
				val = true;
			}
		}
		return val;
	}
}
