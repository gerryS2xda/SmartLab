package businessLogic.prenotazione;

public class PrenByStudPost {

	//instance field
	private String email;
	private String numeroPost;
	private String idLab;
		
	//constructor
	public PrenByStudPost(String email, String numeroPost, String idLab){
		this.email = email;
		this.numeroPost = numeroPost;
		this.idLab = idLab;
	}
		
		
	public String toSqlQuery(){
		String str = String.format("SELECT * FROM " + PrenotazioneRepository.TABLE_NAME 
				+ " WHERE Studente = '%s' AND Postazione = %s AND Laboratorio = %s"  , email, numeroPost, idLab);
		return str;
	}
}
