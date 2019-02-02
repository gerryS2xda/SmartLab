<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="dataAccess.storage.bean.Laboratorio, java.util.Collection, java.util.Iterator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista Laboratori</title>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="script/jquery-3.3.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
	<%Collection<?> laboratori = (Collection<?>) request.getAttribute("laboratori");
	Iterator<?> it = laboratori.iterator();
    while (it.hasNext()) {
    	Laboratorio lab=(Laboratorio) it.next();%>

	    <div class="card">
		  <div class="card-header" id="mycard" style="cursor: pointer">
		  	<input type="hidden" id="id" value="<%= lab.getIDlaboratorio() %>">
		    Laboratorio <%=lab.getNome() %>
		  </div>
		  <div class="row">
			  <div class="col-md-6">
			    <label class="card-text">Stato: <%=lab.isStato() %></label>
			  </div>
			  <div class="col-md-6">
			    <label class="card-text">Apertura: <%=lab.getApertura().toString() %></label>
			  </div>
			  <div class="col-md-6">
			    <label class="card-text">Chiusura: <%=lab.getChiusura().toString() %></label>
			  </div>
		  </div>
		  <div class="row">
     			<div class="col text-center">
			  		<button id="elimina" type="button" class="btn btn-primary" data-toggle="modal" data-target="#confermaModal">
			  			elimina
			  			<input type="hidden" id="id" value="<%= lab.getIDlaboratorio() %>">
			  		</button>
			  		<button id="aggiungiResponsabile" type="button" class="btn btn-primary" data-toggle="modal" data-target="#respDaAssegnare">
			  			<input type="hidden" id="id" value="<%= lab.getIDlaboratorio() %>">
			  			aggiungi responsabile
			  		</button>
		  		</div>
	  		</div>
		</div>
    	
    	<%
    }
	%>
</div>
<!-- modal  conferma eliminazione del laboratorio-->
<div class="modal fade" id="confermaModal" tabindex="-1" role="dialog" aria-labelledby="confermaModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="confermaModalLabel">Vuoi eliminare il laboratorio?</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <!-- 
      <div class="modal-body">
        ...
      </div> -->
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Chiudi</button>
        <button type="button" class="btn btn-primary" id="confermaElimina" data-dismiss="modal">Conferma</button>
      </div>
    </div>
  </div>
</div>

<!-- messaggio esito eliminazione del laboratorio-->
<div class="alert alert-success" id="success-alert" style="display:none">
</div>

<!-- Modal  con i responsabili da assegnare-->
<div class="modal fade" id="respDaAssegnare" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLongTitle">Responsabili da assegnare</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="listaResponsabili">
        <!-- corpo -->
      </div>
    </div>
  </div>
</div>


<script>
$(document).ready(function(){
	
	var id;
	var div;
	//seleziono il laboratorio da eliminare
	$("button#elimina").on("click",function(){
		id=$(this).find("input#id").val();
		div=$(this).parent().parent().parent();//seleziono la scheda card
		
	});
	//ajax eliminazione del laboratorio + messaggio di conferma
	$("button#confermaElimina").on("click",function(){
		console.log(id);
		/*$(document).ajaxStart(function(){
	        $("#wait").css("display", "block");
	    });
		
		$(document).ajaxComplete(function(){
	        $("#wait").css("display", "none");
	    });*/
		
		$.getJSON("laboratorio",{
			action: "rimuovi_lab",
			idlaboratorio: id
		},function(data,status){
			//console.log(data.esito);
			div.remove();//rimuovo la scheda dalla grafica
			id="";
			//messaggio esito
			var mex=data.esito;
			$("#success-alert").css("display","block");
			$("#success-alert").append($("<strong>"+mex+"</strong>"));
			setTimeout(function() {
				$("#success-alert").css("display","none");
		        //$("#success-alert").alert('close');
		    }, 2000);
		});
	});
	
	//lista responsabili assegnati al laboratorio cliccato
	$("div#mycard").on("click",function(){
		var query=$(this).find("input#id").val();
		console.log(query);
		query="assegnamento?action=lista_resp_ass&idlaboratorio="+query;
		$(window.location).attr('href', query);
	});
	
	//lista responsabili da aggiungere 
	$("button#aggiungiResponsabile").on("click",function(){
		var id=$(this).find("input#id").val();
		console.log(id);
		//!
		$("div#listaResponsabili").html("");
		
		$.getJSON("assegnamento",{
			action:"lista_resp",
			idlaboratorio:id
		},function(data,status){
			var responsabili=data.responsabili;
			//console.log(data);
			var i;
			for(i=0;i<responsabili.length;i++){
				var riga=$("<div></div>");
				riga.addClass("row");
				var email=$("<div>"+responsabili[i].email+"</div>");
				email.addClass("col-md-6");
				email.attr("id","email");
				var nome=$("<div>"+responsabili[i].nome+"</div>");
				nome.addClass("col-md-6");
				var cognome=$("<div>"+responsabili[i].cognome+"</div>")
				cognome.addClass("col-md-6");
				var link=$("<button>Aggiungi</button>");
				link.addClass("col-md-6");
				link.addClass("btn btn-primary");
				link.attr("id","assegna");
				//"href","assegnamento?action=aggiungi_ass&idlaboratorio="+id+"&idresponsabile="+responsabili[i].email
				
				link.on("click",function(){
					console.log("ok");
				});
				
				riga.append(email);
				riga.append($("<input id=\"id\" type=\"hidden\" value=\""+id+"\">"));
				riga.append(nome);
				riga.append(cognome);
				riga.append(link);
				
				$("div#listaResponsabili").append(riga);
			}
		});
	});
	
	//assegnamento
	/*$("button#assegna").on("click",function(){
		//var div=$(this).parent();
		console.log("ok");
		console.log(div.find("input#id").val());
		console.log(div.find("div#email").text());
	});*/
});
</script>
</body>
</html>
