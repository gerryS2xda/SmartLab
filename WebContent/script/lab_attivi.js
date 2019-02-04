function loadContent(){
	loadTableBody();
}

//Funzioni per la gestione della tabella "Prenota"
function loadTableBody(){
	var x = $("#tb_prenota tbody"); //ottieni riferimento a <tbody>
	$.post("../laboratorio", {"action": "lab_attivi"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp); //conversione in oggetto JS da strina JSON ricevuta da servlet
			var size = sizeObject(o); //calcolo del numero di proprieta' presenti nell'oggetto
			var str = "";
			for(var i=0; i < size; i++){
				var k = o["lab"+i]; //prendi l'oggetto JS associato alla proprieta' 'pren' dell'oggetto JS
				var statoLab = k.stato;
				if(statoLab){	//se il laboratorio e' aperto, mostra postazioni disponibili
					str+= "<tr><td>" + k.nome + "</td><td></td><td></td><td></td><td></td>" +
						"<td><button type=\"button\" onclick=\"effettuaPrenotazione($(this))\">Prenota</button></td><td class=\"td_hidden\">" + k.IDlaboratorio + "</td></tr>";
				}
			}
			$("#div_tb_prenota_content").show();
			x.html(str);
			
			//add numero postazioni disponibili
			for(var i = 0; i < size; i++){
				var tr = $("#tb_prenota tbody tr");  
				var tds = tr.eq(i).find("td");
				var k = o["lab"+i];
				for(var j = 0; j < 4; j++){
					getNumPostazioniDisponibili(k.posti, j, tds.eq(j+1));
				}
			}
			
		}else{
			window.location.href = "./index.jsp"; //pagina errore 404
		}
	});
}

function getNumPostazioniDisponibili(postOccupate, index, item){
	var th = $("#tb_prenota thead th");
	var ora = th.eq(index).text().split("-"); //dammi il valore della th dividi in due stringhe con split()
	$.post("../prenotazione-serv", {"action": "numero_post_occupate", "ora_inizio": ora[0]}, function(resp, stat, xhr){
		var num = 0;
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp);
			var num = postOccupate - o.numeroPost;
			item.html(num + " disponibili");
		}else{
			window.location.href = "./index.jsp"; //pagina errore 404
		}
	});
}

/* usata per i blocchi che mostrano i laboratori aperti/chiusi
function loadWidget(){
	var x = $(".list_block"); //ottieni riferimento a <ul>
	$.post("../laboratorio", {"action": "lab_attivi"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp); //conversione in oggetto JS da strina JSON ricevuta da servlet
			var size = sizeObject(o); //calcolo del numero di proprieta' presenti nell'oggetto
			var str = "";
			for(var i=0; i < size; i++){
				var k = o["lab"+i]; //prendi l'oggetto JS associato alla proprieta' 'pren' dell'oggetto JS
				if(k.stato){	//se il laboratorio e' aperto, mostra postazioni disponibili
					str+= "<li> <span class=\"block_txt\">" + k.nome + "</span>" +
						"<span class=\"info_dash_text\">Apertura: " + k.apertura +"</span>" +
						"<span class=\"info_dash_text\">Chiusura: " + k.chiusura +"</span> </li>";
				}else{
					str+= "<li> <span class=\"block_txt\">" + k.nome + "</span>" +
					"<span class=\"info_dash_text_red\">Oggi chiuso</li>";
				}
			}
			$("#div_tb_prenota_content").show();
			x.html(str);
		}else{
			window.location.href = "./index.jsp"; //pagina errore 404
		}
	});
}
*/

function effettuaPrenotazione(button){	//pulsante "Prenota"
	var row = button.parents("tr"); //dammi la riga <tr> su cui eseguire le azioni  
	var td = row.find("td"); //dammi tutti gli <td> che sono discendenti di <tr> selezionato prima
	var labName = td.eq(0).text();
	var idlab = td.eq(6).text();
	window.location.href = "./PrenotazionePage.jsp?lab_selected=" + idlab + "&lab_name=" + labName; //pagina errore 404
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