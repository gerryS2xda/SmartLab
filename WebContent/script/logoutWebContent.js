$("#logout").click(function(){

	$.post("utente", {"action": "logout"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			console.log("ok");
			var o = JSON.parse(resp);
			var esito = o.done;
			if(esito){	//logout effettuato con successo
				window.location.href = "Login.jsp";
			}else{
				window.location.href = "./error.jsp"; //pagina errore 404
			}
		}else{
			window.location.href = "./error.jsp"; //pagina errore 404
		}
	});
});