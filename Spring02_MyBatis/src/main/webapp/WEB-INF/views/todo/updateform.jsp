<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/todo/updateform.jsp</title>
</head>
<body>
	<div class="container">
		<h1>할일 수정 폼 입니다.</h1>
		<form action="update" method="post">
			<input type="hidden" name="num" value="${dto.num }" />
			<div>
				<label for="num">번호</label>
				<input type="text" id="num" value="${dto.num }" readonly />
			</div>
			<div>
				<label for="content">할일</label>
				<input type="text" name="content" value="${dto.content }" />
			</div>
			<div>
				<label for="regdate">등록일</label>
				<input type="text" name="regdate" value="${dto.regdate }" readonly />
			</div>
			<button type="submit">수정확인</button>
			<button type="reset">되돌리기</button>
		</form>
		<div>
			<a href="${pageContext.request.contextPath}/todo/list">취소</a>
		</div>
	</div>
</body>
</html>