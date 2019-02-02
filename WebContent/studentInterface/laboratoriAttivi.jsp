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
	<%Collection<?> laboratori = (Collection<?>) request.getAttribute("laboratori");
	Iterator<?> it = laboratori.iterator();
    while (it.hasNext()) {
    	Laboratorio lab=(Laboratorio) it.next();
    	if(lab.isStato()){%>
    	
    	<!-- link per la pagina di prenotazione -->
	    <a href=""></a><div class="card">
		  <div class="card-header">
		  	<input type="hidden" value="<%= lab.getIDlaboratorio()%>">
		    Laboratorio <%=lab.getNome() %>
		  </div>
		  <div class="row">
			  <div class="col-md-6">
			    <label class="card-text">Apertura: <%=lab.getApertura() %></label>
			  </div>
			  <div class="col-md-6">
			    <label class="card-text">Chiusura: <%=lab.getChiusura() %></label>
			  </div>
		  </div></a>
		</div>
    	
    	<%
    	}
    }
	%>
</div>
</body>
</html>