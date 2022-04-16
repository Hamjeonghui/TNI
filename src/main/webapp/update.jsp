<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<html
  lang="ko"
  class="light-style customizer-hide"
  dir="ltr"
  data-theme="theme-default"
  data-assets-path="../assets/"
  data-template="vertical-menu-template-free"
>
  <head>
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
    />

    <title>Update</title>

    <meta name="description" content="" />

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="assets/img/favicon/favicon.ico" />

    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
      rel="stylesheet"
    />

    <!-- Icons. Uncomment required icon fonts -->
    <link rel="stylesheet" href="assets/vendor/fonts/boxicons.css" />

    <!-- Core CSS -->
    <link rel="stylesheet" href="assets/vendor/css/core.css" class="template-customizer-core-css" />
    <link rel="stylesheet" href="assets/vendor/css/theme-default.css" class="template-customizer-theme-css" />
    <link rel="stylesheet" href="assets/css/demo.css" />

    <!-- Vendors CSS -->
    <link rel="stylesheet" href="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" />

    <!-- Page CSS -->
    <!-- Page -->
    <link rel="stylesheet" href="assets/vendor/css/pages/page-auth.css" />
    <!-- Helpers -->
    <script src="assets/vendor/js/helpers.js"></script>

    <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
    <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
    <script src="assets/js/config.js"></script>
  </head>

  <body>
    <!-- Content -->

    <div class="container-xxl mt-5" style="width: 60%;">
      <div class="authentication-basic container-p-y">
        <div class="authentication-inner">
          <!-- Register -->
          <div class="card">
            <div class="card-body">
              <!-- Logo -->
              <div class="app-brand justify-content-center">
                <a href="main.do" class="app-brand-link gap-2">
                  <span class="app-brand-logo demo">
                    <svg
                      width="25"
                      viewBox="0 0 25 42"
                      version="1.1"
                      xmlns="http://www.w3.org/2000/svg"
                      xmlns:xlink="http://www.w3.org/1999/xlink"
                    >
                      <defs>
                        <path
                          d="M13.7918663,0.358365126 L3.39788168,7.44174259 C0.566865006,9.69408886 -0.379795268,12.4788597 0.557900856,15.7960551 C0.68998853,16.2305145 1.09562888,17.7872135 3.12357076,19.2293357 C3.8146334,19.7207684 5.32369333,20.3834223 7.65075054,21.2172976 L7.59773219,21.2525164 L2.63468769,24.5493413 C0.445452254,26.3002124 0.0884951797,28.5083815 1.56381646,31.1738486 C2.83770406,32.8170431 5.20850219,33.2640127 7.09180128,32.5391577 C8.347334,32.0559211 11.4559176,30.0011079 16.4175519,26.3747182 C18.0338572,24.4997857 18.6973423,22.4544883 18.4080071,20.2388261 C17.963753,17.5346866 16.1776345,15.5799961 13.0496516,14.3747546 L10.9194936,13.4715819 L18.6192054,7.984237 L13.7918663,0.358365126 Z"
                          id="path-1"
                        ></path>
                        <path
                          d="M5.47320593,6.00457225 C4.05321814,8.216144 4.36334763,10.0722806 6.40359441,11.5729822 C8.61520715,12.571656 10.0999176,13.2171421 10.8577257,13.5094407 L15.5088241,14.433041 L18.6192054,7.984237 C15.5364148,3.11535317 13.9273018,0.573395879 13.7918663,0.358365126 C13.5790555,0.511491653 10.8061687,2.3935607 5.47320593,6.00457225 Z"
                          id="path-3"
                        ></path>
                        <path
                          d="M7.50063644,21.2294429 L12.3234468,23.3159332 C14.1688022,24.7579751 14.397098,26.4880487 13.008334,28.506154 C11.6195701,30.5242593 10.3099883,31.790241 9.07958868,32.3040991 C5.78142938,33.4346997 4.13234973,34 4.13234973,34 C4.13234973,34 2.75489982,33.0538207 2.37032616e-14,31.1614621 C-0.55822714,27.8186216 -0.55822714,26.0572515 -4.05231404e-15,25.8773518 C0.83734071,25.6075023 2.77988457,22.8248993 3.3049379,22.52991 C3.65497346,22.3332504 5.05353963,21.8997614 7.50063644,21.2294429 Z"
                          id="path-4"
                        ></path>
                        <path
                          d="M20.6,7.13333333 L25.6,13.8 C26.2627417,14.6836556 26.0836556,15.9372583 25.2,16.6 C24.8538077,16.8596443 24.4327404,17 24,17 L14,17 C12.8954305,17 12,16.1045695 12,15 C12,14.5672596 12.1403557,14.1461923 12.4,13.8 L17.4,7.13333333 C18.0627417,6.24967773 19.3163444,6.07059163 20.2,6.73333333 C20.3516113,6.84704183 20.4862915,6.981722 20.6,7.13333333 Z"
                          id="path-5"
                        ></path>
                      </defs>
                      <g id="g-app-brand" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                        <g id="Brand-Logo" transform="translate(-27.000000, -15.000000)">
                          <g id="Icon" transform="translate(27.000000, 15.000000)">
                            <g id="Mask" transform="translate(0.000000, 8.000000)">
                              <mask id="mask-2" fill="white">
                                <use xlink:href="#path-1"></use>
                              </mask>
                              <use fill="#696cff" xlink:href="#path-1"></use>
                              <g id="Path-3" mask="url(#mask-2)">
                                <use fill="#696cff" xlink:href="#path-3"></use>
                                <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-3"></use>
                              </g>
                              <g id="Path-4" mask="url(#mask-2)">
                                <use fill="#696cff" xlink:href="#path-4"></use>
                                <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-4"></use>
                              </g>
                            </g>
                            <g
                              id="Triangle"
                              transform="translate(19.000000, 11.000000) rotate(-300.000000) translate(-19.000000, -11.000000) "
                            >
                              <use fill="#696cff" xlink:href="#path-5"></use>
                              <use fill-opacity="0.2" fill="#FFFFFF" xlink:href="#path-5"></use>
                            </g>
                          </g>
                        </g>
                      </g>
                    </svg>
                  </span>
                  <span class="app-brand-text demo text-body fw-bolder">tni</span>
                </a>
              </div>
              <!-- /Logo -->
              <h4 class="mb-2">작품 수정</h4>
              <p class="mb-4">작품 정보를 수정해보세요.</p>

              <c:set var="v" value="${adata}"/>
              <form id="formAuthentication" class="mb-3" action="updateArticle.do?uid=${uid}&uname=${uname}&aid=${v.aid}" method="POST" enctype="multipart/form-data">
                <%-- 글제목 --%>
                <div class="mb-3">
                  <label for="email" class="form-label">제목</label>
                  <input
                    type="text"
                    class="form-control"
                    id="email"
                    name="title"
                    value="${v.title}"
                    autofocus
                   	required="required"
                  />
                </div>
                <%-- /글제목 --%>
                <%-- 글 내용 --%>
				<div class="mb-3">
                  <label for="email" class="form-label">상세 내용</label>
                  <textarea class="form-control" id="exampleFormControlTextarea1" name="acontent" required="required"
									rows="3" style="resize: none; height: 100px;">${v.acontent}</textarea>
                </div>
                <%-- /글 내용 --%>
                <%-- 이미지 --%>
                <div class="mb-3">
                  <label for="formFile" class="form-label">이미지 첨부</label>
                  <input class="form-control" type="file" id="formFile" name="uploadFile"/>
                </div>
                <%-- /이미지 --%>
               <%-- 작업부위 --%><%-- DB에 저장된 값 가져와서 selelcted --%>
               <div class="mb-3">
                 <label for="defaultSelect" class="form-label">작업부위</label>
                   <select id="defaultSelect" class="form-select" name="part">
                      <option value="팔" <c:if test="${v.part=='팔'}">selected</c:if>>팔</option>
                      <option value="손목" <c:if test="${v.part=='손목'}">selected</c:if>>손목</option>
                      <option value="손가락" <c:if test="${v.part=='손가락'}">selected</c:if>>손가락</option>
                      <option value="손등" <c:if test="${v.part=='손등'}">selected</c:if>>손등</option>
                      <option value="다리" <c:if test="${v.part=='다리'}">selected</c:if>>다리</option>
                      <option value="무릎" <c:if test="${v.part=='무릎'}">selected</c:if>>무릎</option>
                      <option value="발목" <c:if test="${v.part=='발목'}">selected</c:if>>발목</option>
                      <option value="쇄골" <c:if test="${v.part=='쇄골'}">selected</c:if>>쇄골</option>
                      <option value="목" <c:if test="${v.part=='목'}">selected</c:if>>목</option>
                      <option value="그 외" <c:if test="${v.part=='그 외'}">selected</c:if>>그 외</option>
                   </select>
               </div>
               <%-- /작업부위 --%>
               <%-- 소요시간 --%>
               <div class="mb-3">
                 <label for="defaultSelect" class="form-label">소요시간</label>
                   <select id="defaultSelect" class="form-select" name="leadTime">
                      <option value="1시간" <c:if test="${v.leadTime=='1시간'}">selected</c:if>>1시간</option>
                      <option value="2시간" <c:if test="${v.leadTime=='2시간'}">selected</c:if>>2시간</option>
                      <option value="3시간" <c:if test="${v.leadTime=='3시간'}">selected</c:if>>3시간</option>
                      <option value="그 외" <c:if test="${v.leadTime=='그 외'}">selected</c:if>>그 외</option>
                   </select>
               </div>
               <%-- /소요시간 --%>              
                <%-- 주소 --%>
                <div class="mb-3">
                  <label for="email" class="form-label">매장 위치</label>
                  <input
                    type="text"
                    class="form-control"
                    id="email"
                    name="addr"
                    value="${v.addr}"
                   	required="required"
                  />
                </div>
                <%-- /주소 --%>
                <div class="mb-3">
                  <button class="btn btn-primary d-grid w-100" type="submit">수정</button>
                  <a href="deleteArticle.do?aid=${v.aid}" class="btn btn-primary d-grid w-100">삭제</a>
                </div>
              </form>
            </div>
          </div>
          <!-- /Register -->
        </div>
      </div>
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
    <script src="assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

    <script src="assets/vendor/js/menu.js"></script>
    <!-- endbuild -->

    <!-- Vendors JS -->

    <!-- Main JS -->
    <script src="assets/js/main.js"></script>

    <!-- Page JS -->

    <!-- Place this tag in your head or just before your close body tag. -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
  </body>
</html>