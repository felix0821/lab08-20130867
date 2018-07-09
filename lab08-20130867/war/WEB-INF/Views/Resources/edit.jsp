<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.users.*"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.*"%>

<html lang="es">
<head>
<title>Edit Resources</title>
<meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<ul class="pager">
<li><a href="/resources">Regresar...</a></li>
<li><a href="/users/index">Inicio</a></li>
</ul>
<!-- Menú de navegación del sitio -->

<form action="resources/edit" method="get">
<fieldset><legend>Edit resource</legend>
<div class="container">
<%List<Resource> resources = (List<Resource>) request.getAttribute("resource");%>
<%for( Resource r : resources){%>
    <div class="form-group row">
	<div class="col-xs-3">
		<label for="email">ID :</label>
		<input class="form-control" type="text" name="id" maxlength="30" value=<%=r.getId()%> readonly/>
	</div>
	</div>
    <div class="form-group row">
	</div>
	<div class="form-group row">
	<div class="col-xs-3">
		<label for="email">Name(Role) :</label>
		<input class="form-control" type="text" name="name" maxlength="30" value=<%=r.getName()%> pattern="[A-Za-z]{4-16}" title="La descripcion debe tener entre 4 a 16 caracteres" required/>
	</div>
	</div>
  <div class="col-xs-2">
	<div class="form-group row">
	<label class="checkbox-inline">
	<%if(r.getStatus()){%>
	  <input type="checkbox" name="status" value="" checked>Status
	<%}else{%>
      <input type="checkbox" name="status" value="">Status
      <%}%>
    </label>
    </div>
	</div>
	<%}%>
	</div>
	</fieldset>
	<p>
	<div div class="container">
	<div class="col-xs-2">
		<div class="form-group row">
		<input type="submit" class="btn btn-primary" name="modify" value="MODIFY"/>
		</div>
		</div>
	</div>
	</form>

</body>
</html>