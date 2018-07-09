<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.users.*"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.*"%>

<html lang="es">
<head>
<title>New Access</title>
<meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<ul class="pager">
<li><a href="/access">Regresar...</a></li>
<li><a href="/users/index">Inicio</a></li>
</ul>
<!-- Menú de navegación del sitio -->
<%
List<Role> roles = (List<Role>) request.getAttribute("roles");
%>
<%
List<Resource> resources = (List<Resource>) request.getAttribute("resources");
%>
<form action="add" method="get">
<fieldset><legend>New access</legend>
<div class="container">
<%if( roles.size()>0 ) {%>
	<div class="form-group row">
	<div class="col-xs-2">
  <label for="role">Role :</label>
  <select name="role" class="form-control" id="sel1">
  <%for( Role p : roles ) {%>
    <option><%= p.getName() %></option>
    <%}%>
  </select>
  </div>
	</div>
	<%}else{%>
	<li><a href="/roles/add">Crear nuevo Rol</a></li>
	<%}%>
	<p>
<%if( resources.size()>0 ) {%>
	<div class="form-group row">
	<div class="col-xs-2">
  <label for="resource">Resource :</label>
  <select name="resource" class="form-control" id="sel1">
  <%for( Resource s : resources ) {%>
    <option><%= s.getName() %></option>
    <%}%>
  </select>
  </div>
	</div>
	<%}else{%>
	<li><a href="/resources/add">Crear nuevo Recurso</a></li>
	<%}%>
	</fieldset>
	<p>
	<div div class="container">
	<div class="col-xs-2">
		<div class="form-group row">
		<input type="submit" class="btn btn-primary" name="submit" value="SUBMIT"/>
		</div>
		</div>
	</div>
	</form>

</body>
</html>