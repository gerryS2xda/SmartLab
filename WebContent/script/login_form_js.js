
function formValidationAndSubmit(){
	var flag = false;
	$("#errUserRegistration").hide(); //errore di utente gia' registrato
	if(validateUsernameLogin($("#nomeLog"))){
		if(validatePasswordLogin($("#passLog"))){
			var x = $("#nomeLog").val();
			var y = $("#passLog").val();
			$.post("ServletUtente", {"action": "effettuaAutenticazione", "email": x, "password": y}, function(resp, stat, xhr){
				if(xhr.readyState == 4 && stat == "success"){
					var o = JSON.parse(resp);
					var x = o.userstate;
					var y = o.userType;
					if(x == "errLogin"){
						document.login_form.reset();
						$("#errUserArea").show();
						$(".err_txt").eq(0).html("Username e password non validi");
					}else if(x == "ok"){
						$("#errUserArea").hide();
						document.login_form.reset();
						if(y == "studente"){ 
							window.location.href = "./studentInterface/StudentHomePage.jsp"; 
						}else if(y == "responsabile"){ 
							window.location.href = "./index.jsp"; 	//da sostituire con jsp reali
						}else if(y == "admin"){ 
							window.location.href = "./index.jsp";  //da sostituire con jsp reali
						}
					}else if(x == "logged"){
						document.login_form.reset();
						$("#errUserArea").show();
						$(".err_txt").eq(0).html("Utente già loggato");
					}
				}else{
					window.location.href = "./error.jsp"; //pagina errore 404
				}
			});
			flag = true;
		}
	}
	if(!flag){alert("Ci stanno uno o piu' campi che presentano degli errori!! Non si puo' proseguire");}
}

function validateUsernameLogin(item){
	var x = item.val();
	var err = $(".err_txt").eq(0);
	var errArea = $("#errUserArea");
	var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var val = false;
	$("#errUserRegistration").hide(); //errore di utente gia' registrato
	if(x == ""){ //errore campo vuoto
		styleForErrorTextInputLogin(item, errArea);
		err.html("Campo obbligatorio");
	}else if(x.length > 50){ //codice errore per stringa troppo lunga
		styleForErrorTextInputLogin(item, errArea);
		err.html("Email di ateneo non deve essere superare i 50 caratteri");
	}else if(x.match(re)){ //la stringa è conforme all'espressione regolare
		errArea.hide();
		item.css("border","1px solid grey");
		val = true;
	}else{
		styleForErrorTextInputLogin(item, errArea);
		err.html("Input non valido!! Può contenere solo lettere e numeri");
	}
	return val;
}

function validatePasswordLogin(item){
	var x = item.val();
	var err =  $(".err_txt").eq(1);
	var errArea = $("#errPwdArea");
	var re = /^[A-Za-z0-9]{6,20}$/;
	var val = false;
	$("#errUserRegistration").hide();	//errore di utente gia' registrato
	if(x == ""){ //errore campo vuoto
		styleForErrorTextInputLogin(item, errArea);
		err.html("Campo obbligatorio");
	}else if(x.length < 6 || x.length > 20){ //codice errore per stringa troppo lunga
		styleForErrorTextInputLogin(item, errArea);
		err.html("Password deve essere compreso tra 6 e 20 caratteri");
	}else if(x.match(re)){ //la stringa è conforme all'espressione regolare
		errArea.hide();
		item.css("border","1px solid grey");
		val = true;
	}else{
		styleForErrorTextInputLogin(item, errArea);
		err.html("Input non valido!! Può contenere solo lettere e numeri");
	}
	return val;
}

function styleForErrorTextInputLogin(item, errArea){
	item.css("border","1px solid red");
	errArea.show();
}
