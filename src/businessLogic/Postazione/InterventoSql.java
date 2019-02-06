package businessLogic.Postazione;

import java.util.Date;

import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Laboratorio;

public class InterventoSql implements SqlSpecification {
	
	private static final String TABLE_NAME ="Intervento";
	private int idIntervento;
	private static InterventoSql instance;
	

	public InterventoSql(int idIntervento) 
	{
		this.idIntervento = idIntervento;
		
	}
	
	
	
	public static InterventoSql getInstance(int idIntervento) 
    {
            instance = new InterventoSql(idIntervento);
        return instance;

    }


	public String toSqlQuery() 
	{
		return String.format("SELECT * FROM %1$s WHERE idIntervento = %2$d ",TABLE_NAME, this.idIntervento);
		
	}
}
