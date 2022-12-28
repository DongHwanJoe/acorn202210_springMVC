<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/gallery1/upload.jsp</title>
</head>
<body>
	<script>
		alert("사진 업로드에 성공했습니다.");
		location.href = "${pageContext.request.contextPath}/gallery1/list";
	</script>
</body>
</html>