
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
		<h2 style="text-align: center">Corporate Informations</h2>
		<h2 style="text-align: left">
			<a href="home" class="btn btn-secondary btn-lg active">Home</a>
		</h2>
	</div>
	<div class="container">
		<c:choose>
		<c:when test="${mapAllCorp ne null && !empty mapAllCorp}">
			<table class="table table-dark table-striped table-sm">
				<tr>
					<th>Corporate Name</th>
					<th>Corporate ID</th>
					<th>Account No</th>
					<th>Manage</th>
				</tr>
				<c:forEach items="${mapAllCorp}" var="mapElement">
					<tr>
						<td>${mapElement.key}</td>
						<td colspan="3">
							<table class="table table-dark table-striped table-sm">
								<c:forEach items="${mapElement.value}" var="mapValue">
									<tr>
										<td>${mapValue.key}</td>
										<td>
											<table class="table table-dark table-striped table-sm">
												<c:forEach items="${mapValue.value}" var="listElement">
													<tr>
														<td>${listElement.accountNo}</td>
														<td><a href="editForm?crid=${listElement.corporateRID}"> <svg
										xmlns="http://www.w3.org/2000/svg" width="30" height="30"
										fill="currentColor" class="bi bi-pencil" viewBox="0 0 16 16">
  									<path
											d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z" />
								</svg>
							</a> <a href="deleteCorp?crid=${listElement.corporateRID}"
								onclick="confirm('Are you sure to delete')"> <svg
										xmlns="http://www.w3.org/2000/svg" width="30" height="30"
										fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
  									<path
											d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z" />
  									<path fill-rule="evenodd"
											d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z" />
								</svg>
							</a></td>
													<tr>
												</c:forEach>
											</table>
										</td>
									</tr>
								</c:forEach>
							</table>
						</td>	
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>
</div>
</body>
</html>

<