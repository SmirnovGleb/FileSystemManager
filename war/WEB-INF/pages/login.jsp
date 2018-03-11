<!doctype html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="resources/css/login.css" />

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">

<title>Hello, world!</title>
</head>
<body>
	<script language="javascript">
		
	</script>

	<div class="container">
		<div class="container">
			<nav class="navbar navbar-dark bg-dark"
				style="background-color: #e3f2fd;">
				<a class="navbar-brand" href="#"> <img
					src="https://png.icons8.com/color/50/000000/opened-folder.png"
					width="30" height="30" class="d-inline-block align-top" alt="">
					File System Manager
				</a>
			</nav>
		</div>

		<div class="container">
			<div class="container">
				<div class="wrapper">
					<form action="/FileSystemManager/appLogin" method="post"
						name="Login_Form" class="form-signin">
						<h3 class="form-signin-heading">Please Sign In</h3>
						<hr class="colorgraph">
						<br> <input type="text" class="form-control" name="username"
							placeholder="Username" required="" autofocus="" /> <input
							type="password" class="form-control" name="password"
							placeholder="Password" required="" /> <input type="hidden"
							name="${_csrf.parameterName}" value="${_csrf.token}" />
						<button class="btn btn-lg btn-primary btn-block" name="Submit"
							value="Login" type="Submit">Login</button>
						<a href="/FileSystemManager/registration">Registration</a>
					</form>
				</div>
			</div>
		</div>
	</div>

	<script src="resources/js/login.js"></script>
	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>