<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
	<%@ taglib prefix="ham" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>

<!-- =========================================================
* Sneat - Bootstrap 5 HTML Admin Template - Pro | v1.0.0
==============================================================

* Product Page: https://themeselection.com/products/sneat-bootstrap-html-admin-template/
* Created by: ThemeSelection
* License: You must have a valid license purchased in order to legally use the theme for your project.
* Copyright ThemeSelection (https://themeselection.com)

=========================================================
 -->
<!-- beautify ignore:start -->
<html lang="ko" class="light-style" dir="ltr" data-theme="theme-default"
	data-assets-path="assets/"
	data-template="vertical-menu-template-free">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />

<title>Detail</title>

<meta name="description" content="" />

<!-- Favicon -->
<link rel="icon" type="image/x-icon"
	href="assets/img/favicon/favicon.ico" />

<!-- Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
	rel="stylesheet" />

<!-- Icons. Uncomment required icon fonts -->
<link rel="stylesheet" href="assets/vendor/fonts/boxicons.css" />

<!-- Core CSS -->
<link rel="stylesheet" href="assets/vendor/css/core.css"
	class="template-customizer-core-css" />
<link rel="stylesheet" href="assets/vendor/css/theme-default.css"
	class="template-customizer-theme-css" />
<link rel="stylesheet" href="assets/css/demo.css" />

<!-- Vendors CSS -->
<link rel="stylesheet"
	href="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

<!-- Page CSS -->

<!-- Helpers -->
<script src="assets/vendor/js/helpers.js"></script>

<!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
<!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
<script src="assets/js/config.js"></script>
</head>

<body>
<!-- 헤더 -->

				<header class="container-xxl" style="margin-top: 30px;">
					<a href="main.do" class="app-brand-link"> <span
						class="app-brand-logo demo"> <svg width="25"
								viewBox="0 0 25 42" version="1.1"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:xlink="http://www.w3.org/1999/xlink">
                  <defs>
                    <path
									d="M13.7918663,0.358365126 L3.39788168,7.44174259 C0.566865006,9.69408886 -0.379795268,12.4788597 0.557900856,15.7960551 C0.68998853,16.2305145 1.09562888,17.7872135 3.12357076,19.2293357 C3.8146334,19.7207684 5.32369333,20.3834223 7.65075054,21.2172976 L7.59773219,21.2525164 L2.63468769,24.5493413 C0.445452254,26.3002124 0.0884951797,28.5083815 1.56381646,31.1738486 C2.83770406,32.8170431 5.20850219,33.2640127 7.09180128,32.5391577 C8.347334,32.0559211 11.4559176,30.0011079 16.4175519,26.3747182 C18.0338572,24.4997857 18.6973423,22.4544883 18.4080071,20.2388261 C17.963753,17.5346866 16.1776345,15.5799961 13.0496516,14.3747546 L10.9194936,13.4715819 L18.6192054,7.984237 L13.7918663,0.358365126 Z"
									id="path-1"></path>
                    <path
									d="M5.47320593,6.00457225 C4.05321814,8.216144 4.36334763,10.0722806 6.40359441,11.5729822 C8.61520715,12.571656 10.0999176,13.2171421 10.8577257,13.5094407 L15.5088241,14.433041 L18.6192054,7.984237 C15.5364148,3.11535317 13.9273018,0.573395879 13.7918663,0.358365126 C13.5790555,0.511491653 10.8061687,2.3935607 5.47320593,6.00457225 Z"
									id="path-3"></path>
                    <path
									d="M7.50063644,21.2294429 L12.3234468,23.3159332 C14.1688022,24.7579751 14.397098,26.4880487 13.008334,28.506154 C11.6195701,30.5242593 10.3099883,31.790241 9.07958868,32.3040991 C5.78142938,33.4346997 4.13234973,34 4.13234973,34 C4.13234973,34 2.75489982,33.0538207 2.37032616e-14,31.1614621 C-0.55822714,27.8186216 -0.55822714,26.0572515 -4.05231404e-15,25.8773518 C0.83734071,25.6075023 2.77988457,22.8248993 3.3049379,22.52991 C3.65497346,22.3332504 5.05353963,21.8997614 7.50063644,21.2294429 Z"
									id="path-4"></path>
                    <path
									d="M20.6,7.13333333 L25.6,13.8 C26.2627417,14.6836556 26.0836556,15.9372583 25.2,16.6 C24.8538077,16.8596443 24.4327404,17 24,17 L14,17 C12.8954305,17 12,16.1045695 12,15 C12,14.5672596 12.1403557,14.1461923 12.4,13.8 L17.4,7.13333333 C18.0627417,6.24967773 19.3163444,6.07059163 20.2,6.73333333 C20.3516113,6.84704183 20.4862915,6.981722 20.6,7.13333333 Z"
									id="path-5"></path>
                  </defs>
                  <g id="g-app-brand" stroke="none" stroke-width="1"
									fill="none" fill-rule="evenodd">
                    <g id="Brand-Logo"
									transform="translate(-27.000000, -15.000000)">
                      <g id="Icon"
									transform="translate(27.000000, 15.000000)">
                        <g id="Mask"
									transform="translate(0.000000, 8.000000)">
                          <mask id="mask-2" fill="white">
                            <use xlink:href="#path-1"></use>
                          </mask>
                          <use fill="#696cff" xlink:href="#path-1"></use>
                          <g id="Path-3" mask="url(#mask-2)">
                            <use fill="#696cff" xlink:href="#path-3"></use>
                            <use fill-opacity="0.2" fill="#FFFFFF"
									xlink:href="#path-3"></use>
                          </g>
                          <g id="Path-4" mask="url(#mask-2)">
                            <use fill="#696cff" xlink:href="#path-4"></use>
                            <use fill-opacity="0.2" fill="#FFFFFF"
									xlink:href="#path-4"></use>
                          </g>
                        </g>
                        <g id="Triangle"
									transform="translate(19.000000, 11.000000) rotate(-300.000000) translate(-19.000000, -11.000000) ">
                          <use fill="#696cff" xlink:href="#path-5"></use>
                          <use fill-opacity="0.2" fill="#FFFFFF"
									xlink:href="#path-5"></use>
                        </g>
                      </g>
                    </g>
                  </g>
                </svg>
					</span> <span class="app-brand-text demo menu-text fw-bolder ms-2">TNI</span>
					</a>
				</header>

				<!-- /헤더 -->
	<!-- Content -->
	<div class="container-xxl mt-5"
		style="display: flex; justify-content: center;">
			<div class="card mb-3" style="width: 40%;">
			
				<%-- controller에서 넘겨준 data내부, 게시글 vo와 댓글 list중 게시글 vo를 a로 변수화 --%>
				<c:set var="a" value="${data.articleVO}"/>
				<img class="card-img-top" src="img_tattoo/${a.filename}"
					alt="Card image cap" />
				<div class="card-body">
				
					<h5 class="card-title">${a.title}</h5>
					<p class="card-text">
					
						<%-- 내가 쓴 글이면, 수정 or 삭제 가능 --%>
						<c:if test="${a.uid==uid}">
							<a href="update.do?aid=${a.aid}">수정</a>
							<a href="deleteArticle.do?aid=${a.aid}">삭제</a>
							<br>
						</c:if>
						
						<%-- <ham:fav aid="${a.aid}" faid="${a.faid}"/> --%>
						<span class="text-muted">
							<c:if test="${uid != null}">
								<img id="heart" src="" width="12px;" height="12px;" style="cursor: pointer;">
							</c:if>
						<span id="fcnt">좋아요: ${a.fav}</span> | 조회수: ${a.cnt} | 댓글수: ${a.rcnt}
						</span>
					</p>
					<p class="card-text">${a.acontent}</p>
					<p class="card-text">
						작업 부위: ${a.part}  |  
						소요 시간: ${a.leadTime}  |  
						작업 위치: ${a.addr}
					</p>
					<p class="card-text">
						<small class="text-muted">${a.adate}</small>
					</p>	
					
				</div>
				
		</div>
				<!-- 우측 박스 -->
				<div class="card mb-3" style="margin-left: 50px; width: 40%;">
					<div class="card-body">
					<%-- 비로그인이라면, 댓글작성 불가능 --%>
					<c:choose>
						<c:when test="${uid!=null}">
						<form id="formAuthentication" class="mb-3" action="insertComment.do?aid=${a.aid}"
							method="POST">
							<textarea class="form-control" id="exampleFormControlTextarea1" name="ccontent"
									rows="3" style="resize: none; height: 100px;" placeholder="댓글을 작성해보세요." required="required"></textarea>
							<input type="text" class="form-control" id="username" name="uid" value="${uid}" readonly="readonly" style="width: 30%;"/>
						<button class="btn btn-primary d-grid w-100" type="submit">등록</button>
						</form>
						</c:when>
						<c:otherwise>
							<textarea class="form-control" id="exampleFormControlTextarea1"
									rows="3" style="resize: none; height: 100px;" placeholder="로그인 후 이용 가능합니다." disabled="disabled"></textarea>
						</c:otherwise>
					</c:choose>
					<%-- 댓글 목록 --%>
					  <div class="divider">
                        <div class="divider-text">댓글 목록</div>
                      </div>
                      <%-- 댓글 없다면 "댓글이 없습니다."출력 --%>
                      <c:if test="${fn:length(data.commentList1) == 0}">
							<p class="txt-center m-t-10 m-b-40">댓글이 존재하지 않습니다.</p>					
						</c:if>
                      <%-- controller에서 넘겨준 data내부, 게시글 vo와 댓글 list중 댓글 list를 c로 변수화 --%>
                      <c:forEach var="c" items="${data.commentList1}"><!-- 댓글 리스트 -->
                      	<c:if test="${c.uid==uid}"><a href="deleteComment.do?cid=${c.cid}&aid=${c.aid}&cgroup=${c.cid}">삭제</a></c:if>
                      	<div>
                      		<p class="card-text">${c.uid}: ${c.ccontent}</p>
                      		<p class="card-text"><small class="text-muted">${c.cdate}</small></p>	
						<!-- 대댓글 리스트 -->
                      	<div class="form-control" style="border: solid; background: #F5F5F5;">
						<c:forEach var="cc" items="${data.commentList2}">
						<c:if test="${c.cid==cc.cgroup}"> <!-- 출력되는 댓글의 고유번호와 대댓글 리스트 중 부모댓글 고유번호가 같다면 출력 -->
                      			<p class="card-text">
                      				${cc.uid}: ${cc.ccontent}
                      				<c:if test="${cc.uid==uid}"><a href="deleteComment.do?cid=${cc.cid}&aid=${cc.aid}">삭제</a></c:if>
                      			</p>
                      			<p class="card-text"><small class="text-muted">${cc.cdate}</small></p>
                     	</c:if>
                     	</c:forEach>
                     	
                     	<c:choose>
						<c:when test="${uid!=null}">
                     		<%-- 대댓글 작성 porm --%>
                     		<form id="formAuthentication" class="mb-3" action="insertComment2.do?aid=${a.aid}&cgroup=${c.cid}&uid=${uid}" method="POST">
                     			<div>
                     			<input
                   				 type="text"
                   				 class="form-control"
                  				  id="email"
               				      name="ccontent"
              				      placeholder="답글을 남겨보세요."
              				      required="required"
                 				 />
                     			<button class="btn btn-primary d-grid w-100" type="submit">답글 작성</button>
                     			</div>
                     		</form>
                     		<%-- /대댓글 작성 porm --%>
                     	</c:when>	
                     	<c:otherwise>
                     		<input
                   				 type="text"
                   				 class="form-control"
                  				  id="email"
              				      value="로그인 후 이용 가능합니다."
              				     disabled="disabled"
                 				 />
                     	</c:otherwise>
                     	</c:choose>
						</div>
                     	<!-- /대댓글 리스트 -->
                     	</div>
                      </c:forEach>
                   <%-- /댓글 목록 --%>   
					</div>
				</div>
				<!-- /우측 박스 -->
	</div>
	<!-- / Content -->

	<!-- Footer -->
	<footer class="content-footer footer bg-footer-theme">
		<div
			class="container-xxl d-flex flex-wrap justify-content-between py-2 flex-md-row flex-column">
			<div class="mb-2 mb-md-0">
				©
				<script>
                    document.write(new Date().getFullYear());
                  </script>
				, made with ❤️ by <a href="https://themeselection.com"
					target="_blank" class="footer-link fw-bolder">ThemeSelection</a>
			</div>
			<div>
				<a href="https://themeselection.com/license/"
					class="footer-link me-4" target="_blank">License</a> <a
					href="https://themeselection.com/" target="_blank"
					class="footer-link me-4">More Themes</a> <a
					href="https://themeselection.com/demo/sneat-bootstrap-html-admin-template/documentation/"
					target="_blank" class="footer-link me-4">Documentation</a> <a
					href="https://github.com/themeselection/sneat-html-admin-template-free/issues"
					target="_blank" class="footer-link me-4">Support</a>
			</div>
		</div>
	</footer>
	<!-- / Footer -->

	<!-- Core JS -->
	<!-- build:js assets/vendor/js/core.js -->
	<script src="assets/vendor/libs/jquery/jquery.js"></script>
	<script src="assets/vendor/libs/popper/popper.js"></script>
	<script src="assets/vendor/js/bootstrap.js"></script>
	<script
		src="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

	<script src="assets/vendor/js/menu.js"></script>
	<!-- endbuild -->

	<!-- Vendors JS -->

	<!-- Main JS -->
	<script src="assets/js/main.js"></script>

	<!-- Page JS -->

	<!-- Place this tag in your head or just before your close body tag. -->
	<script async defer src="https://buttons.github.io/buttons.js"></script>
	<!-- 추가한 js -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script type="text/javascript">
    $(document).ready(function () {
    	// 좋아요가 있는지 확인한 값을 heartval에 저장
            var heartval = ${a.faid}
            // heartval이 1이면 좋아요가 이미 되있는것이므로 꽉 찬 하트를 출력하는 코드
            if(heartval>0) {
                console.log(heartval);
                $("#heart").prop("src", "assets/img/1.png"); // 꽉찬 하트 이미지
               // $(".heart").prop('name',heartval)
            }
            else {
                console.log(heartval);
                $("#heart").prop("src", "assets/img/0.png"); // 빈 하트 이미지
                //$(".heart").prop('name',heartval)
            }

    	// 좋아요 버튼을 클릭 시 실행되는 코드
            $("#heart").on("click", function () {
            	console.log("클릭됨");
               
    	    $.ajax({
    	    	url :"fav2.do",
    	        type :"POST",
    	        data : {'aid': ${a.aid}, 'uid': '${uid}'}, 
    	    	success : function(result){
    	    		console.log("데이터 반환됨");
    	        	if(result==1) {
    	            	     $('#heart').prop("src","assets/img/1.png");	    
    	        	} else {
                        	 $('#heart').prop("src","assets/img/0.png");
    	        	}
    	        	// 갱신될 ArticleSet이 너무 규모가 커서, 현재 상태에 대해 임의로 좋아요 수 계산
    	        	if(${a.faid==1} && result==0){
    	        		$('#fcnt').text("좋아요: ${a.fav-1}");
    	        	}else if(${a.faid==0} && result==1){
    	        		 $('#fcnt').text("좋아요: ${a.fav+1}");
    	        	}else{
    	        		 $('#fcnt').text("좋아요: ${a.fav}");
    	        	}
                 }
    	    });
            });
        });
	</script>
</body>
</html>
