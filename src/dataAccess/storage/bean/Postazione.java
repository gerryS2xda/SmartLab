package dataAccess.storage.bean;


public class Postazione {

	private int numero;
	private String laboratorio;
	private boolean stato;
	
	//costructor
	
	public Postazione() 
	{
		this.numero=0;
		this.laboratorio="";
		this.stato=false;
	}

	public Postazione(int numero, String laboratorio, boolean stato) {
		super();
		this.numero = numero;
		this.laboratorio = laboratorio;
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
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public boolean isStato() {
		return stato;
	}

	public void setStato(boolean stato) {
		this.stato = stato;
	}
	
	
}
