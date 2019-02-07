package dataAccess.storage.bean;


public class Postazione {

	private int numero;
	private String idlab; 
	private boolean stato;
	
	//costructor
	public Postazione(){
		this.numero=0;
		this.idlab="";
		this.stato=false;
	}

	public Postazione(int numero, String laboratorio, boolean stato) {
		super();
		this.numero = numero;
		this.idlab = laboratorio;
		this.stato = stato;
		
	}

	//metodi
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getLaboratorio() {
		return idlab;
	}

	public void setLaboratorio(String laboratorio) {
		this.idlab = laboratorio;
	}

	public boolean isStato() {
		return stato;
	}

	public void setStato(boolean stato) {
		this.stato = stato;
	}
	
	//other method
	public boolean equals(Object otherObject){
		boolean val = false;
		if(otherObject instanceof Postazione){
			Postazione post = (Postazione) otherObject;
			if(getNumero() == post.getNumero() && getLaboratorio().equals(post.getLaboratorio())){
				val = true;
			}
		}
		return val;
	}
}
