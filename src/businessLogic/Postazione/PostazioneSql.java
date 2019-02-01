package businessLogic.Postazione;

import dataAccess.storage.SqlSpecification;
import dataAccess.storage.bean.Laboratorio;

public class PostazioneSql implements SqlSpecification {
	
	private static PostazioneSql instance;
	private static final String TABLE_NAME = "Postazione";
	private int numero;
<<<<<<< HEAD
	private String idLab;
	
	public PostazioneSql(int num, String idLab) 
	{
		this.numero = num;
		this.idLab = idLab;
=======
	private String lab;
	
	public PostazioneSql() 
	{
		this.numero=0;
		this.lab=null;
	}
	
	public PostazioneSql(int num,String lab) 
	{
		this.numero=num;
		this.lab=lab;
>>>>>>> c30b88218773525244bb39f06b4b4c5537251f33
	}

<<<<<<< HEAD
    public static PostazioneSql getInstance(int num, String idLab) 
    {
        return new PostazioneSql(num, idLab);
=======
    public static PostazioneSql getInstance(int num, String lab) 
    {

        if (instance == null) 
        {
            instance = new PostazioneSql(num,lab);
        }
        return instance;

>>>>>>> c30b88218773525244bb39f06b4b4c5537251f33
    }

	public String toSqlQuery() 
	{
<<<<<<< HEAD
		return String.format("SELECT * FROM %1$s WHERE numero = %2$d && IDlaboratorio = %3$s ",TABLE_NAME, this.numero, this.idLab);
=======
		return String.format("SELECT * FROM %1$s WHERE numero = %2$d && IDlaboratorio = %3$s ",TABLE_NAME, this.numero, this.lab);
>>>>>>> c30b88218773525244bb39f06b4b4c5537251f33
		
	}

}
