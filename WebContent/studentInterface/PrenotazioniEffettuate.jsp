<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import ="java.time.LocalDate" pageEncoding="ISO-8859-1"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			<title>Mie prenotazioni</title>
			<link type="text/css" rel="stylesheet" href="../css/prenotazione_style.css">
			<link type="text/css" rel="stylesheet" href="../css/pren_effettuate_style.css">
		</head>
	<body onLoad="loadContent()">
		<header id="header_part">
			<!--  add nav bar jsp -->
		</header>
		<section id="left_sidebar">
			<!-- add navigation bar left jsp -->
			<div id="sidebar">
				<ul>
					<li><a href="PrenotazionePage.jsp">Prenota postazione</a></li>
					<li class="active"> <a href="javascript:void(0);">Mie prenotazioni</a></li>
					<li> <a href="javascript:void(0);">Segnala problema</a></li>
					<li> <a href="javascript:void(0);">Link 4</a></li>
					<li> <a href="index.jsp">Vai alla Homepage </a></li>
				</ul>
			</div>
		</section>
		<section id="main_content">
			<h1 class="title_page">Mie prenotazioni</h1>
			<div class="text_content">
				<p class="text_suggest"> 
					Ecco le tue prenotazioni che sono state effettuate fino ad oggi. Per annullare o eliminare una prenotazione, 
					mettere la spunta sulla checkbox. Se una prenotazione è attiva, viene richiesto di confermare l'annullamento
				</p>
			</div>
			<div id="div_tb_effettuate_content">
				<div class="table_scroll">
					<table id="tb_pren_effettuate" class="table_data">
						<thead>
							<tr>
								<th> N.ro prenotazione </th>
								<th> Data </th>
								<th> Laboratorio </th>
								<th> Postazione </th>
								<th> Fascia oraria </th>
								<th> Elimina </th>
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
		<script type="text/javascript" src="../script/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="../script/pren_eff_script.js"></script>
	</body>
</html>