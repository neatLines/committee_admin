<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<title>changepassword.html</title>
	<link rel="stylesheet" type="text/css" href="css/register-login1.css">
</head>
<body>
<div id="box"></div>
<div class="cent-box">
	<div class="cent-box-header">
		<h1 class="main-title">居委会</h1>
                <h2 class="sub-title">全心全意为人民服务</h2>
	</div>

	<div class="cont-main clearfix">
		<div class="index-tab">
			<div class="index-slide-nav">
				<a href="changepassword.html" class="active">修改密码</a>
				<div class="slide-bar"></div>				
			</div>
		</div>

		<div class="login form">
			<div class="group">
				<div class="group-ipt email">
					<input type="text" name="user" id="user" class="ipt" placeholder="输入您的账号" required>
				</div>
				<div class="group-ipt password">
					<input type="password" name="password1" id="password1" class="ipt" placeholder="输入您的旧密码" required>
				</div>
				<div class="group-ipt password">
					<input type="password" name="password2" id="password2" class="ipt" placeholder="输入您的新密码" required>
				</div>
			</div>
		</div>

		<div class="button">
			<button type="submit" class="login-btn register-btn" id="button">确认修改</button>
		</div>

		<div class="remember clearfix">			
			<label class="forgot-password">
				<a href="#">忘记密码？</a>
			</label>
		</div>
	</div>
</div>

<div class="footer">
	<p>居委会</p>

</div>
<script src='js/particles.js' type="text/javascript"></script>
<script src='js/background.js' type="text/javascript"></script>
<script src='js/sha1.js' type="text/ecmascript" ></script>

</body>
</html>
<script>
	$(document).ready(function () {
		$("#button").click(function () {
			var mima0=password1.value;
			var mima01=hex_sha1(mima0);
			var mima1=password2.value;
			var mima11=hex_sha1(mima1);
			$.ajax({
				type: "POST",
				url: "/json/changePassword",
				data:JSON.stringify( {userName:$("#user").val(),oldPassword:mima01,newPassword:mima11}),
				success: function(result){
					alert(result.info);
					if (result.info=="update success") {//判断。。。
						location.href="displayselfinfo";
					}
				},
				contentType: "application/json",
				dataType: "json"
			});
		})
	})
</script>