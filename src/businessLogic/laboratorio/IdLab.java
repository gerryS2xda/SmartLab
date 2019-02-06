package businessLogic.laboratorio;

import dataAccess.storage.SqlSpecification;
public class IdLab implements SqlSpecification {
	private static final String TABLE_NAME = "laboratorio";
	private String laboratorio;
	private static IdLab instance;
	
	public static IdLab getInstance(){
		if(instance==null)
			instance=new IdLab("lab1");
		return instance;
	}
	
	public IdLab(String lab){
		this.laboratorio=lab;
	}
	@Override
	public String toSqlQuery() {
		return String.format(
                "SELECT * FROM %1$s WHERE nome='%2$s';",
                TABLE_NAME,
                laboratorio
        );
		
	}

}
