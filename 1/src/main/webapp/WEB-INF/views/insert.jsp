<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Insert todo</title>

<link rel="stylesheet" href="/resources/css/main.css">
</head>

<body>

	<c:if test="${not empty sessionScope.msg}">

		<script>
			alert('${msg}')
		</script>

		<c:remove var="msg" scope="session" />

	</c:if>


	<p class="insertTitle">To do 등록하기</p>

	<form action="/insert" method="post">

		<fieldset class="insertContainer">

			<p>제목</p>
			<input type="text" name="todoTitle" id="todoTitle">

			<p>메모</p>
			<textarea name="todoMemo" id="todoMemo" cols="30" rows="10"></textarea>

			<button class="insertBtn">등록하기</button>
		</fieldset>



	</form>


</body>

</html>