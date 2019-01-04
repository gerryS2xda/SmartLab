package businessLogic.assegnamento;

import dataAccess.storage.SqlSpecification;

public class AssegnamentoSql implements SqlSpecification {
	
	private static AssegnamentoSql instance;

    public static AssegnamentoSql getInstance(String lab,String resp) {

        if (instance == null) {
            instance = new AssegnamentoSql(lab,resp);
        }
        return instance;

    }
    
    public static final String TABLE_NAME = "assegnamento";
    
    private String laboratorio;
    private String responsabile;
    
    
	
	public AssegnamentoSql(String laboratorio, String responsabile) {
		super();
		this.laboratorio = laboratorio;
		this.responsabile = responsabile;
	}



	@Override
	public String toSqlQuery() {
		return String.format(
                "SELECT * FROM %1$s WHERE laboratorio=%2$s && addetto=%3$s;",
                TABLE_NAME,
                this.laboratorio,
                this.responsabile
        );
	}

}
