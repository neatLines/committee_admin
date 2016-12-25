<%--
  Created by IntelliJ IDEA.
  User: 闫明阳
  Date: 2016/12/14
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>title</title>
    <script src="js/jquery.js"></script>
    <style>
        html{
            background-color: darkslategray;
        }
        form{
            padding: 0px;
            display: block;
            position: absolute;
            left: 35%;
            top: 0px;
        }
        table
        {   border-collapse:   separate;   border-spacing:   40px; display: block;color: white}
        table tr td{
            font-size: larger;
        }
        table input{
            text-decoration: none;
            color: darkred;
            font-size: large;;
            font-weight: bold;
            border-radius: 5px;
        }
    </style>
</head>
<body>
<form>
    <table>
        <tr><td><b>违规人：</b></td><td><input type="text" id="2"></td></tr>
        <tr><td><b>管理员：</b></td><td><input type="text" id="3"></td></tr>
        <tr><td><b>flag：</b></td><td><input type="text" id="4"></td></tr>
        <tr><td><b>违规内容：</b></td><td><textarea id="5" rows="10" cols="50"></textarea></td></tr>
        <td><input type="button" value="提交" onclick="butt()"></td><td><input type="reset" value="重置"></td></tr>
    </table>
</form>
</body>
</html>

<script>
    function butt(){
        $.post("/json/changeCommonality",JSON.stringify({u_name:document.getElementById("2").value,admin_name:document.getElementById("3").value,flag:document.getElementById("4").value,describe:document.getElementById("5").value}),function(){alert("处理成功")})
    }
</script>

