package businessLogic.comunicazione;

import dataAccess.storage.SqlSpecification;

public class SegnalazioneSql implements SqlSpecification {
	
	private int idSegnalazione;
	
	public SegnalazioneSql(int id){
		idSegnalazione = id;
	}

	@Override
	public String toSqlQuery() {
		String str = "SELECT * FROM segnalazione WHERE idSegnalazione = " + idSegnalazione;
		return str;
	}
}
