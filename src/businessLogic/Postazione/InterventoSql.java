package businessLogic.Postazione;

import dataAccess.storage.SqlSpecification;

public class InterventoSql implements SqlSpecification {
	
	private static final String TABLE_NAME ="intervento";
	private int idIntervento;
	
	public InterventoSql(int idIntervento) {
		this.idIntervento = idIntervento;
	}
	
	public String toSqlQuery(){ 
		return String.format("SELECT * FROM %1$s WHERE idIntervento = %2$d",TABLE_NAME, idIntervento);
	}
}
