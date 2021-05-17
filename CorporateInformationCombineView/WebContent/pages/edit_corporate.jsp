<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
	<div class="container p-3 my-3 bg-primary text-white">
		<h2 style="text-align: center">Edit Corporate Information</h2>
		<h2 style="text-align: left">
			<a href="home" class="btn btn-secondary btn-lg active">Home</a>
		</h2>
	</div>
	<div class="container">
		<c:if test="${corpDetail ne null && !empty corpDetail}">
			<form action="updateCorp" method="post">

				<table class="table table-dark table-striped table-sm">
					<tr>
						<td colspan="2"><input type="hidden" name="corpRID"
							value="<c:out value='${corpDetail.corporateRID}'/>" /></td>
					</tr>
					<tr>
						<th>Corporate Name:</th>
						<td><input type="text" name="corpName" size="20"
							value="<c:out value='${corpDetail.corporateName}'/>" /></td>
					</tr>
					<tr>
						<th>Corporate Id:</th>
						<td><input type="text" name="corpId" size="20"
							value="<c:out value='${corpDetail.corporateId}' />" /></td>
					</tr>
					<tr>
						<th>Account No:</th>
						<td><input type="text" name="accNo"
							value="<c:out value='${corpDetail.accountNo}' />" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" class="btn btn-outline-light"  value="Update Corporate" /></td>
					</tr>
				</table>
			</form>
			</c:if>
	</div>
</body>
</html>

