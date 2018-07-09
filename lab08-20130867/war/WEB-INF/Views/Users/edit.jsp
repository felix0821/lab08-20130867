<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="controller.users.*"%>
<%@ page import="model.entity.*"%>
<%@ page import="java.util.*"%>

<html lang="es">
<head>
<title>Edit User</title>
<meta charset="utf-8">
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script language="JavaScript" type="text/javascript">
    ahora          = new Date();
    ahoraDay    = ahora.getDate();
    ahoraMonth = ahora.getMonth();
    ahoraYear   = ahora.getYear();

 if (ahoraYear < 2000)
        ahoraYear += 1900;
 
 function cuantosDias(mes, anyo)
 {
     var cuantosDias = 31;
     if (mes == "April" || mes == "June" || mes == "September" || mes == "November") cuantosDias = 30;
     if (mes == "February" && (anyo/4) != Math.floor(anyo/4)) cuantosDias = 28;
     if (mes == "February" && (anyo/4) == Math.floor(anyo/4)) cuantosDias = 29;
     return cuantosDias;
 }

    function asignaDias() {
        comboDias = document.formFecha.selectDay;
        comboMeses = document.formFecha.selectMonth;
        comboAnyos = document.formFecha.selectYear;
        Month = comboMeses[comboMeses.selectedIndex].text;
        Year = comboAnyos[comboAnyos.selectedIndex].text;
        diasEnMes = cuantosDias(Month, Year);
        diasAhora = comboDias.length;
        if (diasAhora > diasEnMes) {
            for (i=0; i<(diasAhora-diasEnMes); i++) {
                comboDias.options[comboDias.options.length - 1] = null } }
        if (diasEnMes > diasAhora) {
            for (i=0; i<(diasEnMes-diasAhora); i++) {
                sumaOpcion = new Option(comboDias.options.length + 1);
                comboDias.options[comboDias.options.length]=sumaOpcion; } }
        if (comboDias.selectedIndex < 0)
          comboDias.selectedIndex = 0; }

    function ponDia() {
        comboDias = eval("document.formFecha.selectDay");
        comboMeses = eval("document.formFecha.selectMonth");
        comboAnyos = eval("document.formFecha.selectYear");
        asignaDias();}

    function rellenaAnyos(masAnyos, otro) {
        cadena = "";
        for (i=0; i<masAnyos; i++){
        	if((ahoraYear - i)==otro){
        		cadena += "<option selected>";
                cadena += ahoraYear - i;
        	}else{
        		cadena += "<option>";
                cadena += ahoraYear - i;
        	}}
        return cadena; }
 </script>
</head>
<body onLoad="ponDia();">
<ul class="pager">
<li><a href="/users">Regresar...</a></li>
<li><a href="/users/index">Inicio</a></li>
</ul>
<!-- Menú de navegación del sitio -->

<form action="users/edit" method="get" name="formFecha">
<fieldset><legend>Edit user</legend>
<div class="container">
<%List<User> users = (List<User>) request.getAttribute("users");%>
<%for( User u : users){%>
    <div class="form-group row">
	<div class="col-xs-3">
		<label for="email">ID :</label>
		<input class="form-control" type="text" name="id" maxlength="30" value=<%=u.getId()%> readonly/>
	</div>
	</div>
    <div class="form-group row">
	<div class="col-xs-3">
	<%List<Role> roles = (List<Role>) request.getAttribute("roles");%>
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
		<label for="email">Email(gmail) :</label>
		<input class="form-control" type="text" name="email" maxlength="30" value=<%=u.getEmail()%> pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" title="La descripcion debe ser un correo valido" required/>
	</div>
	</div>
	<div class="form-group row">
	<div class="col-xs-2">
	<label for="email">Birth :</label>
	</div>
	</div>
	<div class="form-group row">
	<div class="col-xs-1">
	<label for="selectDay">Day :</label>
  <select name="selectDay" class="form-control" id="sel1">
  <%for(int i=1;i<32;i++){ %>
  <%if(i==u.getBirthD()){%><option selected><%=i%></option><%}else{%><option><%=i%></option><%}%>
  <%}%>
  </select>
  </div>
  <div class="col-xs-2">
  <label for="selectMonth">Month :</label>
  <select name="selectMonth" class="form-control" id="sel1" onchange="asignaDias()">
    <%if(u.getBirthM().equals("January")){%><option selected>January</option><%}else{%><option>January</option><%}%>
    <%if(u.getBirthM().equals("February")){%><option selected>February</option><%}else{%><option>February</option><%}%>
    <%if(u.getBirthM().equals("March")){%><option selected>March</option><%}else{%><option>March</option><%}%>
    <%if(u.getBirthM().equals("April")){%><option selected>April</option><%}else{%><option>April</option><%}%>
    <%if(u.getBirthM().equals("May")){%><option selected>May</option><%}else{%><option>May</option><%}%>
    <%if(u.getBirthM().equals("June")){%><option selected>June</option><%}else{%><option>June</option><%}%>
    <%if(u.getBirthM().equals("July")){%><option selected>July</option><%}else{%><option>July</option><%}%>
    <%if(u.getBirthM().equals("August")){%><option selected>August</option><%}else{%><option>August</option><%}%>
    <%if(u.getBirthM().equals("September")){%><option selected>September</option><%}else{%><option>September</option><%}%>
    <%if(u.getBirthM().equals("October")){%><option selected>October</option><%}else{%><option>October</option><%}%>
    <%if(u.getBirthM().equals("November")){%><option selected>November</option><%}else{%><option>November</option><%}%>
    <%if(u.getBirthM().equals("December")){%><option selected>December</option><%}else{%><option>December</option><%}%>
  </select>
  </div>
  <div class="col-xs-2">
  <label for="selectYear">Year :</label>
  <select name="selectYear" class="form-control" id="sel1" onchange="asignaDias()">
    <script language="JavaScript" type="text/javascript">
        document.write(rellenaAnyos(80,<%=u.getBirthY()%>));
    </script>
  </select>
  </div>
   </div>
  <div class="col-xs-2">
	<div class="form-group row">
	<label class="checkbox-inline">
	<%if(u.getGender()){%>
	  <input type="checkbox" name="gender" value="" checked>Gender
	<%}else{%>
      <input type="checkbox" name="gender" value="">Gender
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