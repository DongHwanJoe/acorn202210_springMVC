<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/gallery/detail.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<%-- 만일 이전글(더 옛날글)의 글번호가 0 가 아니라면(이전글이 존재 한다면) --%>
		<c:if test="${dto.prevNum ne 0}">
			<a
				href="detail?num=${dto.prevNum }&condition=${condition}&keyword=${encodedK}">이전글</a>
		</c:if>

		<%-- 만일 다음글(더 최신글)의 글번호가 0 가 아니라면(다음글이 존재 한다면) --%>
		<c:if test="${dto.nextNum ne 0 }">
			<a
				href="detail?num=${dto.nextNum }&condition=${condition}&keyword=${encodedK}">다음글</a>
		</c:if>
		<br /> <a href="${pageContext.request.contextPath}/gallery/list">목록으로</a>

		<%-- 만일 검색 키워드가 있다면 --%>
		<c:if test="${not empty keyword }">
			<p>
				<strong>${condition }</strong> 조건 <strong>${keyword }</strong> 검색어로
				검색된 내용 자세히 보기
			</p>
		</c:if>
		<h3>그림 상세 보기</h3>
		<table>
			<tr>
				<th>글번호</th>
				<td>${dto.num }</td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${dto.writer }</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>${dto.caption }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td>${dto.regdate }</td>
			</tr>
			<tr>
				<td colspan="2">
					<img src="${pageContext.request.contextPath }${dto.imagePath}">
				</td>
			</tr>
		</table>
		<c:if test="${sessionScope.id eq dto.writer }">
			<a href="updateform?num=${dto.num }">수정</a>
			<a href="javascript:" onclick="deleteConfirm()">삭제</a>
		</c:if>
	</div>
	<script>
		function deleteConfirm(){
			const isDelete=confirm("이 글을 삭제 하겠습니까?");
			if(isDelete){
				location.href="delete?num=${dto.num}";
			}
		}
	</script>
</body>
</html>