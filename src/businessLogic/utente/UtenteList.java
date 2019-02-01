package businessLogic.utente;

import dataAccess.storage.SqlSpecification;

public class UtenteList implements SqlSpecification {
	
	private static UtenteList instance;

	public static UtenteList getInstance(){
	        if (instance == null){
	            instance = new UtenteList();
	        }
	        return instance;
	    }

	public static final String TABLE_NAME = "utente";
	    
	@Override
	public String toSqlQuery() {
		return String.format(
				"SELECT * FROM %1$s ;",
				TABLE_NAME);
	}
}
