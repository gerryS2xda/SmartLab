<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>LIBERA POSTAZIONE</title>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
<script src="../script/jquery-3.3.1.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div id="cerca">
	<form id="lib_post_occ">
	Inserisci l'ID del laboratorio: <input type="text" name="lab_in" id="lab_in" value="">
	<select name="fascia_oraria_action" id="fasc_ora_lib_pos" >
		<option value="09:00-11:00">Mattina (9 - 11)</option>
		<option value="11:00-13:00">Mattina (11 - 13)</option>
		<option value="13:00-15:00">Pomeriggio (13 - 15)</option>
		<option value="15:00-17:00">Pomeriggio (15 - 17)</option>
		<option value="17:00-19:00">Sera (17 - 19)</option>
	</select>
	<button type="button" id="libera_btn">Avvia ricerca</button>
</form>
</div>        


<!-- modal per conferma libera postazione -->
<div class="modal fade" id="exampleModaldisattiva" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">Confermi di voler liberare la postazi0ne?</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				        <form>
				          <div class="form-group">
				            <label for="message-text" class="col-form-label">Inserisci motivazione (facoltativo):</label>
				            <textarea class="form-control" id="message-text"></textarea>
				          </div>
				        </form>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
				        <button type="button" class="btn btn-primary" id="conferma">Conferma</button>
				      </div>
				    </div>
				  </div>
				</div>
    <!-- ___________________________________ -->
    <script>
$(document).ready(function()
{
	$("button#libera_btn").click(function()
	{
	console.log("asd");
	
	var x = $("#fasc_ora_lib_pos").val().split("-");  //dammi il valore della <select> che e' stato impostato e dividi in due stringhe con split()
	var lab = $("#lab_in").val();
	//$.post("/postazioni", {"action": "libera_pos", "inizio": x[0], "fine": x[1], "lab": lab}, function(resp, stat, xhr){
		//if(xhr.readyState == 4 && stat == "success"){
			//codice del risultato
			//Tabella risultato
			$.post("postazioni", 
			{
				action: "libera_pos",
				lab: lab,
				inizio: x[0],
				fine: x[1]
			}, function(data,status)
				{
				//lista di prenotazioni
				
					var o = JSON.parse(data);
					var size = sizeObject(o); //calcolo del numero di proprieta' presenti nell'oggetto
					var str = ""; //stringa che contiene codice HTML per la costruzione del contenuto
					for(var i=0; i < size; i++){
						var k = o["pren"+i]; //prendi l'oggetto JS associato alla proprieta' 'pren' dell'oggetto JS appena convertito 
						str+= "<div class=\"row\">" +
		                "<div class=\"col-sm-6\"><div class=\"card\"><div class=\"card-body\">"
	                        "<h4 class=\"card-title\">prenotazione n:"+k.id+
	                        "</h4><p>ora di inizio:</p>"+k.oraInizio+
	                        "<p>ora di fine:</p>" + k.orafine+ "</div></div></div></div>";
					}
					$("div#cerca").append(str);
				console.log(data);
				},"json");
	});
	var mot=$("#message-text").val();
	$.getJSON("postazioni",
		{
		action:"intervento",
		inter: mot
		},function(data,status)
		{
			
		});
});	
		//}else{
			//window.location.href = "./index.jsp"; //pagina errore 404
		//}
		function sizeObject(obj) {
	var size = 0, key;
	for (key in obj) {
		if (obj.hasOwnProperty(key)) size++;
	}
	return size;
};
	
	
</script>

</body>
</html>