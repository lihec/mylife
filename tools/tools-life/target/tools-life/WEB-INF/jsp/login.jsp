<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>用户登录</title>
    <link href="/static/css/life/signin.css" rel="stylesheet">
    <life:script isJQValidation="yes"/>
    <script>
        $(document).ready(function () {
        	$('#loginForm').validate();
        	$("#loginBtn").click(function(){
        		var checked=$("#remember").prop("checked");
        		if(checked){
        			$("remember").val('1');
        		}
        		if($('#loginForm').valid()){
        			$('#loginForm').submit();
        		}
        	});
            $('#loginForm').ofajaxForm({
            	url: '/login',
            	success: function (json) {
                    if (json.result == 'ok') {
                        window.location.href='/home';
                    } else {
                    	showMessage(json.msg,'error');
                    }
                }
            });
        	
        });
    </script>
</head>

<body id="login_page">
 <div class="container">
      <form class="form-signin" role="form" id="loginForm">
        <h2 class="form-signin-heading">请登录</h2>
        <input type="text" name="usercode" id="usercode" class="form-control" placeholder="账号" data-rule-required="true" data-msg-required="请输入账号" required autofocus/>
        <input type="password" name="password" id="password" class="form-control" placeholder="密码" data-rule-required="true" data-msg-required="请输入密码" required/>
        <label class="checkbox">
          <input id="remember" name="remember" type="checkbox" value="remember-me" /> 记住我
          <input type="hidden" name="isRemem" value="" id="isRemem" />
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="button" id="loginBtn">登录</button>
      </form>
    </div> <!-- /container -->
</body>
</html>