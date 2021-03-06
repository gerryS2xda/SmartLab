
function loadContent(){
	loadTableBody();
}

//Funzioni per la gestione della tabella "Prenota"
function loadTableBody(){
	var x = $("#tb_prenota tbody"); //ottieni riferimento a <tbody>
	var lab = $("#idLab").text();
	$.post("../postazioni", {"action": "lista_pos_json", "id_lab":lab }, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp); //conversione in oggetto JS da strina JSON ricevuta da servlet
			var size = sizeObject(o); //calcolo del numero di proprieta' presenti nell'oggetto
			var str = "";
			for(var i=0; i < size; i++){
				var k = o["post"+i]; //prendi l'oggetto JS associato alla proprieta' 'pren' dell'oggetto JS
				if(k.stato){	//se il laboratorio e' aperto, mostra postazioni disponibili
					str+= "<tr><td name=\"numeroPost\">" + k.numero + "<td><select name=\"fascia_oraria_action\" onChange=\"verifyPostazioneAvailable($(this));\">" +
						"<option value=\"09:00-11:00\">Mattina (9 - 11)</option><option value=\"11:00-13:00\">Mattina (11 - 13)</option>"+
						"<option value=\"13:00-15:00\">Pomeriggio (13 - 15)</option><option value=\"15:00-17:00\">Pomeriggio (15 - 17)</option></select></td>" +
						"<td><button name=\"effettuaPren_button\" type=\"button\" onclick=\"effettuaPrenotazione($(this))\">Prenota</button></td></tr>";
				}
			}
			$("#div_tb_prenota_content").show();
			x.html(str);
			
			//inserite qui perche' AJAX effettua richieste asincrone
			deletePostazioniOccupateFromSelect();
			deleteFasceOrarieByOraCorrente();
		}else{
			window.location.href = "./error.jsp"; //pagina errore 404
		}
	});
}

//funzione per rimuovere le postazioni gia' occupate
function deletePostazioniOccupateFromSelect(){
	var tr = $("#tb_prenota tbody tr"); //dammi tutte righe (postazioni)
	
	$.post("../prenotazione-serv", {"action": "lista_pren"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp); //conversione in oggetto JS da strina JSON ricevuta da servlet
			if(o.pren0 == "failure"){
				window.location.href = "./error.jsp"; //pagina errore 404}
			}
			var size = sizeObject(o); //calcolo del numero di proprieta' presenti nell'oggetto
			var str = ""; //stringa che contiene codice HTML per la costruzione del contenuto
			for(var i=0; i < size; i++){
				var k = o["pren"+i]; //prendi l'oggetto JS associato alla proprieta' 'pren' dell'oggetto JS appena convertito 
				for(z = 0; z < tr.length; z++){
					var tds = tr.eq(z).find("td");
					var numPost = tds.eq(0).text();
					var nomeLab = $(".title_page").text();
					if(numPost == k.postazione && nomeLab == k.laboratorio){
						console.log("NumPost: " + numPost + "  PostPren: " + k.postazione);
						var selects = tr.eq(z).find("select");	//prendi la select
						var options = selects.find("option");	//prendi le option della select
						console.log("OptionLength: " + options.length);
						if(options.length == 1){	//tutte le fasce orarie sono occupate -> rimuovi postazione
							tr.eq(z).remove();
						}else{
							var y = k.oraInizio;
							y = y.split(":");
							var oraInizioPren = parseInt(y[0]);
							for(var j = 0; j < options.length; j++){	//per ogni option 
								var oraInizioSel = parseInt(options.eq(j).val().split("-"));
								if(oraInizioSel == oraInizioPren){ //se uguali significa che la postazione e' occupata in quella fascia oraria
									options.eq(j).remove(); 
								}	
							}
						}
						break;
					}
				}
			}
		}else{
			window.location.href = "./error.jsp"; //pagina errore 404
		}
	});
}

function effettuaPrenotazione(button){	//pulsante "Prenota"
	var row = button.parents("tr"); //dammi la riga <tr> su cui eseguire le azioni  
	var td = row.find("td"); //dammi tutti gli <td> che sono discendenti di <tr> selezionato prima
	var f = td.eq(1).children().val().split("-"); //dammi il valore della <select> che e' stato impostato e dividi in due stringhe con split()
	var lab = $("#idLab").text();
	var post = td.eq(0).text();
	$.post("../prenotazione-serv", {"action": "effettua", "postazione": post, "lab": lab, "inizio": f[0], "fine":f[1]}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp);
			var esito = o.esito;
			if(esito == "ok"){
				alert("Prenotazione effettuata con successo!!");
				window.location.href = "./PrenotazioniEffettuate.jsp";
			}else if(esito == "limitPrenException"){
				alert("Hai effettuato gia' 2 prenotazioni!! Riprova dopo la chiusura del laboratorio");
				location.reload(); //refresh della pagina
			}else if(esito == "failure"){
				window.location.href = "./error.jsp"; //pagina errore 404
			}
		}else{
			window.location.href = "./error.jsp"; //pagina errore 404
		}
	});
}

//usato per verificare se la postazione e' ancora disponibile, puo' capitare che un altro utente prenota prima di me
function verifyPostazioneAvailable(item){
	var x = item.val().split("-");  //dammi il valore della <select> che e' stato impostato e dividi in due stringhe con split()
	var row = item.parents("tr"); //dammi la riga <tr> su cui eseguire le azioni  
	var td = row.find("td"); //dammi tutti gli <td> che sono discendenti di <tr> selezionato prima
	var post = td.eq(0).text();
	var lab = $("#idLab").text();
	$.post("../prenotazione-serv", {"action": "check_post", "postazione": post, "inizio": x[0], "fine":x[1], "lab": lab}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp);
			var status = o.status;
			if(status == "failure"){
				window.location.href = "./error.jsp"; //pagina errore 404
			}else if(status != "disponibile"){
				alert("Postazione e' stata gia' occupata! La pagina verra' ricaricata");
				location.reload(); //refresh della pagina
			}
		}else{
			window.location.href = "./error.jsp"; //pagina errore 404
		}
	});
}

//rimuovi fasce orarie se ora corrente > oraInizio in riferimento alla giornata odierna
function deleteFasceOrarieByOraCorrente(){
	var tr = $("#tb_prenota tbody tr"); //dammi tutte righe (postazioni)
	for(z = 0; z < tr.length; z++){
		var tds = tr.eq(z).find("td");
		var numPost = tds.eq(0).text();
		var selects = tr.eq(z).find("select");	//prendi la select
		var options = selects.find("option");	//prendi le option della select
		var oraCorrente = new Date().getHours();
		for(var j = 0; j < options.length; j++){	//per ogni option 
			var oraInizioSel = parseInt(options.eq(j).val().split("-"));
			if(oraCorrente > oraInizioSel){ 
				options.eq(j).remove(); 
			}	
		}
	}
	
}


/* funzioni di utilita' */
/* calcola il numero di proprieta' presenti in un oggetto */
function sizeObject(obj) {
	var size = 0, key;
	for (key in obj) {
		if (obj.hasOwnProperty(key)) size++;
	}
	return size;
};
