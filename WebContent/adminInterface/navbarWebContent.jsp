<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
  <a class="navbar-brand" href="laboratorio?action=lista_lab"><img src="images/smartlab_ico.png"></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="laboratorio?action=lista_lab">laboratori <span class="sr-only">(current)</span></a>
      </li>
       <li class="nav-item active">
        <a class="nav-link" href="adminInterface/creaLaboratorio.jsp">Crea laboratorio</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#">Crea avviso</a>
      </li>
      <li class="nav-item active">
        <a class="nav-link" href="#">Aggiungi responsabile</a>

    </ul>
    <form class="form-inline my-2 my-lg-0">
      <button class="btn btn-secondary my-2 my-sm-0" type="button" id="logout"><img src="images/icon-logout.png"> Logout</button>
    </form>
  </div>
</nav>
<script type="text/javascript" src="script/logoutWebContent.js"></script>