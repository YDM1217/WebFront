<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>todoList메인화면</title>

    <link rel="stylesheet" href="/resources/css/main.css" type="text/css">
</head>

<body>

    <p class="signupTitle">
        회원가입
    </p>
    
    <c:if test="${not empty sessionScope.msg}">

		<script>
			alert('${msg}')
		</script>

		<c:remove var="msg" scope="session"/>

	</c:if>
	


    <form action="/signup" method="post" onsubmit="return validate()">

        <fieldset class="signupContainer">

            <p>아이디</p>
            <input type="text" name="inputId" id="inputId">
            <p id="explainId">영어 대/소문자, 숫자, 특수문자 포함 6~14글자</p>

            <p>비밀번호</p>
            <input type="password" name="inputPw" id="inputPw">

            <p>비밀번호 확인</p>
            <input type="password" name="checkInputPw" id="checkInputPw">
            <span id="pwMsg"></span>

            <p>닉네임</p>
            <input type="text" name="inputNickname" id="inputNickname">
            <span id="nicknameMsg"></span>


            <br>

            <button class="signupBtn">가입하기</button>

        </fieldset>

    </form>


    <script src="/resources/js/signup.js"></script>
</body>

</html>