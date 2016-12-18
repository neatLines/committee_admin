<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/register-login.css">
<title>displayselfinfo.html</title>
<style type="text/css">
/* Table 1 Style */
table.table1{
    font-family: "Trebuchet MS", sans-serif;
    font-size: 16px;
    font-weight: bold;
    line-height: 1.4em;
    font-style: normal;
    border-collapse:separate;
}
.table1 thead th{
    padding:15px;
    color:#fff;
    text-shadow:1px 1px 1px #568F23;
    border:1px solid #93CE37;
    border-bottom:3px solid #9ED929;
    background-color:#9DD929;
    background:-webkit-gradient(
        linear,
        left bottom,
        left top,
        color-stop(0.02, rgb(123,192,67)),
        color-stop(0.51, rgb(139,198,66)),
        color-stop(0.87, rgb(158,217,41))
        );
    background: -moz-linear-gradient(
        center bottom,
        rgb(123,192,67) 2%,
        rgb(139,198,66) 51%,
        rgb(158,217,41) 87%
        );
    -webkit-border-top-left-radius:5px;
    -webkit-border-top-right-radius:5px;
    -moz-border-radius:5px 5px 0px 0px;
    border-top-left-radius:5px;
    border-top-right-radius:5px;
}
.table1 thead th:empty{
    background:transparent;
    border:none;
}
.table1 tbody th{
    color:#fff;
    text-shadow:1px 1px 1px #568F23;
    background-color:#9DD929;
    border:1px solid #93CE37;
    border-right:3px solid #9ED929;
    padding:0px 10px;
    background:-webkit-gradient(
        linear,
        left bottom,
        right top,
        color-stop(0.02, rgb(158,217,41)),
        color-stop(0.51, rgb(139,198,66)),
        color-stop(0.87, rgb(123,192,67))
        );
    background: -moz-linear-gradient(
        left bottom,
        rgb(158,217,41) 2%,
        rgb(139,198,66) 51%,
        rgb(123,192,67) 87%
        );
    -moz-border-radius:5px 0px 0px 5px;
    -webkit-border-top-left-radius:5px;
    -webkit-border-bottom-left-radius:5px;
    border-top-left-radius:5px;
    border-bottom-left-radius:5px;
}
.table1 tfoot td{
	width:250px;
    color: #9CD009;
    font-size:32px;
    text-align:center;
    padding:10px 0px;
    text-shadow:1px 1px 1px #444;
}
.table1 tfoot th{
    color:#666;
}
.table1 tbody td{
	width:250px;
    padding:10px;
    text-align:center;
    background-color:#DEF3CA;
    border: 2px solid #E7EFE0;
    -moz-border-radius:2px;
    -webkit-border-radius:2px;
    border-radius:2px;
    color:#666;
    text-shadow:1px 1px 1px #fff;
}
</style>
</head>
  
<body>
<div id="box"></div>
<div class="cent-box">
		<table class="table1" align="center" id="table">
		<caption>个人信息显示</caption>

		</table>
</div>
<script src='js/particles.js' type="text/javascript"></script>
<script src='js/background.js' type="text/javascript"></script>
</body>
</html>
<script>
    $(document).ready(function(){
        $.getJSON("/json/getMinInfo",function(result) {
            $.each(result,function(index,comment){
                $("#table").append("<tr><td>"+"用户编号"+"</td><td>"+comment.u_id+"</td></tr>");
                $("#table").append("<tr><td>"+"姓名"+"</td><td>"+comment.u_name+"</td></tr>");
                $("#table").append("<tr><td>"+"联系电话"+"</td><td>"+comment.phone_number+"</td></tr>");
                $("#table").append("<tr><td>"+"年龄"+"</td><td>"+comment.u_age+"</td></tr>");
                $("#table").append("<tr><td>"+"性别"+"</td><td>"+comment.u_sex+"</td></tr>");
                $("#table").append("<tr><td>"+"用户名"+"</td><td>"+comment.user_name+"</td></tr>");
                $("#table").append("<tr><td>"+"权限"+"</td><td>"+comment.power+"</td></tr>");
            })
        })
    });
</script>