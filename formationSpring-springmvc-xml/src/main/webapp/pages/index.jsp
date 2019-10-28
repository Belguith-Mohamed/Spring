<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body>
	<h2>Welcome to Client Management</h2>
	<c:choose>
		<c:when test="${clients.size()==0}">
			<em>No registered clients.</em>
		</c:when>
		<c:otherwise>

			<div class="clients">
				<table>
					<thead>
						<tr>
							<td>client ID</td>
							<td>client FirstName</td>
							<td>client LastName</td>
							<td>client Email</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="clt" items="${clients}">
							<tr>
								<td>${clt.id}</td>
								<td>${clt.firstName}</td>
								<td>${clt.lastName}</td>
								<td>${clt.email}</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>
