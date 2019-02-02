
/* funzioni per la parte grafica */
function addActive(label){
	var d = document.getElementById(label);
	if(d.className == ""){
		d.className += "active";
	}
}

function removeActive(id_input, label){
	var x = document.getElementById(id_input);
	var d = document.getElementById(label);
	if(x.value == ""){
		d.className = "";
	}
}

//funzione generale per check possibilita' di submit
function formValidation(){
	var flag = false;
	if(validateOnlyLetter('first_name', 'lbl', 'nome_txt_error', 20)){
		if(validateOnlyLetter('sur_name', 'lbl1', 'cognome_txt_error', 20)){
			if(validateEmail('email', 'lbl2', 'mail_err')){
				if(validatePassword('pwd', 'lbl3', 'pwd_err')){		
					flag = true;
				}
			}
		}
	}

	if(flag){
		return true;
	}else{
		alert("Ci stanno uno o piu' campi che presentano degli errori!! Non si puo' proseguire");
		event.preventDefault();	//utilizzo per bloccare invio submit anche se ritorna false
		return false;
	}
}

//funzioni per validare form

/*  Funzione che valuta i campi di un form che contengono solo caratteri letterali
 *  field e' la variabile che e' stata associata all'elemento 'input' del form
 *  I campi testati sono: nome, cognome, luogoNascita, 
*/ 
function validateOnlyLetter(field, label, id_err, maxlenght){
	var x = document.getElementById(field);
	var d = document.getElementById(label);
	var err = document.getElementById(id_err);
	var re = /^[A-Za-z ]+$/;
	var val = false;
	if(x.value == ""){ //errore campo vuoto
		d.className = "";
		styleForErrorTextInput(x, d);
		err.innerHTML = "Campo obbligatorio";
	}else if(x.value.length > maxlenght){ //codice errore per stringa troppo lunga
		styleForErrorTextInput(x, d);
		err.innerHTML = "Valore troppo lungo!! (max " + maxlenght + " caratteri)";
	}else if(x.value.match(re)){ //la stringa è conforme all'espressione regolare
		err.innerHTML = "";
		styleValidTextInput(x, d);
		val = true;
	}else{
		styleForErrorTextInput(x, d);
		err.innerHTML = "Valore inserito non valido!! Sono ammesse solo lettere dell'alfabeto";
	}
	return val;
}

function validatePassword(field, label, id_err){
	var x = document.getElementById(field);
	var d = document.getElementById(label);
	var err = document.getElementById(id_err);
	var re = /^[A-Za-z0-9]{6,20}$/;
	var val = false;
	if(x.value == ""){ //errore campo vuoto
		d.className = "";
		styleForErrorTextInput(x, d);
		err.innerHTML = "Campo obbligatorio";
	}else if(x.value.length < 6 || x.value.lenght > 20 ){ //codice errore per stringa troppo lunga
		styleForErrorTextInput(x, d);
		err.innerHTML = "Password deve essere compreso tra 6 e 20 caratteri";
	}else if(x.value.match(re)){ //la stringa è conforme all'espressione regolare
		err.innerHTML = "";
		styleValidTextInput(x, d);
		val = true;
	}else{
		styleForErrorTextInput(x, d);
		err.innerHTML = "Input non valido!! Può contenere solo lettere e numeri";
	}
	return val;
}

function validateEmail(field, label, id_err){
	var x = document.getElementById(field);
	var d = document.getElementById(label);
	var err = document.getElementById(id_err);
	var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	var val = false;
	if(x.value == ""){ //errore campo vuoto
		d.className = "";
		styleForErrorTextInput(x, d);
		err.innerHTML = "Campo obbligatorio";
	}else if(x.value.length > 50 ){ //codice errore per stringa troppo lunga
		styleForErrorTextInput(x, d);
		err.innerHTML = "Email non valida!! Troppo lunga (max 50 caratteri)";
	}else if(x.value.match(re)){ //la stringa è conforme all'espressione regolare
		err.innerHTML = "";
		styleValidTextInput(x, d);
		val = true;
	}else{
		styleForErrorTextInput(x, d);
		err.innerHTML = "Email non valida!! Es. email.test@example.com";
	}
	return val;
}

function styleForErrorTextInput(field, label){
	label.style.color = "#F44336";
	field.style.boxShadow = "0 1px 0 0 #F44336";
	field.style.borderBottomColor = "#F44336";
}

function styleValidTextInput(field, label){
	label.style.color = "#00FF00";
	field.style.boxShadow = "0 1px 0 0 #00FF00";
	field.style.borderBottomColor = "#00FF00";
}

function restoreStyleField(field, label){
	label.style.color = "#9e9e9e";
	field.style.boxShadow = "0 1px 0 0 #9e9e9e";
	field.style.borderBottomColor = "#9e9e9e";
}

//non usata (inserita per possibile utilizzo futuro)
function validateDate(item, err){
	var x = item.val();
	var val = false;
	if(!Date.parse(x)){
		//errore campo vuoto
		item.css("border","1px solid red");
		err.html("Campo obbligatorio");
	}else{
		item.css("border","1px solid black");
		err.html("");
		val = true;
	}
	return val;
}
