package dataAccess.storage.bean;

import java.time.LocalTime;

public class Laboratorio{
	
	private String IDlaboratorio;
	private String nome;
	private int posti;
	private boolean stato;
	private LocalTime apertura;
	private LocalTime chiusura;
	
	public Laboratorio(){
		super();
		IDlaboratorio = "";
		this.nome = "";
		this.posti = 0;
		this.stato = false;
		this.apertura = null;
		this.chiusura = null;
	}
	
	public Laboratorio(String iDlaboratorio, String nome, int posti, boolean stato, LocalTime apertura, LocalTime chiusura) {
		super();
		IDlaboratorio = iDlaboratorio;
		this.nome = nome;
		this.posti = posti;
		this.stato = stato;
		this.apertura = apertura;
		this.chiusura = chiusura;
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

	public LocalTime getApertura() {
		return apertura;
	}

	public void setApertura(LocalTime apertura) {
		this.apertura = apertura;
	}

	public LocalTime getChiusura() {
		return chiusura;
	}

	public void setChiusura(LocalTime chiusura) {
		this.chiusura = chiusura;
	}
		

	@Override
	public String toString() {
		return "Laboratorio [IDlaboratorio=" + IDlaboratorio + ", nome=" + nome + ", posti=" + posti + ", stato="
				+ stato + ", apertura=" + apertura + ", chiusura=" + chiusura + "]";
	}
	
	public boolean equals(Object otherObject){
		boolean val = false;
		if(otherObject instanceof Laboratorio){
			Laboratorio lab = (Laboratorio) otherObject;
			if(getIDlaboratorio().equals(lab.getIDlaboratorio()) && getNome().equals(lab.getNome())){
				val = true;
			}
		}
		return val;
	}
	
}
