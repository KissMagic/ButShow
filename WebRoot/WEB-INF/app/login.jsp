<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh_CN" ng-app="app">
  <head>
    <base href="<%=basePath%>">
    
    <title>吧嗒秀</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="butshow,吧嗒秀,ButShow,吧嗒">
	<meta http-equiv="description" content="ButShow">
	<meta property="qc:admins" content="2337107277625430776375" />
	<meta property="wb:webmaster" content="5ec89b86193ce90f" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
	<link href="css/bootstrap.css" rel="stylesheet" media="screen">
	<link href="css/login.css" rel="stylesheet" media="screen">
	<script src="vendor/jquery/jquery-2.1.4.min.js"></script>
    <script src="vendor/jquery/bootstrap.min.js"></script>
    <script src="vendor/angular/angular.js"></script>
  </head>

<body style="background-image: url('angular/img/background_1.jpg');">
	<div class="bodyDiv">
		<div class="logoDiv">
		<div class="logoImg" style="background-image: url('angular/img/logo.png');"></div><h1 class="logoFont">登陆</h1></div>
		<div class="formDiv">
		<form action="<%=basePath%>user/userLogin" class="form-horizontal"
			role="form" method="post">
			<div class="row hidden">
				<div class="alert alert-danger alert-dismissable col-xs-3 col-xs-offset-2">
					<button type="button" class="close" data-dismiss="alert">&times;</button>
					<strong>Error!</strong>${errorInfo }
				</div>
			</div>
			<div class="form-group">
				<label for="userName" class="col-xs-2 control-label logoLabel">账号</label>
				<div class="col-xs-3">
					<input type="text"  id="userName"
						name="username" placeholder="邮箱/手机/账号" class="form-control no-border logoInput" maxlength="40" required
						autoComplete="off" onpaste="return false;" tabindex="1" pattern=".{2,40}" title="请输入邮箱/手机/账号">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-xs-2 control-label logoLabel">密码</label>
				<div class="col-xs-3">
					<input type="password" name="loginpassword"
						id="inputPassword3" placeholder="密码" class="form-control no-border logoInput" maxlength="20" required
				autoComplete="off" onpaste="return false;" oncopy="return false;" tabindex="3" pattern=".{6,20}" title="请输入密码">
				</div>
			</div>
			<div class="form-group">
				<div class="col-xs-offset-2 col-xs-3">
	      			<div class="text-center m-t m-b" style="width: 170px;">
		      			<a href="" style="float:left;margin-left:5px;width:80px;">忘记密码?</a>
		      			<a href="<%=basePath%>user/gotoAdd" style="float:right;margin-right:5px;">注册</a>
		      		</div>
					<button type="submit" class="btn btn-lg btn-primary btn-block logoButton">登陆</button>
				</div>
			</div>
			<div class="form-group" style="margin:0px 0px 0px 0px;">
				<label class="text-muted logoThird">第三方登录</label>
				<a href="<%=basePath%>qq/login"><img class="logoThirdImg" src="img/QQ_Logo.png"></a>
				<a href="<%=basePath%>sina/login"><img class="logoThirdImg" src="img/Sina_Logo.png"></a>
			</div>
		</form>
		</div>
	</div>
	<div class="text-center" style="margin-bottom: 10px;">
		<p>
			<small class="text-muted" style="">&copy; 2015 ButShow.com 版权所有ICP证：<a target="_blank" href="http://www.miitbeian.gov.cn/">浙ICP备15044012号</a>
			<br>友情连接：<a target="_blank" href="http://www.aliyun.com/">阿里云</a>&nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank" href="http://tieba.baidu.com/f?kw=butshow&fr=wwwt">百度贴吧</a></small>
		</p>
	</div>
</body>
<script type="text/javascript">
	function changeLogo(){
	}

	changeBG(1);
	function changeBG(i) {
		var n = i;
		if (n > 4)
			n = 1;
		var bg = document.body || document.documentElement;
		bg.style.backgroundColor = "#FFFFFF";
		bg.style.background = "#FFFFFF url('angular/img/background_" + n + ".jpg') no-repeat";
		bg.style.backgroundPosition = "top center";
		bg.style.backgroundSize = "80% auto";
		n++;
		setTimeout("changeBG(" + n + ")", 130); //1秒轮换一张 2000为两秒
	}
</script> 
</html>
