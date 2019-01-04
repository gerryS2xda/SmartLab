package businessLogic.Postazione;

import dataAccess.storage.SqlSpecification;

public class PostazioneSql implements SqlSpecification {

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
