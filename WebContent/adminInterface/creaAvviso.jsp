<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import = "dataAccess.storage.bean.Utente"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Creazione avviso</title>
		<link type="text/css" rel="stylesheet" href="../css/avviso_style.css">
		<!--<link rel="stylesheet" href="../bootstrap/css/bootstrap.min.css">
		<script src="../script/jquery-3.3.1.min.js"></script>
		<script src="../bootstrap/js/bootstrap.min.js"></script>-->
	</head>
	<% 
		Utente ut = (Utente) session.getAttribute("user");
		String userType = (String) session.getAttribute("userType");
		if(ut == null || userType == null) {
			response.sendRedirect("./error.jsp"); //pagina errore 404
		}else if(userType.equals("addetto")){
	%>
	<body>
		<header id="header_part">
			<!--  add nav bar jsp -->
			<span id="txt_logo">Admin Area</span>
			<span id="welcome_txt">Benvenuto, Addetto</span>
			<span id="logout"><img src="../images/icon-logout.png">Logout</span>
		</header>
		<section id="left_sidebar">
			<!-- add navigation bar left jsp -->
			<div id="sidebar">
				<ul>
					<li><a href="StudentHomePage.jsp">Dashboard</a></li>
					<li><a href="laboratoriAttivi.jsp">Prenota postazione</a></li>
					<li> <a href="PrenotazioniEffettuate.jsp">Mie prenotazioni</a></li>
					<li class="active"> <a href="javascript:void(0);">Crea avviso</a></li>
				</ul>
			</div>
		</section>
		<section id = "main">
			<h1 align = "center">Avviso</h1>
				<h2 align = "center">Inserire le informazioni riguardo l'avviso qui sotto</h2>
				<div id = "body">
					<div align = "center" id = "oggAvviso"> Inserire l'oggetto dell'avviso: <textarea class="" rows = "1" cols = "50" id = "titolo"></textarea></div>
					<div align = "center" id = "desAvviso"> Inserire l'avviso: </div>
					<div align = "center"><textarea rows = "10" cols = "100" id = "descrizione"></textarea></div>
					<div align = "center"><button id = "crea">Crea</button></div>
				</div>
		</section>
		<section id="right_sidebar"></section>
		<footer>
			<!-- add footer jsp -->
		</footer>
		<%
			}else{
				response.sendRedirect("./error.jsp"); //pagina errore 404
			}%>
		<script type="text/javascript" src="../script/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="../script/avviso_js.js"></script>
	</body>
</html>