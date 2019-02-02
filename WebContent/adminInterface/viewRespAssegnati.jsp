<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="dataAccess.storage.bean.Addetto, java.util.Collection, java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Responsabili Assegnati</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="script/jquery-3.3.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
<h5><%=request.getAttribute("nome") %></h5><!-- nome laboratorio -->
<%Collection<?> responsabili = (Collection<?>) request.getAttribute("responsabili");
	Iterator<?> it = responsabili.iterator();
    while (it.hasNext()) {
    	Addetto resp=(Addetto) it.next();%>

	    <div class="card">
		  <div class="card-header">
		  	<input type="hidden" value="<%=request.getAttribute("id") %>" id="id">
		    Email <%=resp.getEmail() %> 
		  </div>
		  <div class="row">
			  <div class="col-md-6">
			    <label class="card-text">Nome: <%=resp.getName() %></label>
			  </div>
			  <div class="col-md-6">
			    <label class="card-text">Cognome: <%=resp.getSurname() %></label>
			  </div>
		  </div>
		  <div class="row">
     			<div class="col text-center">
			  		<button id="elimina" type="button" class="btn btn-primary" data-toggle="modal" data-target="#confermaModal">
			  			elimina
			  		</button>
		  		</div>
	  		</div>
		</div>
    	
    	<%
    }
	%>
</div>

<!-- modal  conferma eliminazione del laboratorio-->
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
        <button type="button" class="btn btn-primary" id="confermaElimina" data-dismiss="modal">Conferma</button>
      </div>
    </div>
  </div>
</div>

<!-- messaggio esito eliminazione del laboratorio-->
<div class="alert alert-success" id="success-alert" style="display:none">
</div>

</body>
</html>