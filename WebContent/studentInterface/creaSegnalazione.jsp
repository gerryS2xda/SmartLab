<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<title> Segnalazione problema </title>
		<link type="text/css" rel="stylesheet" href="Segnalazione_style.css">
	</head>
	<body>
		<header id = "page_header">
			test
		</header>
		<section id = "left_banner">
			test
		</section>
		<section id = "main_content">
			<h1 align = "center"> Segnalazione </h1>
			<h2 align = "center"> Immettere i dati qui sotto </h2>
			<div id = "body">
				<div id = "labInfo">
					<div align = "center" id = "sceltaLab"> Scegliere il laboratorio: 
						<input list = "Laboratori" name = "NomeLab"> 
						<datalist id = "Laboratori"> 
							<option value = "SesaLab"> 
							<option value = "Lab sistemi"> 
						</datalist> 
					</div>
					<div align = "center" id = "sceltaPos"> Numero della postazione: <input type = "number" min = "1" max = "50" id = "NumPos"> </div>
				</div>
				<div align = "center" id = "insOgg">Inserire l'oggetto: <textarea rows = "1" cols = "50" id = "oggetto"></textarea></div>
			    <div align = "center" id = "insDes"> Inserire il motivo della segnalazione: </div>
				<div align = "center"><textarea rows = "10" cols = "100" id = "descrizione"></textarea></div>
				<div align = "center"><button id = "button">Invia</button></div>
				<div align = "center" id = "errore">Alcuni dati non sono stati immessi oppure sono scorretti</div>
		    </div>
		</section>
		<section id = "right_banner">
			test
		</section>
		<footer id = "page_footer">
			test
		</footer>
	</body>
</html>