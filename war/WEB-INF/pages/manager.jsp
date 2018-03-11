<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="resources/css/manager.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="container">
			<h1>Folders</h1>
			<div class="row">
				<div class="col-sm-10">
					<table id="foldertable" class="table table-hover" cellspacing="0"
						width="100%">
						<thead class="updown">
							<tr>
								<th>Folder Name</th>
								<th>Path</th>
								<th>Action</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>Folder Name</th>
								<th>Path</th>
								<th>Action</th>
							</tr>
						</tfoot>
						<tbody id="tablefolders">
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="container">
			<h1>Files</h1>
			<div class="row">
				<div class="col-sm-10">
					<table id="example" class="table table-hover" cellspacing="0"
						width="100%">
						<thead class="updown">
							<tr>
								<th>File Name</th>
								<th>Path</th>
								<th>Action</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<th>File Name</th>
								<th>Path</th>
								<th>Action</th>
							</tr>
						</tfoot>
						<tbody id="tablefiles">
						</tbody>
					</table>
				</div>
			</div>
		</div>
		
		<div class="container">
			<div id="stepBackDiv"></div>
			<div id="folderForm"></div>
			<div id="fileForm"></div>
		</div>
		
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<div class="conteiner">
				<form action="<%=request.getContextPath()%>/changerole" method="get">
					<input type="submit" value="Change User's Role"/> 
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
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
	<script src="resources/js/manager.js"></script>
	<script src="resources/js/managertable.js"></script>
	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
</body>
</html>
