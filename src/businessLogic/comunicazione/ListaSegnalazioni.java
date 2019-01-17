package businessLogic.comunicazione;

import dataAccess.storage.SqlSpecification;

public class ListaSegnalazioni implements SqlSpecification {
	
	@Override
	public String toSqlQuery() {
		String sql = "SELECT * FROM segnalazione";
		return sql;
	}
	
}
