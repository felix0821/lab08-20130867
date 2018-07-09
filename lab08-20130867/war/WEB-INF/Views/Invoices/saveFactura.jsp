<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.*"%>
<%@ page import="java.util.*"%>
<html lang="es">
<head>
<title>Save Factura</title>
<meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<ul class="pager">
<li><a href="/invoices">Regresar...</a></li>
<li><a href="/invoices/listFacturas">Lista</a></li>
</ul>
<!-- Menú de navegación del sitio -->

<form action="saveFactura" method="get">
<fieldset><legend>Registro de factura</legend>
<div class="container">
	<div class="form-group row">
	<div class="col-xs-3">
	<%String nombrecl = (String)request.getAttribute("nameCL");%>
		<label for="nameCL">Señor(es)(Nombre-Apellido) :</label>
		<input class="form-control" type="text" name="nameCL" maxlength="20" value=<%=nombrecl%> pattern="([a-Z]{5,20})-([a-Z]{5,20})" title="La descripcion debe tener de 5 a 20 caracteres" required/>
	</div>
	</div>
	<div class="form-group row">
	<div class="col-xs-3">
	<%String ruccl = (String)request.getAttribute("rucCL");%>
		<label for="rucCL">RUC N° :</label>
		<input class="form-control" type="text" name="rucCL" maxlength="11" value=<%=ruccl%> pattern="((10|20)[0-9]{9})" title="Este Ruc no es valido" required/>
	</div>
	</div>
	<div class="form-group row">
	<div class="col-xs-3">
	<%String direc = (String)request.getAttribute("direccion");%>
		<label for="direccion">Direccion(Nombre-Numero) :</label>
		<input class="form-control" type="text" name="direccion" maxlength="30" value=<%=direc%> pattern="[a-Z]{5,30}" title="La descripcion debe tener de 5 a 30 caracteres" required/>
	</div>
	</div>
	<div class="form-group row">
	<div class="col-xs-3">
	<%String fch = (String)request.getAttribute("fecha");%>
		<label for="fecha">Fecha(YYYY-MM-DD) :</label>
		<input class="form-control" type="text" name="fecha" maxlength="20" value=<%=fch%> pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" title="Ingrese formato correcto" required/>
	</div>
	</div>
	<div class="container">
	<form>
	<div class="form-group row">
	<%int con = (int)request.getAttribute("pedido");%>
	<%if(con>0){ %>
	<div class="col-xs-1">
	<label for="ex2">Cantidad:</label>
	<%ArrayList<Double> cant = (ArrayList<Double>)request.getAttribute("cant");%>
	<%for(int i=0;i<con;i++ ) {%>
	<%if(con>0&&i<con-1) {%>
	<input class="form-control" type="text" id="ex2" name=<%="can"+i%> value=<%=cant.get(i)%> maxlength="4" pattern="[0-9]{1,2}(.[0-9]{1})?" required/>
	<%}else{%>
	<input class="form-control" type="text" id="ex2" name=<%="can"+i%>  maxlength="4" pattern="[0-9]{1,2}(.[0-9]{1})?" required/>
	<%}%>
	<%}%>
	</div>
	<div class="col-xs-4">
    <label for="ex4">Descripcion:</label>
    <%ArrayList<Double> desc = (ArrayList<Double>)request.getAttribute("desc");%>
	<%for(int i=0;i<con;i++ ) {%>
	<%if(con>0&&i<con-1) {%>
	<input class="form-control" type="text" id="ex4" name=<%="des"+i%> value=<%=desc.get(i)%> maxlength="20" pattern="[a-Z]{3,20}" title="La descripcion debe tener de 5 a 20 caracteres" required/>
	<%}else{%>
	<input class="form-control" type="text" id="ex4" name=<%="des"+i%>  maxlength="20" pattern="[a-Z]{3,20}" title="La descripcion debe tener de 3 a 20 caracteres" required/>
	<%}%>
	<%}%>
	</div>
	<div class="col-xs-2">
	<label for="ex2">Precio unitario:</label>
	<%ArrayList<Double> unit = (ArrayList<Double>)request.getAttribute("unit");%>
	<%for(int i=0;i<con;i++ ) {%>
	<%if(con>0&&i<con-1) {%>
	<input class="form-control" type="text" id="ex2" name=<%="uni"+i%> value=<%=unit.get(i)%> maxlength="7" pattern="[0-9]{1,4}(.[0-9]{1,2})?" required/>
	<%}else{%>
	<input class="form-control" type="text" id="ex2" name=<%="uni"+i%>  maxlength="7" pattern="[0-9]{1,4}(.[0-9]{1,2})?" required/>
	<%}%>
	<%}%>
	<label for="ex2">Total:</label>
	</div>
    <div class="col-xs-2">
	<label for="ex2">Valor de venta:</label>
	<%ArrayList<Double> valo = (ArrayList<Double>)request.getAttribute("valo");%>
	<%double tot = (double)request.getAttribute("final");%>
	<%for(int i=0;i<con;i++ ) {%>
	<%if(con>0&&i<con-1) {%>
	<input class="form-control" type="text" id="ex2" name=<%="val"+i%> value=<%=valo.get(i)%> maxlength="20" readonly/>
	<%}else{%>
	<input class="form-control" type="text" id="ex2" name=<%="val"+i%>  maxlength="20" readonly/>
	<%}%>
	<%}%>
	<input class="form-control" type="text" id="ex2" name="final" value=<%=tot%> maxlength="20" readonly/>
	</div>
	</div>
	</form>
	</div>
	<input type="submit" class="btn btn-primary" name="agr" value="agregar pedido" class="sign-up-button"/><p>
		<%}else{%>
		<p>la lista de pedidos esta vacia.<input type="submit" class="btn btn-primary" name="agr" value="agregar pedido" class="sign-up-button"/>
		<%}%>
	</div>
	<p>
	<div div class="container">
		<div class="form-group row">
		<input type="reset" class="btn btn-primary" value="Limpiar"/>
		<input type="submit" class="btn btn-primary" name="enviar" value="Enviar"/>
		</div>
	</div>
</fieldset>
</form>
</body>
</html>