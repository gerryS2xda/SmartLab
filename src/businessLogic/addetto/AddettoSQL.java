package businessLogic.addetto;

import dataAccess.storage.SqlSpecification;

public class AddettoSQL implements SqlSpecification {

	public static final String TABLE_NAME = "addetto";
	
	private String email;
	
	public AddettoSQL(String email){
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
