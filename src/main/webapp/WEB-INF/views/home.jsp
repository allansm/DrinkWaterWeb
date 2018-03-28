<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="resources/style/estilo.css">
<title>DrinkWater</title>
</head>
<body>
    <nav class="navbar bblue1 op1" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="./">DrinkWater</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <li><a href="./" class="">Home</a></li>
	          	<li><a href="./registro" class="">Registrar</a></li>
	          	<li><a href="./historico" class="">Historico</a></li>
	            
            </ul>
            <ul class="nav navbar-nav navbar-right">
            	<li><a href="">${login}</a></li>
                <li><a href="./logout">Logout</a></li>
            </ul>
        </div>
    </nav>

    <div class="container">
      <div class="row row-offcanvas row-offcanvas-right " style="padding:20px">
		<h2>${title}</h2>
        <div class="col-xs-12 col-sm-9" >
         	<div class="row">
		        <c:forEach var="copos" items="${lista2}" varStatus="id">
		        	<c:set var="color" value="#e55"/>
		        	<c:if test="${copos.times >= 8 }">
		        		<c:set var="color" value="#5e5"/>
		        	</c:if>
					<div class="col-xs-12 col-lg-4 op1" style="background:${color};height:200px;">
						<h2 class="op">${copos.cupDate}</h2>
						<p class="op">
						Total bebido:${copos.times}<br/>
						<div style="overflow-y:auto;max-height:80px" class="op">
						${lista3.get(id.count-1)}
						</div>
						</p>
					</div>
				</c:forEach>
				<c:if test="${showForm}">
					<div class="col-xs-12 col-lg-12 bwhite1 " style="padding:20px;text-align:center">
						Copos hoje : ${todayCup.getTimes()}<br/><br/>
						Usuario : ${login}<br/><br/>
						Data : ${todayCup.getCupDate()}<br/><br/>
					<form method="post" action="./registrar" class="form">
						Quantidade : <input class=" " type="number" style="width:50px" name="quantidade"><br/><br/><input type="submit" value="Registrar" class="btn btn-primary btn-lg btn-block" style="border-radius:0px">
					</form>
					</div>
				</c:if>
				<table class="table bwhite1">
				<c:forEach var="historico" items="${UserHistory}" varStatus="id">
						<tr>
							<c:if test="${id.count == 1}">
								<td>#</td>
								<td>Quantidade</td>
								<td>Data</td>
								<td>Hora</td>
							</c:if>
						</tr>
						<tr>
							<td>${historico.id}</td>
							<td>${historico.times}</td>
							<td>${historico.cupDate}</td>
							<td>${historico.cupTime}</td>
						</tr>
				</c:forEach>
				</table>
        	</div>
        </div>
		
        <div class="col-xs-12 col-sm-3 sidebar-offcanvas" id="sidebar">
          <div class="list-group">
          	<a href="./" class="list-group-item">Home</a>
          	<a href="./registro" class="list-group-item">Registrar</a>
          	<a href="./historico" class="list-group-item">Historico</a>
            <a href="./logout" class="list-group-item">Logout</a>
          </div>
        </div>
      </div>

      <br/>

      <footer>
        <p>© 2018 Allan de souza melo.</p>
      </footer>

    </div><!--/.container-->


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
   	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
  

</body>
</html>