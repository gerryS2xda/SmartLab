package businessLogic.utente;

import dataAccess.storage.SqlSpecification;

public class UtenteSQL implements SqlSpecification {

	public static final String TABLE_NAME = "utente";
	
	private String email;
	
	public UtenteSQL(String email){
		this.email = email;
	}
	
	@Override
	public String toSqlQuery(){
		return String.format(
				"SELECT * FROM %s WHERE email='%s';",
                TABLE_NAME, email);
	}
}
