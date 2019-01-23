package dataAccess.storage.bean;

import java.util.Date;

public class Sospensione {
	private int durata;
	private Date data;
	private String motivazione;
	private String studente;
	private String addetto;
	private int IDSospensione;
	
	public Sospensione (){
		durata = 0;
		data = null;
		motivazione = "";
		studente = "";
		addetto = "";
		IDSospensione = 0;
	}

	public void setID (int x){
		IDSospensione = x;
	}
	
	public int getID(){
		return IDSospensione;
	}
	
	public void setDurata(int x){
		durata = x;
	}
	
	public int getDurata(){
		return durata;
	}
	
	public void setData(Date x){
		data = x;
	}
	
	public Date getData(){
		return data;
	}
	
	public void setMotivazione(String x){
		motivazione = x;
	}
	
	public String getMotivazione(){
		return motivazione;
	}
	
	public void setStudente(String x){
		studente = x;
	}
	
	public String getStudente(){
		return studente;
	}
	
	public void setAddetto(String x){
		addetto = x;
	}
	
	public String getAddetto(){
		return addetto;
	}
}
