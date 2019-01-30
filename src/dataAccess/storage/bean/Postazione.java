package dataAccess.storage.bean;


public class Postazione {

	private int numero;
	private Laboratorio lab; 
	private boolean stato;
	
	//costructor
	
	public Postazione() 
	{
		this.numero=0;
		this.lab=new Laboratorio();
		this.stato=false;
	}

	public Postazione(int numero, Laboratorio laboratorio, boolean stato) {
		super();
		this.numero = numero;
		this.lab = laboratorio;
		this.stato = stato;
		
	}

	//metodi
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Laboratorio getLaboratorio() {
		return lab;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.lab = laboratorio;
	}

	public boolean isStato() {
		return stato;
	}

	public void setStato(boolean stato) {
		this.stato = stato;
	}
	
	
}
