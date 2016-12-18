<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <meta charset="UTF-8">
<title>change.html</title>
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
              <h1>个人信息修改<strong><br><br><br>请确认信息无误</strong></h1>    
         </div>
        <!-- form -->
        <form action="#" method="post" id="myform" class="expose" > 
                <fieldset class="col_f_1">
                        <label>用户名</label><input type="text" name="username" id="1" class="required"  />
                        <label>姓名</label><input type="text" name="uname" id="2"   class="required email"  />
                        <label>性别</label> <input type="text" name="sex" id="3" />
                </fieldset>
                <fieldset class="col_f_2">	
                        <label>联系电话</label> <input type="text" name="telephone" id="4"  class="required"  />
                        <label>年龄</label><input type="text" name="age" id="5" />
                         
                 </fieldset>
                 <div class="clr"></div>
                 <hr />
                 <button type="submit">确认修改</button>
        </form>	
        <!--/form -->

         <div class="clr"></div>
     </div>
     </div>
     <script src='js/particles.js' type="text/javascript"></script>
	 <script src='js/background.js' type="text/javascript"></script>
  </body>
</html>
<script>
    $(document).ready(function(){
        $("#button").click(function(){
            $.ajax({
                type: "POST",
                url: "/json/changeInfo",
                data:JSON.stringify( {userName:$("#user").val(),password:$("#password").val()}),
                success: function(result){
                    location.href(result);
                },
                contentType: "application/json",
                dataType: "json"
            });
        });
    });
</script>
