package businessLogic.assegnamento;

import dataAccess.storage.SqlSpecification;
//da instazioare sul repository del laboratorio per ottenere tutti i laboratori legati a un responsabile
public class ListaLabAss implements SqlSpecification {
	
	private String responsabile;//id del responsabile
	private static ListaLabAss instance;

    public static ListaLabAss getInstance() {

        if (instance == null) {
            instance = new ListaLabAss("resp1");
        }
        return instance;

    }
    
    public ListaLabAss(String responsabile){
    	this.responsabile=responsabile;
    }
	
	private static final String TABLE_NAME = "assegnamento A join laboratorio L on A.laboratorio = L.IDlaboratorio";//nome della tabella su cui saranno effettuate le operazioni
	
	@Override
	public String toSqlQuery() {
		return String.format(
                "SELECT * FROM %1$s WHERE responsabile = %2$s ;",
                TABLE_NAME,
                this.responsabile
        );
	}

}
