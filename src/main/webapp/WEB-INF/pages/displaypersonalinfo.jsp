<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/register-login.css">
<title>displaypersonalinfo.html</title>
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
div{
padding: 0px;
}
ul{
    width: 800px;
    display: block;
    padding: 0px;
    margin:0px;
}
ul li{
    width: 200px;
    list-style: none;
    float: left;
    display: block;
    border-width: 1px;
}
div ul:first-child li{
    width: 200px;
    background-color: lightgreen;
    color: white;
    font-size: larger;
    font-weight: bold;
}
.first,.forth,.third{width: 25%}
.second{width:20%}
</style>
</head>
  
<body>
<div id="box"></div>
<div class="cent-box">
    <table class="table1" align="center" id="table">
        <caption>人事信息</caption>
        <tr><td>用户编号</td><td>姓名</td><td>联系电话</td><td>职务</td></tr>

    </table>
    <%--<ul>--%>
        <%--<li>用户编号</li>--%>
        <%--<li>姓名</li>--%>
        <%--<li>联系电话</li>--%>
        <%--<li>职务</li>--%>
    <%--</ul>--%>
</div>
<script src='js/particles.js' type="text/javascript"></script>
<script src='js/background.js' type="text/javascript"></script>
</body>
</html>
<script>
    $(document).ready(function(){
        $.getJSON("/json/getPersonnelOnlyRead",function(result) {
            $.each(result,function(index,comment){
//                $("div.cent-box").append("<ul><li>"+comment.u_id+"</li><li>"+comment.u_name+"</li><li>"+comment.phone_number+"</li><li>"+comment.duty+"</li></ul>");
                $("#table").append("<tr><td>"+comment.u_id+"</td><td>"+comment.u_name+"</td><td>"+comment.phone_number+"</td><td>"+comment.duty+"</td></tr>");
            })
        })
    });
</script>