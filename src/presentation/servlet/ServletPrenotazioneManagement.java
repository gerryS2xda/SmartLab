package presentation.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import businessLogic.prenotazione.PrenotazioneException;
import businessLogic.prenotazione.PrenotazioneManager;
import dataAccess.storage.bean.Prenotazione;
import dataAccess.storage.bean.Studente;

@WebServlet("/prenotazione-serv")
public class ServletPrenotazioneManagement extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrenotazioneManager manager = PrenotazioneManager.getInstance();
		HttpSession session = request.getSession();
		Gson json = new Gson();

		//costruisci risposta JSON (valido per tutta la servlet)
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");

		String action = request.getParameter("action");
		if(action == null){
			response.setStatus(404);
			response.sendRedirect("./error.jsp"); //pagina errore 404
		}else if(action.equals("effettua")){
			boolean done = true;

			//ottieni oggetto studente da sessione
			Studente s = (Studente) session.getAttribute("user");

			String lab = request.getParameter("lab"); //usare Postazione obj per ottenere il lab

			int post = Integer.parseInt(request.getParameter("postazione")); //usare postazione repository per ottenere obj Postazione

			String inizio = request.getParameter("inizio");
			String fine =  request.getParameter("fine");

			try{
				manager.effettuaPrenotazione(s.getEmail(), post, inizio, fine, lab);
			}catch (PrenotazioneException e){
				response.getWriter().write(json.toJson("{\"esito\": \"limitPrenException\"}"));
				done = false;
			}catch(SQLException e){
				response.getWriter().write(json.toJson("{\"esito\": \"failure\"}"));
				done = false;
			}

			if(done){
				response.getWriter().write(json.toJson("{\"esito\": \"ok\"}"));
			}

		}else if(action.equals("check_post")){	//verifica se la postazioni e' ancora disponibile
			boolean done = true;

			String lab = request.getParameter("lab"); //usare Postazione obj per ottenere il lab
			int post = Integer.parseInt(request.getParameter("postazione")); //usare postazione repository per ottenere obj Postazione
			String inizio = request.getParameter("inizio");
			String fine =  request.getParameter("fine");

			//collegarsi con la repository di postazione per ottenere un oggetto Postazione da cui vedere se e' disponibile
			List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
			try {
				prenotazioni = manager.getPrenotazioniByQuery(inizio, fine, post, lab);
			} catch (SQLException e) {
				done = false;
			}
			if(!done){
				response.getWriter().write(json.toJson("{\"status\": \"failure\"}"));
			}else if(prenotazioni.size() == 0){
				response.getWriter().write(json.toJson("{\"status\": \"disponibile\"}"));
			}else{
				response.getWriter().write(json.toJson("{\"status\": \"occupata\"}"));
			}
		}else if(action.equals("lista_pren_by_stud")){
			boolean done = true;
			Studente s = (Studente) session.getAttribute("user");

			List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
			try {
				prenotazioni = manager.getListPrenotazioniByStudent(s.getEmail());
			} catch (SQLException e) {
				done = false;
			}
			if(done){
				String str = "{";
				int i = 0;
				for(Prenotazione p : prenotazioni){
					str+= "\"pren" + i + "\":" + p.toString() + ",";
					i++;
				}
				str = str.substring(0, str.length() - 1) + "}"; //rimuovi ultima ',' e poi aggiungi '}'
				response.getWriter().write(json.toJson(str));
			}else{
				response.getWriter().write(json.toJson("{\"pren0\": \"failure\"}"));
			}
		}else if(action.equals("pren_status")){	//per ora non usato (forse da rimuovere)

			int id = Integer.parseInt(request.getParameter("id_pren"));
			Prenotazione pr = new Prenotazione();

			try{
				pr = manager.findPrenotazioneById(id);
			}catch(PrenotazioneException e){
				response.getWriter().write(json.toJson("{\"status\": \"notValid\"}")); //id non valido
			}catch(SQLException e){
				response.getWriter().write(json.toJson("{\"status\": \"failure\"}"));
			}

			//cambia lo stato se e' scaduta
			try {
				manager.changePrenotazioneStatus(pr);
			}catch (SQLException e) {
				response.getWriter().write(json.toJson("{\"status\": \"failure\"}"));
			}

			if(pr.isPrenotazioneActive()){
				response.getWriter().write(json.toJson("{\"status\": \"active\"}"));
			}else{
				response.getWriter().write(json.toJson("{\"status\": \"scaduta\"}"));
			}

		}else if(action.equals("del_pren")){
			boolean done = true;

			int id = Integer.parseInt(request.getParameter("id_pren"));
			Prenotazione pr = new Prenotazione();
			try{
				pr = manager.findPrenotazioneById(id);
			}catch (PrenotazioneException e){
				//id non valido
				done = false;
			}catch(SQLException e){
				done = false;
			}

			try {
				manager.annullaPrenotazione(pr);
			} catch (PrenotazioneException e) {
				//la prenotazione non si puo' annullare
				done = false;
			}catch(SQLException e){
				done = false;
			}

			if(done){
				response.getWriter().write(json.toJson("{\"esito\": \"ok\"}"));
			}else{
				response.getWriter().write(json.toJson("{\"esito\": \"failure\"}"));
			}
		}else if(action.equals("numero_post_occupate")){

			String oraInizio = request.getParameter("ora_inizio");
			String idLab = request.getParameter("idLab");

			int num = 0;
			try {
				num = manager.getNumeroPostazioniPrenotate(oraInizio, idLab);
			} catch (SQLException e) {
				num = -1; //indica situazione di failure
			}

			response.getWriter().write(json.toJson("{\"numeroPost\": " + num + " }"));
		}else if(action.equals("del_pren_after_orario_chiusura")){	//cancella le prenotazioni dopo l'orario di chiusura
			boolean done = true;

			try{
				manager.deleteAllPrenotazioni();
			}catch(SQLException e){
				done = false;
			}

			if(done){
				response.getWriter().write(json.toJson("{\"esito\": \"true\"}"));
			}else{
				response.getWriter().write(json.toJson("{\"esito\": \"false\"}"));
			}
		}else if(action.equals("del_pren_after_days")){	//cancella le prenotazioni dopo vari giorni
			boolean done = true;

			try{
				manager.deleteAllPrenotazioniAfterDays();
			}catch(SQLException e){
				done = false;
			}

			if(done){
				response.getWriter().write(json.toJson("{\"esito\": \"true\"}"));
			}else{
				response.getWriter().write(json.toJson("{\"esito\": \"false\"}"));
			}
		}else if(action.equals("lista_pren")){
			boolean done = true;

			List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();
			try {
				prenotazioni = manager.getAllPrenotazioni();
			} catch (SQLException e) {
				done = false;
			}
			if(done){
				String str = "{";
				int i = 0;
				for(Prenotazione p : prenotazioni){
					str+= "\"pren" + i + "\":" + p.toString() + ",";
					i++;
				}
				str = str.substring(0, str.length() - 1) + "}"; //rimuovi ultima ',' e poi aggiungi '}'
				response.getWriter().write(json.toJson(str));
			}else{
				response.getWriter().write(json.toJson("{\"pren0\": \"failure\"}"));
			}
		}else if(action.equals("num_pren_effettuate")){ //usata per controllare se lo studente ha superato il limite max di prenotazioni giornaliere

			Studente s = (Studente) session.getAttribute("user");
			int numP = 0;
			try {
				numP = manager.getNumPrenotazioniEffettuateOggi(s.getEmail());
			} catch (SQLException e) {
				numP = -1; //indica situazione di failure
			}

			response.getWriter().write(json.toJson("{\"numeroPren\": " + numP+ "}"));
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
