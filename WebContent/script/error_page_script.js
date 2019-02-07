
function loadFunctionsBody(){
	forceLogout();
}

//funzione che viene eseguita per forzare il logout appena si entra in questa pagina
//il forzamento del logout serve per poter ritonare ad effettuare il login (altrimenti compare "Utente gia' loggato")
function forceLogout(){
	$.post("utente", {"action": "logout"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp);
			var esito = o.done;
			//no utilizzo del risultato
		}else{
			window.location.href = "./error.jsp"; //pagina errore 404
		}
	});
}