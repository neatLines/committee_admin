<%--
  Created by IntelliJ IDEA.
  User: fuyipeng
  Date: 2016/11/9
  Time: 下午8:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        div{
            padding: 0px;
        }
        ul{
            display: block;
            padding: 0px;
            margin:0px;
        }
        ul li{
            list-style: none;
            float: left;
            display: block;
            border-width: 1px;
        }
        div ul:first-child li{
            background-color: brown;
            color: white;
            font-size: larger;
            font-weight: bold;
        }
        .first,.forth,.third{width: 25%}
        .second{width:20%}
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

</div>
</body>
</html>
<script>
    $(document).ready(function(){

//        $.ajax({
//            type : "get",//设置参数表
//            url:"/json/getAllCommonality",
//            cache :true,
//            async :true,
//            dataType:"json",
//            success : function(result) {
//                $.each(result,function(index,comment){
//                    $("div").append("<ul><li class=\"first\">"+comment.con_id+"</li><li class=\"second\">"+comment.con_name+"</li><li class=\"third\">"+comment.con_date+"<li class=\"forth\">"+comment.count+"</li></ul>");
//                })
//            },
//            error : function(jqXHR, textStatus, errorThrown) {
//                alert(jqXHR.responseText);
//            }
//        })
        $.getJSON("/json/getAllCommonality",function(result) {
                $.each(result,function(index,comment){
                    $("div").append("<ul><li class=\"first\">"+comment.con_id+"</li><li class=\"second\">"+comment.con_name+"</li><li class=\"third\">"+comment.con_date+"<li class=\"forth\">"+comment.count+"</li></ul>");
                })
        })
    });
</script>
