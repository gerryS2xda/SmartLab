package dataAccess.storage.bean;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Laboratorio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String IDlaboratorio;
	private String nome;
	private int posti;
	private boolean stato;
	private LocalTime apertura;
	private LocalTime chiusura;
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
	
	public Laboratorio(String iDlaboratorio, String nome, int posti, boolean stato, LocalTime apertura, LocalTime chiusura, List<Addetto> responsabili) {
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
	
	public void add(Addetto responsabile){//l'oggetto addetto deve essere un responsabile
		if(!this.responsabili.contains(responsabile)){
			this.responsabili.add(responsabile);
			//responsabile.add(this);
		}
	}
	
	public void remuve(Addetto responsabile){
		this.responsabili.remove(responsabile);
	}

	@Override
	public String toString() {
		return "Laboratorio [IDlaboratorio=" + IDlaboratorio + ", nome=" + nome + ", posti=" + posti + ", stato="
				+ stato + ", apertura=" + apertura + ", chiusura=" + chiusura + ", responsabili=" + responsabili + "]";
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
