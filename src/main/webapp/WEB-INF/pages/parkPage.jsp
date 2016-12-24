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
      ul{
          display: block;
          padding:0px;
          margin-left:20px;
      }
       ul:first-child
       {
           margin-left: 10px;
       }
        ul li {
            list-style: none;
            float: left;
            width: 54%;
            text-align: center;
            display: block;
            opacity:.75;
            color: black;
            font-weight: bold;
            font-size: larger;
            background-color: white;

        }
        ul:first-child li{
            background-color: brown;
            color: white;
            font-weight: bold;
            font-size: x-large;
            height:30px;
            padding-top: 5px;

        }
     div ul:not(:first-child):hover li{
         opacity: 1;
         height: 50px;
         font-size: xx-large;
         padding-top: 15px;
         z-index: 10;
     }
        .second,.first{width: 12%;}
        .third{width: 18%}
        ul:first-child .second,.first{width:13%}
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
    </style>
</head>
<body><div>
    <ul>
        <li class="first">用户ID</li>
        <li class="second">用户名</li>
        <li class="third">停车位</li>
        <li>地址</li>
    </ul>
    <ul>
        <li class="first">用户ID</li>
        <li class="second">用户名</li>
        <li class="third">停车位</li>
        <li>地址</li>
    </ul>  <ul>
    <li class="first">用户ID</li>
    <li class="second">用户名</li>
    <li class="third">停车位</li>
    <li>地址</li>
</ul>  <ul>
    <li class="first">用户ID</li>
    <li class="second">用户名</li>
    <li class="third">停车位</li>
    <li>地址</li>
    </ul>
    <ul>
    <li class="first">用户ID</li>
    <li class="second">用户名</li>
    <li class="third">停车位</li>
    <li>地址</li>
</ul>
</div>
</body>
</html>
<script>
    $("ul").click(function(){
        var temp=this.getElementsByTagName("li");
        window.location.href="changeParkPage.jsp?id='"+temp[0].innerHTML+"'"+"&name='"+temp[1].innerHTML+"'"+"&park='"+temp[2].innerHTML+"'"+"&address='"+temp[3].innerHTML+"'";
    })
    $(document).ready(function(){
        $.getJSON("/json/getPersonnelOnlyRead",function (result) {
            var example=["first","second","third",""];
            for(var i=0;i<result.length;i++)
            {
                var temp = result[i];
                var str="";
                var j=0;
                for(var x in temp)
                {
                    str=str+"<li class="+example[j++]+">"+temp[x]+"</li>";
                }
                $("div").append("<ul>"+str+"</ul>");
            }

        })
    })
</script>