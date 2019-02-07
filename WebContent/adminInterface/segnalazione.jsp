<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import = "dataAccess.storage.bean.Utente"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
		<script src="../script/jquery-3.3.1.min.js"></script>
		<script src="../bootstrap/js/bootstrap.min.js"></script>
		<title>Segnalazione</title>
	</head>
		<% 
		Utente ut = (Utente) session.getAttribute("user");
		String userType = (String) session.getAttribute("userType");
		if(ut == null || userType == null) {
			response.sendRedirect("./error.jsp"); //pagina errore 404
		}else if(userType.equals("addetto")){
	%>
	<body>
	<%@include file="navbar.jsp" %>
		<span id = "data"></span>
		<h1 id = "oggetto"></h1>
		<div id = "descrizione"></div>
		<span id = "lab"></span><span id = "pos"></span>
		<%
			}else{
				response.sendRedirect("./error.jsp"); //pagina errore 404
			}%>
	</body>
		<script type="text/javascript" src="../script/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="../script/segnalazione_js.js"></script>
</html>