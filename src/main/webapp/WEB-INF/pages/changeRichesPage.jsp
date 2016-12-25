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
        {   border-collapse:   separate;   border-spacing:   40px; display: block;}
        table tr td{
            font-size: larger;
            color: white;
            font-weight: bold;
        }
        table input{
            text-decoration: none;
            color: darkred;
            font-size: large;
            border-radius: 5px;
            font-weight: bold;
        }
    </style>
</head>
<body>
<form>
    <table>
        <tr><td><b>资产ID：</b></td><td><input type="text" id="1" readonly="true"></td></tr>
        <tr><td><b>资产名：</b></td><td><input type="text" id="2" ></td></tr>
        <tr><td><b>数量：</b></td><td><input type="text" id="3"></td></tr>
        <tr><td><b>单价：</b></td><td><input type="text" id="4"></td></tr>
        <tr><td><input type="submit" onclick="butt()"></td>
            <td><input type="button" value="删除" onclick="_delete()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="重置" onclick="_reset()"></td></tr>
    </table>
</form>
</body>
</html>
<%
    String temp0=request.getParameter("property_id");
    String temp1=request.getParameter("p_name");
    String temp2=request.getParameter("count");
    String temp3=request.getParameter("p_value");
%>
<script>
    var temp0=<%=temp0%>;
    var temp1=<%=temp1%>;
    var temp2=<%=temp2%>;
    var temp3=<%=temp3%>;
    document.getElementById("1").value=temp0;
    document.getElementById("2").value=temp1;
    document.getElementById("3").value=temp2;
    document.getElementById("4").value=temp3;
    function butt(){
        $.post("/json/changeProperty",JSON.stringify({property_id:document.getElementById("1").value,p_name:document.getElementById("2").value,count:document.getElementById("3").value,p_value:document.getElementById("4").value}),function(){alert("处理成功")})
    }
    function _reset(){
        document.getElementById("1").value=temp0;
        document.getElementById("2").value=temp1;
        document.getElementById("3").value=temp2;
        document.getElementById("4").value=temp3;
    }
    function _delete(){
        $.post("/json/deleteProperty",JSON.stringify({property_id:temp0}),function(){alert("删除成功");});
    }
</script>


