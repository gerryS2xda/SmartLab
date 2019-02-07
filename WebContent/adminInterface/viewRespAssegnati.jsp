<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="dataAccess.storage.bean.Addetto, java.util.Collection, java.util.Iterator, dataAccess.storage.bean.Utente"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Responsabili Assegnati</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="script/jquery-3.3.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<% 
		Utente ut = (Utente) session.getAttribute("user");
		String userType = (String) session.getAttribute("userType");
		if(ut == null || userType == null) {
			response.sendRedirect("./error.jsp"); //pagina errore 404
		}else if(userType.equals("admin")){	
	%>
<body>
<!-- navbar -->
<%@include file="navbarWebContent.jsp" %>
<div class="container">
<h5  class="text-center"><%=request.getAttribute("nome") %></h5><!-- nome laboratorio -->
<%Collection<?> responsabili = (Collection<?>) request.getAttribute("responsabili");
	if(!responsabili.isEmpty()){
	Iterator<?> it = responsabili.iterator();
    while (it.hasNext()) {
    	Addetto resp=(Addetto) it.next();%>

	    <div class="card">
		  <div class="card-header">
		  	<input type="hidden" value="<%=request.getAttribute("id") %>" id="id">
		    Email <span id="email"><%=resp.getEmail() %></span>
		  </div>
		  <div class="row card-body">
			  <div class="col-md-6 text-center">
			    <label class="card-text">Nome: <%=resp.getName() %></label>
			  </div>
			  <div class="col-md-6 text-center">
			    <label class="card-text">Cognome: <%=resp.getSurname() %></label>
			  </div>
		  </div>
		  <div class="row card-body">
     			<div class="col text-center">
			  		<button id="elimina" type="button" class="btn btn-danger" data-toggle="modal" data-target="#confermaModal">
			  			Rimuovi
			  		</button>
		  		</div>
	  		</div>
		</div>
    	
    	<%
    	}
	}else{
	%>
		<h6>Non ci sono responsabili assegnati</h6>
	<%} %>
</div>

<!-- modal  conferma eliminazione del responsabile dal laboratorio-->
<div class="modal fade" id="confermaModal" tabindex="-1" role="dialog" aria-labelledby="confermaModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="confermaModalLabel">Vuoi rimuovere il responsabile dal laboratorio?</h5>
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
        <button type="button" class="btn btn-danger" id="confermaElimina" data-dismiss="modal">Rimuovi</button>
      </div>
    </div>
  </div>
</div>

<!-- messaggio esito eliminazione del laboratorio-->
<div class="alert alert-success" id="success-alert" style="display:none">
</div>

<script>
$(document).ready(function(){
	var labid;
	var respid;
	var scheda;
	$("button#elimina").on("click",function(){
		scheda=$(this).parent().parent().parent();
		div=scheda.find("div.card-header");
		console.log(div.find("input#id").val());
		console.log(div.find("span#email").text());
		labid=div.find("input#id").val();
		respid=div.find("span#email").text();
	});
	
	$("button#confermaElimina").on("click",function(){
		$.getJSON("assegnamento",{
			action: "rimuovi_ass",
			idlaboratorio: labid,
			idresponsabile: respid
		},function(data,status){
			scheda.remove();//rimuovo la scheda dalla grafica
			labid="";
			respid="";
			//messaggio esito
			var mex=data.esito;
			$("#success-alert").css("display","block");
			$("#success-alert").html("");
			$("#success-alert").append($("<strong>"+mex+"</strong>"));
			$("#success-alert").css("position","fixed");
			$("#success-alert").css("top","0");
			$("#success-alert").css("width","100%");
			setTimeout(function() {
				$("#success-alert").css("display","none");
		        //$("#success-alert").alert('close');
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