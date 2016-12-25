<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>左侧导航</title>
    <head>

        <style type="text/css">
            html{
                background-color: darkslategray;
            }
            body{margin:0;padding:0;overflow-x:hidden;}
            html, body{height:100%;}
            img{border:none;}
            *{font-family:'微软雅黑';font-size:12px;color: black;}
            dl,dt,dd{display:block;margin:0; background-color:black;opacity:.75;font-weight: bold;font-size:large}
            a{text-decoration:none;font-size: large}
            /*#bg{background-image:url(images/content/dotted.png);}*/
            .container{width:100%;height:100%;margin:auto;}
            dl{font-size:x-large;letter-spacing: 10px;}
            /*left*/
            .leftsidebar_box{ border-radius:5px;width:13%;height:auto !important;overflow:visible !important;position:fixed;height:100% !important;background-color:black;}
            /**.line{height:2px;width:100%;background-image:url(images/left/line_bg.png);background-repeat:repeat-x;}*!*/
            .leftsidebar_box dt{border-radius:5px;padding-left:40px;padding-right:10px;width:inherit;background-repeat:no-repeat;background-position:10px center;color:#f5f5f5;font-size:large;position:relative;line-height:48px;cursor:pointer;}
            .leftsidebar_box dd{padding-left:40px;background-color:black;width:inherit;opacity:.75}
            .leftsidebar_box dd a{color:#f5f5f5;line-height:20px;}
            .leftsidebar_box dt{background-color: black;}
            .leftsidebar_box dt:hover{background-color: brown;}
            .leftsidebar_box dt img{position:absolute;right:10px;top:20px;}
            .leftsidebar_box dl dd:last-child{padding-bottom:10px;}
            .right{height:100% ;width:87%;position: relative;float:right;border: none;}
            .right iframe{width: 100%;height:100%}
            .person{background-image:url(images/left/person.png);background-size: 20px,20px }
            .info{background-image:url(images/left/info.png);background-size: 20px,20px }
            .comm{background-image:url(images/left/committee.png);background-size: 21px,21px }


        </style>

    </head>

<body id="bg">

<div class="container">

    <div class="leftsidebar_box">
        <dl class="system_log">
            <dt onClick="changeImage()" class="person">个人管理<img src="images/left/select_xl01.png"></dt>
            <dd><a href="singleIfo.jsp" target="rightPage">个人信息</a></dd>
        </dl>

        <%--//<dl class="custom">--%>
          <%--//  <dt onClick="changeImage()" class="comm">居委会管理<img src="images/left/select_xl01.png"></dt>--%>
            <%--//<dd><a href="#" target="rightPage">资产</a></dd>--%>
        <%--//</dl>--%>

        <dl class="custom">
            <dt onClick="changeImage()" class="info">信息管理<img src="images/left/select_xl01.png"></dt>
            <dd class="first_dd" ><a href="consultPage" target="rightPage">咨询管理</a></dd>
            <dd><a href="publicFacPage" target="rightPage">公共设施管理</a></dd>
            <dd><a href="peccancyPage" target="rightPage">违章记录</a></dd>
            <dd><a href="parkPage" target="rightPage" >车位信息</a></dd>
            <dd><a href="housePage" target="rightPage" >房间信息</a></dd>
            <dd class="first_dd" ><a href="richesPage" target="rightPage">资产信息</a></dd>
        </dl>
    </div>
    <div class="right" style="background-color: darkslategray">
        <iframe name="rightPage"></iframe>
    </div>
</div>


<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
    $(".leftsidebar_box dt img").attr("src","images/left/select_xl01.png");
    $(function(){
        $(".leftsidebar_box dd").hide();
        $(".leftsidebar_box dt").click(function(){
            $(".leftsidebar_box dt").css({"background-color":"black"});
            $(this).css({"background-color": "brown"});
            $(".leftsidebar_box dt img").attr("src","images/left/select_xl01.png");
            $(this).parent().find('img').attr("src","images/left/select_xl.png");
            $(this).parent().find('dd').slideToggle();
        });
    })
</script>
</body>
</html>