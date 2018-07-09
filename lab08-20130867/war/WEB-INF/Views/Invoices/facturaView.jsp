<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.*"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.*"%>

<html lang="es">
<head>
<title>View Factura</title>
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
  <li><a href="/invoices">Regresar...</a>
  <li><a href="/invoices/saveFactura">Registro</a>
</ul>
<%
List<Factura> personas = (List<Factura>) request.getAttribute("facturas");
%>
<%if( personas.size()>0 ) {%>
<div class="Table">
	<div class="Title"><p>Mostrando <%= personas.size() %> personas</p></div>
	<div class="Heading">
	<div class="Cell"><p>Código</p></div>
	<div class="Cell"><p>numero de RUC</p></div>
		<div class="Cell"><p>Nombres</p></div>
		<div class="Cell"><p>RUC</p></div>
		<div class="Cell"><p>Direccion</p></div>
	</div>

<%for( Factura p : personas ) {%>
	<div class="Row">
	    <div class="Cell"><p><%= p.getId() %></p></div>
		<div class="Cell"><p><%= p.getNumF() %></p></div>
		<div class="Cell"><p><%= p.getNameCL() %></p></div>
		<div class="Cell"><p><%= p.getRucCL() %></p></div>
		<div class="Cell"><p><%= p.getDireccion() %></p></div>
	</div>
<%}%>
</div>
<%}else{%>
	<p>No hay facturas todavia.<a href="/invoices/saveFactura">Haga clic aquí para Agregar facturas.</a>
<%}%>
</body>
</html>