<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LIBERA POSTAZIONE</title>
</head>
<body>

	<form id="lib_post_occ">
	Inserisci laboratorio: <input type="text" name="lab_in" id="lab_in" value="">
	<select name="fascia_oraria_action" id="fasc_ora_lib_pos" onChange="verifyPostazioneAvailable($(this));">
		<option value="09:00-11:00">Mattina (9 - 11)</option>
		<option value="11:00-13:00">Mattina (11 - 13)</option>
		<option value="13:00-15:00">Pomeriggio (13 - 15)</option>
		<option value="15:00-17:00">Pomeriggio (15 - 17)</option>
		<option value="17:00-19:00">Sera (17 - 19)</option>
	</select>
	<button type="button" id="lib_pos_btn">Avvia ricerca</button>
</form>
                 

</body>
</html>