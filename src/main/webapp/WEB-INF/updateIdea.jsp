<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<%@ page isErrorPage="true" %>

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
			<h1>
				Edit
				<c:out value="${idea.ideaName }"></c:out>
			</h1>
		</div>
		<div>
			<form:form action="/ideas/${idea.id }/edit" method="post"
				modelAttribute="idea">
				<input type="hidden" name="_method" value="put">
				<p>
					<form:label path="ideaName">Content:</form:label>
					<form:errors class="text-danger" path="ideaName" />
					<form:input path="ideaName" />
				</p>
				<form:hidden value="${user.id }" path="creator" />
				<input type="submit" value="Update" />
			</form:form>
		</div>
		<form action="/ideas/${idea.id}/delete" method="post">
			<input type="hidden" name="_method" value="delete"> <input
				class="btn btn-danger" type="submit" value="Delete">
		</form>
	</div>
</body>
</html>