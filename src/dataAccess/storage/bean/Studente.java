package dataAccess.storage.bean;


public class Studente extends Utente {
	public Studente (String email, String password, String name, String surname){
		super(email, password, name, surname);
		stato = false;
	}
	
	public Studente (){
		
	}

	public void setStato(boolean b){
		stato = b;
	}
	
	public boolean getStato(){
		return stato;
	}
	
	private boolean stato;
}
