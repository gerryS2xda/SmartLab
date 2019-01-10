
function loadContent(){
	loadTableBody();
}

//Funzioni per la gestione della tabella "Prenota"
function loadTableBody(){
	var x = $("#tb_prenota tbody"); //ottieni riferimento a <tbody>
	var size = 10;
	var str = "";
	for(var i=0; i < size; i++){
		str+= "<tr><td>" + i + "<td><select name=\"fascia_oraria_action\" onChange=\"verifyPostazioneAvailable($(this));\">" +
			"<option value=\"9:00-11:00\">Mattina (9 - 11)</option><option value=\"11:00-13:00\">Mattina (11 - 13)</option>"+
			"<option value=\"13:00-15:00\">Pomeriggio (13 - 15)</option><option value=\"15:00-17:00\">Pomeriggio (15 - 17)</option>" +
			"<option value=\"17:00-19:00\">Sera (17 - 19)</option></select></td>" +
			"<td><button type=\"button\" onclick=\"effettuaPrenotazione($(this))\">Prenota</button></td></tr>";
	}
	$("#div_tb_prenota_content").show();
	x.html(str);
}

function effettuaPrenotazione(button){	//pulsante "Prenota"
	var row = button.parents("tr"); //dammi la riga <tr> su cui eseguire le azioni  
	var td = row.find("td"); //dammi tutti gli <td> che sono discendenti di <tr> selezionato prima
	var f = td.eq(1).children().val().split("-"); //dammi il valore della <select> che e' stato impostato e dividi in due stringhe con split()
	var lab = $(".title_page").text();
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

function verifyPostazioneAvailable(item){
	var x = item.val().split("-");  //dammi il valore della <select> che e' stato impostato e dividi in due stringhe con split()
	var row = item.parents("tr"); //dammi la riga <tr> su cui eseguire le azioni  
	var td = row.find("td"); //dammi tutti gli <td> che sono discendenti di <tr> selezionato prima
	var post = td.eq(0).text();
	var lab = $(".title_page").text();
	$.post("../prenotazione-serv", {"action": "check_post", "postazione": post, "inizio": f[0], "fine":f[1], "lab": lab}, function(resp, stat, xhr){
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

/* Non usare!!
 * function effettuaPrenotazione(button){	//pulsante "Prenota"
	var row = button.parents("tr"); //dammi la riga <tr> su cui eseguire le azioni  
	var td = row.find("td"); //dammi tutti gli <td> che sono discendenti di <tr> selezionato prima
	var f = td.eq(1).children().val().split("-"); //dammi il valore della <select> che e' stato impostato e dividi in due stringhe con split()
	var lab = $(".title_page").text();
	var pren = new Object();
	pren.id = 0;
	pren.data = ""; //la data verra' impostata nella servlet (LocalDate.now())
	pren.oraInizio = f[0];
	pren.oraFine = f[1];
	pren.stato = true;
	pren.studente = ""; //l'email verra' specificata nella servlet
	pren.postazione = td.eq(0).text();;
	pren.laboratorio = null;
	$.post("../prenotazione-serv", {"action": "effettua", "prenotazione": JSON.stringify(pren), "lab": lab}, function(resp, stat, xhr){
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
*/
