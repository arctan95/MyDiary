<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>注册账号</title>
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
<script src="${pageContext.request.contextPath}/bootstrap/js/jQuery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.js"></script>
<style type="text/css">
	  body {
        padding-top: 200px;
        padding-bottom: 40px;
        background-image: url('${pageContext.request.contextPath}/images/back.jpg');
        background-repeat: no-repeat;
        background-size:100%;
      }
      
      .form-signin-heading{
      	text-align: center;
      }

      .form-signin {
        max-width: 300px;
        padding: 19px 29px 0px;
        margin: 0 auto 20px;
        background-color: #fff;
        border: 1px solid #e5e5e5;
        -webkit-border-radius: 5px;
           -moz-border-radius: 5px;
                border-radius: 5px;
        -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
           -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
                box-shadow: 0 1px 2px rgba(0,0,0,.05);
      }
      .form-signin .form-signin-heading,
      .form-signin .checkbox {
        margin-bottom: 10px;
      }
      .form-signin input[type="text"],
      .form-signin input[type="password"] {
        font-size: 16px;
        height: auto;
        margin-bottom: 15px;
        padding: 7px 9px;
      }

</style>
</head>
<body>
<div class="container">
	<form name="myForm" class="form-signin" action="${pageContext.request.contextPath}/user/register" method="post" onsubmit="return checkForm()">
	<h2 class="form-signin-heading">注册账号</h2>
       		<input id="userName" name="userName" type="text" class="input-block-level" placeholder="用户名">
       		<input id="password" name="password" type="password" class="input-block-level" placeholder="密码">
       		<input id="password" name="password2" type="password" class="input-block-level" placeholder="确认密码">
       <button class="btn btn-large btn-primary btn-block" type="submit">立即注册</button>
       <font id="error" color="red">${backNews }</font>
       <br/>
	</form>
</div>
</body>
</html>