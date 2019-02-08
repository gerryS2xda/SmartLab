package businessLogic.Postazione;

import dataAccess.storage.SqlSpecification;

//classe usata per il test 
public class GetPostazioneByLabSQL implements SqlSpecification {
	
	private static final String TABLE_NAME = "Postazione";
	private String idLab;
	
	public GetPostazioneByLabSQL(String idLab) { 
		this.idLab = idLab;
	}
	
	public String toSqlQuery() {
		return String.format("SELECT * FROM postazione WHERE laboratorio = %s", this.idLab);
	}

}
