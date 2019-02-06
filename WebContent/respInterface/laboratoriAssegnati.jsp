<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dataAccess.storage.bean.Laboratorio, java.util.Collection, java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Laboratori</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="script/jquery-3.3.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container"> 
	<h5 class="text-center">Laboratori</h5>
	<%Collection<?> laboratori = (Collection<?>) request.getAttribute("laboratori");
	if(laboratori!=null){
	Iterator<?> it = laboratori.iterator();
    while (it.hasNext()) {
    	Laboratorio lab=(Laboratorio) it.next();%>
    	
    	<!-- link per la pagina di prenotazione -->
	    <div class="card">
		  <div class="row card-body">
		  	<div class="col-md-3 text-center">
		    	<label class="card-text">Laboratorio <span><%=lab.getNome() %><span></span></label>
			  </div>
			  <div class="col-md-3 text-center">
			    <label class="card-text">Apertura: <%=lab.getApertura() %></label>
			  </div>
			  <div class="col-md-3 text-center">
			    <label class="card-text">Chiusura: <%=lab.getChiusura() %></label> 
			  </div>
			  <div class="col-md-3 text-center">
			    <a href="postazioni?action=lista_pos&idlaboratorio=<%=lab.getIDlaboratorio()%>" class="btn btn-primary">Postazioni</a>
			  </div>
		  </div>
		</div>
    	
    	<%
    	}
	}else{
	%>
	<h3 class="text-center">Non ci sono laboratori assegnati</h3>
	<%} %>
</div>
</body>
</html>