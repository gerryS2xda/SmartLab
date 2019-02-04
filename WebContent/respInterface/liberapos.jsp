<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LIBERA POSTAZIONE</title>
</head>
<body>
<div id="cerca">
	<form id="lib_post_occ">
	Inserisci l'ID del laboratorio: <input type="text" name="lab_in" id="lab_in" value="">
	<select name="fascia_oraria_action" id="fasc_ora_lib_pos" onChange="verifyPostazioneAvailable($(this));">
		<option value="09:00-11:00">Mattina (9 - 11)</option>
		<option value="11:00-13:00">Mattina (11 - 13)</option>
		<option value="13:00-15:00">Pomeriggio (13 - 15)</option>
		<option value="15:00-17:00">Pomeriggio (15 - 17)</option>
		<option value="17:00-19:00">Sera (17 - 19)</option>
	</select>
	<button type="button" id="libera_btn">Avvia ricerca</button>
</form>
</div>            
<script>
	
$("#libera_btn").click(function(){
	var x = $("#fasc_ora_lib_pos").val().split("-");  //dammi il valore della <select> che e' stato impostato e dividi in due stringhe con split()
	var lab = $("#lab_in").val();
	//$.post("/postazioni", {"action": "libera_pos", "inizio": x[0], "fine": x[1], "lab": lab}, function(resp, stat, xhr){
		//if(xhr.readyState == 4 && stat == "success"){
			//codice del risultato
			//Tabella risultato
			$.getJSON("postazioni", {
				action: "libera_pos",
				lab: lab,
				inizio: x[0],
				fine: x[1]
			}, function(data,status){});
		//}else{
			//window.location.href = "./index.jsp"; //pagina errore 404
		//}
	});
	
</script>
</body>
</html>