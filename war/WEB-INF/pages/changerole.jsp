<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change role</title>
<style type="text/css">
.table-responsive {
	height: 250px;
}
</style>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>

	<div class="container">
		<div class="container">
			<h3>Users</h3>
			<div class="row">
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>User ID</th>
								<th>User Name</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody id="myTable">
							<c:forEach var="user" items="${users}">
								<tr>
									<td>${user.id}</td>
									<td>${user.username}</td>
									<td>
										<form action="/FileSystemManager/change" method="post">
											<input type="hidden" name="userid" value="${user.id}" /> <input
												type="hidden" name="rolename" value="ROLE_ADMIN" /> <input
												type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
											<button class="btn btn-info" type="Submit">Set Role
												Admin</button>
										</form>
									</td>
								</tr>
							</c:forEach>
					</table>
				</div>
				<div class="col-md-12 text-center">
					<ul class="pagination pagination-lg pager" id="myPager"></ul>
				</div>
			</div>
		</div>
		<br>

		<div class="container">
			<h3>Admins</h3>
			<div class="row">
				<div class="table-responsive">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Admin ID</th>
								<th>Admin Name</th>
								<th>Action</th>
							</tr>
						</thead>
						<tbody id="myTable">
							<c:forEach var="user" items="${admins}">
								<tr>
									<td>${user.id}</td>
									<td>${user.username}</td>
									<td>
										<form action="/FileSystemManager/change" method="post">
											<input type="hidden" name="userid" value="${user.id}" /> <input
												type="hidden" name="rolename" value="ROLE_USER" /> <input
												type="hidden" name="${_csrf.parameterName}"
												value="${_csrf.token}" />
											<button class="btn btn-info" type="Submit">Set Role
												User</button>
										</form>
									</td>
								</tr>
							</c:forEach>
					</table>
				</div>
				<div class="col-md-12 text-center">
					<ul class="pagination pagination-lg pager" id="myPager"></ul>
				</div>
			</div>
		</div>

		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div class="conteiner">
				<form action="<%=request.getContextPath()%>/manager" method="get">
					<input type="submit" value="File System Manager" /> <input
						type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				</form>
			</div>
		</sec:authorize>
		<div class="conteiner">
			<form action="<%=request.getContextPath()%>/appLogout" method="POST">
				<input type="submit" value="Logout" /> <input type="hidden"
					name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</div>
	</div>

	<meta name="_csrf" content="${_csrf.token}" />
	<meta name="_csrf_header" content="${_csrf.headerName}" />

	<script src="resources/js/changerole.js"></script>

</body>
</html>