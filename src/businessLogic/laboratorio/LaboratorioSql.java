package businessLogic.laboratorio;

import dataAccess.storage.SqlSpecification;

public class LaboratorioSql implements SqlSpecification {
	
	public static final String TABLE_NAME = "laboratorio";
	
	private String laboratorio;
	
	public LaboratorioSql(String lab){
		this.laboratorio=lab;
	}
	@Override
	public String toSqlQuery() {
		return String.format(
                "SELECT * FROM %1$s WHERE IDlaboratorio=%2$s;",
                TABLE_NAME,
                this.laboratorio
        );
	}

}
