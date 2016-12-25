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
            overflow: hidden;
            text-align: center;
            list-style: none;
            float: left;
            display: block;
            width:40%;
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
        .first ,.second, .third .forth{width:13%}
        ul:first-child .first ,.second, .third ,.forth{width:15%}
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
            width: 99%;
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
        <li class="first" >咨询ID</li>
        <li class="second">标题</li>
        <li class="third">发布者</li>
        <li class="forth">发布日期</li>
        <li class="fifth">内容</li>
    </ul>
    <ul>
        <li class="first" >咨询ID</li>
        <li class="second">标题</li>
        <li class="third">发布者</li>
        <li class="forth">发布日期</li>
        <li class="fifth">内容</li>
    </ul>
    <ul>
        <li class="first" >咨询ID</li>
        <li class="second">标题</li>
        <li class="third">发布者</li>
        <li class="forth">发布日期</li>
        <li class="fifth">内容</li>
    </ul>


</div>
<div id="insert">
    <a href="insertConsultPage" target="rightPage">添加新的咨询</a>
</div>
</body>
</html>
<script>
    $("ul").click(function(){
        var temp=this.getElementsByTagName("li");
        var str="changeConsult.jsp?";
        str=str+"p_id='"+temp[0].innerHTML+"'";
        str=str+"&title='"+temp[1].innerHTML+"'";
        str=str+"&writer_id='"+temp[2].innerHTML+"'";
        str=str+"&date='"+temp[3].innerHTML+"'";
        str=str+"&detail='"+temp[4].innerHTML+"'";
        self.location.href=str;
    })
    $(document).ready(function(){
        $.getJSON("/json/getAllpublic",function (result) {
            for(var i=0;i<result.length;i++) {
                var temp =result[i];
                var str="<ul>";
                str=str+"<li class='first'>"+temp.p_id+"</li>";
                str=str+"<li class='second'>"+temp.title+"</li>";
                str=str+"<li class='third'>"+temp.writer_id+"</li>";
                str=str+"<li class='forth'>"+temp.date+"</li>";
                str=str+"<li class='fifth'>"+temp.detail+"</li>";
                str=str+"</li>";
                $("div").append(str);
            }
        })
    })
</script>
