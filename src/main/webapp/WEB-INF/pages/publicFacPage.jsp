<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <script src="js/jquery.js"></script>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        html,body{
            width:100%;
            height: 100%;
            background-color: darkslategray;
        }
        div{
            padding: 0px;
            margin-left:5px;
        }

        ul{
            display: block;
            padding: 0px;
            margin-left: 5px;
        }
        div ul:not(:first-child)
        {
            margin-left: 13px;
        }
        ul li{
            list-style: none;
            float: left;
            display: block;
            border-width: 1px;
            margin: 0px;
            padding: 0px;
            opacity: .75;
            text-align: center;
            color: black;
            font-weight: bold;
            font-size: larger;
            background-color: white;
        }
        div ul:not(:first-child):hover li{
            opacity: 1;
            height: 50px;
            font-size: xx-large;
            padding-top: 15px;
            z-index: 10;
        }
        ul:not(:first-child) .first,.forth,.third{width: 25%}
        ul:not(:first-child) .second{width:20%}
        .first,.forth,.third{width: 26%}
         .second{width:20%}
        #insert{
            opacity: .75;
            width: 98%;
            text-align: center;
            background-color: brown;
            float: left;
            border-radius: 5px;
            height: 25px;
            font-size: x-large;
            font-weight: bold;
        }
        #insert a{
            text-decoration: none;
            color: white;
            font-weight: bold;
        }

        div ul:first-child li{
            color: white;
            font-weight: bold;
            background-color: brown;
            font-size: x-large;
            height:30px;
            padding-top:5px;
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
        <li class="first">公共设施ID</li>
        <li class="second">名字</li>
        <li class="third">日期</li>
        <li class="forth">数量</li>
    </ul>
    <ul>
        <li class="first">公共设施ID</li>
        <li class="second">名字</li>
        <li class="third">日期</li>
        <li class="forth">数量</li>
    </ul>
    <ul>
        <li class="first">公共设施ID</li>
        <li class="second">名字</li>
        <li class="third">日期</li>
        <li class="forth">数量</li>
    </ul>
    <ul>
        <li class="first">公共设施ID</li>
        <li class="second">名字</li>
        <li class="third">日期</li>
        <li class="forth">数量</li>
    </ul>
    <ul>
        <li class="first">公共设施ID</li>
        <li class="second">名字</li>
        <li class="third">日期</li>
        <li class="forth">数量</li>
    </ul>
    <ul>
        <li class="first">公共设施ID</li>
        <li class="second">名字</li>
        <li class="third">日期</li>
        <li class="forth">数量</li>
    </ul>
    <ul>
        <li class="first">公共设施ID</li>
        <li class="second">名字</li>
        <li class="third">日期</li>
        <li class="forth">数量</li>
    </ul>
</div>
<div id="insert">
    <a href="insertPublicFacPage" target="rightPage">添加新的公共设施数据</a>
</div>
</body>
</html>
<script>
    $(document).ready(function(){
        $.getJSON("192.168.1.118:8080/json/getAllCommonality", function(result){
            var example=["first","second","third","forth"];
            for(var i=0;i<result.length;i++) {
                var temp = result[i];
                var str ="";
                var j = 0;
                for (var x in temp) {
                    str = str + "<li class=" + example[j++] + ">" + temp[x] + "</li>";
                }
                $("div").append("<ul>" + str + "</ul>");
            }
        })
        })
        $("ul:not(:first-child)").click(function () {
               var id=this.getElementsByTagName("li")[0].innerHTML;
            var name=this.getElementsByTagName("li")[1].innerHTML;
            var date=this.getElementsByTagName("li")[2].innerHTML;
            var num=this.getElementsByTagName("li")[3].innerHTML;
            self.location.href="publicFacPageInfo?id='"+id+"'"+"&name='"+name+"'"+"&data='"+date+"'"+"&num='"+num+"'";
        })

</script>