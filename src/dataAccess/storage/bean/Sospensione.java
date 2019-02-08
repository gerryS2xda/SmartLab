package dataAccess.storage.bean;

import java.time.LocalDate;

public class Sospensione {
	private String motivazione;
	private int durata;
	private String data;
	private String addetto;
	private String studente;
	private int id;
	
	public Sospensione(int id, String motivazione, String studente) {
		this.id = id;
		this.motivazione = motivazione;
		this.studente = studente;
		addetto = "";
		data = LocalDate.now().toString();
		durata = 0;
	}
	
	public Sospensione(int id, int durata, String data, String motivazione, String studente, String addetto) {
		this.id = id;
		this.durata = durata;
		this.data = data;
		this.motivazione = motivazione;
		this.studente = studente;
		this.addetto = addetto;
	}
	
	public Sospensione(){
		motivazione = "";
		studente = "";
		id = -1;
		addetto = "";
		data = LocalDate.now().toString();;
		durata = 0;
	}

	public void setID(int x){
		id = x;
	}
	
	public int getID(){
		return id;
	}
	
	public void setMotivazione(String x){
		motivazione = x;
	}
	
	public String getMotivazione(){
		return motivazione;
	}
	
	public void setStudente(String s){
		studente = s;
	}
	
	public String getStudente(){
		return studente;
	}
	
	
	
	/**
	 * @return the durata
	 */
	public int getDurata() {
		return durata;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @return the addetto
	 */
	public String getAddetto() {
		return addetto;
	}

	/**
	 * @param durata the durata to set
	 */
	public void setDurata(int durata) {
		this.durata = durata;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @param addetto the addetto to set
	 */
	public void setAddetto(String addetto) {
		this.addetto = addetto;
	}

	public String toString(){
		String str = "{\"id\":" + id + "\"motivazione\": \"" + motivazione + "\"studente\": \"" + studente + " }";
		return str;
	}
	
	public boolean equals(Object o){
		boolean val = false;
		if(o instanceof Sospensione){
			Sospensione s = (Sospensione) o;
			if(getID() == (s.getID()) && getMotivazione().compareTo(s.getMotivazione()) == 0 
					&& studente.compareTo(s.getStudente()) == 0){
				val = true;
			}
		}
		return val;
	}
}
