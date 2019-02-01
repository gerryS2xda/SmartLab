package businessLogic.assegnamento;

import dataAccess.storage.SqlSpecification;

public class ListaRespDaAssegnare implements SqlSpecification {
	//restituisce tutti i responsabili che non sono stati assegnati a un laboratorio
	private String laboratorio;
	private static ListaRespDaAssegnare instance;

    public static ListaRespDaAssegnare getInstance() {

        if (instance == null) {
            instance = new ListaRespDaAssegnare("lab1");
        }
        return instance;

    }
	
	public ListaRespDaAssegnare(String lab){
		this.laboratorio=lab;
	}
	
	
	private static final String TABLE_NAME = "((assegnamento A join laboratorio L on A.laboratorio = L.IDlaboratorio) AL join utente U on AL.IDaddetto = U.email";//nome della tabella su cui saranno effettuate le operazioni

	@Override
	public String toSqlQuery() {
		return String.format(
				"SELECT * FROM"
		                + "((SELECT IDaddetto FROM addetto WHERE tipo=false) except"
		                + "(SELECT IDaddetto FROM addetto Ad join assegnamento As on Ad.IDresponsabile = As.responsabile"
		                + ")) AS a join %1$s b on a.IDaddetto = b.email "
		                + "WHERE IDlaboratorio = %2$s ;",
                TABLE_NAME,
                this.laboratorio
        );
	}

}
