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
	
	
	@Override
	public String toSqlQuery() {
		return String.format(
                "SELECT * FROM assegnamento ass join laboratorio lab on ass.laboratorio = lab.IDlaboratorio WHERE responsabile = '%1$s' ;",
                this.responsabile
        );
	}

}
