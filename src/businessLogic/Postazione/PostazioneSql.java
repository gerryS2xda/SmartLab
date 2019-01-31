package businessLogic.Postazione;

import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Laboratorio;

public class PostazioneSql implements SqlSpecification {
	
	
	
	private static final String TABLE_NAME ="Postazione";
	private int numero;
	private Laboratorio lab;
	private boolean stato;
	
	public PostazioneSql(int num,Laboratorio lab, boolean stato) 
	{
		this.numero=num;
		this.lab=lab;
		this.stato=stato;
	}
	
	
	private static PostazioneSql instance;

    public static PostazioneSql getInstance(int num, Laboratorio lab, boolean stato) 
    {

        if (instance == null) 
        {
            instance = new PostazioneSql(num,lab,stato);
        }
        return instance;

    }


	public String toSqlQuery() 
	{
		return String.format("SELECT * FROM %1$s WHERE numero = %2$d && IDlaboratorio = %3$s ",TABLE_NAME, this.numero, this.lab, this.stato);
		
	}

}
