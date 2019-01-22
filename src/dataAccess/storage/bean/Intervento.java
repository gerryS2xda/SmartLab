package dataAccess.storage.bean;

import java.util.Date;

public class Intervento {
	
	private int idIntervento;
	private String descrizione;
	private Date data;
	private int postazione;
	private int laboratorio;
	private String addetto;
	
	//costruttori
	public Intervento() 
	{
		
	}
	
	public Intervento(int idIntervento, String descrizione, Date data, int postazione, int laboratorio,String addetto) 
	{
		super();
		this.idIntervento = idIntervento;
		this.descrizione = descrizione;
		this.data = data;
		this.postazione = postazione;
		this.laboratorio = laboratorio;
		this.addetto = addetto;
	}
	
	//metodi
	public int getIdIntervento() {
		return idIntervento;
	}
	public void setIdIntervento(int idIntervento) {
		this.idIntervento = idIntervento;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public int getPostazione() {
		return postazione;
	}
	public void setPostazione(int postazione) {
		this.postazione = postazione;
	}
	public int getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(int laboratorio) {
		this.laboratorio = laboratorio;
	}
	public String getAddetto() {
		return addetto;
	}
	public void setAddetto(String addetto) {
		this.addetto = addetto;
	}

}
