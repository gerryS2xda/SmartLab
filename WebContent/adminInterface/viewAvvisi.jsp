<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Lista avvisi</title>
	</head>
	<body onload = "loadAvvisi()">
		<div id = "div_avvisi_table">
			<table id = "tb_avvisi">
				<thead>
					<tr>
						<th>Id</th>
						<th>Oggetto</th>
						<th>Descrizione</th>
						<th>Data</th>
						<th>Addetto</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
	</body>
	<script type="text/javascript" src="../script/jquery-3.3.1.min.js"></script>
	<script type="text/javascript" src="../script/avviso_js.js"></script>
</html>