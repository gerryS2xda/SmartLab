
function loadContent(){
	loadTableBody();	//mostra soltanto quelle di oggi
}


//Funzioni per la gestione della tabella "Prenotazioni Effettuate"
function loadTableBody(){
	$.post("../prenotazione-serv", {"action": "lista_pren_by_stud"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp); //conversione in oggetto JS da strina JSON ricevuta da servlet
			if(o.pren0 == "failure"){
				window.location.href = "./index.jsp"; //pagina errore 404}
			}
			var size = sizeObject(o); //calcolo del numero di proprieta' presenti nell'oggetto
			var str = ""; //stringa che contiene codice HTML per la costruzione del contenuto
			for(var i=0; i < size; i++){
				var k = o["pren"+i]; //prendi l'oggetto JS associato alla proprieta' 'pren' dell'oggetto JS appena convertito 
				str+= "<tr><td>" + k.id + "</td><td>"+ k.data + "</td><td>" + k.laboratorio + "</td>" +
				"<td>" + k.postazione + "</td><td><span>" + k.oraInizio + "</span> - <span>" + k.oraFine + "</span></td>" + 
				"<td><input type=\"checkbox\" onchange=\"deletePrenotazione($(this))\"> </td><td class=\"hidden_item\">" + k.stato + "</tr>";
			}
			$("#div_tb_effettuate_content").show();
			$("#tb_pren_effettuate tbody").html(str);	//aggiungi contenuto a <tbody>
			
			updatePrenotazioniStatus(); //aggiorna lo stato delle prenotazioni in base all'ora corrente
		}else{
			window.location.href = "./index.jsp"; //pagina errore 404
		}
	});
}

function deletePrenotazione(item){	//usata da checkbox per cancellare le prenotazioni
	if(item.is(':checked')){
		var row = item.parents("tr");
		var td = row.find("td");
		var id = td.eq(0).text(); //dammi l'ID della prenotazione da cancellare
		var statoPren = td.eq(6).text();
		if(statoPren == "true"){	//se attiva procedi con rimozione
			var esito = confirm("Desidera annullare la prenotazione n.ro " + id + " del " + td.eq(1).text() + " ?");
			if(esito){
				$.post("../prenotazione-serv", {"action": "del_pren", "id_pren" : id}, function(resp, stat, xhr){
					if(xhr.readyState == 4 && stat == "success"){
						var res = JSON.parse(resp);
						if(res.esito == "ok"){
							row.remove();	//rimuovi la riga interessata
						}else{
							alert("La prenotazione non e' piu' annullabile!!");
							item.prop('checked', false);
						}
					}else{
						window.location.href = "./error.jsp"; //pagina errore 404
					} 
				});
			}else{
				item.prop('checked', false);
			}
		}else{
			alert("La prenotazione non annullabile poiche' e' scaduta!!");
			item.prop('checked', false);
		}
	}
}

//Ogni volta che si accede alla pagina PrenotazioniEffettuate, aggiorna il loro stato se necessario (utile per la rimozione)
function updatePrenotazioniStatus(){
	var trs = $("#tb_pren_effettuate tbody tr");
	for(var i = 0; i < trs.length; i++){
		var tds = trs.eq(i).find("td");
		var id = tds.eq(0).text(); //dammi l'ID della prenotazione da controllare e aggiornare lo stato
		$.post("../prenotazione-serv", {"action": "pren_status", "id_pren" : id}, function(resp, stat, xhr){
			if(xhr.readyState == 4 && stat == "success"){
				var o = JSON.parse(resp);
				var esito = o.esito;
				if(esito == "failure"){ 
					window.location.href = "./index.jsp"; //pagina errore 404
				}
			}else{
				window.location.href = "./index.jsp"; //pagina errore 404
			} 
		});
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
