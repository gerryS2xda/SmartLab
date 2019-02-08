package businessLogic.comunicazione;

import dataAccess.storage.SqlSpecification;

public class SegnalazioneByOggetto implements SqlSpecification {
	
	private String obj;
	
	public SegnalazioneByOggetto(String obj){
		this.obj = obj;
	}

	@Override
	public String toSqlQuery() {
		return "SELECT * FROM segnalazione WHERE oggetto = '" + obj + "'";
	}
	
}
