<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Aggiungi responsabile</title>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
<script src="../script/jquery-3.3.1.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="navbar.jsp" %>

<div class="container">
	<form id="form">
		<input type="hidden" name="action" value="aggiungi_resp">
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="nomeResp">Nome</label>
	      <input name="nome" type="text" class="form-control">
	    </div>
	   	<div class="form-group col-md-6">
	      <label for="cognomeResp">Cognome</label>
	      <input name="cognome" type="text" class="form-control">
	    </div>
	    <div class="form-group col-md-6">
	      <label for="emailResp">Email</label>
	      <input name="email" type="text" class="form-control">
	    </div>
	  </div>
	  <div class="form-row">
		  <div class="form-group col-md-6">
	      	<label for="passwordResp1">Password</label>
	      	<input name="password1" type="password" class="form-control">
	      </div>
		  <div class="form-group col-md-6">
	      	<label for="passwordResp2">Conferma Password</label>
	      	<input name="password2" type="password" class="form-control">
	      </div>
	  </div>
	  <div class="row">
     <div class="col text-center">
	  <button id="submitButton" type="button" class="btn btn-primary" >Aggiungi</button>
	  </div></div>
	</form>
</div>

<div class="alert alert-success" id="success-alert" style="display:none">
</div>

<script>
$(document).ready(function(){
	$("#submitButton").on("click",function(event){
		var form = $("form#form");
		console.log(form);
		console.log(form.find("input[name=\"nome\"]").val());
		console.log(form.find("input[name=\"posti\"]").val());
		console.log(form.find("input[name=\"apertura\"]").val());
		console.log(form.find("input[name=\"chiusura\"]").val());
		var nome=form.find("input[name=\"nome\"]");
		var cognome=form.find("input[name=\"cognome\"]");
		var email=form.find("input[name=\"email\"]");
		var password1=form.find("input[name=\"password1\"]");
		var password2=form.find("input[name=\"password2\"]");
		if (password1.val() != password2.val()) alert("Password non combaciano");
		console.log("ok");
		$.getJSON("../addetto",{
			action:"addResp",
			nome: nome.val(),
			cognome: cognome.val(),
			email: email.val(),
			password: password2.val()
		},function(data,status){
			var mex=data.esito;
			$("#success-alert").css("display","block");
			$("#success-alert").html($("<strong>"+mex+"</strong>"));
			setTimeout(function() {
				$("#success-alert").css("display","none");
		    }, 2000);
		});
	});
});
</script>
</body>
</html>