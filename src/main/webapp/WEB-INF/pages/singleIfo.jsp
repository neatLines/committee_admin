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
        {   border-collapse:   separate;   border-spacing:   40px; display: block;margin:0px;color: white}
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
        <tr><td><b>用户名：</b></td><td><input type="text" id="1"></td></tr>
        <tr><td><b>名字：</b></td><td><input type="text" id="2"></td></tr>
        <tr><td><b>手机号：</b></td><td><input type="text" id="3"></td></tr>
        <tr><td><b>年龄：</b></td><td><input type="text" id="4"></td></tr>
        <tr><td><b>性别：</b></td><td><input type="text" id="5"></td></tr>
        <tr><td><b>权限：</b></td><td><input type="text" id="6" readonly="true"></td></tr>
        <tr class="button"><td><input type="submit" id="_submit" ></td>
            <td><input type="button" value="重置"  id="reset">&nbsp;&nbsp;&nbsp;<input type="button" value="修改" id="change"></td></tr>
    </table>
</form>
</body>
</html>
<script>
    var temp;
    $(document).ready(function(){
        $.getJSON("/json/userAdmin", function(result) {
            var j=1;
            temp=result;
            for (var x in result)
            {
                $("#"+j).value=result[x];
                j+=1;
            }
        })
        $("#_submit").click(function(){
            $.post("/json/changeInfo","{}",function(result){alert("提交成功");},"JSON");
        })
    });

    /*目前没有*/
</script>