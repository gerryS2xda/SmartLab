package businessLogic.assegnamento;

import dataAccess.storage.SqlSpecification;

//da usare come argomento per il repository riguardante il responsabile ritorna una lista di responsabili assegnati a un laboratorio

public class ListaRespAss implements SqlSpecification {
	
	private String laboratorio;//id del laboratorio
	private static ListaRespAss instance;

    public static ListaRespAss getInstance() {

        if (instance == null) {
            instance = new ListaRespAss("");
        }
        return instance;

    }
    
    public ListaRespAss(String laboratorio){
    	this.laboratorio=laboratorio;
    }

    private static final String TABLE_NAME = "assegnamento A join addetto Ad on A.responsabile = Ad.IDaddetto";//nome della tabella su cui saranno effettuate le operazioni
	
	@Override
	public String toSqlQuery() {
		return String.format(
                "SELECT * FROM %1$s WHERE responsabile = %2$s ;",
                TABLE_NAME,
                this.laboratorio
        );
	}

}
