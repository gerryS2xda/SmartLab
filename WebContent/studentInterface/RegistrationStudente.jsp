<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Registrazione</title>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link type="text/css" rel="stylesheet" href="../css/reg_form_style.css">
		<link rel="icon" href="./images/icon_web_window_32.jpg" type="image/jpg" />
	</head>
	<body>
	<header>
		<!-- inserire navbar.jsp -->
	</header>
	<section id="left_sidebar"></section>
	<section id="main_content">
		<!--  spazio per inserire la prenotazione da altri utenti (responsabile e admin) -->
		<% String userType = request.getParameter("userType"); 
		   if(userType == null || userType == ""){
			  	response.sendRedirect("./error.jsp"); //pagina errore 404
		   }else if(userType.equals("admin") || userType.equals("responsabile")){
		   			String secure = (String) session.getAttribute("UTProtect");	//protezione per la registrazione di un amministratore/responsabile
		   			if(secure == null || !secure.equals("1")){
						response.sendRedirect("./error.jsp"); //pagina errore 404
			   		}
			   	}
		%>
		<h1 id="title">Modulo di registrazione</h1>
		<div id="box_form">
			<form name="reg_form" action="../utente" method="post">
				<fieldset>
					<legend>Dati personali</legend>
					<div class="input_field">	
						<label id="lbl" for="first_name" name="l_name" class="">Nome</label>
						<input id="first_name" type="text" name="s_name" value="" onfocus="addActive('lbl')" onblur="validateOnlyLetter('first_name', 'lbl', 'nome_txt_error', 20);">
						&nbsp;&nbsp; 
					</div> 
					<p id="nome_txt_error" class="txt_error"> </p> <br>
					<div class="input_field">	
						<label id="lbl1" for="sur_name" class="">Cognome</label>
						<input id="sur_name" type="text" name="s_surname" value="" onfocus="addActive('lbl1')" onblur="validateOnlyLetter('sur_name', 'lbl1', 'cognome_txt_error', 20)">
						&nbsp;&nbsp;																					
					</div>
					<p id="cognome_txt_error" class="txt_error"> </p> <br>
				</fieldset>
				<fieldset>
					<legend>Dati login</legend>
					<div class="input_field">	
						<label id="lbl2" for="email" class="">Email di ateneo</label>
						<input id="email" type="email" name="s_email" value="" onfocus="addActive('lbl2')" onblur="validateEmail('email', 'lbl2', 'mail_err')">
						&nbsp;&nbsp;
					</div>
					<p id="mail_err" class="txt_error"> </p> <br>
					<div class="input_field">	
						<label id="lbl3" for="pwd" class="">Password</label>
						<input id="pwd" type="text" name="password" value="" onfocus="addActive('lbl3')" onblur="validatePassword('pwd', 'lbl3', 'pwd_err')">
						&nbsp;&nbsp;
					</div>
					<p id="pwd_err" class="txt_error"> </p> <br>
				</fieldset>
				<input type="hidden" name="action" value="registraStudente">
				<div id="but_submit">
					<button type="submit" name="u_submit_but" onclick="formValidation();">Procedi</button>
				</div>
			</form>
		</div>
		</section>
		<section id="right_sidebar"></section>
		<footer>
			<!-- inserire footer.jsp -->
		</footer>
		<script type="text/javascript" src="../script/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="../script/reg_form_js.js"></script>
	</body>	
</html>