<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>User Registered</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="butshow,吧嗒秀,ButShow">
<meta http-equiv="description" content="User Registered">

<script src="vendor/jquery/jquery-2.1.4.min.js"></script>
<script src="vendor/jquery/bootstrap.min.js"></script>
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
<style type="text/css">
	.inputtip{color: #FF0000;margin-left:200px;display:none;}
</style>
</head>

<body>
<div class="container">
	<h1 class="page-header">ButShow 注册</h1>
	<form action="<%=basePath%>user/addUser" class="form-horizontal" role="form" method="post" id="userRegistered">
		<div class="form-group">
			<label for="email" class="col-xs-2 control-label">登陆邮箱</label>
			<div class="col-xs-3">
				<input type="email" class="form-control" id="email" name="email" placeholder="邮箱" maxlength="40" required
				autoComplete="off" onpaste="return false;" tabindex="1" pattern=".{6,40}" title="请输入正确邮箱">
			</div>
			<div id="emailTip" class="inputtip">该邮箱已存在!</div>
		</div>
		<div class="form-group">
			<label for="userName" class="col-xs-2 control-label">登陆账号</label>
			<div class="col-xs-3">
				<input type="text" class="form-control" id="userName" name="username" placeholder="2-12位汉字、字母、数字" maxlength="12" required
				autoComplete="off" onpaste="return false;" tabindex="2" pattern=".{2,12}" title="只允许2到12位字符">
			</div>
			<div id="userNameTip" class="inputtip">该账号已存在!</div>
		</div>
		<div class="form-group">
			<label for="inputPassword" class="col-xs-2 control-label">输入密码</label>
			<div class="col-xs-3">
				<input type="password" class="form-control" name=loginpassword id="inputPassword" placeholder="6-20位符号、字母、数字" maxlength="20" required
				autoComplete="off" onpaste="return false;" oncopy="return false;" tabindex="3" pattern=".{6,20}" title="只允许6到20位字符">
			</div>
			<div id="passworderr" class="inputtip">密码格式不正确!</div>
			<div id="capslock" class="inputtip">键盘大写锁定已打开，请注意大小写!</div>
		</div>
		<div class="form-group">
			<label for="inputRePassword" class="col-xs-2 control-label">确认密码</label>
			<div class="col-xs-3">
				<input type="password" class="form-control" name="Repassword" id="inputRePassword" placeholder="6-20位符号、字母、数字" maxlength="20" required
				autoComplete="off" onpaste="return false;" oncopy="return false;" tabindex="4" pattern=".{6,20}" title="只允许6到20位字符">
			</div>
			<div id="repassworderr" class="inputtip">两次输入密码不一致!</div>
		</div>
		<div class="form-group">
			<label for="inputPassword" class="col-xs-2 control-label">性别</label>
			<div class="col-xs-3">
				<div class="checkbox">
					<label> <input type="radio" checked="checked" name="SexRadio" value="1" id="man" tabindex="5"> 男 </label>
					<label style="float:right;margin-right:40px;">	<input type="radio" name="SexRadio" value="0" id="woman" tabindex="6"> 女 </label>
				</div>
			</div>
		</div>
		<div class="form-group">
			<label for="inputPhone" class="col-xs-2 control-label">手机</label>
			<div class="col-xs-3">
				<input type="tel" class="form-control" name="phone" id="inputPhone" placeholder="手机号码" maxlength="11" required 
				autoComplete="off" onpaste="return false;" tabindex="7" onblur="phoneBlur();" pattern=".{11,11}" title="请输正确入手机号">
			</div>
			<div id="phoneerr" class="inputtip">手机号码不正确!</div>
			<div id="phoneTip" class="inputtip">手机号码已被使用!</div>
		</div>
		
		<div class="form-group">
			<div class="col-xs-offset-2 col-xs-3">
				<button type="submit" class="btn btn-success btn-block">注册</button>
			</div>
			<div id="submiterr" class="inputtip">您填写的信息有误!</div>
		</div>
	</form>
	</div>
</div>

<script type="text/javascript">

$('#userName').blur(userNameblur);
$('#email').blur(emailblur);
$('#inputPassword').blur(PasswordBlur);
$('#inputRePassword').blur(rePasswordBlur);
$('#inputPhone').blur(phoneBlur);
$('#userRegistered').on("submit",formSubmit);

function userNameblur(){
	var userName = $('#userName').val();
	if(userName.length<2 || userName.length>12)
		return;
	if(userName != ''){
		var url = "<%=basePath%>user/getUserByName";
		$.ajax({  
	        type:'post',      
	        url:url,  
	        data:{username:userName},  
	        cache:false,  
	        dataType:'json',  
			success:function(data){ 
				 if(data) {
					 $("#userNameTip").show();
					 $("#userNameTip").prop('iserror',true);
				 }else {
				 	$("#userNameTip").hide();
					$("#userNameTip").prop('iserror',false);
				 }
	        }  
	     });  
	}
}

function emailblur(){
	var email = $('#email').val();
	if(email.length<6 || email.length>40)
		return;
	if(email != ''){
		var url = "<%=basePath%>user/getUserByEmail";
		$.ajax({  
	        type:'post',      
	        url:url,  
	        data:{email:email},  
	        cache:false,  
	        dataType:'json',  
			success:function(data){ 
				 if(data) {
					 $("#emailTip").show();
					 $("#emailTip").prop('iserror',true);
				 }else {
				 	$("#emailTip").hide();
					$("#emailTip").prop('iserror',false);
				 }
	        }  
	     });  
	}
}

$('#inputPassword')[0].onkeypress = function (event) {
	    var e = event || window.event,
	        $tip = $('#capslock'),
	        kc = e.keyCode || e.which, // 按键的keyCode
	        isShift = e.shiftKey || (kc == 16 ) || false; // shift键是否按住
	    if (((kc >= 65 && kc <= 90) && !isShift) || ((kc >= 97 && kc <= 122) && isShift)) {
	            $tip.show();
	    } else {
	            $tip.hide();
	    }
};

function PasswordBlur(){
	var password = $('#inputPassword').val();
	if(password=='') return;
	var regex = new RegExp('^[\@A-Za-z0-9\!\#\$\%\^\&\*\.\~]{6,20}$');
	if(!regex.test(password)){
		$('#passworderr').show();
		$("#passworderr").prop('iserror',true); 
		return;
	}else {
		$('#passworderr').hide();
		$("#passworderr").prop('iserror',false);
	}
	var repassword = $('#inputRePassword').val();
	if(repassword!='' && password!=repassword){
		$("#repassworderr").show();
		$("#passworderr").prop('iserror',true); 
	}else {
		$("#repassworderr").hide();
		$("#passworderr").prop('iserror',false);
	}
}

function rePasswordBlur(){
	var repassword = $('#inputRePassword').val();
	var password = $('#inputPassword').val();
	if(repassword!='' && password!=repassword){
		$("#repassworderr").show();
		$("#passworderr").prop('iserror',true); 
	}else {
		$("#repassworderr").hide();
		$("#passworderr").prop('iserror',false);
	}
}

function phoneBlur(){
	var phone = $('#inputPhone').val();
	if(phone=='') return;
	var regex = new RegExp('^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$');
	if(!regex.test(phone)){
		$('#phoneerr').show();
		$("#phoneerr").prop('iserror',true); 
	}else {
		var url = "<%=basePath%>user/getUserByPhone";
		$.ajax({  
	        type:'post',      
	        url:url,  
	        data:{phone:phone},  
	        cache:false,  
	        dataType:'json',  
			success:function(data){ 
				 if(data) {
				 	$("#phoneTip").show();
					$("#phoneerr").hide();
					$("#phoneerr").prop('iserror',true);
				 }else {
				 	$("#phoneerr").hide();
				 	$("#phoneTip").hide();
					$("#phoneerr").prop('iserror',false);
				 }
	        }  
	     });
	}
}

function formSubmit(){
	if($("#userNameTip").prop('iserror')||$("#phoneerr").prop('iserror')||$("#passworderr").prop('iserror')||$("#emailTip").prop('iserror')){
		$("#submiterr").show();; 
		return false;
	}else $("#submiterr").hide(); 
}
</script> 
</body>
</html>
