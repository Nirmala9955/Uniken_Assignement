<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container p-3 my-3 bg-primary text-white">
		<h2 style="text-align: center">Corporate Registration</h2>
		<h2 style="text-align: left">
			<a href="home" class="btn btn-secondary btn-lg active">Home</a>
		</h2>
	</div>
	<div class="container">
		<form action="addCorp" method="get">
			<table class="table table-dark table-striped table-sm">
				<tr>
					<th>Corporate Name</th>
					<th><input type="text" name="cname" size="20" /></th>
				</tr>
				<tr>
					<th>Corporate Id</th>
					<th><input type="text" name="cid" size="20" /></th>
				</tr>
				<tr>
					<th>Account Number</th>
					<th><input type="text" name="account" /></th>
				</tr>
				<tr>
					<th colspan="2"><input type="submit" class="btn btn-outline-light" value="Register" />
				</tr>
			</table>
		</form>
	</div>
</body>
</html>