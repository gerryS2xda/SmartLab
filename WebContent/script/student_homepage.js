function loadContent(){
	loadWidget();
	showMailInviateDashboard();
}

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
			//inserite qui perche' viene eseguita prima questa pagina e poi laboratoriAttivi.jsp
			if(!resetPostazioniDisponibiliAfterOrarioChiusura()){
				deletePrenotazioniAfterDays(); //invoca solo se non e' stato effettuato il reset dopo orario di chiusura
			}
		}else{
			window.location.href = "./error.jsp"; //pagina errore 404
		}
	});
}

/* Funzioni importanti per la fase di prenotazione (non si tiene traccia dello storico delle prenotazioni) */
//reset postazioni disponibili dopo orario di chiusura (se non basta, si esegue il reset in base alla data riportata sulle prenotazioni)
function resetPostazioniDisponibiliAfterOrarioChiusura(){
	var consumata = false;	//se e' stata effettuata almeno una rimozione
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
			$.post("../prenotazione-serv", {"action": "del_pren_after_orario_chiusura"}, function(resp, stat, xhr){
				if(xhr.readyState == 4 && stat == "success"){
					var o = JSON.parse(resp);
					var esito = o.esito;
					if(!esito){
						window.location.href = "./error.jsp";
					}
				}else{
					window.location.href = "./error.jsp"; //pagina errore 404
				}
			});
			consumata = true;
		}
	}
	return consumata;
}

//cancella le prenotazioni dopo diversi giorni	(NO HISTORY)
function deletePrenotazioniAfterDays(){
	$.post("../prenotazione-serv", {"action": "del_pren_after_days"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp);
			var esito = o.esito;
			if(!esito){
				window.location.href = "./error.jsp";
			}
		}else{
			window.location.href = "./error.jsp"; //pagina errore 404
		}
	});
}

//funzioni per la dashboard area
$("#ic1").click(function(){	//animazione widget 'Dati personali'
	$("#content1").slideToggle("slow");
});

$("#ic2").click(function(){ //animazione widget 'Avviso'
	$("#content2").slideToggle("slow");
});

function showMailInviateDashboard(){
	$.post("../ServletAvviso", {"action": "viewAvvisi"}, function(resp, status, xhr){
		if(xhr.readyState == 4 & status=="success"){
			var o = JSON.parse(resp); //conversione in oggetto JS da strina JSON ricevuta da servlet
			var str = ""; //stringa che contiene codice HTML per la costruzione del contenuto
			if(o.email == "NoEmail"){
				str += "<li><div class=\"email_txt_div\">" + 
				"<p class=\"email_txt_body_link\">Nessun avviso da mostrare</p></div></li>";
			}else{
				var size = sizeObject(o); //calcolo del numero di proprieta' presenti nell'oggetto
				for(var i = 0; i < size && i<6; i++){ //mostra solo le prime 6 mail arrivate di recente
					var k = o["av"+i];	//prendi l'oggetto JS associato alla proprieta' 'email' dell'oggetto JS appena convertito 
					var message = k.messaggio;
					if(message.length >= 50){ 	//costruisci una breve descrizione da mostrare (simulazione dell'oggetto di un avviso)
						message = message.subString(0, 50); 
						message += "...";
					}
					str+= "<li><div class=\"email_txt_div\" onclick=\"showContentPopup($(this))\"><span class=\"email_txt_body\">" + k.titolo +
						  " del " + k.data + "<p class=\"email_txt_body_link\">" + message + "</p><span class=\"hidden_item\">" + k.id +"</span></div> " +
						  "<img id=\"del_avviso\" src=\"../images/trash_icon_32.png\" onclick=\"deleteAvviso($(this))\"></li>";
				}
			}
			$(".mail_arrivate").html(str);
		}else{
			window.location.href = "./error.jsp"; //pagina errore 404
		}
	});
}

//funzioni per il popup 'Contenuto email'
function showContentPopup(item){
	riempiPopup(item);
	$("#content_popup").show();
	$("#content_popup").addClass("popup_body");
}

function riempiPopup(item){
	var x = item.find("span");
	var idAvviso = x.eq(1).text();
	$.post("../ServletAvviso", {"action": "openAvviso", "id": idAvviso}, function(resp, status, xhr){
		if(xhr.readyState == 4 & status=="success"){
			var o = JSON.parse(resp); //conversione in oggetto JS da strina JSON ricevuta da servlet
			var h1 = $("#content_popup_area h1").eq(0).html("Avviso del " + o.data);
			var h3 = $("#content_popup_area h3").eq(0).html(o.titolo);
			var p = $("#content_popup_area p").eq(0).html(o.messaggio);
		}else{
			window.location.href = "./error.jsp"; //pagina errore 404
		}
	});
}

$("#back_content").click(removeContentPopup);

function removeContentPopup(){
	$("#content_popup").removeClass("popup_body");
	$("#content_popup").hide();
}

function deleteAvviso(item){
	var li = item.parents("li"); //dammi <li> su cui eseguire le azioni 
	var x = li.find("span");
	var idAvviso = x.eq(1).text();
	$.post("../ServletAvviso", {"action": "deleteAvviso", "id": idAvviso}, function(resp, stat, xhr){
		if(xhr.readyState == 4 &&stat =="success"){
			var res = JSON.parse(resp);
			if(res.esito == "successo"){
				li.remove();
			}else{
				window.location.href("./error.jsp");
			}
		}else
			window.location.href("./error.jsp");
	});
}

function validatePassword(item){
	var x = item.val();
	var err = $(".account_text_err").eq(0);
	var re = /^[A-Za-z0-9]{6,20}$/;
	var val = false;
	if(x == ""){ //errore campo vuoto
		styleForErrorTextInput(item);
		err.html("Campo obbligatorio");
	}else if(x.length < 6 || x.length > 20){ //codice errore per stringa troppo lunga
		styleForErrorTextInput(item);
		err.html("Password deve essere compreso tra 6 e 20 caratteri");
	}else if(x.match(re)){ //la stringa è conforme all'espressione regolare
		err.empty();
		item.css("border","1px solid green");
		val = true;
	}else{
		styleForErrorTextInput(item);
		err.html("Input non valido!! Può contenere solo lettere e numeri");
	}
	return val;
}

function styleForErrorTextInput(item){
	item.css("border","1px solid red");
}

//modifica solo la password
$("#edit_pwd").click(function(){
	var s = $("#content1 span");
	var email = s.eq(5).text();
	var pwd = $("#pwd_input");
	if(validatePassword(pwd)){
		$.post("../ServletUtente", {"action": "edit_password", "email": email, "pwd": pwd.val()}, function(resp, statusTxt, xhr){
			if(statusTxt == "success" && xhr.readyState == 4){
				var o = JSON.parse(resp);
				if(o.esito){
					alert("Modifica password eseguita correttamente");
				}else{
					alert("Password non modificata a causa di un problema");
				}
				
			}else{
				window.location.href = "./error.jsp"; //pagina errore 404
			}
		});
	}else{
		alert("Il campo password presenta degli errori!! Non si puo' proseguire");
	}
});

/* funzioni di utilita' */
/* calcola il numero di proprieta' presenti in un oggetto */
function sizeObject(obj) {
	var size = 0, key;
	for (key in obj) {
		if (obj.hasOwnProperty(key)) size++;
	}
	return size;
};
