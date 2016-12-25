<%--
  Created by IntelliJ IDEA.
  User: 闫明阳
  Date: 2016/12/14
  Time: 18:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>title</title>
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
        {   border-collapse:   separate;   border-spacing:   40px; display: block; color: white}
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
    <tr><td><b>用户ID：</b></td><td><input type="text" id="1" ></td></tr>
    <tr><td><b>停车位：</b></td><td><input type="text" id="2" readonly="true"></td></tr>
    <tr><td><b>地址：</b></td><td><input type="text" id="3" readonly="true"></td></tr>
    <tr class="button"><td><input type="button" value="修改" onclick="butt()"></td>
        <td><input type="button" value="重置" onclick="_reset()"></td></tr>
</table></form>
</body>
</html>
<%
    String temp0=request.getParameter("u_id");
    String temp1=request.getParameter("park_id");
    String temp2=request.getParameter("place");

%>
<script>
    var temp0,temp1,temp2;
    temp0=<%=temp0%>;
    temp1=<%=temp1%>;
    temp2=<%=temp2%>;
    document.getElementById("1").value=temp0;
    document.getElementById("2").value=temp1;
    document.getElementById("3").value=temp2;
    function butt(){
        $.post("/json/changePark",JSON.stringify({u_id:document.getElementById("1").value,park_id:document.getElementById("2").value,place:document.getElementById("3").value,count:document.getElementById("4").value}),function(){alert("处理成功")})
    }
//    待重写
    function _reset(){
        document.getElementById("1").value=temp0;
        document.getElementById("2").value=temp1;
        document.getElementById("3").value=temp2;
    }
</script>