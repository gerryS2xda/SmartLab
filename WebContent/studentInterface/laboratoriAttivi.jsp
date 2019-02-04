<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="java.time.*, java.time.format.*, dataAccess.storage.bean.Utente" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Prenota postazione</title>
		<link type="text/css" rel="stylesheet" href="../css/prenotazione_style.css">
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
			<span id="welcome_txt">Benvenuto, Studente</span>
			<span id="logout"><img src="../images/icon-logout.png">Logout</span>
		</header>
		<section id="left_sidebar">
			<!-- add navigation bar left jsp -->
			<div id="sidebar">
				<ul>
					<li><a href="StudentHomePage.jsp">Dashboard</a></li>
					<li class="active"><a href="laboratoriAttivi.jsp">Prenota postazione</a></li>
					<li> <a href="PrenotazioniEffettuate.jsp">Mie prenotazioni</a></li>
					<li> <a href="creaSegnalazione.jsp">Segnala problema</a></li>
				</ul>
			</div>
		</section>
		<section id="main_content">
			<h1 class="title_page">Laboratori disponibili</h1>
			<div class="row_block_info">
					<ul class="list_block"></ul>
			</div>
			<div class="text_content">
				<p class="info_update_txt">
					Situazione posti disponibili aggiornata alle <span><%=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) %>
					</span> del <span><%= LocalDate.now().toString() %></span>
				</p>
				<p class="text_suggest"> 
					Le prenotazioni che si andranno a fare faranno riferimento al giorno <span id="info_data_prenotazione"></span>
					Scegli il laboratorio in cui prenotare una postazione e premi su "Prenota" per continuare.
				</p>
			</div>
			<div id="div_tb_prenota_content">
				<div class="table_scroll">
					<table id="tb_prenota" class="table_data">
						<thead>
							<tr>
								<th> Laboratori </th>
								<th> 09:00-11:00 </th>
								<th> 11:00-13:00 </th>
								<th> 13:00-15:00 </th>
								<th> 15:00-17:00 </th>
								<th> </th>
							</tr>
						</thead>
						<tbody></tbody>
					</table>
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
		<script type="text/javascript" src="../script/lab_attivi.js"></script>
		<script type="text/javascript" src="../script/student_pages.js"></script>
	</body>
</html>