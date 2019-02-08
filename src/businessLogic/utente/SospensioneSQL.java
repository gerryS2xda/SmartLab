package businessLogic.utente;

import dataAccess.storage.SqlSpecification;

public class SospensioneSQL implements SqlSpecification {
	
	public static final String TABLE_NAME = "sospensione";
	
	private int id;
	
	public SospensioneSQL(int id){
		this.id = id;
	}
	
	@Override
	public String toSqlQuery(){
		return String.format(
				"SELECT * FROM %1$s WHERE IDsospensione=%2$s",
                TABLE_NAME,
                this.id);
	}
}
