package businessLogic.assegnamento;

import dataAccess.storage.SqlSpecification;
//ritorna una lista di responsabili assegnati a un laboratorio
//da usare come argomento per il repository riguardante il responsabile 

public class ListaRespAss implements SqlSpecification {
	
	private String laboratorio;//id del laboratorio
	private static ListaRespAss instance;

    public static ListaRespAss getInstance() {

        if (instance == null) {
            instance = new ListaRespAss("lab1");
        }
        return instance;

    }
    
    public ListaRespAss(String laboratorio){
    	this.laboratorio=laboratorio;
    }

	
	@Override
	public String toSqlQuery() {
		return String.format(
                "select * from (SELECT * FROM assegnamento join addetto on assegnamento.responsabile = addetto.email"
                + " where laboratorio= %1$s ) x join utente u on x.responsabile = u.email ;",
                this.laboratorio
        );
	}

}
