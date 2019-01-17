package businessLogic.comunicazione;

import dataAccess.storage.SqlSpecification;

public class ListaAvvisi implements SqlSpecification {
	
	@Override
	public String toSqlQuery() {
		String sql = "SELECT * FROM avviso";
		return sql;
	}
	
}
