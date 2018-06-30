<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.*"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.*"%>

<html lang="es">
<head>
<title>Edit Factura</title>
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
  <li><a href="/index.html">Regresar...</a>
  <li><a href="/saveFactura">Registro</a>
</ul>
<%
List<Factura> personas = (List<Factura>) request.getAttribute("facturas");
%>
<%if( personas.size()>0 ) {%>
<%int c=0;%>
<form action="editFacturas" method="get">
<h2>Mostrando <%= personas.size() %> resultados</h2>
<div class="container">
<div class="col-xs-2">
	<label for="ex2">ID° :</label>
	<%for( Factura p : personas ) {%>
		<div class="Cell"><input type="text" class="form-control" name=<%="id"+c%> value=<%= p.getId() %> readonly/></div>
		<%c++; %>
    <%}%>
</div>
<%c=0; %>
<div class="col-xs-3">
	<label for="ex2">Nombre :</label>
	<%for( Factura p : personas ) {%>
		<div class="Cell"><input type="text" class="form-control" name=<%="nombre"+c%> value=<%= p.getNameCL() %> readonly/></div>
		<%c++; %>
    <%}%>
</div>
<%c=0; %>
<div class="col-xs-1">
	<label for="ex2">Editar :</label>
	<%for( Factura p : personas ) {%>
		<div class="Cell"><input type="submit" class="btn btn-default" class="form-control" name="edit" value=<%="Edit-"+c%> /></div>
		<%c++; %>
    <%}%>
</div>
<%c=0; %>
<div class="col-xs-1">
	<label for="ex2">Eliminar :</label>
	<%for( Factura p : personas ) {%>
		<div class="Cell"><input type="submit" class="btn btn-default" class="form-control" name="del" value=<%="Del-"+c%>  /></div>
		<%c++; %>
    <%}%>
</div>
</div>
</form>
<%}else{%>
	<p>No hay facturas todavia.<a href="/saveFactura">Haga clic aquí para Agregar facturas.</a>
<%}%>
</body>
</html>