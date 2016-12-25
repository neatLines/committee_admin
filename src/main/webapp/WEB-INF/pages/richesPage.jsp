<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <script src="js/jquery.js"></script>
    <title>Title</title>
    <style>
        html{
            background-color: darkslategray;
        }
        div{
            padding: 0px;
        }
        ul{
            display: block;
            padding: 0px;
            margin-left:35px;
        }
        ul:first-child{margin-left: 15px;}
        div ul:not(:first-child):hover li{
            opacity: 1;
            height: 50px;
            font-size: xx-large;
            padding-top: 15px;
            z-index: 10;
        }
        ul li{
            text-align: center;
            list-style: none;
            float: left;
            display: block;
            width:55%;
            opacity:.75;
            color: black;
            font-weight: bold;
            font-size: larger;
            background-color: white;
        }
        div ul:first-child li{
            background-color: brown;
            color: white;
            font-weight: bold;
            font-size: x-large;
            height:30px;
            padding-top: 5px;
        }
        .first,.second {width:24%;}
        .third{width:23%}
        .forth{width:23%}
        ul:first-child .first,.second{width:26%}
        a{text-decoration: none;
            color: inherit;
            font-size: inherit}
        #insert a{
            text-decoration: none;
            font-weight: bold;
            color: white;
        }
        #insert{
            opacity: .75;
            border-radius: 5px;
            height: 25px;
            font-size: x-large;
            font-weight: bold;
            text-align: center;
            background-color: brown;
            float: left;
            width: 97%;
            margin-left: 15px;
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

    </style>
</head>
<body>
<div>
    <ul>
        <li class="first" >资产ID</li>
        <li class="second">资产名</li>
        <li class="third">数量</li>
        <li class="forth">单价</li>
    </ul>
    <ul>
        <li class="first" >资产ID</li>
        <li class="second">资产名</li>
        <li class="third">数量</li>
        <li class="forth">单价</li>
    </ul>
    <ul>
        <li class="first" >资产ID</li>
        <li class="second">资产名</li>
        <li class="third">数量</li>
        <li class="forth">单价</li>
    </ul>
    <ul>
        <li class="first" >资产ID</li>
        <li class="second">资产名</li>
        <li class="third">数量</li>
        <li class="forth">单价</li>
    </ul>

</div>
<div id="insert">
    <a href="insertConsultPage" target="rightPage">添加新的资产</a>
</div>
</body>
</html>
<script>
    $("ul").click(function(){
        var temp=this.getElementsByTagName("li");
        window.location.href="changeRichesPage?property_id='"+temp[0].innerHTML+"'"+"&p_name='"+temp[1].innerHTML+"'"+"&count='"+temp[2].innerHTML+"'"+"&p_value='"+temp[3].innerHTML+"'";
    })
    $(document).ready(function(){
        $.getJSON("/json/getAllProperty",function (result) {
            for(var i=0;i<result.length;i++)
            {
                var temp=result[i];
                $("div").append("<ul><li class='first'>"+temp.property_id+"</li>"+"<li class='second'>"+temp.p_name+"</li>"+"<li>"+temp.count+"</li>"+"<li>"+temp.p_value+"</li></ul>");
            }
        })
    })
</script>
