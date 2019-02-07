package businessLogic.assegnamento;

import java.sql.SQLException;
import java.util.List;
import businessLogic.addetto.AddettoRepository;
import dataAccess.storage.bean.Addetto;
import dataAccess.storage.bean.Assegnamento;
	/** contiene tutte le operazione necessarie per gestire la relazione tra laboratorio e responsabile
	*@author giuseppe paolisi
	*/
public class AssegnamentoManager {
	
	private static AssegnamentoManager instance;

    public static AssegnamentoManager getInstance() {

        if (instance == null) {
            instance = new AssegnamentoManager();
        }
        return instance;

    }

 /** permette di assegnare un responsabile a un laboratorio
    *@param ass contiene il responsabile e il laboratorio che saranno associati
    * @pre il laboratorio ha meno di 2 responsabile assegnati
    * @post il laboratorio ha 1 responsabile in piu' assegnato
    * @return flag indica se l'operazione e' andata a buon fine
    */
    public boolean setRespToLab(Assegnamento ass){
    	boolean flag;
    	if(ass!=null){
    		AssegnamentoRepository repository= new AssegnamentoRepository();
        	try {
    			repository.add(ass);
    			flag=true;
    		} catch (SQLException e) {
    			flag=false;
    			System.err.println("non e' possibile inserire l'oggetto nel database");
    			e.printStackTrace();
    		}
    	}else{
    		flag=false;
    		System.err.println("l'oggetto ass = null");
    	}
    	return flag;
    }
 /**permette di rimuovere un responsabile da un laboratorio
    * @param ass contiene la coppia responsabile laboratorio che sara' eliminata dal sistema
    * @pre il laboratorio ha 1 o 2 responsabili assegnati
    * @post il laboratorio ha un responsabile in meno assegnato
    * @return flag indica se l'operazione e' andata a buon fine
    */
    public boolean removeResponsabile(Assegnamento ass) {
    	boolean flag;
    	if(ass!=null){
    		AssegnamentoRepository repository= new AssegnamentoRepository();
        	try {
    			repository.delete(ass);
    			flag=true;
    		} catch (SQLException e) {
    			flag=false;
    			System.err.println("non e' possibile eliminare l'oggetto nel database");
    			e.printStackTrace();
    		}
    	}else{
    		flag=false;
    		System.err.println("l'oggetto ass = null");
    	}
    	return flag;
    }
    /**permette di visualizzare la lista dei responsabili assegnati a un laboratorio
    *@return lista dei responsabili assegnati ai corrispettivi laboratori
    */
    public List<Addetto> showResponsabileAndLaboratorio(String idlaboratorio){
    	AddettoRepository repository=new AddettoRepository();
    	
    	try {
			return repository.query(new ListaRespAss(idlaboratorio));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
	}
    
    /*ritorna tutti i responsabile che non sono assegnati al laboratorio
     * @param idlaboratorio id del laboratorio
     * @return una lista con tutti i responsabili che non sono assegnati a un laboratorio*/
    public List<Addetto> showResponsabileAddLaboratorio(String idlaboratorio){
    	AddettoRepository repository=new AddettoRepository();
    	
    	try {
			return repository.query(new ListaRespDaAssegnare(idlaboratorio));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return null;
    }
}
