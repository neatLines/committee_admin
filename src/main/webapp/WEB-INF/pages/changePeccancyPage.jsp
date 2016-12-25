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
        <tr><td><b>违章记录ID：</b></td><td><input type="text" id="1" readonly="true"></td></tr>
        <tr><td><b>违规人：</b></td><td><input type="text" id="2"></td></tr>
        <tr><td><b>管理员：</b></td><td><input type="text" id="3"></td></tr>
        <tr><td><b>flag：</b></td><td><input type="text" id="4"></td></tr>
        <tr><td><b>违规内容：</b></td><td><textarea id="5" rows="10" cols="50"></textarea></td></tr>
        <tr><td><input type="button" value="删除" onclick="_delete()"></td>
            <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="重置" onclick="_reset()"></td></tr>
    </table>
</form>
</body>
</html>
<%
    String temp0=request.getParameter("break_log_id");
    String temp1=request.getParameter("u_name");
    String temp2=request.getParameter("admin_name");
    String temp3=request.getParameter("flag");
    String temp4=request.getParameter("describe");

%>
<script>
    var temp0=<%=temp0%>;
    var temp1=<%=temp1%>;
    var temp2=<%=temp2%>;
    var temp3=<%=temp3%>;
    var temp4=<%=temp4%>;
    document.getElementById("1").value=temp0;
    document.getElementById("2").value=temp1;
    document.getElementById("3").value=temp2;
    document.getElementById("4").value=temp3;
    document.getElementById("5").value=temp4;
    function _delete(){
        $.post("/json/deleteBreakLog",JSON.stringify({break_log_id:temp0}),function(){alert("删除成功");});
    }
    function _reset(){
        document.getElementById("1").value=temp0;
        document.getElementById("2").value=temp1;
        document.getElementById("3").value=temp2;
        document.getElementById("4").value=temp3;
        document.getElementById("5").value=temp4;

    }
</script>
