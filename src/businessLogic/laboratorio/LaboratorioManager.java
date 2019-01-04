package businessLogic.laboratorio;

import java.sql.SQLException;
import java.util.List;

import dataAccess.storage.bean.Laboratorio;

public class LaboratorioManager {
	 /**Crea un laboratorio in base al parametro in input
	    *@param lab laboratorio che si vuole aggiungere al sistema
	    *@pre lab!=null
	    *@return flag indica l'esito dell'operazione
	    */
	    public boolean createLaboratory(Laboratorio lab){
	    	boolean flag;
	    	if(lab!=null){
	    		LaboratorioRepository repository=new LaboratorioRepository();
	    		flag=true;
	    		try {
					repository.add(lab);
				} catch (SQLException e) {
					flag=false;
					System.err.println("non è possibile aggiungere il laboratorio al sistema");
					e.printStackTrace();
				}
	    	}else{
	    		System.err.println("è stato passato un oggetto laboratorio nullo");
	    		flag=false;
	    	}
	    	return flag;
	    }
	    
	   /** Rimuove il laboratorio passato come parametro dal sistema
	    * @param lab laboratorio che si vuole eliminare dal sistema
	    * @pre lab!=null
	    *@return flag indica l'esito dell'operazione
	    */
	    public boolean removeLaboratory(Laboratorio lab){
	    	boolean flag;
	    	if(lab!=null){
	    		LaboratorioRepository repository=new LaboratorioRepository();
	    		flag=true;
	    		try {
					repository.delete(lab);
				} catch (SQLException e) {
					flag=false;
					System.err.println("non è possibile aggiungere il laboratorio al sistema");
					e.printStackTrace();
				}
	    	}else{
	    		System.err.println("è stato passato un oggetto laboratorio nullo");
	    		flag=false;
	    	}
	    	return flag;
	    }
	  /** Ritorna una lista contenenti tutti i laboratori presenti nel sistema
	    * @return lista laboratori
	    */
	    public List<Laboratorio> getLaboratoryList(){
	    	
			return null;
		}
	  /** mostra una statistica di utilizzo di un laboratorio in termini di quante postazioni vengono         * occupate al giorno.
	    *@param lab laboratori su desidera vedere la statistica di utilizzo
	    *@return statistiche di utilizzo di un laboratorio 
	    public int viewStatistiche(Laboratorio lab){}*/

}
