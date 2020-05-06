<%@page import="com.Pharmacy"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pharmacy Service</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/pharmacys.js"></script>
</head>
<body>

<div class="container">
		<div class="row">
			<div class="col-6">
				
				<h1 class="m-3">Pharmacy Service</h1>
			
				<form id="formPharmacy" name="formPharmacy" method="post" action="pharmacys.jsp">
					Hospital Name : 
					<input id="name" name="name" type="text" class="form-control form-control-sm"><br>
					Hospital Address : 
					<input id="owner" name="owner" type="text" class="form-control form-control-sm"><br>
					Hospital Email : 
					<input id="address" name="address" type="text" class="form-control form-control-sm"><br>
					Hospital Phone : 
					<input id="email" name="email" type="text" class="form-control form-control-sm"><br>
					Hospital Charge : 
					<input id="phone" name="phone" type="text" class="form-control form-control-sm"><br>
					
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"><br>
					<input id="hidHosIDSave" name="hidHosIDSave" type="hidden" value="Save" >
				</form>
				<br>
				<div id="alertSuccess" class="alert alert-success">
				</div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<%
					Pharmacy itemObj = new Pharmacy();
					out.print(itemObj.view());
				%>
			
			</div>
		</div>	
	</div>

</body>
</html>