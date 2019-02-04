<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="dataAccess.storage.bean.Studente, java.util.List"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>Sospendi Account</title>
		<script type="text/javascript" src="script/jquery-3.3.1.min.js"></script>
	</head>
	<body>
		<% List<Studente> studente = (List<Studente>) request.getAttribute("utenti"); %>
		<% for(int i=0; i<studente.size(); i++) { %>
		 <tr>      
	            <td>Email: <%=studente.get(i).getEmail()%></td>
	            <td>Nome: <%=studente.get(i).getName()%></td>
	            <td>Cognome: <%=studente.get(i).getSurname()%></td>
	            <td>Stato: <%=studente.get(i).getStato()%></td>
	      </tr>
		<% } %>
	</body>
</html>