package dataAccess.storage.bean;


public class Addetto extends Utente {
	public Addetto(String email, String password, String name, String surname){
		super(email, password, name, surname);
		tipo = false; //0 Responsabile, 1 Admin
	}
	
	public Addetto() {
		// TODO Auto-generated constructor stub
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}
	
	public boolean isTipo() {
		return tipo;
	}



	private boolean tipo;
}
