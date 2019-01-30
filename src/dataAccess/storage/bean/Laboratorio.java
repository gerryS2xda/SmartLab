package dataAccess.storage.bean;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Laboratorio implements Serializable {
	
	private String IDlaboratorio;
	private String nome;
	private int posti;
	private boolean stato;
	private Time apertura;
	private Time chiusura;
	private List<Addetto> responsabili;
	
	public Laboratorio(){
		super();
		IDlaboratorio = "";
		this.nome = "";
		this.posti = 0;
		this.stato = false;
		this.apertura = null;
		this.chiusura = null;
		responsabili=new ArrayList<Addetto>();
	}
	
	public Laboratorio(String iDlaboratorio, String nome, int posti, boolean stato, Time apertura, Time chiusura, List<Addetto> responsabili) {
		super();
		IDlaboratorio = iDlaboratorio;
		this.nome = nome;
		this.posti = posti;
		this.stato = stato;
		this.apertura = apertura;
		this.chiusura = chiusura;
		this.responsabili=responsabili;
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

	public Time getChiusura() {
		return chiusura;
	}

	public void setChiusura(Time chiusura) {
		this.chiusura = chiusura;
	}
	
	public void add(Addetto responsabile){//l'oggetto addetto deve essere un responsabile
		if(!this.responsabili.contains(responsabile)){
			this.responsabili.add(responsabile);
			//responsabile.add(this);
		}
	}
	
	public void remuve(Addetto responsabile){
		this.responsabili.remove(responsabile);
	}
	
}
