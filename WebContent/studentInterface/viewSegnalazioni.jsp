<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import = "dataAccess.storage.bean.Utente"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Lista segnalazioni</title>
	</head>
	<% 
		Utente ut = (Utente) session.getAttribute("user");
		String userType = (String) session.getAttribute("userType");
		if(ut == null || userType == null) {
			response.sendRedirect("./error.jsp"); //pagina errore 404
		}else if(userType.equals("studente")){	
	%>
	<body onload = "loadSegnalazioni()">
		<div id = "div_segnalazioni_table" align = "center">
			<table id = "tb_segnalazioni" border = "1">
				<thead>
					<tr>
						<th>Id</th>
						<th>Laboratorio</th>
						<th>Postazione</th>
						<th>Oggetto</th>
						<th>Descrizione</th>
						<th>Data</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
		<%
			}else{
				response.sendRedirect("./error.jsp"); //pagina errore 404
			}%>
	</body>
		<script type="text/javascript" src="../script/jquery-3.3.1.min.js"></script>
		<script type="text/javascript" src="../script/segnalazione_js.js"></script>
</html>