<!DOCTYPE html>
<html>
<head>
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
              <h1>������Ϣ�޸�<strong><br><br><br>��ȷ����Ϣ����</strong></h1>
         </div>
        <!-- form -->
        <form action="#" method="post" id="myform" class="expose" > 
                <fieldset class="col_f_1">	
                        <label>�û���</label><input type="text" name="username"  class="required"  /> 
                        <label>��ϵ�绰</label><input type="text" name="uname"   class="required email"  />
                        <label>Ȩ��</label> <select name="catalogs">
                                            <option value="level1">ϵͳ����Ա</option>
                                            <option value="level2">��ͨ����Ա</option>
                                            <option value="level3">�û�</option>
                                            </select>
                </fieldset>
                <!--<fieldset class="col_f_2">-->
                        <!--<img src="https://gss0.bdstatic.com/70cFsjip0QIZ8tyhnq/img/iknow/logo-iknowxjd.gif">-->

                 <!--</fieldset>-->
                 <div class="clr"></div>
                 <hr />
                 <button type="submit">ȷ���޸�</button>
        </form>	
        <!--/form -->

         <div class="clr"></div>
     </div>
     </div>
     <script src='js/particles.js' type="text/javascript"></script>
	 <script src='js/background.js' type="text/javascript"></script>
  </body>
</html>
