<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dataAccess.storage.bean.Sospensione, dataAccess.storage.bean.Utente, dataAccess.storage.bean.Studente, java.util.Collection, java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sospendi studente</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="script/jquery-3.3.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<% 
		Utente ut = (Utente) session.getAttribute("user");
		String userType = (String) session.getAttribute("userType");
		if(ut == null || userType == null) {
			response.sendRedirect("./error.jsp"); //pagina errore 404
		}else if(userType.equals("responsabile")){	
	%>
<body>
<!-- navbar -->
<%@include file="navbarWebContent.jsp" %>

<div class="container">
	<h5 class="text-center">Studenti</h5>
	<% Collection<?> studenti = (Collection<?>) request.getAttribute("studenti");
	if(!studenti.isEmpty()){
		Iterator<?> it = studenti.iterator();
		while(it.hasNext()){
			Studente s = (Studente) it.next();
			if(s.getStato()==false){
		%>
			
			<div class="card">
			  <div class="card-header">
			  	<span></span>
			  	<input type="hidden" value="<%= s.getEmail() %>">
			   </div>
			  <div class="row card-body">
				  <div class="col-md-4 text-center">
				    <label class="card-text">Nome: <%=s.getName() %></label>
				  </div>
				  <div class="col-md-4 text-center">
				    <label class="card-text">Cognome: <%= s.getSurname() %></label>
				  </div>
				  <div class="col-md-4 text-center">
				    <label class="card-text">Email: <%=s.getEmail() %></label>
				  </div>
			  </div>
			  <div class="row card-body">
	     			<div class="col text-center">
				  		<button id="sospendi" type="button" class="btn btn-danger" data-toggle="modal" data-target="#confermaModal">
				  			Sospendi
				  			<input type="hidden" id="email" value="<%= s.getEmail() %>">
				  		</button>
			  		</div>
		  		</div>
			</div>
    	<%
			} else { continue; }
	    }
    }else{
    	%>
    	<h3 class="text-center">Non ci sono studenti registrati al sistema</h3>
    	<%
    }
	%>
</div>

<div class="modal fade" id="confermaModal" tabindex="-1" role="dialog" aria-labelledby="confermaModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="confermaModalLabel">Vuoi sospendere lo studente?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form name="area">
          <div class="form-group">
            <label for="message-text" class="col-form-label">Motivazione:</label>
            <textarea class="form-control" id="motivazione"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
        <button type="button" class="btn btn-primary" id="confermaSospensione" data-dismiss="modal">Conferma</button>
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
	var motivazione;

	$("button#sospendi").on("click",function(){
		email=$(this).find("input#email").val();
		div=$(this).parent().parent().parent();//seleziono la scheda card
	});
	
	//ajax eliminazione del laboratorio + messaggio di conferma
	$("button#confermaSospensione").on("click",function(){
		motivazione=document.getElementById("motivazione").value;
		if(motivazione == "") alert("Inserisci motivazione");
		$.getJSON("utente",{
			action: "effettuaSospensione",
			emailStudente: email,
			motivazione: motivazione
		},function(data,status){
			div.remove();//rimuovo la scheda dalla grafica
			email="";
			motivazione="";
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
<%} else{
				response.sendRedirect("./error.jsp"); //pagina errore 404
			}%>
</html>