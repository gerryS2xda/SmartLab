package businessLogic.laboratorio;

import dataAccess.storage.SqlSpecification;

public class LaboratorioSql implements SqlSpecification {
	
	private static LaboratorioSql instance;

    public static LaboratorioSql getInstance(String lab) {

        if (instance == null) {
            instance = new LaboratorioSql(lab);
        }
        return instance;

    }
	
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
