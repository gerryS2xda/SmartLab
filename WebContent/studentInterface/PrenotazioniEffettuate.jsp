<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import ="java.time.LocalDate, dataAccess.storage.bean.Utente" pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Mie prenotazioni</title>
			<link type="text/css" rel="stylesheet" href="../css/prenotazione_style.css">
			<link type="text/css" rel="stylesheet" href="../css/pren_effettuate_style.css">
	</head>
	<% 
		Utente ut = (Utente) session.getAttribute("user");
		String userType = (String) session.getAttribute("userType");
		if(ut == null || userType == null) {
			response.sendRedirect("./error.jsp"); //pagina errore 404
		}else if(userType.equals("studente")){	
	%>
	<body onLoad="loadContent()">
		<header id="header_part">
			<!--  add nav bar jsp -->
			<span id="txt_logo">User Area</span>
			<span id="welcome_txt">Benvenuto, <%=ut.getName() %></span>
			<span id="logout"><img src="../images/icon-logout.png">Logout</span>
		</header>
		<section id="left_sidebar">
			<!-- add navigation bar left jsp -->
			<div id="sidebar">
				<ul>
					<li><a href="StudentHomePage.jsp">Dashboard</a></li>
					<li><a href="laboratoriAttivi.jsp">Prenota postazione</a></li>
					<li class="active"> <a href="PrenotazioniEffettuate.jsp">Mie prenotazioni</a></li>
					<li> <a href="creaSegnalazione.jsp">Segnala problema</a></li>
					<li> <a href="viewSegnalazioni.jsp">Lista segnalazioni</a></li>
					<li> <a href="viewAvvisi.jsp">Lista avvisi</a></li>
				</ul>
			</div>
		</section>
		<section id="main_content">
			<div class="main_content_margin">
				<h1 class="title_page">Mie prenotazioni</h1>
				<div class="text_content">
					<p class="text_suggest"> 
						Ecco le tue prenotazioni che sono state effettuate. Per annullare una prenotazione, 
						mettere la spunta sulla checkbox. Se una prenotazione è attiva, viene richiesto di confermare l'annullamento
					</p>
				</div>
				<div id="div_tb_effettuate_content">
					<div class="table_scroll_eff">
						<table id="tb_pren_effettuate" class="table_data">
							<thead>
								<tr>
									<th> N.ro prenotazione </th>
									<th> Data </th>
									<th> Laboratorio </th>
									<th> Postazione </th>
									<th> Fascia oraria </th>
									<th> </th>
								</tr>
							</thead>
							<tbody></tbody>
						</table>
					</div>
				</div>
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
		<script type="text/javascript" src="../script/pren_eff_script.js"></script>
		<script type="text/javascript" src="../script/student_pages.js"></script>
	</body>
</html>