package businessLogic.utente;

import dataAccess.storage.SqlSpecification;

public class StudenteSQL implements SqlSpecification {

	public static final String TABLE_NAME = "studente";
	
	private String email;
	
	public StudenteSQL(String email){
		this.email = email;
	}
	
	@Override
	public String toSqlQuery(){
		return String.format(
				"SELECT U.nome, U.cognome, S.*, U.password FROM utente U JOIN studente S ON U.email = S.email WHERE U.email='%s';",
                email);
	}
}
