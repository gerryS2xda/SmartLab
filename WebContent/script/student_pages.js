//questo script contiene le funzioni JS comuni a quasi tutte le pagine presenti in student interface

//pulsante 'Logout' presente in header
$("#logout").click(function(){
	$.post("../ServletUtente", {"action": "logout"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp);
			var esito = o.done;
			if(esito){	//logout effettuato con successo
				window.location.href = "../Login.jsp";
			}else{
				window.location.href = "./error.jsp"; //pagina errore 404
			}
		}else{
			window.location.href = "./error.jsp"; //pagina errore 404
		}
	});
});