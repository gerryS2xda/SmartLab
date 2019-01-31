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
                "SELECT * FROM %1$s WHERE nome=%2$s, posti=%3$s, ora_apertura=%4$s, ora_chiusura=%5$s;",
                TABLE_NAME,
                laboratorio.getNome(),
                laboratorio.getPosti(),
                Time.valueOf(laboratorio.getApertura()),
                Time.valueOf(laboratorio.getChiusura())
        );
		
	}

}
