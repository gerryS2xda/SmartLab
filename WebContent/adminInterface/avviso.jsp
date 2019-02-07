<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import = "dataAccess.storage.bean.Utente"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Avviso</title>
	</head>
	<% 
		Utente ut = (Utente) session.getAttribute("user");
		String userType = (String) session.getAttribute("userType");
		if(ut == null || userType == null) {
			response.sendRedirect("./error.jsp"); //pagina errore 404
		}else if(userType.equals("addetto")){	
	%>
	<body>
		<h1 id = "title"></h1><br>
		<div id = "message"></div>
		<span id = "date"></span>
		<span id = "creator"></span>
		<%
			}else{
				response.sendRedirect("./error.jsp"); //pagina errore 404
			}%>
	</body>
		<script type="text/javascript" src="../script/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="../script/avviso_js.js"></script>
</html>