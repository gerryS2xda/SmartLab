package businessLogic.Postazione;
import dataAccess.storage.SqlSpecification;

public class ListaPos implements SqlSpecification{
	public static final String TABLE_NAME = "postazione";
	
	private static ListaPos instance;
	private String laboratorio;
	
	public ListaPos(String lab) 
	{
		this.laboratorio=lab;
	}
	
    public static ListaPos getInstance() {

        if (instance == null) {
            instance = new ListaPos("");
        }
        return instance;

    }
    
	
	 @Override
	    public String toSqlQuery() {
	        return String.format(
	                "SELECT * FROM %1$s WHERE laboratorio = %2$s;",TABLE_NAME,laboratorio);
	    }

}
