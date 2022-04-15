<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${uid != null}">
		<ul class="navbar-nav flex-row align-items-center ms-auto">
			<!-- Place this tag where you want the button to render. -->
			<li class="nav-item lh-1 me-3"><a href="favList.do?uid=${uid}">좋아요
					한 작품</a></li>

			<!-- User -->
			<li class="nav-item navbar-dropdown dropdown-user dropdown"><a
				class="nav-link dropdown-toggle hide-arrow"
				href="javascript:void(0);" data-bs-toggle="dropdown">
					<div class="avatar avatar-online">
						<img src="https://png.clipart.me/istock/previews/9349/93493545-people-icon.jpg" alt
							class="w-px-40 h-auto rounded-circle" />
					</div>
			</a>
				<ul class="dropdown-menu dropdown-menu-end">
					<li><a class="dropdown-item" href="#">
							<div class="d-flex">
								<div class="flex-shrink-0 me-3">
									<div class="avatar avatar-online">
										<img src="https://png.clipart.me/istock/previews/9349/93493545-people-icon.jpg" alt
											class="w-px-40 h-auto rounded-circle" />
									</div>
								</div>
								<div class="flex-grow-1">
									<span class="fw-semibold d-block">${uname}</span> <small
										class="text-muted"> <c:choose>
											<c:when test="${uauth==0}">
																일반
															</c:when>
											<c:otherwise>
																타투이스트
															</c:otherwise>
										</c:choose></small>
								</div>
							</div>
					</a></li>
					<li>
						<div class="dropdown-divider"></div>
					</li>
					<li><a class="dropdown-item" href="mypage.do?uid=${uid}">
							<i class="bx bx-user me-2"></i> <span class="align-middle">내
								정보 수정</span>
					</a></li>
					<li><a class="dropdown-item" href="logout.do"> <i
							class="bx bx-power-off me-2"></i> <span class="align-middle">로그아웃</span>
					</a></li>
				</ul></li>
			<!--/ User -->
		</ul>
	</c:when>
	<c:otherwise>
		<ul class="navbar-nav flex-row align-items-center ms-auto">
			<li><a href="login.do">로그인</a></li>
		</ul>
	</c:otherwise>
</c:choose>