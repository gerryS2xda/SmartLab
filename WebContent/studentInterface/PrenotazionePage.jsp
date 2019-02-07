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
			<span id="welcome_txt">Benvenuto, <%=ut.getName() %></span>
			<span id="logout"><img src="../images/icon-logout.png">Logout</span>
		</header>
		<section id="left_sidebar">
			<!-- add navigation bar left jsp -->
			<div id="sidebar">
				<ul>
					<li><a href="javascript:void(0);">Dashboard</a></li>
					<li class="active"><a href="laboratoriAttivi.jsp">Prenota postazione</a></li>
					<li> <a href="PrenotazioniEffettuate.jsp">Mie prenotazioni</a></li>
					<li> <a href="creaSegnalazione.jsp">Segnala problema</a></li>
				</ul>
			</div>
		</section>
		<section id="main_content">
			<div class="main_content_margin">
			<% String lab = request.getParameter("lab_name");
			   String idLab = request.getParameter("lab_selected");
				if(lab == null || lab.equals("") || idLab == null || idLab.equals("")){
					//vai alla pagina di errore
				}
			%>
				<span id="idLab" class="hidden_item"><%=idLab %></span>
				<h1 class="title_page"><%=lab %></h1>
				<div class="text_content">
					<p class="info_update_txt">
						Situazione posti disponibili aggiornata alle <span><%=LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")) %>
						</span> del <span><%= LocalDate.now().toString() %></span>
					</p>
					<p class="text_suggest"> 
						Scegli la postazione che si desidera prenotare, imposta la fascia oraria e premi su "Prenota" per continuare.
					</p>
				</div>
				<div id="div_tb_prenota_content">
					<div class="table_scroll">
						<table id="tb_prenota" class="table_data">
							<thead>
								<tr>
									<th> Postazione </th>
									<th> Fascia oraria </th>
									<th>  </th>
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
		<script type="text/javascript" src="../script/pren_page_js.js"></script>
		<script type="text/javascript" src="../script/student_pages.js"></script>
	</body>
</html>
