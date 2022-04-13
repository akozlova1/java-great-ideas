<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration Page</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<!-- <p class="text-danger"><c:out value ="${login-msg }"/></p>-->
				<h1>Register!</h1>


				<form:form class="form-group push" method="POST"
					action="/registration" modelAttribute="user">
					<p>
						<form:label path="firstName">First Name:</form:label>
						<form:input type="text" path="firstName" /><br/>
						<form:errors class="text-danger" path="firstName" />
					</p>
					<p>
						<form:label path="lastName">Last Name:</form:label>
						<form:input type="text" path="lastName" /><br/>
						<form:errors class="text-danger" path="lastName" />
					</p>

					<p>
						<form:label path="email">Email:</form:label>
						<form:input type="email" path="email" /><br/>
						<form:errors class="text-danger" path="email" />
					</p>
					<p>
						<form:label path="password">Password:</form:label>
						<form:password path="password" /><br/>
						<form:errors class="text-danger" path="password" />
					</p>
					<p>
						<form:label path="passwordConfirmation">Password Confirmation:</form:label>
						<form:password path="passwordConfirmation" /><br/>
					</p>
					<input class="btn btn-success mt3" type="submit" value="Register" />
				</form:form>
			</div>
			<div class="col">
				<h1>Login</h1>
				<p>
					<p class="text-danger" ><c:out value="${error}" /></p>
				</p>
				<form method="post" action="/login">
					<p>
						<label for="email">Email</label> <input type="text" id="email"
							name="email" />
					</p>
					<p>
						<label for="password">Password</label> <input type="password"
							id="password" name="password" />
					</p>
					<input class="btn btn-success mt3" type="submit" value="Login" />
				</form>
			</div>
		</div>
	</div>

</body>
</html>