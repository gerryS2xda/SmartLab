package businessLogic.addetto;

import dataAccess.storage.SqlSpecification;

public class AddettoSQL implements SqlSpecification {

	public static final String TABLE_NAME = "addetto";
	public static final String TABLE_NAME_UTENTE = "utente";
	
	private String email;
	
	public AddettoSQL(String email){
		this.email = email;
	}
	
	@Override
	public String toSqlQuery(){
		return String.format(
				"SELECT U.nome, U.cognome, A.*, U.password " +
				"FROM %s U JOIN %s A ON U.email = A.email WHERE A.email='%s';",
                TABLE_NAME, TABLE_NAME_UTENTE, email);
	}
}
