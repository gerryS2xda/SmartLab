package dataAccess.storage.bean;

import java.io.Serializable;
import java.sql.Time;

public class Laboratorio implements Serializable {
	
	private String IDlaboratorio;
	private String nome;
	private int posti;
	private boolean stato;
	private Time apertura;
	
	public Laboratorio(){
		super();
		IDlaboratorio = "";
		this.nome = "";
		this.posti = 0;
		this.stato = false;
		this.apertura = null;
	}
	
	public Laboratorio(String iDlaboratorio, String nome, int posti, boolean stato, Time apertura) {
		super();
		IDlaboratorio = iDlaboratorio;
		this.nome = nome;
		this.posti = posti;
		this.stato = stato;
		this.apertura = apertura;
	}

	public String getIDlaboratorio() {
		return IDlaboratorio;
	}

	public void setIDlaboratorio(String iDlaboratorio) {
		IDlaboratorio = iDlaboratorio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPosti() {
		return posti;
	}

	public void setPosti(int posti) {
		this.posti = posti;
	}

	public boolean isStato() {
		return stato;
	}

	public void setStato(boolean stato) {
		this.stato = stato;
	}

	public Time getApertura() {
		return apertura;
	}

	public void setApertura(Time apertura) {
		this.apertura = apertura;
	}

	
}
