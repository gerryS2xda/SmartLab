<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="java.time.*, java.time.format.*" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Prenota postazione</title>
		<link type="text/css" rel="stylesheet" href="./css/prenotazione_style.css">
	</head>
<body onLoad="loadContent()">
	<header id="header_part">
		<!--  add nav bar jsp -->
	</header>
	<section id="left_sidebar">
		<!-- add navigation bar left jsp -->
		<div id="sidebar">
			<ul>
				<li class="active"><a href="javascript:void(0);">Prenota postazione</a></li>
				<li> <a href="javascript:void(0);">Miei prenotazioni</a></li>
				<li> <a href="javascript:void(0);">Segnala problema</a></li>
				<li> <a href="javascript:void(0);">Link 4</a></li>
				<li> <a href="index.jsp">Vai alla Homepage </a></li>
			</ul>
		</div>
	</section>
	<section id="main_content">
		<h1 class="title_page">Laboratorio 1</h1>
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
	</section>
	<section id="right_sidebar">
		<!--  add bacheca avvisi.jsp -->
		<h1>Bacheca avvisi</h1>
	</section>
	<footer>
		<!-- add footer jsp -->
	</footer>
	<script type="text/javascript" src="script/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="script/pren_page_js.js"></script>
</body>
</html>