package businessLogic.Postazione;

import java.util.Date;

import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Laboratorio;

public class InterventoSql implements SqlSpecification {
	
	private static final String TABLE_NAME ="Intervento";
	private int idIntervento;
	

	public InterventoSql(int idIntervento) 
	{
		this.idIntervento = idIntervento;
		
	}
	
	private static InterventoSql instance;
	
	public static InterventoSql getInstance(int idIntervento) 
    {
            instance = new InterventoSql(idIntervento);
        return instance;

    }


	public String toSqlQuery() 
	{
		return String.format("SELECT * FROM %1$s WHERE idIntervento = %2$s ",TABLE_NAME, this.idIntervento);
		
	}
}
