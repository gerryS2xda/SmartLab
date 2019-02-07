package dataAccess.storage.bean;

import java.util.ArrayList;
import java.util.List;

public class Addetto extends Utente {
	public Addetto(String email, String password, String name, String surname){
		super(email, password, name, surname);
		tipo = false; //0 Responsabile, 1 Admin
	}
	
	public Addetto(){
		super();
	}

	public void setTipo(boolean tipo) {
		this.tipo = tipo;
	}
	
	public boolean getTipo() {
		return tipo;
	}
	
	public List<Laboratorio> getLista(){
		return laboratori;
	}
	
	public void aggiungiLab(Laboratorio l){
		laboratori.add(l);
	}
	
	//other method
	public String toString(){
		String str = super.toString(); 
		str += "\"tipo\": " + tipo + " }";
		return str;
	}
	
	public boolean equals(Object otherObject){
		boolean val = super.equals(otherObject);
		return val;
	}
	
	
	private List<Laboratorio> laboratori = new ArrayList<Laboratorio>();
	private boolean tipo;
}
