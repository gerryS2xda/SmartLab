<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dataAccess.storage.bean.*, java.util.Collection, java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sospendi Account</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="script/jquery-3.3.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<%Collection<Utente> utenti = (Collection<Utente>) request.getAttribute("utenti");
	Iterator<Utente> it = utenti.iterator();
	 while (it.hasNext()) {
	    	Studente s = (Studente) it.next();%>

		    <div class="card">
		  		<div class="card-header" id="mycard" style="cursor: pointer">
		  			<input type="hidden" id="email" value="<%= s.getEmail() %>">
			    Email: <%= s.getEmail() %>
			  </div>
			  <div class="row">
				  <div class="col-md-6">
				    <label class="card-text">Nome: <%=s.getName() %> Cognome: <%= s.getSurname() %></label>
				  </div>
				  <div class="col-md-6">
				    <label class="card-text">Stato: <%=s.getStato() %></label>
				  </div>
			  </div>
			  <div class="row">
	     			<div class="col text-center">
				  		<button id="sospendi" type="button" class="btn btn-primary" data-toggle="modal" data-target="#confermaModal">
				  			sospendi
				  			<input type="hidden" id="email" value="<%= s.getEmail() %>">
				  		</button>
			  		</div>
		  		</div>
			</div>
	    	
	    	<%
	    }
		%>
</div>
<!-- modal -->
<div class="modal fade" id="confermaModal" tabindex="-1" role="dialog" aria-labelledby="confermaModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="confermaModalLabel">Vuoi sospendere lo studente?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <!-- 
      <div class="modal-body">
        ...
      </div> -->
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
        <button type="button" class="btn btn-primary" id="confermaSospendi" data-dismiss="modal">Conferma</button>
      </div>
    </div>
  </div>
</div>
<!-- messaggio esito -->
<div class="alert alert-success" id="success-alert" style="display:none">
</div>

<script>
$(document).ready(function(){
	
	var id;
	var div;
	$("button#sospendi").on("click",function(){
		email=$(this).find("input#email").val();
		div=$(this).parent().parent().parent();
		
	});
	$("button#confermaSospendi").on("click",function(){
		console.log(email);
		
		$.getJSON("utente",{
			action: "effettuaSospensione",
			emailStud: email
		},function(data,status){
			div.remove();
			email="";
			var mex=data.esito;
			$("#success-alert").css("display","block");
			$("#success-alert").append($("<strong>"+mex+"</strong>"));
			setTimeout(function() {
				$("#success-alert").css("display","none");
		    }, 2000);
		});
	});
});
</script>

</body>
</html>