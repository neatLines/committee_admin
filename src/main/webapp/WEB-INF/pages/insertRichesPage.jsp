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
        <tr><td><b>资产名：</b></td><td><input type="text" id="1" ></td></tr>
        <tr><td><b>数量：</b></td><td><input type="text" id="2"></td></tr>
        <tr><td><b>单价：</b></td><td><input type="text" id="3" readonly="true"></td></tr>
        <tr>
            <td><input type="button" value="提交" onclick="butt()"></td><td><input type="reset" value="重置"></td></tr>
    </table>
</form>
</body>
</html>

<script>
    function butt(){
        var str={p_name:document.getElementById("0").value,count:document.getElementById("1").value,p_value:document.getElementById("2").value};
        $.post("/json/addProperty",JSON.stringify(str),function(){alert("添加成功");})
    }
</script>
