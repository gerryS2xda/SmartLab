<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="dataAccess.storage.bean.Utente, java.util.Collection, java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Responsabili Assegnati</title>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
<script src="../script/jquery-3.3.1.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>

<%Collection<?> responsabili = (Collection<?>) request.getAttribute("responsabili");
	Iterator<?> it = responsabili.iterator();
    while (it.hasNext()) {
    	Utente resp=(Utente) it.next();%>

	    <div class="card">
		  <div class="card-header">
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

</body>
</html>