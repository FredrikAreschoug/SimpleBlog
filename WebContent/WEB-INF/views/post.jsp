<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.List"%>
<%@page import="bloggy.entities.Post"%>
<%@page import="bloggy.entities.Comment"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
	</head>
	<body>
		<header>
		<c:if test="${ username != null }">
			<form action="${pageContext.request.contextPath}/logout" method="post">
				<input type="submit" value="logout">
				<p>Hej <c:out value="${username}" /></p>
			</form>
		</c:if>	
		<c:if test="${ username == null }">
			<form action="${pageContext.request.contextPath}/login" method="post">
				<input type="text" name="username" placeholder="Username" required="required">
				<input type="password" name="password" placeholder="Password" required="required">
				<input type="submit" value="login">
				<p>Hej!</p>
			</form>
		</c:if>				
		</header>
		<form action="${pageContext.request.contextPath}/comment " method="post">
			<div>
				<p>
		<c:forEach var="post" items="${posts}">
		<c:out value="${ postId  }"/>
			<c:if test="${ post.id == sessionScope.postId }">						
						<c:out value="${ post.name }"/> - <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${post.date}" /> <c:out value="${ post.date }"/></br>
						<c:out value="${ post.post }"/>
					</c:if>	
				</c:forEach>				
				</p>
			</div>
			<div>
				<dl>
					<dt><lable for="name">Name</lable></dt>
					<dd><input type="text" id="name" name="name" value="${ username }"></dd>
					<dt><lable for="comment">Kommentar</lable></dt>
					<dd><textarea rows="5" cols="80" id="comment" name="comment"></textarea></dd>
				</dl>
				<input type="submit" value="Kommentera">
			</div>
		</form>
		<ol>
		<c:forEach var="comment" items="${comments}">
			<c:if test="${ comment.postId == sessionScope.postId }">
			<li>
			<c:out value="${ comment.name }"/> - <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${comment.date}" /> <c:out value="${ comment.date }"/>
			<c:out value="${ comment.comment }"/>
			</c:if>	
		</c:forEach>
			</li>
		</ol>
		<form action="${pageContext.request.contextPath}/Home " method="get">
			<input type="submit" value="Home">	
		</form>
	</body>
</html>