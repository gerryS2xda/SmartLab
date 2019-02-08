package businessLogic.utente;

import dataAccess.storage.SqlSpecification;

public class StudenteLoginSQL implements SqlSpecification{

	public static final String TABLE_NAME = "studente";
	public static final String TABLE_NAME_UTENTE = "utente";
	
	private String email;
	private String password;
	
	public StudenteLoginSQL(String email, String password){
		this.email = email;
		this.password = password;
	}
	
	@Override
	public String toSqlQuery(){
		return String.format(
				"SELECT U.nome, U.cognome, S.*, U.password FROM utente U JOIN studente S ON U.email = S.email WHERE U.email='%s' AND U.password='%s'",
                email, password);
	}
}
