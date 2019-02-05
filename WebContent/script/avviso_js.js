$("#crea").click(function(){
	var flag = 0;
	var titolo = $("#titolo").val();
	var descrizione = $("#descrizione").val();
	if(!$("#titolo").val()){
		$("#oggAvviso").css("color", "red");
		flag++;
	}
	if(!$("#descrizione").val()){
		$("#desAvviso").css("color", "red");
		flag++;
	}
	if(flag != 0)
		alert("I dati inseriti non sono completi");
	else{
		$("#oggAvviso").css("color", "black");
		$("#desAvviso").css("color", "black");
		$.post("../ServletAvviso", {"action": "newAvviso", "titolo": titolo, "descrizione": descrizione}, function(resp, stat, xhr){
			if(xhr.readyState == 4 && stat == "success"){
				var ris = JSON.parse(resp);
				var esito = ris.esito;
				if(esito == "errore")
					alert("Non hai i permessi per creare un avviso");
				else if(esito == "avviso creato")
					alert("Avviso creato con successo");
				else
					alert("Errore nella creazione dell'avviso");
			}else
				window.location.href("./index.jsp");
		});
	}
});

function loadAvvisi(){
	$.post("../ServletAvviso", {"action": "viewAvvisi"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var avvisi = JSON.parse(resp);
			var size = sizeObject(avvisi);
			var str = "";
			for(var i = 0; i < size; i++){
				var tmp = avvisi["av" + i];
				str += "<tr><td><a href = \"avviso.jsp\"" + tmp.id + "></td><td>"+ tmp.titolo + "</td><td>" + tmp.messaggio + "</td><td>" + tmp.data + "</td><td>" + tmp.addetto + "</td></tr>";
			}
			$("#tb_avvisi tbody").html(str);
		}else
			window.location.href = "./index.jsp";
	});
}

function selectAvviso(){
	var id;
	$(document).click(function(event){
		id = $(event.target).text();
	});
	$.post("../ServletAvviso", {"action": "openAvviso", "id": id}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var avviso = JSON.parse(resp);
			if(avviso.esito == "successo"){
				if(avviso.tipo == "addetto"){
					var btn = document.createElement("BUTTON");
					var t = document.createTextNode("Cancella avviso");
					btn.appendChild(t);
					btn.setAttribute("id", "delAvviso");
					btn.setAttribute("tag", id);
					document.body.appendChild(btn);
				}
				//document.getElementById("delAvviso").setAttribute("tag", id);
				$("#title").html(avviso.titolo);
				$("#message").html(avviso.messaggio);
				$("#date").html(avviso.data);
				$("#creator").html(avviso.addetto);
			}else
				alert("Avviso non trovato");
		}else
			window.location.href = "./index.jsp";
	});
}

$("#delAvviso").click(function(){
	var id = document.getElementById("#delAvviso").getAttribute("tag");
	var titolo = $("#title").text();
	var messaggio = $("#message").text();
	var addetto = $("#creator").text();
	$.post("../ServletAvviso", {"action": "deleteAvviso", "id": id, "titolo": titolo, "messaggio": messaggio, "addetto": addetto}, function(resp, stat, xhr){
		if(xhr.readyState == 4 &&stat =="success"){
			var res = JSON.parse(resp);
			if(res.esito == "successo")
				alert("Eliminazione avvenuta con successo");
			else
				alert("Eliminazione non riuscita");
		}else
			window.location.href("./index.jsp");
	});
});