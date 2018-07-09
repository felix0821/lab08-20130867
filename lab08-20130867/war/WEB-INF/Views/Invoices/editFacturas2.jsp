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
  <li><a href="/invoices/editFacturas">Regresar...</a>
</ul>
<%
List<Factura> personas = (List<Factura>) request.getAttribute("fact");
%>
<%if( personas.size()>0 ) {%>
<%for( Factura p : personas ) {%>
<h2>Modificar Factura</h2>
<%}%>
<div class="container">
<form class="form-horizontal" action="renovar" method="get">
<div class="form-group">
	<label class="col-sm-2 control-label">ID :</label>
	<%for( Factura p : personas ) {%>
		<div class="col-sm-3"><input type="text" class="form-control" name="id" value=<%= p.getId() %> readonly/></div>
    <%}%>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label">Nombre :</label>
	<%for( Factura p : personas ) {%>
		<div class="col-sm-3"><input type="text" class="form-control" name="nombre" value=<%= p.getNameCL() %> pattern="[a-Z]{5,20}" title="La descripcion debe tener de 5 a 20 caracteres" required/></div>
    <%}%>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label">Ruc :</label>
	<%for( Factura p : personas ) {%>
		<div class="col-sm-3"><input type="text" class="form-control" name="ruc" value=<%= p.getRucCL() %> pattern="(10|15|17|20)[0-9]{9})" title="Este Ruc no es valido" required/></div>
    <%}%>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label">Direccion :</label>
	<%for( Factura p : personas ) {%>
		<div class="col-sm-3"><input type="text" class="form-control" name="direccion" value=<%= p.getDireccion() %> pattern="[a-Z]{5,30}" title="La descripcion debe tener de 5 a 30 caracteres" required/></div>
    <%}%>
</div>
<div class="form-group">
	<label class="col-sm-2 control-label">Fecha :</label>
	<%for( Factura p : personas ) {%>
		<div class="col-sm-3"><input type="text" class="form-control" name="fecha" value=<%= p.getFecha() %> pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" title="Ingrese formato correcto" required/></div>
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
<p>
<div div class="container">
		<div class="form-group row">
		<input type="reset" class="btn btn-primary" value="Limpiar"/>
		<input type="submit" class="btn btn-primary" name="modificar" value="Enviar"/>
		</div>
	</div>
</form>
<%}else{%>
	<p>No hay facturas todavia.<a href="/invoices/saveFactura">Haga clic aquí para Agregar facturas.</a>
<%}%>
</body>
</html>