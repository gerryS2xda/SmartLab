package businessLogic.Postazione;

import dataAccess.storage.SqlSpecification;

public class PostazioneSql implements SqlSpecification {
	
	private static PostazioneSql instance;

    public static PostazioneSql getInstance(int num, String lab) {

        if (instance == null) {
            instance = new PostazioneSql(num,lab);
        }
        return instance;

    }

	private static final String TABLE_NAME ="Postazione";
	private int numero;
	private String laboratorio;
	
	public PostazioneSql(int num,String lab) 
	{
		this.numero=num;
		this.laboratorio=lab;
	}
	

	public String toSqlQuery() 
	{
		return String.format("SELECT * FROM %1$s WHERE numero = %2$d && IDlaboratorio = %3$s ",TABLE_NAME, this.numero, this.laboratorio);
		
	}

}
