package businessLogic.laboratorio;

import java.sql.SQLException;
import java.util.List;

import businessLogic.Postazione.PostazioneRepository;
import businessLogic.assegnamento.ListaLabAss;
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
					Laboratorio temp=new Laboratorio();
					temp=repository.findItemByQuery(new IdLab(lab.getNome()));
					lab.setIDlaboratorio(temp.getIDlaboratorio());
					//System.out.println("id "+lab.getIDlaboratorio());
					//crea le postazioni
					PostazioneRepository rPostazione=new PostazioneRepository();
					for(int i=0;i<lab.getPosti();i++){
						Postazione p=new Postazione();
						p.setLaboratorio(temp.getIDlaboratorio());
						p.setNumero(i+1);
						p.setStato(true);
						rPostazione.add(p);
					}
					
				} catch (SQLException e) {
					flag=false;
					System.out.println("non e' possibile aggiungere il laboratorio al sistema");
					e.printStackTrace();
				}
	    	}else{
	    		System.out.println("e' stato passato un oggetto laboratorio nullo");
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
					//System.out.println("ok manager");
				} catch (SQLException e) {
					flag=false;
					System.err.println("non e' possibile eliminare il laboratorio dal sistema");
					e.printStackTrace();
				}
	    	}else{
	    		System.err.println("e' stato passato un oggetto laboratorio nullo");
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
				System.err.println("non e' possibile ritornare la lista dei laboratori");
				e.printStackTrace();
			}
			return null;
		}
	    
	    /*Ritorna una lista di laboratori assegnati a un responsabile
	     * @param email email del responsabile a cui saranno assegnati i laboratori
	     * @pre email !=null
	     * @return lista laboratori assegnati al responsabile
	     * */
	    public List<Laboratorio> getLaboratoryListForResp(String email){
	    	LaboratorioRepository repository=new LaboratorioRepository();
			if(email!=null){
	    		try {
					return repository.query(new ListaLabAss(email));//forse da spostare in laboratorio
				} catch (SQLException e) {
					System.err.println("non e' possibile ritornare la lista dei laboratori");
					e.printStackTrace();
				}
	    	}
			return null;
	    }

}
