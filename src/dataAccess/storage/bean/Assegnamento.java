package dataAccess.storage.bean;

import java.io.Serializable;

public class Assegnamento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String responsabile;
	private String laboratorio;
	
	public Assegnamento() {
		super();
		this.responsabile = "";
		this.laboratorio = "";
	}

	public Assegnamento(String responsabile, String laboratorio) {
		super();
		this.responsabile = responsabile;
		this.laboratorio = laboratorio;
	}

	public String getResponsabile() {
		return responsabile;
	}

	public void setResponsabile(String responsabile) {
		this.responsabile = responsabile;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}
	
	
	
}
