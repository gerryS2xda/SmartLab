function loadContent(){
	loadWidget();
	//altre funzioni sono state inserite all'interno di loadWidget
}

//Funzioni per la gestione della tabella "Prenota"
function loadTableBody(){
	var x = $("#tb_prenota tbody"); //ottieni riferimento a <tbody>
	$.post("../laboratorio", {"action": "lab_attivi"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp); //conversione in oggetto JS da strina JSON ricevuta da servlet
			var size = sizeObject(o); //calcolo del numero di proprieta' presenti nell'oggetto
			var str = "";
			var count = 0; //usato per controllare se tutti i laboratori sono chiusi
			for(var i=0; i < size; i++){
				var k = o["lab"+i]; //prendi l'oggetto JS associato alla proprieta' 'pren' dell'oggetto JS
				var statoLab = k.stato;
				if(statoLab){	//se il laboratorio e' aperto, mostra postazioni disponibili
					str+= "<tr><td>" + k.nome + "</td><td></td><td></td><td></td><td></td>" +
						"<td><button type=\"button\" onclick=\"effettuaPrenotazione($(this))\">Prenota</button></td><td class=\"td_hidden\">" + k.IDlaboratorio + "</td></tr>";
				}else{
					count++; //lab chiuso --> incrementa
				}
			}
			if(count == size){
				$("#tb_prenota").html("<p> Tutti i laboratori sono chiusi </p>");
			}else{
				$("#div_tb_prenota_content").show();
				x.html(str);
				//add numero postazioni disponibili
				var tr = $("#tb_prenota tbody tr"); 
				for(var i = 0; i < size; i++){ 
					var tds = tr.eq(i).find("td");
					var k = o["lab"+i];
					for(var j = 0; j < 4; j++){
						getNumPostazioniDisponibili(k.posti, j+1, tds.eq(j+1), k.IDlaboratorio);
					}
				}
			}
			
		}else{
			window.location.href = "./index.jsp"; //pagina errore 404
		}
	});
}

function getNumPostazioniDisponibili(postTotali, index, item, idLab){
	var th = $("#tb_prenota thead th");
	var ora = th.eq(index).text().split("-"); //dammi il valore della th dividi in due stringhe con split()
	$.post("../prenotazione-serv", {"action": "numero_post_occupate", "ora_inizio": ora[0], "idLab": idLab}, function(resp, stat, xhr){
		var num = 0;
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp);
			if(o.numeroPost == -1){ window.location.href = "./index.jsp"; }	//error page
			var num = postTotali - o.numeroPost;
			item.html(num + " disp.");
		}else{
			window.location.href = "./index.jsp"; //pagina errore 404
		}
	});
}

/* usata per i blocchi che mostrano i laboratori aperti/chiusi */
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
						"<span class=\"info_dash_text\">Apertura: <span>" + k.apertura +"</span></span>" +
						"<span class=\"info_dash_text\">Chiusura: <span>" + k.chiusura +"</span></span> </li>";
				}else{
					str+= "<li> <span class=\"block_txt\">" + k.nome + "</span>" +
					"<span class=\"info_dash_text_red\">Oggi chiuso</li>";
				}
			}
			$("#div_tb_prenota_content").show();
			x.html(str);
			
			//inserite qui perche' le richieste di AJAX sono asincrone
			setPrenotazioniForNextDayText(); //verifica se e' passato l'orario di chiusura dei laboratori; se passato -> mostra la data di domani
			loadTableBody();
		}else{
			window.location.href = "./index.jsp"; //pagina errore 404
		}
	});
}


function effettuaPrenotazione(button){	//pulsante "Prenota"
	$.post("../prenotazione-serv", {"action": "num_pren_effettuate"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp);
			if(o.numeroPren == -1){window.location.href = "./index.jsp";} //page error
			if(o.numeroPren < 2){
				var row = button.parents("tr"); //dammi la riga <tr> su cui eseguire le azioni  
				var td = row.find("td"); //dammi tutti gli <td> che sono discendenti di <tr> selezionato prima
				var labName = td.eq(0).text();
				var idlab = td.eq(6).text();
				window.location.href = "./PrenotazionePage.jsp?lab_selected=" + idlab + "&lab_name=" + labName; //pagina errore 404
			}else{
				alert("Hai effettuato gia' 2 prenotazioni!! Riprova dopo la chiusura del laboratorio");
			}
		}else{
			window.location.href = "./index.jsp"; //pagina errore 404
		}
	});
}

//setta la data per il giorno successivo dopo la chiusura del laboratorio
function setPrenotazioniForNextDayText(){
	//ottieni orario di chiusura dai blocchi contenti le info sui laboratori 
	var x = $(".list_block li"); 
	var nLab = x.length;
	
	for(var i = 0; i < nLab; i++){
		var spans = x.eq(i).find("span");
		var oraCorrente = new Date().getHours();
		var y = spans.eq(4).text();
		y = y.split(":");
		var oraChiusura = parseInt(y[0]); //ottieni l'ora di chiusura
		if(oraCorrente > oraChiusura){	//laboratorio chiuso --> resetta le prenotazioni
			var tomorrow = new Date();
			tomorrow.setDate(tomorrow.getDate() + 1);
			$("#info_data_prenotazione").text(tomorrow.toLocaleDateString());
		}else{
			$("#info_data_prenotazione").text(new Date().toLocaleDateString());
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