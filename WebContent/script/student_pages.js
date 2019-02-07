//questo script contiene le funzioni JS comuni a quasi tutte le pagine presenti in student interface

//pulsante 'Logout' presente in header
$("#logout").click(function(){
	$.post("../utente", {"action": "logout"}, function(resp, stat, xhr){
		if(xhr.readyState == 4 && stat == "success"){
			var o = JSON.parse(resp);
			var esito = o.done;
			if(esito){	//logout effettuato con successo
				window.location.href = "../Login.jsp";
			}else{
				window.location.href = "./error.jsp"; //pagina errore 404
			}
		}else{
			window.location.href = "./error.jsp"; //pagina errore 404
		}
	});
});

/* NON SONO STATE UTILIZZATE
// funzioni per la parte responsive 
$("#left_sidebar").mouseenter(function(){
	var x = parseInt($(this).css("width"));
	if(x == 20){
		openSideNavBar();
	}		
});

$("#left_sidebar").mouseleave(function(){
	var x = parseInt($(this).css("width"));
	if(x == 220){
		closeSideNavBar();
	}		
});

function openSideNavBar(){
	$("#left_sidebar").css("width", "220px");
	$("#sidebar").show();
	$("#main_content").css("margin-left", "300px");
}

function closeSideNavBar(){
	$("#left_sidebar").css("width", "20px");
	$("#sidebar").hide();
	$("#main_content").css("margin-left", "80px");
}

*/