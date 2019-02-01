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
				"SELECT * FROM %1$s WHERE email=%2$s;",
                TABLE_NAME,
                this.email);
	}
}
