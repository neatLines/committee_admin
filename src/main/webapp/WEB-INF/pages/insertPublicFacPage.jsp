<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery.js"></script>
    <meta charset="UTF-8">
    <title>Title</title>
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
        {   border-collapse:   separate;   border-spacing:   40px; display: block;margin:0px;color: white }
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
        <tr><td><b>名字:</b></td><td><input type="text" id="1"></td></tr>
        <tr><td><b>日期</b></td><td><input type="text" id="2"></td></tr>
        <tr><td><b>数量</b></td><td><input type="text" id="3"></td></tr>
        <tr><td></td><td><input type="submit" onclick="butt()">&nbsp;&nbsp;&nbsp;<input type="reset"></td></tr>
    </table>
</form>
</body>
</html>
<script>
    function butt(){
        $.post("/json/changeCommonality",{conName:document.getElementById("1").value,conDate:document.getElementById("2").value,count:document.getElementById("3").value},function(){alert("处理成功")})
    }
</script>