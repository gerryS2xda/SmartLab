
function loadContent(){
	loadTableBody();
}


//Funzioni per la gestione della tabella "Prenotazioni Effettuate"
function loadTableBody(){
	$.post("lista_pren", {"action": "lista_pren"}, function(reso, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp); //conversione in oggetto JS da strina JSON ricevuta da servlet
			var size = sizeObject(o); //calcolo del numero di proprieta' presenti nell'oggetto
			var str = ""; //stringa che contiene codice HTML per la costruzione del contenuto
			for(var i=0; i < size; i++){
				var k = o["pren"+i]; //prendi l'oggetto JS associato alla proprieta' 'pren' dell'oggetto JS appena convertito 
				str+= "<tr><td>" + k.id + "</td><td>"+ k.data + "</td><td>" + k.laboratorio + "</td>" +
				"<td>" + k.postazione + "</td><td>" + k.fasciaOr + "</td><td><input type=\"checkbox\" onchange=\"deletePrenotazione($(this))\"> </td></tr>";
			}
			$("#div_tb_effettuate_content").show();
			$("#tb_pren_effettuate tbody").html(str);	//aggiungi contenuto a <tbody>
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
		
		if(isPrenotazioneActive(id)){
			var esito = confirm("Desidera annullare la prenotazione n.ro " + id + " del " + td.eq(1).text() + " ?");
			if(esito){
				$.post("posta-contr", {"action": "del_pren", "id_pren" : id}, function(resp, stat, xhr){
					if(xhr.readyState == 4 && stat == "success"){
						row.remove();	//rimuovi la riga interessata
					}else{
						window.location.href = "./error.jsp"; //pagina errore 404
					} 
				});
			}
		}
	}
}

function isPrenotazioneActive(id){
	var b = true;
	$.post("posta-contr", {"action": "pren_status", "id_pren" : id}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp);
			var esito = o.esito;
			if(esito == "scaduta"){
				b = false;
			}
		}else{
			window.location.href = "./index.jsp"; //pagina errore 404
		} 
	});
	return b;
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