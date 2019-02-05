<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dataAccess.storage.bean.Utente, dataAccess.storage.bean.Addetto, java.util.Collection, java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aggiungi responsabile</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="script/jquery-3.3.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="navbar.jsp" %>

<form>
  <div class="form-group">
    <label for="InputEmail1">Email</label>
    <input type="email" class="form-control" id="inputEmail" aria-describedby="emailHelp" placeholder="Inserisci email">
  </div>
  <div class="form-group">
    <label for="InputPassword1">Password</label>
    <input type="password" class="form-control" id="InputPassword1" placeholder="Password">
  </div>
 <div class="form-group">
    <label for="InputPassword2">Conferma Password</label>
    <input type="password" class="form-control" id="InputPassword2" placeholder="Password">
  </div>
   <div class="form-group">
    <label for="InputNome">Nome</label>
    <input type="text" class="form-control" id="InputNome" placeholder="Nome">
  </div>
   <div class="form-group">
    <label for="InputPassword1">Cognome</label>
    <input type="text" class="form-control" id="InputCognome" placeholder="Cognome">
  </div>
   <button type="submit" class="btn btn-primary" id="sospendi">Aggiungi</button>
</form>

<!-- modal conferma aggiunta-->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Vuoi aggiungere il responsabile?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
        <button type="button" class="btn btn-primary" id="confermaAggiungi" data-dismiss="modal">Aggiungi</button>
      </div>
    </div>
  </div>
</div>

<div class="alert alert-success" id="success-alert" style="display:none">
</div>

<script>
$(document).ready(function(){
	
	var email;
	var div;
	var password1;
	var password2;
	var nome;
	var cognome;
	//seleziono il responsabile da eliminare
	$("button#sospendi").on("click",function(){
		email=$(this).find("input#InputEmail").val();
		password1=$(this).find("input#InputPassword1").val();
		password2=$(this).find("input#InputPassword2").val();
		if(password1 != password2) alert("Le password non combaciano");
		nome=$(this).find("input#InputNome").val();
		cognome=$(this).find("input#InputCognome").val();
		div=$(this).parent().parent().parent();//seleziono la scheda card
		
	});
	//ajax eliminazione del laboratorio + messaggio di conferma
	$("button#confermaAggiungi").on("click",function(){
		motivazione=$(this).find("textarea#message-text").val();
		console.log(email);	
		$.getJSON("addetto",{
			action: "addResp",
			email: email,
			password: password2,
			nome: nome,
			cognome: cognome
		},function(data,status){
			div.remove();//rimuovo la scheda dalla grafica
			email="";
			password1="";
			password2="";
			nome="";
			cognome="";
			var mex=data.esito;
			$("#success-alert").css("display","block");
			$("#success-alert").html("");
			$("#success-alert").append($("<strong>"+mex+"</strong>"));
			$("#success-alert").css("position","fixed");
			$("#success-alert").css("top","0");
			$("#success-alert").css("width","100%");
			setTimeout(function() {
				$("#success-alert").css("display","none");
		    }, 2000);
		});
	});
});
</script>
</body>
</html>