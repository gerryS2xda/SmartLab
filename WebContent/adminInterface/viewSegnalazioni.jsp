<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Lista segnalazioni</title>
	</head>
	<body onload = "loadSegnalazioni()">
		<div id = "div_segnalazioni_table">
			<table id = "tb_segnalazioni">
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
	</body>
</html>