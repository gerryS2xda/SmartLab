package businessLogic.utente;

import dataAccess.storage.SqlSpecification;

public class StudentList implements SqlSpecification {
	
	public static final String TABLE_NAME = "utente";
	    
	@Override
	public String toSqlQuery() {
		return "SELECT U.nome, U.cognome, S.*, U.password FROM utente U JOIN studente S ON U.email = S.email";
	}
}
