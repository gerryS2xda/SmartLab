<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    import="java.time.*, java.time.format.*, dataAccess.storage.bean.Utente, dataAccess.storage.bean.Studente" pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Dashboard Studente</title>
		<link type="text/css" rel="stylesheet" href="../css/student_home_style.css">
	</head>
	<% 
		Utente ut = (Utente) session.getAttribute("user");
		String userType = (String) session.getAttribute("userType");
		if(ut == null || userType == null) {
			response.sendRedirect("./error.jsp"); //pagina errore 404
		}else if(userType.equals("studente")){
			Studente stud = (Studente) ut; //non necessita di controllo poiche' viene fatto nella servletUtenteManagement
	%>
	<body onLoad="loadContent()">
		<!--  popup che saranno mostrati al momento opportuno -->
		<div id="content_popup">
			<div id="content_popup_area">
				<h1>Contenuto avviso</h1>
				<h3></h3>
				<hr>
				<p></p>
				<div class="content_pop_button_area">
					<button id="back_content" type="button">Indietro</button>
				</div>
			</div>	
		</div>
		<header id="header_part">
			<!--  add nav bar jsp -->
			<span id="txt_logo">User Area</span>
			<span id="welcome_txt">Benvenuto, <%=stud.getName() %></span>
			<span id="logout"><img src="../images/icon-logout.png">Logout</span>
		</header>
		<section id="left_sidebar">
			<!-- add navigation bar left jsp -->
			<div id="sidebar">
				<ul>
					<li class="active"><a href="javascript:void(0);">Dashboard</a></li>
					<li><a href="laboratoriAttivi.jsp">Prenota postazione</a></li>
					<li> <a href="PrenotazioniEffettuate.jsp">Mie prenotazioni</a></li>
					<li> <a href="javascript:void(0);">Segnala problema</a></li>
				</ul>
			</div>
		</section>
		<section id="main_content">
			<div class="row_block_info">
					<ul class="list_block"></ul>
			</div>
			<table class="row_table_data">
					<tr>
						<th> </th>
						<th> </th>
					</tr>
					<tr>
						<td id="personal_data">
							<div class="widget-box">
								<div class="widget-title">
									<span id="ic1" class="icon"><img class="icon_arrow" src="../images/arrow_btm.png"></span>
									<span class="category_text">Dati personali</span>
								</div>
								<div id="content1" class="widget-content" >
									<span class="text_content_dati_pers">Nome: <span><%= stud.getName() %></span></span><br>
									<span class="text_content_dati_pers">Cognome: <span><%= stud.getSurname() %></span></span><br>
									<span class="text_content_dati_pers">Email di ateneo: <span><%= stud.getEmail() %></span></span><br>
									<span class="text_content_dati_pers">Password: 
									<input type="text" id="pwd_input" name="pwd" value="<%= stud.getPassword() %>" onblur="validatePassword($(this))"> 
									<span class="account_text_err"></span> <button type="button" id="edit_pwd">Modifica</button></span><br>
								</div>
							</div>
						</td>
						<td id="email_list">
							<div class="widget-box_email">
								<div class="widget-title">
									<span id="ic2" class="icon"><img class="icon_arrow" src="../images/arrow_btm.png"></span>
									<span class="category_text">Bacheca avvisi</span>
								</div>
								<div id="content2" class="widget_content_mail" >
									<ul class="mail_arrivate"></ul>
								</div>
							</div>
						</td>
					</tr>
				</table>
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
		<script type="text/javascript" src="../script/student_homepage.js"></script>
		<script type="text/javascript" src="../script/student_pages.js"></script>
	</body>
</html>