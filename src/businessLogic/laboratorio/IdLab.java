package businessLogic.laboratorio;

import dataAccess.storage.SqlSpecification;

public class IdLab implements SqlSpecification {
	
	private static final String TABLE_NAME = "laboratorio";
	private String laboratorio;
	
	public IdLab(String lab){
		this.laboratorio = lab;
	}
	
	@Override
	public String toSqlQuery() {
		return String.format("SELECT * FROM %1$s WHERE nome='%2$s';", TABLE_NAME, laboratorio);
	}

}
