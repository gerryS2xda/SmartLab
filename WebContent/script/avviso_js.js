function creaAvviso(){
	var flag = 0;
	var titolo = $("#titolo").text();
	var descrizione = $("#descrizione").text();
	if(oggetto == null){
		document.getElementById("#oggAvviso").style.color = "red";
		flag++;
	}
	if(descrizione == null){
		document.getElementById("#desAvviso").style.color = "red";
		flag++;
	}
	if(flag != 0)
		document.getElementById("#errore").style.display = "block";
	else{
		document.getElementById("#errore").style.display = "none";
		$.post("../ServletAvviso", {"action": "newAvviso", "titolo": titolo, "descrizione": descrizione, "addetto": addetto}, function(resp, stat, xhr){
			if(xhr.readyState == 4 && stat == "success"){
				var ris = JSON.parse(resp)
				var esito = ris.esito
				if(esito == "avviso creato")
					alert("Avviso creato con successo")
				else
					alert("Errore nella creazione dell'avviso")
			}
		});
	}
}

function loadAvvisi(){
	$.post("../ServletAvviso", {"action": "viewAvvisi"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var avvisi = JSON.parse(resp)
			var size = sizeObject(avvisi)
			var str = ""
			for(var i = 0; i < size; i++){
				var tmp = avvisi["av" + i];
				str += "<tr><td>" + tmp.id + "</td><td>"+ tmp.titolo + "</td><td>" + tmp.messaggio + "</td><td>" + tmp.data + "</td><td>" + tmp.addetto + "</td></tr>";
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
	})
	$.post("../ServletAvviso", {"action": "openAvviso", "id": id}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var avviso = JSON.parse(resp)
			$("#title").html(avviso.titolo)
			$("#message").html(avviso.messaggio)
			$("#date").html(avviso.data)
			$("#creator").html(avviso.addetto)
		}else
			window.location.href = "./index.jsp";
	});
}