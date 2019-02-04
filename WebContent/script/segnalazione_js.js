function inviaSegnalazione(){
	var flag = 0;
	var laboratorio = $("#Laboratori").text();
	var postazione = $("#NumPos").text();
	var oggetto = $("#oggetto").text();
	var descrizione = $("#descrizione").text();
	if(laboratorio == null){
		document.getElementById("#sceltaLab").style.color = "red";
		flag++;
	}
	if(postazione == null){
		document.getElementById("#sceltaPos").style.color = "red";
		flag++;
	}
	if(oggetto == null){
		document.getElementById("#insOgg").style.color = "red";
		flag++;
	}
	if(descrizione == null){
		document.getElementById("#insDes").style.color = "red";
		flag++;
	}
	if(flag != 0)
		document.getelementById("#errore").style.display = "block";
	else{
		document.getelementById("#errore").style.display = "none";
		$.post("../ServletSegnalazione", {"action": "newSegnalazione", "laboratorio": laboratorio, "postazione": postazione, "oggetto": oggetto, "descrizione": descrizione}, function(resp, stat, xhr){
			if(xhr.readyState == 4 && stat == "success"){
				alert("Segnalazione inviata con successo");
			}else
				window.location.href("./index.jsp");
		});
	}
}

function loadSegnalazioni(){
	$.post("../ServletSegnalazione", {"action": "viewSegnalazioni"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && success == "success"){
			var segnalazioni = JSON.parse(resp);
			var size = sizeObject(segnalazioni);
			var str = "";
			for(var i = 0; i < size; i++){
				var tmp = segnalazioni["sg" + i];
				str += "<tr><td>" + tmp.id + "</td><td>"+ tmp.laboratorio + "</td><td>" + tmp.postazione + "</td><td>" + tmp.oggetto + "</td><td>" + tmp.descrizione + "</td><td>" + tmp.data + "</td></tr>";
			}
			$("#tb_segnalazioni tbody").html(str);
		}else
			window.location.href("./index.jsp");
	});
}

function selectSegnalazione(){
	var id;
	$(document).click(function(event){
		id = $(event.target).text();
	})
	var btn = document.createElement("BUTTON");
	var t = document.createTextNode("Cancella segnalazione");
	btn.appendChild(t);
	btn.setAttribute("id", "delSegnalazione");
	btn.setAttribute("tag", id);
	document.body.appendChild(btn);
	$.post("../ServletSegnalazione", {"action": "openSegnalazione", "id": id}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var segnalazione = JSON.parse(resp);
			document.getElementById("delSegnalazione").setAttribute("id", id);
			$("#data").html(segnalazione.data);
			$("#oggetto").html(segnalazione.oggetto);
			$("descrizione").html(segnalazione.descrizione);
			$("lab").html(segnalaizone.laboratorio);
			$("pos").html(segnalazione.postazione);
		}else
			window.location.href("./index.jsp");
	});
}

function deleteSegnalazione(){
	var id = document.getElementById("#delSegnalazione").getAttribute("tag");
	var oggetto = $("#oggetto").text();
	var descrizione = $("#descrizione").text();
	var data = $("#data").text();
	var laboratorio = $("#lab").text();
	var postazione = $("pos").text();
	$.post("../ServletSegnalazione", {"action": "deleteSegnalazione", "id": id, "oggetto": oggetto, "descrizione": descrizione, "data": data, "lab": laboratorio, "pos": postazione}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var res = JSON.parse(resp);
			if(res.esito == "successo")
				alert("Segnalazione eliminata con successo");
			else
				alert("Segnalazione non eliminata");
		}else
			window.location.href("./index.jsp");
	});
}