<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Great Ideas</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<div>
			<h1>Create a new idea</h1>
		</div>
		<div>
			<form:form action="/ideas/new" method="post" modelAttribute="idea">
				<p>
					<form:label path="ideaName">Content:</form:label>
					<form:errors class="text-danger" path="ideaName" />
					<form:input path="ideaName" />
				</p>
				<form:hidden value="${user.id }" path="creator" />
				<input type="submit" value="Create" />
			</form:form>
		</div>
	</div>
</body>
</html>