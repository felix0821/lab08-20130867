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
  <li><a href="/invoices/listFacturas">Regresar...</a>
  <li><a href="/invoices/saveFactura">Registro</a>
</ul>
<%
List<Factura> personas = (List<Factura>) request.getAttribute("fact");
%>
<%if( personas.size()>0 ) {%>
<%for( Factura p : personas ) {%>
<h2>Mostrando resultado de ID: <%= p.getId() %></h2>
<%}%>
<div class="container">
<form class="form-horizontal">
<div class="form-group">
	<label class="col-sm-2 control-label">Nombre :</label>
	<%for( Factura p : personas ) {%>
		<div class="col-sm-3"><input type="text" class="form-control" name="nombre" value=<%= p.getNameCL() %> readonly/></div>
    <%}%>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label">Ruc :</label>
	<%for( Factura p : personas ) {%>
		<div class="col-sm-3"><input type="text" class="form-control" name="ruc" value=<%= p.getRucCL() %> readonly/></div>
    <%}%>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label">Direccion :</label>
	<%for( Factura p : personas ) {%>
		<div class="col-sm-3"><input type="text" class="form-control" name="ruc" value=<%= p.getDireccion() %> readonly/></div>
    <%}%>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label">Fecha :</label>
	<%for( Factura p : personas ) {%>
		<div class="col-sm-3"><input type="text" class="form-control" name="fecha" value=<%= p.getFecha() %> readonly/></div>
    <%}%>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label">Compras :</label></div>
	<%for( Factura p : personas ) {%>
	<%ArrayList<String>des=p.getDes();%>
	<%ArrayList<Double>con=p.getCan();%>
	<%ArrayList<Double>uni=p.getUni();%>
	<%ArrayList<Double>val=p.getVal();%>
	<%for( int i=0;i<des.size();i++) {%>
	<div class="form-group">
	<label class="col-sm-2 control-label">-</label>
		<div class="col-sm-5"><input type="text" class="form-control" name="compras" value=<%=" "+con.get(i)+"-"+des.get(i)+"-("+uni.get(i)+")-val-"+val.get(i) %> readonly/></div>
	</div>
	<%}%>
    <%}%>
    <div class="form-group">
<label class="col-sm-2 control-label">Total :</label>
<%for( Factura p : personas ) {%>
		<div class="col-sm-2"><input type="text" class="form-control" name="total" value=<%= p.getTotal() %> readonly/></div>
    <%}%>
</div>
</div>
</form>
<%}else{%>
	<p>No hay facturas todavia.<a href="/saveFactura">Haga clic aquí para Agregar facturas.</a>
<%}%>
</body>
</html>