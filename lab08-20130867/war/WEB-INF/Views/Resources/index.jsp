<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.*"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.*"%>

<html lang="es">
<head>
<title>Index Resources</title>
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
  <li><a href="/resources/add">Registro</a>
</ul>
<%
List<Resource> resources = (List<Resource>) request.getAttribute("resources");
%>
<%if( resources.size()>0 ) {%>
<%int c=0;%>
<form action="resources" method="get">
<h2>Mostrando <%= resources.size() %> resultados de Recursos</h2>
<div class="container">
<table class="table table-hover">
    <thead>
      <tr>
        <th>ID°</th>
        <th>Name</th>
        <th>Status</th>
        <th>Created</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
    <%c=0; %>
    <%for( Resource p : resources ) {%>
      <tr class="info">
        <td><%= p.getId() %></td>
        <td><%= p.getName() %></td>
        <td><%= p.getStatus() %></td>
        <td><%= p.getCreated() %></td>
        <td>
        <div class="Cell">
        <button type="submit" value=<%=c%> name="edit" class="btn btn-default">Edit</button>
        <button type="submit" value=<%=c%> name="del" class="btn btn-default">Del</button>
        </div>
        </td>
      </tr>
      <%c++; %>
      <%}%>
    </tbody>
  </table>
</div>
</form>
<%}else{%>
	<p>No hay Recursos todavia.<a href="/resources/add">Haga clic aquí para Agregar recursos.</a>
<%}%>
</body>
</html>