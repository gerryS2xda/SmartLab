package dataAccess.storage.bean;

import java.time.LocalDate;
import java.util.Date;

public class Intervento {
	
	private int idIntervento;
	private String descrizione;
	private LocalDate data;
	private int postazione;
	private String laboratorio;
	private String addetto;
	
	//costruttori
	public Intervento() 
	{
		
	}
	
	public Intervento(int idIntervento, String descrizione, LocalDate data, int postazione, String laboratorio,String addetto) 
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
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public int getPostazione() {
		return postazione;
	}
	public void setPostazione(int postazione) {
		this.postazione = postazione;
	}
	public String getLaboratorio() {
		return laboratorio;
	}
	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	public String getAddetto() {
		return addetto;
	}
	public void setAddetto(String addetto) {
		this.addetto = addetto;
	}

}
