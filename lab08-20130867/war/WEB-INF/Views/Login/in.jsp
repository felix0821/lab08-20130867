<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.*"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.*"%>
<%@ page import= "com.google.appengine.api.users.User"%>
<html lang="es">
<head>
<title>Login</title>
<meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<!-- Menú de navegación del sitio -->
<ul class="pager">
  <li><a href="/..">Regresar..</a>
</ul>
<%
User user = (User) request.getAttribute("user");
%>
<h2>Pagina de inicio</h2>
<form action="login" method="get">
<fieldset><legend>Login</legend>
		<div class="form-group row">
	<div class="col-xs-3">
		<label for="role">Correo :</label>
	    <input class="form-control" type="text" name="email" maxlength="30" value=<%=user.getEmail() %> pattern="[A-Za-z]{4-16}" title="La descripcion debe tener entre 4 a 16 caracteres" readonly/>
	</div>
	</div>
	</fieldset>
	<p>
	<div div class="container">
	<div class="col-xs-2">
		<div class="form-group row">
		<input type="submit" class="btn btn-primary" name="entrar" value="login"/>
		<input type="submit" class="btn btn-primary" name="salir" value="logout"/>
		</div>
		</div>
	</div>
		</form>
</body>
</html>