package businessLogic.addetto;

import dataAccess.storage.SqlSpecification;

public class AddettoLoginSQL implements SqlSpecification{

	public static final String TABLE_NAME = "addetto";
	public static final String TABLE_NAME_UTENTE = "utente";

	private String email;
	private String password;

	public AddettoLoginSQL(String email, String password){
		this.email = email;
		this.password = password;
	}

	@Override
	public String toSqlQuery(){
		return String.format("SELECT U.nome, U.cognome, A.*, U.password FROM utente U JOIN addetto A ON U.email = A.email WHERE U.email='%s' AND U.password='%s';", email, password);
	}
}
