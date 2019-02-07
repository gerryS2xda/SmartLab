//funzione per inviare e creare una segnalazione
$("#button").click(function inviaSegnalazione(){
	var flag = 0;
	var laboratorio = $("#Laboratori").text();
	var postazione = $("#NumPos").val();
	var oggetto = $("#oggetto").val();
	var descrizione = $("#descrizione").val();
	//se qualche campo non è compilato, esso diventa rosso
	if(laboratorio == null){
		$("#sceltaLab").prev().css("color", "red");
		flag++;
	}
	if(!$("#NumPos").val()){
		$("#sceltaPos").css("color", "red");
		flag++;
	}
	if(!$("#oggetto").val()){
		$("#insOgg").css("color", "red");
		flag++;
	}
	if(!$("#descrizione").val()){
		$("#insDes").css("color", "red");
		flag++;
	}
	if(flag != 0){
		alert("I dati inseriti non sono completi");
	}
	else{
		//se tutto è corretto, i campi ritornano neri
		$("#insDes").css("color", "black");
		$("#insOgg").css("color", "black");
		$("#sceltaPos").css("color", "black");
		$("#sceltaLab").css("color", "black");
		$.post("../ServletSegnalazione", {"action": "newSegnalazione", "laboratorio": laboratorio, "postazione": postazione, "oggetto": oggetto, "descrizione": descrizione}, function(resp, stat, xhr){
			if(xhr.readyState == 4 && stat == "success"){
				var risultato = JSON.parse(resp);//esito dell'operazione
				if(risultato.esito == "successo")
					alert("Segnalazione inviata con successo");
				else
					alert("Segnalazione non inviata");
			}else
				window.location.href("./error.jsp");
		});
	}
});

//funzione per ottenere la lista di segnalazioni
function loadSegnalazioni(){
	$.post("../ServletSegnalazione", {"action": "viewSegnalazioni"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var segnalazioni = JSON.parse(resp);
			var size = sizeObject(segnalazioni);
			var str = "";
			for(var i = 0; i < size; i++){
				var tmp = segnalazioni["sg" + i];//vengono prese tutte le segnalazioni a ogni iterazione
				str += "<tr><td onCLick = \"selectSegnalazione(" + tmp.id + ")\" id = \"sg " + tmp.id + "\">" + tmp.id + "</td><td>"+ tmp.laboratorio + "</td><td>" + tmp.postazione + "</td><td>" + tmp.oggetto + "</td><td>" + tmp.descrizione + "</td><td>" + tmp.data + "</td></tr>";
			}
			$("#tb_segnalazioni tbody").html(str);
		}else
			window.location.href("./error.jsp");
	});
}

//funzione per selezionare una singola segnalazione
function selectSegnalazione(id){
	/*var id;
	$(document).click(function(event){//l'evento che indica il click su una segnalazione
		id = $(event.target).text();
	});*/
	var btn = document.createElement("BUTTON");
	var t = document.createTextNode("Cancella segnalazione");
	btn.appendChild(t);
	btn.setAttribute("id", "delSegnalazione");
	btn.setAttribute("tag", id);
	document.body.appendChild(btn);
	$.post("../ServletSegnalazione", {"action": "openSegnalazione", "id": id}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var segnalazione = JSON.parse(resp);
			document.getElementById("delSegnalazione").setAttribute("id", id);//il bottone per poter eliminare una segnalazione
			$("#data").html(segnalazione.data);
			$("#oggetto").html(segnalazione.oggetto);
			$("descrizione").html(segnalazione.descrizione);
			$("lab").html(segnalazione.laboratorio);
			$("pos").html(segnalazione.postazione);
		}else
			window.location.href("./error.jsp");
	});
}

//funzione per poter eliminare una segnalazione
$("delSegnalazione").click(function deleteSegnalazione(){
	var id = document.getElementById("#delSegnalazione").getAttribute("tag");//id della segnalazione da eliminare
	var oggetto = $("#oggetto").text();
	var descrizione = $("#descrizione").text();
	var laboratorio = $("#lab").text();
	var postazione = $("pos").text();
	$.post("../ServletSegnalazione", {"action": "deleteSegnalazione", "id": id, "oggetto": oggetto, "descrizione": descrizione, "laboratorio": laboratorio, "postazione": postazione}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var res = JSON.parse(resp);//esito dell'operazione
			if(res.esito == "successo")
				alert("Segnalazione eliminata con successo");
			else
				alert("Segnalazione non eliminata");
		}else
			window.location.href("./error.jsp");
	});
});

function sizeObject(obj) {
	var size = 0, key;
	for (key in obj) {
		if (obj.hasOwnProperty(key)) size++;
	}
	return size;
};