<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Prenota postazione</title>
		<link type="text/css" rel="stylesheet" href="./css/prenotazione_style.css">
	</head>
<body>
	<header id="header_part">
		<!--  add nav bar jsp -->
	</header>
	<section id="left_sidebar">
		<!-- add navigation bar left jsp -->
	</section>
	<section id="main_content">
		<h1 class="title_page">Laboratorio 1</h1>
		<div class="text_content">
			<p class="info_update_txt">
				Situazione posti disponibili aggiornata alle <span>22:52</span> del <span>02/01/2019</span>
			</p>
			<p class="text_suggest"> 
				Scegli la postazione che si desidera prenotare, imposta la fascia oraria e premi su "Prenota" per continuare.
			</p>
		</div>
		<div class="div_table_content">
			<div class="table_scroll">
				<table id="tb_current" class="table_data">
					<thead>
						<tr>
							<th> Postazione </th>
							<th> Fascia oraria </th>
							<th>  </th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td> 1 </td>
							<td>
								<select name="fascia_oraria_action">
									<option value="9-11">Mattina (9 - 11)</option>
									<option value="11-13">Mattina (11 - 13)</option>
									<option value="13-15">Pomeriggio (13 - 15)</option>
									<option value="15-17">Pomeriggio (15 - 17)</option>
									<option value="17-19">Sera (17 - 19)</option>
								</select>
							</td>
							<td>
								<button type="button" class="btn_prenota">Prenota</button>
							</td>
						</tr>
						<tr>
							<td> 2 </td>
							<td>
								<select name="fascia_oraria_action">
									<option value="9-11">Mattina (9 - 11)</option>
									<option value="11-13">Mattina (11 - 13)</option>
									<option value="13-15">Pomeriggio (13 - 15)</option>
									<option value="15-17">Pomeriggio (15 - 17)</option>
									<option value="17-19">Sera (17 - 19)</option>
								</select>
							</td>
							<td>
								<button type="button" class="btn_prenota">Prenota</button>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</section>
	<section id="right_sidebar">
		<!--  add bacheca avvisi.jsp -->
	</section>
	<footer>
		<!-- add footer jsp -->
	</footer>
</body>
</html>