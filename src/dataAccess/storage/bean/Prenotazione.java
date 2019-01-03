package dataAccess.storage.bean;

import java.time.LocalDate;

public class Prenotazione {

	//instance field
	private int id;
	private String data;
	private String fascia_oraria;
	private boolean stato; 
	private String studente; //sostituire con oggetto studente
	private int postazione;  //sostituire con oggetto postazione
	private int laboratorio; //sostituire con oggetto Laboratorio
	
	//constructor
	public Prenotazione(){
		id = 0;
		data = LocalDate.now().toString();
		fascia_oraria = "";
		stato = false;
		studente = "";
		postazione = 0;
		laboratorio = 0;
	}
	
	public Prenotazione(String fascia_or, String stud, int post){
		id = 0;
		data = LocalDate.now().toString();
		fascia_oraria = fascia_or;
		stato = false;
		studente = stud;
		postazione = post;
		laboratorio = 0; //si puo' ottenere dall'oggetto Postazione
	}
	
	public Prenotazione(String d, String fascia_or, String stud, int post, int lab){
		data = d;
		fascia_oraria = fascia_or;
		studente = stud;
		postazione = post;
		laboratorio = lab;
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
	 * @return the fascia_oraria
	 */
	public String getFasciaOraria() {
		return fascia_oraria;
	}

	/**
	 * @return the studente
	 */
	public String getStudente() {
		return studente;
	}

	/**
	 * @return the postazione
	 */
	public int getPostazione() {
		return postazione;
	}

	/**
	 * @return the laboratorio
	 */
	public int getLaboratorio() {
		return laboratorio;
	}

	public boolean isPrenotazioneActive(){
		return stato;
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
	 * @param fascia_oraria the fascia_oraria to set
	 */
	public void setFasciaOraria(String fascia_oraria) {
		this.fascia_oraria = fascia_oraria;
	}

	/**
	 * @param studente the studente to set
	 */
	public void setStudente(String studente) {
		this.studente = studente;
	}

	/**
	 * @param postazione the postazione to set
	 */
	public void setPostazione(int postazione) {
		this.postazione = postazione;
	}

	/**
	 * @param laboratorio the laboratorio to set
	 */
	public void setLaboratorio(int laboratorio) {
		this.laboratorio = laboratorio;
	}
	
	public void setStatus(boolean s){
		stato = s;
	}
	
	//other method
	public String toString(){	//adattato per costruzione della stringa JSON
		String str = "{\"id\":" + id + ", \"data\": \"" + data + "\", \"fasciaOr\": \"" + fascia_oraria + "\", "
				+ "\"email\": \"" + studente + "\", \"postazione\": " + postazione + ", \"laboratorio\": \"" + laboratorio + "\"}";
		return str;
	}

}