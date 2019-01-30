<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dataAccess.storage.bean.Laboratorio, java.util.Collection, java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista Laboratori</title>
<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
<script src="../script/jquery-3.3.1.min.js"></script>
<script src="../bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="background-color:red">
	<table class="table">
	  <thead class="thead-dark">
	    <tr>
	      <th scope="col">nome</th>
	      <th scope="col">posti</th>
	      <th scope="col">stato</th>
	      <th scope="col">Apertura</th>
	      <th scope="col">Chiusura</th>
	    </tr>
	  </thead>
	  <tbody>
		<%Collection<?> laboratori = (Collection<?>) request.getAttribute("laboratori");
		Iterator<?> it = laboratori.iterator();
	    while (it.hasNext()) {
	    	Laboratorio lab=(Laboratorio) it.next();%>
	    	
	    	<tr>
		      <th scope="row"><%=lab.getNome() %></th>
		      <td><%=lab.getPosti() %></td>
		      <td><%=lab.isStato() %></td>
		      <td><%=lab.getApertura() %></td>
		      <td><%=lab.getChiusura() %></td>
		    </tr>
	    	
	    	<%
	    }
		%>
		</tbody>
	</table>
</div>
</body>
</html>