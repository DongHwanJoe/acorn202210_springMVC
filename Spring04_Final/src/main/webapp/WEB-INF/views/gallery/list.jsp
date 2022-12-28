<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/gallery/list.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<a href="${pageContext.request.contextPath}/">인덱스로</a><br />
		<a href="${pageContext.request.contextPath}/gallery/uploadform">새 그림 업로드</a>
		<h3>갤러리 리스트</h3>
		
		<table class="table table-striped">
			<thead class="table-dark">
				<tr class="text-center row">
					<th class="col-1">글번호</th>
					<th class="col-2">작성자</th>
					<th class="col-3">제목</th>
					<th class="col-4">미리보기</th>
					<th class="col-2">작성일</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach var="tmp" items="${list }">
				<tr class="text-center row">
					<td class="col-1">${tmp.num }</td>
					<td class="col-2">${tmp.writer }</td>
					<td class="col-3">
						<a href="detail?num=${tmp.num }&condition=${condition}&keyword=${encodedK}">${tmp.caption }</a>
					</td>
					<td class="col-4">
						<img width="120" height="80" src="${pageContext.request.contextPath }${tmp.imagePath}">
					</td>
					<td class="col-2">${tmp.regdate }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<nav class="mt-2">
			<ul class="pagination">
				<%--
				  startPageNum 이 1 이 아닌 경우에만 Prev 링크를 제공한다. 
				  &condition=${condition}&keyword=${encodedK}
				--%>
				<c:if test="${startPageNum ne 1 }">
					<li class="page-item">
						<a class="page-link" href="list?pageNum=${startPageNum - 1 }&condition=${condition}&keyword=${encodedK}">Prev</a>
					</li>
				</c:if>
				<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
					<li class="page-item ${pageNum eq i ? 'active' : '' }">
						<a class="page-link" href="list?pageNum=${i }&condition=${condition}&keyword=${encodedK}">${i }</a>
					</li>
				</c:forEach>

				<%--
					마지막 페이지 번호가 전체 페이지의 갯수보다 작으면 Next 링크를 제공한다. 
				--%>
				<c:if test="${endPageNum lt totalPageCount }">
					<li class="page-item">
						<a class="page-link" href="list?pageNum=${endPageNum + 1 }&condition=${condition}&keyword=${encodedK}">Next</a>
					</li>
				</c:if>
			</ul>
		</nav>
		
		<!-- 검색 폼 -->
		<form action="list" method="get">
			<label for="condition">검색조건</label>
			<select name="condition" id="condition">
				<option value="caption" ${condition eq 'caption' ? 'selected' : '' }>제목</option>
				<option value="writer" ${condition eq 'writer' ? 'selected' : '' }>작성자</option>
			</select>
			<input type="text" name="keyword" placeholder="검색어..." value="${keyword }" />
			<button type="submit">검색</button>
		</form>
		<c:if test="${not empty condition }">
			<p>
				<strong>${totalRow }</strong>개의 자료가 검색 되었습니다.
				<a href="list">리셋</a>
			</p>
		</c:if>
		
	</div>
</body>
</html>