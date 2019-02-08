package businessLogic.comunicazione;

import dataAccess.storage.SqlSpecification;

public class AvvisoByNameSQL implements SqlSpecification {
	
	private String title;
	
	public AvvisoByNameSQL(String title){
		this.title = title;
	}

	@Override
	public String toSqlQuery() {
		return "SELECT * FROM avviso WHERE titolo = '" + title + "'";
	}
}
