<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/todo/insertform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>할일 추가 폼 입니다.</h1>
		<form action="${pageContext.request.contextPath}/todo/insert" method="post">
			<div>
				<label for="content">할일</label>
				<input type="text" name="content" id="content" />
			</div>
			<button type="submit">추가</button>
		</form>
		<div>
			<a href="${pageContext.request.contextPath}/todo/list">취소</a>
		</div>
	</div>
</body>
</html>