<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.*"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.*"%>
<%@ page import= "com.google.appengine.api.users.User"%>
<html lang="es">
<head>
<title>Start</title>
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
  <li><a href="/users/logout">LogOut</a>
  <li><a href="/users/login">LogIn</a>
</ul>
<%
User user = (User) request.getAttribute("user");
%>
<h2>Bienvenido a la pagina principal <%=user.getEmail()%></h2>
<form action="add" method="get">
<fieldset><legend>New access</legend>
<div class="col-xs-2">
		<div class="form-group row">
		<li><a href="/users">Users</a></li>
		</div>
		</div>
		<p>
		<div class="col-xs-2">
		<div class="form-group row">
		<li><a href="/roles">Roles</a></li>
		</div>
		</div>
		<p>
		<div class="col-xs-2">
		<div class="form-group row">
		<li><a href="/resources">Resources</a></li>
		</div>
		</div>
		<p>
		<div class="col-xs-2">
		<div class="form-group row">
		<li><a href="/access">Access</a></li>
		</div>
		</div>
	<p>
	<div class="col-xs-2">
		<div class="form-group row">
		<li><a href="/invoices">Invoices</a></li>
		</div>
		</div>
		</fieldset>
		</form>
</body>
</html>