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
<!-- navbar -->
<%@include file="navbarWebContent.jsp" %>
    <h1 style="text-align: center"> LABORATORIO  </h1>
    <div>
    	<% Collection<?> postazioni = (Collection<?>) request.getAttribute("lista");
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
                        <input type="hidden" id="id" value="<%= pos.getNumero() %>">
                        <input type="hidden" id="idlab" value="<%=pos.getLaboratorio() %>">
                        <% if(pos.isStato()==true){ %> 	
                        	<button type="button" data-toggle="modal" data-target="#exampleModaldisattiva" data-whatever="@mdo" style="display:block" class="btn btn-danger" id="disattiva">disattiva</button>
                        	<button type="button" data-toggle="modal" data-target="#exampleModalattiva" style="display:none" class="btn btn-success" id="attiva">attiva</button>
                        	<% }else{%>
                        	<button type="button" data-toggle="modal" data-target="#exampleModaldisattiva" data-whatever="@mdo" style="display:none" class="btn btn-danger" id="disattiva">disattiva</button>
                        	<button type="button" data-toggle="modal" data-target="#exampleModalattiva" style="display:block" class="btn btn-success" id="attiva">attiva</button>
                        	<% } %>
                    </div>
                    </div>
                </div>
                <!-- modulo della conferma "cambio stato postazione" disattiva-->
                <div class="modal fade" id="exampleModaldisattiva" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="exampleModalLabel">Confermi di voler disattivare la postazine?</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <div class="modal-body">
				        <form>
				          <div class="form-group">
				            <label for="message-text" class="col-form-label">Inserisci motivazione (facoltativo):</label>
				            <textarea class="form-control" id="message-text"></textarea>
				          </div>
				        </form>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
				        <button type="button" class="btn btn-primary" id="confermadisattiva">Conferma</button>
				      </div>
				    </div>
				  </div>
				</div>

<!-- Button trigger modal -->

<!-- Modal attiva-->
<div class="modal fade" id="exampleModalattiva" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Vuoi attivare la postazione?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" id="confermaatt">Conferma</button>
      </div>
    </div>
  </div>
</div>
                
            </div>
            <% } %>     
    </div>
    
    <script>
    $(document).ready(function()
	{
    	var numero;
    	var idlab;
    	var div;
    	var button;
    	
    	//prende i dati per l'attivazione
    	$("button#attiva").on("click",function(){
    		button=$(this);
    		div=$(this).parent();
    		numero=div.find("input#id").val();
    		idlab=div.find("input#idlab").val();
    		console.log(numero);
    		console.log(idlab);
    	});
    	//conferma l'attivazione manda i dati alla servlet
    	$("button#confermaatt").on("click",function(){
    		$.getJSON("postazioni", { 
    			action: "attiva_pos",
    			id: numero,
    			idlab: idlab
    			},function(data,status){
    				if(data.esito="stato modificato"){
    					button.css("display","none");
        	    		div.find("button#disattiva").css("display","block");
    				}
    	    		console.log(data.esito);
    			});
    	});
    	
    	//prende i dati per la disattivazione
    	$("button#disattiva").on("click",function(){
    		button=$(this);
    		div=$(this).parent();
    		numero=div.find("input#id").val();
    		idlab=div.find("input#idlab").val();
    		console.log(numero);
    		console.log(idlab);
    	});
    	
    	//conferma la disattivazione, manda i dati alla servlet
    	$("button#confermadisattiva").on("click",function(){
    		var modal=$(this).parent().parent();
    		var mex=modal.find("#message-text").val();
    		console.log(mex);
    		//invio dei dati
    		$.post("postazioni", { 
    			action: "disattiva_pos",
    			id: numero,
    			idlab: idlab,
    			msg: mex
    		},function(data,status){
    			if(data.esito="stato modificato"){
    				button.css("display","none");
            		div.find("button#attiva").css("display","block");
    			}
        		console.log(data.esito);
    		});
    	});
    	/*
    		$("button#confermadisattiva").click(function(){
    			
    			console.log("asd");
        		console.log(id);
        		console.log(idlab);
        		
        		$.post("postazioni", { 
        			action: "disattiva_pos",
        			id: id,
        			idlab: idlab
        		},function(data,status){
        			if(data.esito="stato modificato"){
        				button.css("display","none");
                		div.find("button#attiva").css("display","block");
        			}
            		console.log(data.esito);
        		});
        		
    		});*/
    	});
    
    </script>
</body>
</html>