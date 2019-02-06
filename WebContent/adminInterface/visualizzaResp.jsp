<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dataAccess.storage.bean.Addetto, dataAccess.storage.bean.Utente, java.util.Collection, java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista Responsabili</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="script/jquery-3.3.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<%@include file="navbar.jsp" %>

<div class="container">
	<h5 class="text-center">Responsabili</h5>
	<% Collection<?> responsabili = (Collection<?>) request.getAttribute("responsabili");
	if(!responsabili.isEmpty()){
		Iterator<?> it = responsabili.iterator();
		while(it.hasNext()){
			Addetto a = (Addetto) it.next(); %>
			
			<div class="card">
			  <div class="card-header">
			  	<span>Responsabile</span>
			  	<input type="hidden" id="email" value="<%= a.getEmail() %>">
			   </div>
			  <div class="row card-body">
				  <div class="col-md-4 text-center">
				    <label class="card-text">Nome: <%=a.getName() %></label>
				  </div>
				  <div class="col-md-4 text-center">
				    <label class="card-text">Cognome: <%= a.getSurname() %></label>
				  </div>
				  <div class="col-md-4 text-center">
				    <label class="card-text">Email: <%=a.getEmail() %></label>
				  </div>
			  </div>
			  <div class="row card-body">
	     			<div class="col text-center">
				  		<button id="elimina" type="button" class="btn btn-danger" data-toggle="modal" data-target="#confermaModal">
				  			Rimuovi
				  			<input type="hidden" id="email" value="<%= a.getEmail() %>">
				  		</button>
			  		</div>
		  		</div>
			</div>
    	<%
	    }
    }else{ %>
    	<h3 class="text-center">Non ci sono responsabili registrati al sistema</h3>
    <%  }	%>
</div>

<!-- modal  conferma eliminazione del responsabile-->
<div class="modal fade" id="confermaModal" tabindex="-1" role="dialog" aria-labelledby="confermaModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="confermaModalLabel">Vuoi eliminare il responsabile?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <!-- 
      <div class="modal-body">
        ...
      </div> -->
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
        <button type="button" class="btn btn-danger" id="confermaElimina" data-dismiss="modal">Elimina</button>
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
	//seleziono il responsabile da eliminare
	$("button#elimina").on("click",function(){
		email=$(this).find("input#email").val();
		div=$(this).parent().parent().parent();//seleziono la scheda card
		
	});
	//ajax eliminazione del laboratorio + messaggio di conferma
	$("button#confermaElimina").on("click",function(){

		$.getJSON("addetto",{
			action: "rimuoviResp",
			email: email
		},function(data,status){
			div.remove();//rimuovo la scheda dalla grafica
			email="";
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