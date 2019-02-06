package dataAccess.storage.bean;


public class Assegnamento{
	

	private String responsabile;
	private String laboratorio;
	
	public Assegnamento() {
		this.responsabile = "";
		this.laboratorio = "";
	}

	public Assegnamento(String responsabile, String laboratorio) {
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
