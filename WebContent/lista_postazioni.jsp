<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dataAccess.storage.bean.*, java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista postazioni</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="script/jquery-3.3.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>


</head>
<body>
    <h1 style="text-align: center"> LABORATORIO  </h1>
    <div>
    	<% 
    	
    	Collection<?> postazioni = (Collection<?>) request.getAttribute("lista");
	Iterator<?> it = postazioni.iterator();
    while (it.hasNext()) {
    	//Laboratorio lab=new Laboratorio();
    	//int npos=lab.getPosti();
    	Postazione pos=(Postazione) it.next();
    	int n=0;%>
            <div class="row">
                <div class="col-sm-6">
                    <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">postazione n: <%= pos.getNumero() %> </h5>
                        <p class="card-text"></p>
                        <% if(pos.isStato()==true){ %> 	
                        	<button  class="btn btn-primary">disattiva</button>
                        	<% } %>
                        	<% if(pos.isStato()!=true){ %>
                        	<button class="btn btn-primary">attiva</button>
                        	<% } %>
                        	<a href="liberapos.jsp" class="btn btn-primary">Libera</a>
                    </div>
                    </div>
                </div>

                
            </div>
            <% } %>     
    </div>
</body>
</html>