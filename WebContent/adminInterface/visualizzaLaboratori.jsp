<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dataAccess.storage.bean.Laboratorio, java.util.Collection, java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista Laboratori</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="script/jquery-3.3.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<%Collection<?> laboratori = (Collection<?>) request.getAttribute("laboratori");
	Iterator<?> it = laboratori.iterator();
    while (it.hasNext()) {
    	Laboratorio lab=(Laboratorio) it.next();%>

	    <div class="card">
		  <div class="card-header">
		    Laboratorio <%=lab.getNome() %>
		  </div>
		  <div class="row">
			  <div class="col-md-6">
			    <label class="card-text">Stato: <%=lab.isStato() %></label>
			  </div>
			  <div class="col-md-6">
			    <label class="card-text">Stato: <%=lab.isStato() %></label>
			  </div>
			  <div class="col-md-6">
			    <label class="card-text">Apertura: <%=lab.getApertura() %></label>
			  </div>
			  <div class="col-md-6">
			    <label class="card-text">Chiusura: <%=lab.getChiusura() %></label>
			  </div>
		  </div>
		  <div class="row">
     			<div class="col text-center">
			  		<button id="elimina" type="button" class="btn btn-primary" data-toggle="modal" data-target="#confermaModal">
			  			elimina
			  			<input type="hidden" id="id" value="<%= lab.getIDlaboratorio() %>">
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
        <h5 class="modal-title" id="confermaModalLabel">Vuoi eliminare il laboratorio?</h5>
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
        <a class="btn btn-primary" id="elimina">Conferma</a>
      </div>
    </div>
  </div>
</div>

<script>
$(document).ready(function(){
	$("button#elimina").on("click",function(){
		var id=$(this).find("input#id").val();
		$("a#elimina").attr("href", "laboratorio?action=rimuovi_lab&idlaboratorio="+id)
	});
});
</script>
</body>
</html>
