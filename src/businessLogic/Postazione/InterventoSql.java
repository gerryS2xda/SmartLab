package businessLogic.Postazione;

import dataAccess.storage.SqlSpecification;

public class InterventoSql implements SqlSpecification {
	
	private static final String TABLE_NAME ="intervento";
	private int idIntervento;
	private static InterventoSql instance;
	

	public InterventoSql(int idIntervento) {
		this.idIntervento = idIntervento;
	}
	
	
	
	public static InterventoSql getInstance(int idIntervento){
        instance = new InterventoSql(idIntervento);
        return instance;
    }


	public String toSqlQuery(){ 
		return String.format("SELECT * FROM %1$s WHERE idIntervento = %2$d ",TABLE_NAME, this.idIntervento);
	}
}
