<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>알림창</title>
</head>
<body>

<script type="text/javascript">

if(${msg != null}){
	alert('${msg}');	
}
	// ${pageContext.request.contextPath}를 통해 /app/요청주소로 절대경로를 완성해줌
	// https://m.blog.naver.com/takijang/221014895320
	location.href="${pageContext.request.contextPath}${url}";
</script>

</body>
</html>