<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="ham" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

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
<html lang="ko" class="light-style layout-menu-fixed" dir="ltr"
	data-theme="theme-default" data-assets-path="assets/"
	data-template="vertical-menu-template-free">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0" />

<title>main</title>

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
<!-- 추가한 CSS -->
<style type="text/css">
.clicked_filter {
	background: #483D8B;
}
</style>

<!-- Helpers -->
<script src="assets/vendor/js/helpers.js"></script>

<!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
<!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
<script src="assets/js/config.js"></script>
</head>

<body>
	<!-- Layout wrapper -->
	<div class="layout-wrapper layout-content-navbar layout-without-menu">
		<div class="layout-container">
			<!-- Layout container -->
			<div class="layout-page">

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

				<!-- Navbar -->

				<nav
					class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
					id="layout-navbar">
					<div class="navbar-nav-right d-flex align-items-center"
						id="navbar-collapse">
						<!-- Search -->
						<div class="navbar-nav align-items-center">
							<div class="nav-item d-flex align-items-center">
								<i class="bx bx-search fs-4 lh-0"></i>
								<form id="formAuthentication" action="search.do" method="POST">
									<div style="display: inline-flex;">
										<select id="defaultSelect" class="form-select"
											name="condition">
											<option value="title">제목</option>
											<option value="uname">작성자</option>
											<option value="acontent">내용</option>
											<option value="part">작업 부위</option>
											<option value="addr">위치(주소)</option>
										</select> <input type="text" class="form-control border-0 shadow-none"
											name="search" placeholder="Search..." aria-label="Search..."
											required="required" />
									</div>
								</form>
							</div>
						</div>
						<!-- /Search -->

						<%-- 커스텀 태그 활용 --%>
						<ham:login />

					</div>
				</nav>

				<!-- / Navbar -->

				<!-- Content wrapper -->
				<div class="content-wrapper">
					<!-- Content -->

					<div class="container-xxl flex-grow-1 container-p-y">

						<c:choose>
							<c:when test="${favcheck==false}">
								<a href="main.do?lang=ko">한국어</a> | <a href="main.do?lang=en">English</a>
							</c:when>
							<c:otherwise>
								<a href="favList.do?lang=ko">한국어</a> | <a
									href="favList.do?lang=en">English</a>
							</c:otherwise>
						</c:choose>

						<%-- 타투이스트면, 글쓰기 가능 --%>
						<c:if test="${uauth==1}">
							<div>
								<a href="insert.jsp"><strong><spring:message
											code="message.main.insert" /> </strong></a>
							</div>
						</c:if>
						<c:if test="${favcheck==false}">
							<%-- if, 내가 좋아요 누른 목록 요청하면, 필터&언어설정 비활성화 --%>
							<div class="btnBox"
								style="display: flex; justify-content: flex-end; margin-bottom: 20px;">
								<button class="filter btn btn-primary"
									onclick="location.href ='main.do'" style="margin-right: 5px;">
									<spring:message code="message.main.filterDate" />
								</button>
								<button class="filter btn btn-primary"
									onclick="location.href ='main.do?cnt=1'"
									style="margin-right: 5px;">
									<spring:message code="message.main.filterCnt" />
								</button>
								<button class="filter btn btn-primary"
									onclick="location.href ='main.do?fav=1'"
									style="margin-right: 5px;">
									<spring:message code="message.main.filterFav" />
								</button>
								<button class="filter btn btn-primary"
									onclick="location.href ='main.do?rcnt=1'">
									<spring:message code="message.main.filterRcnt" />
								</button>
							</div>
						</c:if>

						<c:choose>
							<%-- null은 인스턴스가 생성되지 않은 상태 --%>
							<%-- 인스턴스는 생성되어 있지만, 데이터가 적재되지 않은 상태 --%>
							<c:when test="${adatas.isEmpty()}">
								<h2>
									😅 <spring:message code="message.main.noResult" />
								</h2>
							</c:when>
							<c:otherwise>
								<div class="row">
									<c:forEach var="v" items="${adatas}" varStatus="status">
										<div class="col-md-6 col-lg-4 mb-3">
											<div class="card h-100">
												<div class="card-body">
													<h5 class="card-title">${v.title}</h5>
													<%-- 글제목 --%>
													<h6 class="card-subtitle jtext-muted">${v.uname}&nbsp;|&nbsp;
														${v.adate}</h6>
													<%-- 작성자이름 | 작성일자 --%>
												</div>
												<a href="detail.do?uid=${v.uid}&aid=${v.aid}&cnt=1"><img
													class="img-fluid" src="img_tattoo/${v.filename}"
													alt="작품 이미지" /></a>
												<%-- 작품 사진, 클릭시 상세보기로 이동 --%>
												<div class="card-body">
													<p class="card-text" style="overflow: hidden;">${v.acontent}</p>													
													
													<!-- 좋아요 버튼 -->
													<%-- 비로그인시 확인 안됨, faid(좋아요유무)결과에 따라 ♡♥︎로 구분, 하트 이미지 클릭시 해당 aid, fav, faid를 인자로 함수에 전달 --%>
													<c:if test="${uid != null}">
														<c:choose>
															<c:when test="${v.faid==1}">
																<img id="${v.aid}heart" src="assets/img/1.png" width="12px;"
																	height="12px;" style="cursor: pointer;"
																	onclick="f1(${v.aid}, ${v.fav}, ${v.faid})">
															</c:when>
															<c:otherwise>
																<img id="${v.aid}heart" src="assets/img/0.png" width="12px;"
																	height="12px;" style="cursor: pointer;"
																	onclick="f1(${v.aid}, ${v.fav}, ${v.faid})">
															</c:otherwise>
														</c:choose>
														</c:if>
														<!--/ 좋아요 버튼 -->
													<a href="javascript:void(0);" class="card-link " id="${v.aid}fcnt"><spring:message
															code="message.main.fav" />: ${v.fav}</a> <a
														href="javascript:void(0);" class="card-link"><spring:message
															code="message.main.cnt" />: ${v.cnt}</a> <a
														href="javascript:void(0);" class="card-link"><spring:message
															code="message.main.rcnt" />: ${v.rcnt}</a>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</c:otherwise>
						</c:choose>

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

					<div class="content-backdrop fade"></div>
				</div>
				<!-- Content wrapper -->
			</div>
			<!-- / Layout page -->
		</div>
	</div>
	<!-- / Layout wrapper -->

	<!-- Core JS -->
	<!-- build:js assets/vendor/js/core.js -->
	<script src="assets/vendor/libs/jquery/jquery.js"></script>
	<script src="assets/vendor/libs/popper/popper.js"></script>
	<script src="assets/vendor/js/bootstrap.js"></script>
	<script src="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

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
            function f1(aid, fav, faid) {//변수명을 작성해야하는데, ${v.aid}등으로 불러서 오류 발생!
            	console.log("클릭됨");
            	console.log('aid:'+aid);
            	console.log('uid: ${uid}');
            	console.log('faid: '+faid);
            	console.log('fav:'+fav);
    	    $.ajax({
    	    	url :"fav2.do",
    	        type :"POST",
    	        data : {'aid': aid, 'uid': '${uid}'}, 
    	    	success : function(result){ //controller에서 전달받은 결과에 따라
    	    		console.log("데이터 반환됨");
    	        	if(result==1) { //result가 1이면,
						$('#'+aid+'heart').prop("src","assets/img/1.png");// ♥  
    	        	} else { //result가 0이면,
						$('#'+aid+'heart').prop("src","assets/img/0.png");// ♡  
    	        	}
    	        	// controller에서 데이터 반환받지 않고, 임의로 계산하여 개수 화면에 출력
    	        	if(faid==1 && result=='0'){
						$('#'+aid+'fcnt').text("좋아요: "+(fav-1));
    	        	}else if(faid==0 && result=='1'){
    	        		$('#'+aid+'fcnt').text("좋아요: "+(fav+1));
    	        	}else{
    	        		$('#'+aid+'fcnt').text("좋아요: "+fav);
    	        	}
                 }
    	    });
            };
	</script>
	<script src="js_1/filter.js"></script>

</body>
</html>
