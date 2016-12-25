<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery.js"></script>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
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
        <tr><td><b>公共设施ID：</b></td><td><input type="text" id="1" readonly="true"></td></tr>
        <tr><td><b>名字：&nbsp;&nbsp;&nbsp;</b></td><td><input type="text" id="2"></td></tr>
        <tr><td><b>日期：&nbsp;&nbsp;&nbsp;</b></td><td><input type="text" id="3"></td></tr>
        <tr><td><b>数量：&nbsp;&nbsp;&nbsp;</b></td><td><input type="text" id="4"></td></tr>
        <tr class="button"><td><input type="submit" onclick="butt()"></td>
            <td><input type="button" value="删除" onclick="_delete()">&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="重置" onclick="_reset()"></td></tr>
    </table>
</form>
</body>
</html>
<%
    String temp0=request.getParameter("id");
    String temp1=request.getParameter("name");
    String temp2=request.getParameter("data");
    String temp3=request.getParameter("num");

%>
<script>
    var temp0,temp1,temp2,temp3;
    temp0=<%=temp0%>;
    temp1=<%=temp1%>;
    temp2=<%=temp2%>;
    temp3=<%=temp3%>;
    document.getElementById("1").value=temp0;
    document.getElementById("2").value=temp1;
    document.getElementById("3").value=temp2;
    document.getElementById("4").value=temp3;
    function butt(){
        $.post("/json/changeCommonality",JSON.stringify({conId:document.getElementById("1").value,conName:document.getElementById("2").value,conDate:document.getElementById("3").value,count:document.getElementById("4").value}),function(){alert("处理成功")})
    }
    function _reset(){
        document.getElementById("1").value=temp0;
        document.getElementById("2").value=temp1;
        document.getElementById("3").value=temp2;
        document.getElementById("4").value=temp3;
    }
    function _delete(){
        $.post("",JSON.stringify({conId:temp0}),function(){alert("删除成功");});
    }
</script>
