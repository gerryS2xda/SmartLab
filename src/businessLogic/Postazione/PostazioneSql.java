package businessLogic.Postazione;

import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Laboratorio;

public class PostazioneSql implements SqlSpecification {
	
	private static PostazioneSql instance;
	private static final String TABLE_NAME = "Postazione";
	private int numero;
	private String idLab;
	
	public PostazioneSql(int num, String idLab) 
	{
		this.numero = num;
		this.idLab = idLab;
	}
	
    public static PostazioneSql getInstance(int num, String idLab) 
    {
        return new PostazioneSql(num, idLab);
    }

	public String toSqlQuery() 
	{
		return String.format("SELECT * FROM %1$s WHERE numero = %2$d AND laboratorio = %3$s ",TABLE_NAME, this.numero, this.idLab);
	}

}
