package businessLogic.comunicazione;

import dataAccess.storage.SqlSpecification;

public class AvvisoSql implements SqlSpecification {
	
	private int idAvviso;
	
	public AvvisoSql(int id){
		idAvviso = id;
	}

	@Override
	public String toSqlQuery() {
		return "SELECT * FROM avviso WHERE idAvviso = " + idAvviso;
	}
}
