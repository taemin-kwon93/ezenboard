<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    
    <style>
        * { box-sizing:border-box; }
        form {
            width:400px;
            height:600px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%) ;
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
        }
        .input-field {
            width: 300px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }
        label {
            width:300px;
            height:30px;
            margin-top :4px;
        }
        button {
            background-color: rgb(89,117,196);
            color : white;
            width:300px;
            height:50px;
            font-size: 17px;
            border : none;
            border-radius: 5px;
            margin : 20px 0 30px 0;
        }
        .title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }
        .msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }
        .sns-chk {
            margin-top : 5px; 
        }
    </style>
    
    <title>회원가입</title>
    
</head>

<body>
	<form action="/member/join" method="post">
		<div class="title">회원가입</div>
		<div id="msg" class="msg">
			    <c:if test="${not empty param.msg}">
		<i class="fa fa-exclamation-circle"> ${URLDecoder.decode(param.msg)}</i>            
		</c:if>
		</div> 
		<label for="">아이디</label>
		<input class="input-field" type="text" name="userid" placeholder="8~12자리의 영대소문자와 숫자 조합">
		<label for="">비밀번호</label>
		<input class="input-field" type="text" name="userpw" placeholder="8~12자리의 영대소문자와 숫자 조합">
		<label for="">이름</label>
		<input class="input-field" type="text" name="userName" placeholder="이름">
		<input type="hidden" name="enabled" value="true">
		<!-- <div class="sns-chk">
		    <label><input type="checkbox" name="sns" value="facebook"/>페이스북</label>
		    <label><input type="checkbox" name="sns" value="kakaotalk"/>카카오톡</label>
		    <label><input type="checkbox" name="sns" value="instagram"/>인스타그램</label>
		</div> -->
		 <button>제출</button>
	</form> 
	
</body>

<script>
	$(document).ready(function(){
	
	});

</script>

</html>

<%@include file="../includes/footer.jsp"%>
