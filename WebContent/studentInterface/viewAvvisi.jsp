<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import = "dataAccess.storage.bean.Utente"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link type="text/css" rel="stylesheet" href="../css/segnalazione_style.css">
		<title>Lista avvisi</title>
	</head>
	<% 
		Utente ut = (Utente) session.getAttribute("user");
		String userType = (String) session.getAttribute("userType");
		if(ut == null || userType == null) {
			response.sendRedirect("./error.jsp"); //pagina errore 404
		}else if(userType.equals("studente")){	
	%>
	<body onload = "loadAvvisi()">
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
					<li> <a href="creaSegnalaizone.jsp">Segnala problema</a></li>
					<li> <a href="viewSegnalazioni.jsp">Lista segnalazioni</a></li>
					<li class = "active"> <a href="javascript:void(0);">Lista avvisi</a></li>
				</ul>
			</div>
		</section>
		<section id = "main_content">
			<div id = "div_avvisi_table">
				<table id = "tb_avvisi" border = "1">
					<thead>
						<tr>
							<th>Id</th>
							<th>Oggetto</th>
							<th>Descrizione</th>
							<th>Data</th>
							<th>Addetto</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
		</section>
		<%
			}else{
				response.sendRedirect("./error.jsp"); //pagina errore 404
			}%>
	</body>
		<script type="text/javascript" src="../script/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="../script/avviso_js.js"></script>
</html>