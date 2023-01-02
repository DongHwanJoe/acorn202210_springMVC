<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/views/gallery/uploadform.jsp</title>
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
		<h3>그림 업로드 폼입니다.</h3>
		
		<form action="${pageContext.request.contextPath}/gallery/upload" method="post">
			<input type="hidden" name="imagePath" value="empty" />
			<div>
				<label for="caption">제목</label> 
				<input type="text" name="caption" id="caption" />
			</div>
			
			<a id="imagePathLink" href="javascript:">
				<svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
					<path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z"/>
					<path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z"/>
				</svg>
			</a>
			<br />
			<button type="submit">업로드</button>
		</form>
		<a href="${pageContext.request.contextPath}/gallery/list">취소</a>
		
		<form id="imageForm" action="${pageContext.request.contextPath}/gallery/image_upload" method="post" enctype="multipart/form-data">
			그림 사진
			<input type="file" id="image" name="image" accept=".jpg, .png, .gif, .jpeg" />
			<button type="submit">업로드</button>
		</form>
		
	</div>
	
	<!-- gura_util.js 로딩 -->
	<script src="${pageContext.request.contextPath }/resources/js/gura_util.js"></script>
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
</body>
</html>