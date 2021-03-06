<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import = "dataAccess.storage.bean.Utente"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title> Segnalazione problema </title>
		<link type="text/css" rel="stylesheet" href="../css/segnalazione_style.css">
	</head>
	<% 
		Utente ut = (Utente) session.getAttribute("user");
		String userType = (String) session.getAttribute("userType");
		if(ut == null || userType == null) {
			response.sendRedirect("./error.jsp"); //pagina errore 404
		}else if(userType.equals("studente")){
	%>
	<body>
		<header id="header_part">
			<!--  add nav bar jsp -->
			<span id="txt_logo">User Area</span>
			<span id="welcome_txt">Benvenuto, Studente</span>
			<span id="logout"><img src="../images/icon-logout.png">Logout</span>
		</header>
		<section id="left_sidebar">
			<!-- add navigation bar left jsp -->
			<div id="sidebar">
				<ul>
					<li><a href="StudentHomePage.jsp">Dashboard</a></li>
					<li><a href="laboratoriAttivi.jsp">Prenota postazione</a></li>
					<li> <a href="PrenotazioniEffettuate.jsp">Mie prenotazioni</a></li>
					<li class="active"> <a href="javascript:void(0);">Segnala problema</a></li>
					<li> <a href="viewSegnalazioni.jsp">Lista segnalazioni</a></li>
					<li> <a href="viewAvvisi.jsp">Lista avvisi</a></li>
				</ul>
			</div>
		</section>
		<section id = "main_content">
			<h1 class="title_page"> Segnala un problema </h1>
			<h2 align = "center"> Immettere i dati qui sotto </h2>
			<div id = "body">
				<div id = "labInfo">
					<div align = "center" id = "sceltaLab"> Scegliere il laboratorio: 
						<input list = "Laboratori" name = "NomeLab"> 
						<datalist id = "Laboratori"> 
							<option value = "SesaLab"> 
							<option value = "Lab sistemi"> 
						</datalist> 
					</div>
					<div align = "center" id = "sceltaPos"> Numero della postazione: <textarea rows = "1" cols = "5" id = "NumPos"></textarea> </div>
				</div>
				<div align = "center" id = "insOgg">Inserire l'oggetto: <textarea rows = "1" cols = "50" id = "oggetto"></textarea></div>
			    <div align = "center" id = "insDes"> Inserire il motivo della segnalazione: </div>
				<div align = "center"><textarea rows = "10" cols = "100" id = "descrizione"></textarea></div>
				<div align = "center"><button id = "button">Invia</button></div>
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
	</body>
		<script type="text/javascript" src="../script/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="../script/segnalazione_js.js"></script>
</html>