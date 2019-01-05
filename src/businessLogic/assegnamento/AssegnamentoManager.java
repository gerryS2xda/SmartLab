package businessLogic.assegnamento;

import java.sql.SQLException;
import java.util.List;

import businessLogic.laboratorio.LaboratorioManager;
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
    * @post il laboratorio ha 1 responsabile in più assegnato
    * @return flag indica se l'operazione è andata a buon fine
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
    			System.err.println("non è possibile inserire l'oggetto nel database");
    			e.printStackTrace();
    		}
    	}else{
    		flag=false;
    		System.err.println("l'oggetto ass = null");
    	}
    	return flag;
    }
 /**permette di rimuovere un responsabile da un laboratorio
    * @param ass contiene la coppia responsabile laboratorio che sarà eliminata dal sistema
    * @pre il laboratorio ha 1 o 2 responsabili assegnati
    * @post il laboratorio ha un responsabile in meno assegnato
    * @return flag indica se l'operazione è andata a buon fine
    */
    public boolean removeResponsabile(Assegnamento ass){
    	boolean flag;
    	if(ass!=null){
    		AssegnamentoRepository repository= new AssegnamentoRepository();
        	try {
    			repository.delete(ass);
    			flag=true;
    		} catch (SQLException e) {
    			flag=false;
    			System.err.println("non è possibile eliminare l'oggetto nel database");
    			e.printStackTrace();
    		}
    	}else{
    		flag=false;
    		System.err.println("l'oggetto ass = null");
    	}
    	return flag;
    }
    /**permette di visualizzare la lista dei laboratori con i responsabili assegnati
    *@return lista dei responsabili assegnati ai corrispettivi laboratori
    */
    public List<Assegnamento> showResponsabileAndLaboratorio(){
    	AssegnamentoRepository repository= new AssegnamentoRepository();
    	try {
			return repository.query(new ListaRespAss());
		} catch (SQLException e) {
			System.err.println("non è possibile tornare la lista ");
			e.printStackTrace();
		}
		return null;
	}
}
