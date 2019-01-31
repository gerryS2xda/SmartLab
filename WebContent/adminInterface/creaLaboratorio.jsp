<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crea Laboratorio</title>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
<script src="../script/jquery-3.3.1.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<h4 class="text-center">Crea Laboratorio</h4>
	<form id="form">
		<input type="hidden" name="action" value="aggiungi_lab">
	  <div class="form-row">
	    <div class="form-group col-md-6">
	      <label for="nomeLaboratorio">Nome Laboratorio</label>
	      <input name="nome" type="text" class="form-control" placeholder="lab1">
	    </div>
	    <div class="form-group col-md-6">
	      <label for="posti">Posti</label>
	      <input name="posti" type="number" class="form-control" placeholder="20" min="0">
	    </div>
	  </div>
	  <div class="form-row">
		  <div class="form-group col-md-6">
		    <label for="oraApertura">Ora apertura</label>
		    <input name="apertura" type="time" class="form-control" placeholder="8:00">
		  </div>
		  <div class="form-group col-md-6">
		    <label for="oraChiusura">Ora chiusura</label>
		    <input name=chiusura type="time" class="form-control" placeholder="18:00">
		  </div>
	  </div>
	  <div class="row">
     <div class="col text-center">
	  <button id="submitButton" type="button" class="btn btn-primary" >Crea laboratorio</button>
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
		var posti=form.find("input[name=\"posti\"]");
		var apertura=form.find("input[name=\"apertura\"]");
		var chiusura=form.find("input[name=\"chiusura\"]")
		console.log("ok");
		$.getJSON("../laboratorio",{
			action:"aggiungi_lab",
			nome:nome.val(),
			posti:posti.val(),
			apertura:apertura.val(),
			chiusura:chiusura.val()
		},function(data,status){
			var mex=data.esito;
			$("#success-alert").css("display","block");
			$("#success-alert").html($("<strong>"+mex+"</strong>"));
			setTimeout(function() {
				$("#success-alert").css("display","none");
		        //$("#success-alert").alert('close');
		    }, 2000);
		});
	});
});
</script>
</body>
</html>