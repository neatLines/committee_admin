<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>change.html</title>
    <meta charset="UTF-8">
    <link href="css/register-login.css" rel="stylesheet" type="text/css" />
<link href="css/reset.css" rel="stylesheet" type="text/css" />
<link href="css/master.css" rel="stylesheet" type="text/css" />
<link href="css/fonts.css" rel="stylesheet" type="text/css" />
<!-- jQuery Library + ALL jQuery Tools -->
<script src="http://cdn.jquerytools.org/1.2.5/full/jquery.tools.min.js" type="text/javascript" ></script>
<!-- fancy box img viewer -->
<script type="text/javascript" src="js/fancybox/jquery.fancybox-1.3.1.pack.js"></script>
<script type="text/javascript" src="js/fancybox/jquery.easing-1.3.pack.js"></script>
<link rel="stylesheet" type="text/css" href="js/fancybox/jquery.fancybox-1.3.1.css" media="screen" />
<!-- form validation -->
<script src="js/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript">
$(document).ready(function() {
	$("#myform").validate();
});
</script>  
</head>
  
  <body>
        <div id="box"></div>
		<div class="cent-box">
        <div id="main_container">
         <div id="header_content">
              <h1>人事信息修改<strong><br><br><br>请确认信息无误</strong></h1>
         </div>
        <!-- form -->
        <form method="post" id="myform" class="expose" >
                <fieldset class="col_f_1">	
                        <label>用户名</label><input type="text" name="username" id="1" class="required" value=<%=request.getParameter("u_name")%> />
                        <label>联系电话</label><input type="text" name="uname"  id="2" class="required" value=<%=request.getParameter("phone_number")%> />

                </fieldset>
                <fieldset class="col_f_2">
                    <label>权限</label> <select id="select" name="catalogs">
                    <option id="a" value="level2">系统管理员</option>
                    <option id="b" value="level1">普通管理员</option>
                    <option id="c" value="level0">用户</option>
                                       </select>
                    <label>职位</label><input type="text" name="duty"  id="3" class="required" value=<%=request.getParameter("duty")%> />
                </fieldset>
                <div class="clr"></div>
                <hr />

        </form>	
        <!--/form -->

         <div class="clr">
             <button id="bt2">确认修改</button>
         </div>
     </div>
     </div>
     <script src='js/particles.js' type="text/javascript"></script>
	 <script src='js/background.js' type="text/javascript"></script>
  </body>
</html>
<%--<script>--%>
    <%--$(document).ready(function () {--%>
        <%--var all_options = document.getElementById("select").options;--%>
        <%--$.getJSON("/json/getMinInfo",function(result) {--%>
            <%--document.getElementById("1").value=result.userName;--%>
            <%--document.getElementById("2").value=result.uName;--%>
            <%--var role = result.power;--%>
            <%--if(role=="0"){--%>
                <%--all_options[2].selected=true;--%>
            <%--}else if(role=="1"){--%>
                <%--all_options[1].selected=true;--%>
            <%--}else if(role=="2"){--%>
                <%--all_options[0].selected=true;--%>
            <%--}--%>
        <%--})--%>
    <%--})--%>
<%--</script>--%>
<script>
    $(document).ready(function () {
        var role;
        var all_options = document.getElementById("select").options;
        if(all_options[0].selected){
            role="2";
        }else if(all_options[1].selected){
            role="1";
        }else if(all_options[2].selected){
            role="0";
        }
        $("#bt2").click(function () {
            $.ajax({
                type: "POST",
                url: "/json/changeUserPower",
                data:JSON.stringify( {userName:$("#1").val(),uName:$("#2").val(),power:role}),
                success: function(result){
                    alert(result.info);
                },
                contentType: "application/json",
                dataType: "json"
            })
        })
    })
</script>