<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/gallery/updateform.jsp</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<style>
	/* 이미지 업로드 폼을 숨긴다 */
	#imageForm{
	   display: none;
	}
	#uploadImage{
	   width: 100px;
	   height: 100px;
	   border: 1px solid #cecece;
	}
</style>
</head>
<body>
	<div class="container">
		<h3>수정 폼입니다.</h3>
		<form action="update" method="post">
			<input type="hidden" name="imagePath" value="${dto.imagePath}" />
			<input type="hidden" name="num" value="${dto.num }" />
			<div>
			   <label for="writer">작성자</label>
			   <input type="text" id="writer" value="${dto.writer }" disabled/>
			</div>
			<div>
				<label for="caption">제목</label>
				<input type="text" name="caption" id="caption" value="${dto.caption }" />
			</div>
			<a id="imagePathLink" href="javascript:">
				<img src="${pageContext.request.contextPath }${dto.imagePath}">
			</a>
			<br />
			<button class="btn btn-primary" type="submit">수정확인</button>
			<a class="btn btn-danger" href="${pageContext.request.contextPath }/gallery/detail?num=${dto.num }">취소</a>
		</form>
		
		<form id="imageForm" action="${pageContext.request.contextPath}/gallery/image_upload" method="post" enctype="multipart/form-data">
			그림 사진
			<input type="file" id="image" name="image" accept=".jpg, .png, .gif, .jpeg" />
			<button type="submit">업로드</button>
		</form>
	</div>
	<!-- gura_util.js 로딩 -->
	<script src="${pageContext.request.contextPath }/js/gura_util.js"></script>
	<script>
	
		//프로필 이미지 링크를 클릭하면 
		document.querySelector("#imagePathLink").addEventListener("click", function(){
		   // input type="file" 을 강제 클릭 시킨다. 
		   document.querySelector("#image").click();
		});   
		
	      //프로필 이미지를 선택하면(바뀌면) 실행할 함수 등록
	      document.querySelector("#image").addEventListener("change", function(){
	         //ajax 전송할 폼의 참조값 얻어오기
	         const form = document.querySelector("#imageForm");
	         //gura_util.js 에 있는 함수를 이용해서 ajax 전송하기 
	         ajaxFormPromise(form)
	         .then(function(response){
	            return response.json();
	         })
	         .then(function(data){
	            console.log(data);
	            // input name="profile" 요소의 value 값으로 이미지 경로 넣어주기
	            document.querySelector("input[name=imagePath]").value = data.imagePath;
	            
	            // img 요소를 문자열로 작성한 다음
	            let img=`<img id="uploadImage" 
	               src="${pageContext.request.contextPath }\${data.imagePath}">`;
	            //id가 profileLink 인 요소의 내부(자식요소)에 덮어쓰기 하면서 html 형식으로 해석해 주세요. 라는 의미
	            document.querySelector("#imagePathLink").innerHTML=img;
	         });
	      });
	</script>
	
	<c:if test="${sessionScope.id ne dto.writer }">
		<script>
			alert("작성자만 수정할 수 있습니다.")
			location.href = "${pageContext.request.contextPath }/gallery/detail?num=${dto.num }";
		</script>
	</c:if>
</body>
</html>