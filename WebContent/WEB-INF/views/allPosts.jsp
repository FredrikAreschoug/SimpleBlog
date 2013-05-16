<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="java.util.List"%>
<%@page import="bloggy.entities.Post"%>
<fmt:setLocale value="se_SE"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
		<header>
	<c:if test="${ username != null }">
			<form action="${pageContext.request.contextPath}/logout" method="post">
				<p>Hej <c:out value="${username}" /></p>
				<input type="submit" value="logout">
			</form>
			<form action="${pageContext.request.contextPath}/createNewPost" method="get">
				<input type="submit" value="New Post">
			</form>	
			
	</c:if>
	<c:if test="${ username == null }">
			<form action="${pageContext.request.contextPath}/login" method="post">
				<input type="text" name="username" placeholder="Username" required="required">
				<input type="password" name="password" placeholder="Password" required="required">
				<input type="submit" value="login">
			</form>
	</c:if>			
		</header>
	<c:if test="${ newPost }">
		<form action="${pageContext.request.contextPath}/newPost " method="post">
			<div>
				<dl>
					<dt><lable for="newPost">New post</lable></dt>
					<dd><textarea rows="5" cols="80" id="post" name="post"></textarea></dd>
				</dl>
				<input type="submit" name="post" value="Post">
			</div>
		</form>
	</c:if>
		<ol>
		<c:forEach var="post" items="${posts}">
			<li>
			<c:out value="${ post.name }"/> - <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${post.date}" /> <c:out value="${ post.date }"/></br>
			<c:out value="${ post.post }"/>
			<form action="${pageContext.request.contextPath}/post/<c:out value="${ post.id }"/> " method="post">
				<input type="hidden" name="id" value="${ post.id }">
				<input type="submit" value="Kommentera" name="get" >
			</form>
			</li>
		</c:forEach>
		</ol>
</body>
</html>