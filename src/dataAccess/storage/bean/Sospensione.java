package dataAccess.storage.bean;

import java.sql.Date;

public class Sospensione {
	private int durata;
	private Date data;
	private String motivazione;
	private Studente studente;
	private Addetto addetto;
	private int id;
	
	public Sospensione (){
		durata = 0;
		data = null;
		motivazione = "";
		studente = new Studente();
		addetto = new Addetto();
		id = 0;
	}

	public void setID (int x){
		id = x;
	}
	
	public int getID(){
		return id;
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
	
	public void setStudente(Studente s){
		studente = s;
	}
	
	public Studente getStudente(){
		return studente;
	}
	
	public void setAddetto(Addetto a){
		addetto = a;
	}
	
	public Addetto getAddetto(){
		return addetto;
	}
	
	public String toString(){
		String str = "{\"id\":" + id + ", \"durata\": \"" + durata + "\", \"data\": \"" + data.toString() + "\", "
				+ "\"motivazione\": \"" + motivazione + "\"studente\": \"" + studente.toString() + "\", \"addetto\": \"" + addetto.toString() + " }";
		return str;
	}
	
	public boolean equals(Object o){
		boolean val = false;
		if(o instanceof Sospensione){
			Sospensione s = (Sospensione) o;
			if(getID() == (s.getID()) && getDurata() == (s.getDurata()) && 
					getData().equals(s.getData()) && getMotivazione().compareTo(s.getMotivazione()) == 0 &&
					getStudente().equals(s.getStudente()) && getAddetto().equals(s.getAddetto())){
				val = true;
			}
		}
		return val;
	}
}
