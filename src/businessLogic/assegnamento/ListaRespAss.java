package businessLogic.assegnamento;

import dataAccess.storage.SqlSpecification;

//da usare come argomento per il repository riguardante il responsabile ritorna una lista di responsabili assegnati a un laboratorio

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
                "SELECT * FROM assegnamento join laboratorio on assegnamento.laboratorio = laboratorio.IDlaboratorio"
                + " where responsabile= %1$s;",
                this.laboratorio
        );
	}

}
