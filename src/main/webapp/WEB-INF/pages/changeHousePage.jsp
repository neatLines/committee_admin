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
        {   border-collapse:   separate;   border-spacing:   40px; display: block;color: white;}
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
        <tr><td><b>房子Id：</b></td><td><input type="text" id="1" readonly="true"></td></tr>
        <tr><td><b>住户Id：</b></td><td><input type="text" id="2" ></td></tr>
        <tr><td><b>住户名：</b></td><td><input type="text" id="3"></td></tr>
        <tr><td><b>公寓：</b></td><td><input type="text" id="4" readonly="true" readonly="true"></td></tr>
        <tr><td><b>房产状态：</b></td><td><input type="text" id="5"></td></tr>
        <tr><td><input type="submit" onclick="butt()"></td>
            <td><input type="button" value="重置" onclick="_reset()"></td></tr>
    </table>
</form>
</body>
</html>
<%
    String temp0=request.getParameter("houseId");
    String temp1=request.getParameter("userId");
    String temp2=request.getParameter("name");
    String temp3=request.getParameter("house");
    String temp4=request.getParameter("state");

%>
<script>
    var temp0,temp1,temp2,temp3,temp4;
    temp0=<%=temp0%>;
    temp1=<%=temp1%>;
    temp2=<%=temp2%>;
    temp3=<%=temp3%>;
    temp4=<%=temp4%>;
            document.getElementById("1").value=temp0;
    document.getElementById("2").value=temp1;
    document.getElementById("3").value=temp2;
    document.getElementById("4").value=temp3;
    document.getElementById("5").value=temp4;
    function butt(){
        $.post("/json/change",JSON.stringify({house_id:document.getElementById("1").value,u_id:document.getElementById("2").value,u_name:document.getElementById("3").value,ap_house:document.getElementById("4").value,state:document.getElementById("5").value}),function(){alert("处理成功")})
  }
//  待重写
    function _reset(){
        document.getElementById("1").value=temp0;
        document.getElementById("2").value=temp1;
        document.getElementById("3").value=temp2;
        document.getElementById("4").value=temp3;
        document.getElementById("5").value=temp4;
    }
</script>
