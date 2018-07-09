<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.users.*"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.*"%>

<html lang="es">
<head>
<title>Edit Access</title>
<meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<ul class="pager">
<li><a href="/access">Regresar...</a></li>
<li><a href="/users/index">Inicio</a></li>
</ul>
<!-- Menú de navegación del sitio -->

<form action="access/edit" method="get">
<fieldset><legend>Edit access</legend>
<div class="container">
<%List<Access> access = (List<Access>) request.getAttribute("access");%>
<%for( Access u : access){%>
<%List<Role> roles = (List<Role>) request.getAttribute("roles");%>
<%List<Resource> resources = (List<Resource>) request.getAttribute("resources");%>
    <div class="form-group row">
	<div class="col-xs-3">
		<label for="email">ID :</label>
		<input class="form-control" type="text" name="id" maxlength="30" value=<%=u.getId()%> readonly/>
	</div>
	</div>
    <div class="form-group row">
	<div class="col-xs-3">
		<label for="sel1">Rol:</label>
		<%if(roles.size()>0 ){%>
      <select class="form-control" name="rol" id="sel1">
      <%for(Role r : roles){%>
      <%if(r.getId().equals(Long.toString(u.getRole()))){%>
        <option selected><%=r.getName() %></option>
      <%}else{%>
        <option><%=r.getName() %></option>
        <%}%>
        <%}%>
      </select>
      <%}else{%>
      <li><a href="add">Crear nuevo Rol</a></li>
      <%}%>
	</div>
	</div>
	<div class="form-group row">
	<div class="col-xs-3">
		<label for="sel1">Resource:</label>
		<%if(resources.size()>0 ){%>
      <select class="form-control" name="resourc" id="sel1">
      <%for(Resource r : resources){%>
      <%if(r.getId().equals(Long.toString(u.getResource()))){%>
        <option selected><%=r.getName() %></option>
      <%}else{%>
        <option><%=r.getName() %></option>
        <%}%>
        <%}%>
      </select>
      <%}else{%>
      <li><a href="add">Crear nuevo Recurso</a></li>
      <%}%>
	</div>
	</div>
  <div class="col-xs-2">
	<div class="form-group row">
	<label class="checkbox-inline">
	<%if(u.getStatus()){%>
	  <input type="checkbox" name="status" value="" checked>Status
	<%}else{%>
      <input type="checkbox" name="status" value="">Status
      <%}%>
    </label>
    </div>
	</div>
	<%}%>
	</div>
	</fieldset>
	<p>
	<div div class="container">
	<div class="col-xs-2">
		<div class="form-group row">
		<input type="submit" class="btn btn-primary" name="modify" value="MODIFY"/>
		</div>
		</div>
	</div>
	</form>

</body>
</html>