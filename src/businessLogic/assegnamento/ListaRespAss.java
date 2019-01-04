package businessLogic.assegnamento;

import dataAccess.storage.SqlSpecification;

public class ListaRespAss implements SqlSpecification {
	
	private static ListaRespAss instance;

    public static ListaRespAss getInstance() {

        if (instance == null) {
            instance = new ListaRespAss();
        }
        return instance;

    }
	
	public static final String TABLE_NAME = "assegnamento";//nome della tabella su cui saranno effettuate le operazioni
	
	@Override
	public String toSqlQuery() {
		return String.format(
                "SELECT * FROM %1$s ;",
                TABLE_NAME
        );
	}

}
