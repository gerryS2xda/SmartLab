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
	
	
	@Override
	public String toSqlQuery() {
		//tipo= false è un responsabile
		return String.format("select * from utente where email in ("
				+ "select email from addetto ad1 where ad1.tipo='false' && ad1.email not in("
				+ " SELECT responsabile FROM addetto ad join assegnamento a on ad.email = a.responsabile "
				+ "where laboratorio=%1$s));",
				this.laboratorio);
	}

}
