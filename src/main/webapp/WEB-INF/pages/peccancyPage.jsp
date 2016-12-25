<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="js/jquery.js"></script>
    <title>Title</title>
    <style>
        html,body{
            width:100%;
            height: 100%;
            background-color: darkslategray;
        }
        div{
            padding: 0px;
            margin:0px;
        }
        ul{
            display: block;
            padding: 0px;
            margin-left:30px;
        }
        div ul:first-child
        {
            margin:0px;
        }
        ul li{
            overflow:hidden;
            list-style: none;
            margin: 0px;
            padding: 0px;
            float: left;
            display: block;
            width: 19.2%;
            opacity:.75;
            text-align: center;
            color: black;
            font-weight: bold;
            font-size: larger;
            background-color: white;
        }
        div ul:first-child li{
            width: 19.6%;
            background-color: brown;
            color: white;
            font-size: x-large;
            font-weight: bold;
            height:30px;
            padding-top: 5px;
        }
        div ul:first-child li:first-child
        {
            border-bottom-left-radius: 5px;
            border-top-left-radius: 5px;
        }
        div ul:first-child li:last-child
        {
            border-top-right-radius: 5px;
            border-bottom-right-radius: 5px;
        }
        div ul:not(:first-child):hover li{
            opacity: 1;
            height: 50px;
            font-size: xx-large;
            padding-top: 15px;
            z-index: 10;
        }
        #insert a{
            text-decoration: none;
            color: white;
            font-weight: bold;
        }
        #insert{
            opacity: .75;
            border-radius: 5px;
            width: 98%;
            height: 25px;
            font-size: x-large;
            font-weight: bold;
            text-align: center;
            background-color: brown;
            float: left;
        }
    </style>
</head>
<body>
<div id="first">
    <ul>
        <li>违章记录ID</li>
        <li>违规人</li>
        <li>管理员</li>
        <li>flag</li>
        <li>违章记录</li>
    </ul>
    <ul>
        <li>违章记录ID</li>
        <li>违规人</li>
        <li>管理员</li>
        <li>flag</li>
        <li>违章记录</li>
    </ul>
    <ul>
        <li>违章记录ID</li>
        <li>违规人</li>
        <li>管理员</li>
        <li>flag</li>
        <li>违章记录</li>
    </ul>


</div>
<div id="insert">
    <a href="insertPeccancyPage" target="rightPage">添加新的违规记录</a>
</div>
</body>
</html>
<script>
    $("ul").click(function(){
        var temp=this.getElementsByTagName("li");
        window.location.href="changePeccancyPage?break_log_id='"+temp[0].innerHTML+"'"+"&u_name='"+temp[1].innerHTML+"'"+"&admin_name='"+temp[2].innerHTML+"'"+"&flag='"+temp[3].innerHTML+"'"+"&describe='"+temp[4].innerHTML+"'";
    })
    $(document).ready(function(){
        $.getJSON("/json/getAllBreakLog",function (result) {
            for(var i=0;i<result.length;i++)
            {
                var temp = result[i];
                var str="";
                var j=0;
                for(var x in temp)
                {
                    str=str+"<li>"+temp[x]+"</li>";
                }
                $("div").append("<ul>"+str+"</ul>");
            }
        })
    })
</script>