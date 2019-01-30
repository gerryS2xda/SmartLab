package businessLogic.laboratorio;

import java.sql.SQLException;
import java.util.List;

import businessLogic.Postazione.PostazioneRepository;
import dataAccess.storage.bean.Laboratorio;
import dataAccess.storage.bean.Postazione;

/** contiene tutte le operazione necessarie per gestire i laboratori
*@author giuseppe paolisi
*/
public class LaboratorioManager {
	
	private static LaboratorioManager instance;

    public static LaboratorioManager getInstance() {

        if (instance == null) {
            instance = new LaboratorioManager();
        }
        return instance;

    }
	
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
					//crea le postazioni
					PostazioneRepository rPostazione=new PostazioneRepository();
					for(int i=0;i<lab.getPosti();i++){
						Postazione p=new Postazione();
						p.setLaboratorio(lab.getIDlaboratorio());
						p.setNumero(i+1);
						p.setStato(false);
						rPostazione.add(p);
					}
					
				} catch (SQLException e) {
					flag=false;
					System.err.println("non � possibile aggiungere il laboratorio al sistema");
					e.printStackTrace();
				}
	    	}else{
	    		System.err.println("� stato passato un oggetto laboratorio nullo");
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
					System.err.println("non � possibile eliminare il laboratorio dal sistema");
					e.printStackTrace();
				}
	    	}else{
	    		System.err.println("� stato passato un oggetto laboratorio nullo");
	    		flag=false;
	    	}
	    	return flag;
	    }
	  /** Ritorna una lista contenenti tutti i laboratori presenti nel sistema
	    * @return lista laboratori
	    */
	    public List<Laboratorio> getLaboratoryList(){
	    	LaboratorioRepository repository=new LaboratorioRepository();
	    	try {
				return repository.query(new ListaLab());
			} catch (SQLException e) {
				System.err.println("non � possibile ritornare la lista dei laboratori");
				e.printStackTrace();
			}
			return null;
		}
	  /** mostra una statistica di utilizzo di un laboratorio in termini di quante postazioni vengono         * occupate al giorno.
	    *@param lab laboratori su desidera vedere la statistica di utilizzo
	    *@return statistiche di utilizzo di un laboratorio 
	    public int viewStatistiche(Laboratorio lab){}*/

}
