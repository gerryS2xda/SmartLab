package businessLogic.laboratorio;

import java.sql.Time;

import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Laboratorio;

public class IdLab implements SqlSpecification {

public static final String TABLE_NAME = "laboratorio";
	
	private Laboratorio laboratorio;
	
	public IdLab(Laboratorio lab){
		this.laboratorio=lab;
	}
	@Override
	public String toSqlQuery() {
		return String.format(
                "SELECT * FROM %1$s WHERE nome='%2$s';",
                TABLE_NAME,
                laboratorio.getNome()
        );
		
	}

}
