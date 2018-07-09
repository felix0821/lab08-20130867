<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.users.*"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.*"%>

<html lang="es">
<head>
<title>New Roles</title>
<meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<ul class="pager">
<li><a href="/roles">Regresar...</a></li>
<li><a href="/users/index">Inicio</a></li>
</ul>
<!-- Menú de navegación del sitio -->

<form action="add" method="get">
<fieldset><legend>New role</legend>
<div class="container">
	<div class="form-group row">
	<div class="col-xs-3">
		<label for="role">Name(Role) :</label>
	    <input class="form-control" type="text" name="role" maxlength="30" value="" pattern="[A-Za-z]{4-16}" title="La descripcion debe tener entre 4 a 16 caracteres" required/>
	</div>
	</div>
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