<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<meta charset="utf-8">
<title>selfmanage.html</title>
<style type="text/css">
body{margin:0;padding:0;overflow-x:hidden;}
html, body{height:100%;}
img{border:none;}
*{font-family:'微软雅黑';font-size:12px;color:#626262;}
dl,dt,dd{display:block;margin:0;}
a{text-decoration:none;}

#bg{background-image:url(/images/content/dotted.png);}
.container{width:100%;height:100%;margin:auto;}

/*left*/
.leftsidebar_box{width:12%;height:auto !important;overflow:visible !important;position:fixed;height:100% !important;background-color:#3992d0;}
.line{height:2px;width:100%;background-image:url(/images/left/line_bg.png);background-repeat:repeat-x;}
.leftsidebar_box dt{padding-left:40px;padding-right:10px;background-repeat:no-repeat;background-position:10px center;color:#f5f5f5;font-size:14px;position:relative;line-height:48px;cursor:pointer;}
.leftsidebar_box dd{background-color:#317eb4;padding-left:40px;}
.leftsidebar_box dd a{color:#f5f5f5;line-height:20px;}
.leftsidebar_box dt img{position:absolute;right:10px;top:20px;}
.system_log dt{background-image:url(/images/left/custom.png)}
.custom dt{background-image:url(/images/left/system.png)}
.channel dt{background-image:url(/images/left/channel.png)}
.app dt{background-image:url(/images/left/app.png)}
.cloud dt{background-image:url(/images/left/cloud.png)}
.syetem_management dt{background-image:url(/images/left/syetem_management.png)}
.source dt{background-image:url(/images/left/source.png)}
.statistics dt{background-image:url(/images/left/statistics.png)}
.leftsidebar_box dl dd:last-child{padding-bottom:10px;}
.right{height:100% ;width:88%;position: relative;float:right;border: none;}
.right iframe{width: 100%;height:100%}
</style>
</head>
  
<body id="bg">

<div class="container">

	<div class="leftsidebar_box">
		<div class="line"></div>
		<dl class="system_log">
			<dt onClick="changeImage()">个人管理<img src="images/left/select_xl01.png"></dt>
			<dd class="first_dd"><a target="rightPage" href="displayselfinfo.html">查看个人信息</a></dd>
			<dd><a target="rightPage" href="changeselfinfo.html">修改个人信息</a></dd>
			<dd><a target="rightPage" href="changepassword.html">修改密码</a></dd>
		</dl>
	
		<dl class="custom">
			<dt onClick="changeImage()">居委会管理<img src="images/left/select_xl01.png"></dt>
			<dd class="first_dd" ><a target="rightPage" href="displaypersonalinfo.html">查看人事信息</a></dd>
			<dd><a target="rightPage" href="displayuserinfo.html">查询用户</a></dd>
			<dd><a target="rightPage" href="changepersonalinfo.html">编辑人事信息</a></dd>
		</dl>
	</div>
	<div class="right">
	<iframe name="rightPage"></iframe>
	</div>
</div>


<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$(".leftsidebar_box dt").css({"background-color":"#3992d0"});
$(".leftsidebar_box dt img").attr("src","images/left/select_xl01.png");
$(function(){
	$(".leftsidebar_box dd").hide();
	$(".leftsidebar_box dt").click(function(){
		$(".leftsidebar_box dt").css({"background-color":"#3992d0"})
		$(this).css({"background-color": "#317eb4"});
		$(this).parent().find('dd').removeClass("menu_chioce");
		$(".leftsidebar_box dt img").attr("src","images/left/select_xl01.png");
		$(this).parent().find('img').attr("src","images/left/select_xl.png");
		$(".menu_chioce").slideUp(); 
		$(this).parent().find('dd').slideToggle();
		$(this).parent().find('dd').addClass("menu_chioce");
	});
})
</script>
</body>
</html>
