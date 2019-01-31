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
		  <div class="card-header">
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
		  		</div>
	  		</div>
		</div>
    	
    	<%
    }
	%>
</div>
<!-- modal -->
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
<!-- messaggio esito -->
<div class="alert alert-success" id="success-alert" style="display:none">
</div>

<script>
$(document).ready(function(){
	
	var id;
	var div;
	$("button#elimina").on("click",function(){
		id=$(this).find("input#id").val();
		div=$(this).parent().parent().parent();//seleziono la scheda card
	    //create a message 
	    //create a message 
	    /*var alert=$("div");
	    alert.addClass("alert alert-success");
	    alert.attr("id","success-alert");
	    var button=$("button").addClass("close").attr("data-dismiss","alert");
	    button.attr("value","x");
	    button.attr("type","button");
		console.log(div);
		console.log(id);
		var strong=$("strong");
		strong.text("ciao");
		alert.append(button);
		alert.append(strong);
		$("#mymes").append(alert);*/
		//$("a#elimina").attr("href", "laboratorio?action=rimuovi_lab&idlaboratorio="+id)
		
	});
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
			var mex=data.esito;
			$("#success-alert").css("display","block");
			$("#success-alert").append($("<strong>"+mex+"</strong>"));
			setTimeout(function() {
				$("#success-alert").css("display","none");
		        //$("#success-alert").alert('close');
		    }, 2000);
		});
	});
});
</script>

</body>
</html>
