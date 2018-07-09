<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.*"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.*"%>

<html lang="es">
<head>
<title>Index Roles</title>
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
  <li><a href="/users/index">Regresar...</a>
</ul>
<h2>Facturas</h2>
<div class="container">
<table>
      <tr>
        <td><a href="/invoices/listFacturas">Lista de facturas</a></td>
      </tr>
      <tr>
        <td><a href="/invoices/saveFactura">Nueva factura</a></td>
      </tr>
      <tr>
        <td><a href="/invoices/editFacturas">Editar factura</a></td>
      </tr>
      <tr>
        <td><a href="/invoices/removeFactura">Eliminar todas las Facturas</a></td>
      </tr>
    </table>
</div>
</body>
</html>