<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>
	<div class="container">
		<h1>
			Welcome,
			<c:out value="${user.firstName} ${user.lastName}" />
		</h1>
		<h3>Ideas</h3>
		<div>
			<table class="table table-striped">
				<thead>
					<tr>
						<th>Idea</th>
						<th>Creator</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ideas}" var="idea">
						<tr>
							<td><a href="/idea/${idea.id}">${idea.ideaName}</a></td>
							<td><c:out
									value="${idea.creator.firstName} ${idea.creator.lastName}" /></td>
							<td><a href="">Like</a> <a href="">Unlike</a>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<p>
			<a class="btn btn-info" href="/ideas/new">New Idea</a>
		</p>
		<a class="btn btn-info" href="/logout">Logout</a>
	</div>

</body>
</html>