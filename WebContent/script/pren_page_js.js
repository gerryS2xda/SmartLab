
function loadContent(){
	loadTableBody();
}

//Funzioni per la gestione della tabella "Prenota"
function loadTableBody(){
	var x = $("#tb_prenota tbody"); //ottieni riferimento a <tbody>
	var lab = $("#idLab").text();
	$.post("../ServletPostazione", {"action": "lista_pos_json", "id_lab":lab }, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp); //conversione in oggetto JS da strina JSON ricevuta da servlet
			var size = sizeObject(o); //calcolo del numero di proprieta' presenti nell'oggetto
			var str = "";
			for(var i=0; i < size; i++){
				var k = o["post"+i]; //prendi l'oggetto JS associato alla proprieta' 'pren' dell'oggetto JS
				if(k.stato){	//se il laboratorio e' aperto, mostra postazioni disponibili
					str+= "<tr><td>" + k.numero + "<td><select name=\"fascia_oraria_action\" onChange=\"verifyPostazioneAvailable($(this));\">" +
						"<option value=\"09:00-11:00\">Mattina (9 - 11)</option><option value=\"11:00-13:00\">Mattina (11 - 13)</option>"+
						"<option value=\"13:00-15:00\">Pomeriggio (13 - 15)</option><option value=\"15:00-17:00\">Pomeriggio (15 - 17)</option>" +
						"<option value=\"17:00-19:00\">Sera (17 - 19)</option></select></td>" +
						"<td><button type=\"button\" onclick=\"effettuaPrenotazione($(this))\">Prenota</button></td></tr>";
				}
			}
			$("#div_tb_prenota_content").show();
			x.html(str);
		}else{
			window.location.href = "./index.jsp"; //pagina errore 404
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
				//trasportare in altra pagina (quella delle prenotazioni effettuate o nel report)
			}else{
				alert("Prenotazione non effettuata!! Premi \"Ok\" per aggiornare la pagina");
				location.reload(); //refresh della pagina
			}
		}else{
			window.location.href = "./index.jsp"; //pagina errore 404
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
			if(status != "disponibile"){
				alert("Postazione e' stata gia' occupata! La pagina verra' ricaricata");
				location.reload(); //refresh della pagina
			}
		}else{
			window.location.href = "./index.jsp"; //pagina errore 404
		}
	});
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
