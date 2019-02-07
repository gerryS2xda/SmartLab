<%@ page language="java" import="java.io.*"  contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isErrorPage="true"  %>
<!DOCTYPE HTML>
<html>
	<head>
		<link type="text/css" rel="stylesheet" href="css/error_style.css">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>Error 404</title>
		<link rel="icon" href="./images/error_404_icon.jpg" type="image/jpg" />
	</head>
	<body onLoad="loadFunctionsBody()">
		<div class="center">
			<img src="images/error.png" alt=":(">
			<h1>404: Pagina non trovata</h1>
			<p>La pagina che stavi cercando di raggiungere non esiste oppure non è al momento disponibile. <br>
			<a href="Login.jsp">Vai alla pagina di Login</a>, se il problema persiste contattare l'amministratore.</p>
		</div>
		<script type="text/javascript" src="./script/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="./script/error_page_script.js"></script>
	</body>
</html>