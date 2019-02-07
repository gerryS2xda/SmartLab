package dataAccess.storage.bean;

public class Sospensione {
	private String motivazione;
	private String studente;
	private int id;
	
	public Sospensione(int id, String motivazione, String studente) {
		
	}
	
	public Sospensione (){
		motivazione = "";
		studente = "";
		id = 0;
	}

	public void setID (int x){
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
	
	public String toString(){
		String str = "{\"id\":" + id + "\"motivazione\": \"" + motivazione + "\"studente\": \"" + studente + " }";
		return str;
	}
	
	public boolean equals(Object o){
		boolean val = false;
		if(o instanceof Sospensione){
			Sospensione s = (Sospensione) o;
			if(getID() == (s.getID()) && getMotivazione().compareTo(s.getMotivazione()) == 0 &&
					studente.compareTo(s.getStudente()) == 0){
				val = true;
			}
		}
		return val;
	}
}
