package businessLogic.prenotazione;

public class PrenotazioneById implements PrenotazioneSqlSpecification{

	//instance field
	private int id;
	
	//constructor
	public PrenotazioneById(int id){
		this.id = id;
	}
	
	public String toSqlQuery(){
		String str = String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME 
				+ " WHERE IDprenotazione = %d", id);
		return str;
	}
}
