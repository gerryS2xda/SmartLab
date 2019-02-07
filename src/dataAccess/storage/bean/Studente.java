package dataAccess.storage.bean;


public class Studente extends Utente {
	public Studente(String email, String password, String name, String surname){
		super(email, password, name, surname);
		stato = false;
	}
	
	public Studente(){
		super();
	}

	public void setStato(boolean b){
		stato = b;
	}
	
	public boolean getStato(){
		return stato;
	}
	
	//other methods
	public String toString(){
		String str = super.toString();
		str += "\"statoStudente\": " + stato + " }";
		return str;
	}
	
	public boolean equals(Object otherObject){
		return super.equals(otherObject);
	}
	
	
	private boolean stato;
	
	
}
