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
                        	<button style="display:block" class="btn btn-danger" id="disattiva">disattiva</button>
                        	<button style="display:none" class="btn btn-success" id="attiva">attiva</button>
                        	<% }else{%>
                        	<button style="display:none" class="btn btn-danger" id="disattiva">disattiva</button>
                        	<button style="display:block" class="btn btn-success" id="attiva">attiva</button>
                        	<% } %>
                        	
                        	
                    </div>
                    </div>
                </div>
                <!-- modulo della conferma "cambio stato postazione" -->
                <div class="modal fade" id="confermaModal" tabindex="-1" role="dialog" aria-labelledby="confermaModalLabel" aria-hidden="true">
				  <div class="modal-dialog modal-dialog-centered" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <h5 class="modal-title" id="confermaModalLabel">Vuoi cambiare lo stato della postazione?</h5>
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
				          <span aria-hidden="true">&times;</span>
				        </button>
				      </div>
				      <!-- 
				      <div class="modal-body">
				        ...
				      </div> -->
				      <div class="modal-footer">
				        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annulla</button>
				        <button type="button" class="btn btn-danger" id="conferma" data-dismiss="modal">Conferma</button>
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
    	$("button#attiva").on("click",function(){
    		
    		var button=$(this);
    		var div=$(this).parent();
    		var id=div.find("input#id").val();
    		var idlab=div.find("input#idlab").val();
    		
    		
    		
    		$.getJSON("postazioni", { 
    			action: "attiva_pos",
    			id: id,
    			idlab: idlab
    			},function(data,status){
    				if(data.esito="stato modificato"){
    					button.css("display","none");
        	    		div.find("button#disattiva").css("display","block");
    				}
    	    		console.log(data.esito);
    			});
    		
    		
    		});
    		$("button#disattiva").on("click",function(){
    			
    			var button=$(this);
        		var div=$(this).parent();
        		var id=div.find("input#id").val();
        		var idlab=div.find("input#idlab").val();
        		
        		console.log(id);
        		console.log(idlab);
        		
        		$.getJSON("postazioni", { 
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
        		
    		});
    	});
    
    </script>
</body>
</html>