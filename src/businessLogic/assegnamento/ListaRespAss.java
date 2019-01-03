package businessLogic.assegnamento;

import dataAccess.storage.SqlSpecification;

public class ListaRespAss implements SqlSpecification {
	
	public static final String TABLE_NAME = "assegnamento";//nome della tabella su cui saranno effettuate le operazioni
	
	@Override
	public String toSqlQuery() {
		return String.format(
                "SELECT * FROM %1$s ;",
                TABLE_NAME
        );
	}

}
