<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="js/jquery.js"></script>
    <title>Title</title>
    <style>
        div{
            padding: 0px;
        }
        html{
            background-color: darkslategray;
        }
        ul{
            display: block;
            margin-left: 20px;
        }
        ul li{
            list-style: none;
            float: left;
            display: block;
            opacity:.75;
            text-align: center;
            color: black;
            font-weight:bold;
            height:30px;
            font-size:larger;
            background-color:white;
        }
        div ul:first-child li{
            background-color: brown;
            color: white;
            font-weight: bold;
            font-size: x-large;
            height:30px;
            padding-top: 5px;
        }
        ul:first-child{
            margin-left:5px;
        }
        .first,.second ,.fifth {width: 15%;}
        .third{width:25%}
        .forth{width:25%}
        ul:first-child .first,.second ,.fifth{width:16%;}
        a{text-decoration: none;
        color: inherit;
        font-size: inherit}
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
    </style>
</head>
<body><div id="first">
        <ul>
            <li class="first" >房子ID</li>
            <li class="second">用户ID</li>
            <li class="fifth">用户名</li>
            <li class="third">公寓</li>
            <li  class="forth">房产状态</li>
        </ul>
    <ul>
        <li class="first" >房子ID</li>
        <li class="second">用户ID</li>
        <li class="fifth">用户名</li>
        <li class="third">公寓</li>
        <li  class="forth">房产状态</li>
    </ul>
    <ul>
        <li class="first" >房子ID</li>
        <li class="second">用户ID</li>
        <li class="fifth">用户名</li>
        <li class="third">公寓</li>
        <li  class="forth">房产状态</li>
    </ul>
</div>
</body>
</html>
<script>
    $("ul").click(function(){
        var temp=this.getElementsByTagName("li");
        window.location.href="changeHousePage?houseId='"+temp[0].innerHTML+"'"+"&userId='"+temp[1].innerHTML+"'"+"&name='"+temp[2].innerHTML+"'"+"&house='"+temp[3].innerHTML+"'"+"&state='"+temp[4].innerHTML+"'";
    })
    $(document).ready(function(){
        $.getJSON("/json/getAllHouseInfo",function (result) {
            for(var i=0;i<result.length;i++)
            {
                var temp = result[i];
                var str="<ul><li  class='first'>"+temp.house_id+"</li>"+"<li class='second'>"+temp.temp_id+"</li>"
                +"<li class='fifth'>"+temp.u_name+"</li>"+"<li class='third'>"+temp.ap_house+"</li>"+"<li class='forth'>"+temp.state
                +"</li></ul>"};
            $("#first").append(str);
        })
    })
</script>