<%@ page language="java" import="com.butshow.entity.UserDefault,java.util.*,net.sf.json.JSONObject" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
UserDefault user = (UserDefault)request.getAttribute("user");
JSONObject userJson = JSONObject.fromObject(user);
String userString = userJson.toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh_CN" data-ng-app="app">
  <head>
    <base href="<%=basePath%>">
    
    <title>ButShow</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="But, Show, butshow">
	<meta http-equiv="description" content="ButShow">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  	<link rel="stylesheet" href="<%=basePath%>angular/css/bootstrap.css" type="text/css" />
  	<link rel="stylesheet" href="<%=basePath%>angular/css/animate.css" type="text/css" />
  	<link rel="stylesheet" href="<%=basePath%>angular/css/font-awesome.min.css" type="text/css" />
  	<link rel="stylesheet" href="<%=basePath%>angular/css/simple-line-icons.css" type="text/css" />
  	<link rel="stylesheet" href="<%=basePath%>angular/css/font.css" type="text/css" />
  	<link rel="stylesheet" href="<%=basePath%>angular/css/app.css" type="text/css" />

  </head>
  
  <body ng-controller="AppCtrl">
  <div class="app" id="app"  ng-init="user='<%=user%>'" ng-class="{'app-header-fixed':app.settings.headerFixed, 'app-aside-fixed':app.settings.asideFixed, 'app-aside-folded':app.settings.asideFolded, 'app-aside-dock':app.settings.asideDock, 'container':app.settings.container}" ui-view>

  </div>

  <!-- jQuery -->
  <script src="<%=basePath%>angular/vendor/jquery/jquery-2.1.4.min.js"></script>

  <!-- Angular -->
  <script src="<%=basePath%>angular/vendor/angular/angular.js"></script>
  
  <script src="<%=basePath%>angular/vendor/angular/angular-animate/angular-animate.js"></script>
  <script src="<%=basePath%>angular/vendor/angular/angular-cookies/angular-cookies.js"></script>
  <script src="<%=basePath%>angular/vendor/angular/angular-resource/angular-resource.js"></script>
  <script src="<%=basePath%>angular/vendor/angular/angular-sanitize/angular-sanitize.js"></script>
  <script src="<%=basePath%>angular/vendor/angular/angular-touch/angular-touch.js"></script>
<!-- Vendor -->
  <script src="<%=basePath%>angular/vendor/angular/angular-ui-router/angular-ui-router.js"></script> 
  <script src="<%=basePath%>angular/vendor/angular/ngstorage/ngStorage.js"></script>

  <!-- bootstrap -->
  <script src="<%=basePath%>angular/vendor/angular/angular-bootstrap/ui-bootstrap-tpls.js"></script>
  <!-- lazyload -->
  <script src="<%=basePath%>angular/vendor/angular/oclazyload/ocLazyLoad.js"></script>
  <!-- translate -->
  <script src="<%=basePath%>angular/vendor/angular/angular-translate/angular-translate.js"></script>
  <script src="<%=basePath%>angular/vendor/angular/angular-translate/loader-static-files.js"></script>
  <script src="<%=basePath%>angular/vendor/angular/angular-translate/storage-cookie.js"></script>
  <script src="<%=basePath%>angular/vendor/angular/angular-translate/storage-local.js"></script>

  <!-- App -->
  <script src="<%=basePath%>angular/js/app.js"></script>
  <script src="<%=basePath%>angular/js/config.js"></script>
  <script src="<%=basePath%>angular/js/config.lazyload.js"></script>
  <script src="<%=basePath%>angular/js/config.router.js"></script>
  <script src="<%=basePath%>angular/js/main.js"></script>
  <script src="<%=basePath%>angular/js/services/ui-load.js"></script>
  <script src="<%=basePath%>angular/js/filters/fromNow.js"></script>
  <script src="<%=basePath%>angular/js/directives/setnganimate.js"></script>
  <script src="<%=basePath%>angular/js/directives/ui-butterbar.js"></script>
  <script src="<%=basePath%>angular/js/directives/ui-focus.js"></script>
  <script src="<%=basePath%>angular/js/directives/ui-fullscreen.js"></script>
  <script src="<%=basePath%>angular/js/directives/ui-jq.js"></script>
  <script src="<%=basePath%>angular/js/directives/ui-module.js"></script>
  <script src="<%=basePath%>angular/js/directives/ui-nav.js"></script>
  <script src="<%=basePath%>angular/js/directives/ui-scroll.js"></script>
  <script src="<%=basePath%>angular/js/directives/ui-shift.js"></script>
  <script src="<%=basePath%>angular/js/directives/ui-toggleclass.js"></script>
  <script src="<%=basePath%>angular/js/directives/ui-validate.js"></script>
  <script src="<%=basePath%>angular/js/controllers/bootstrap.js"></script>
  <!-- Lazy loading -->
</body>
<script type="text/javascript">
	var uservar = '<%=userString%>';
	var user = $.parseJSON(uservar);
		app.controller('userController', ['$scope', function($scope){
	    $scope.user = user;
	}]);
</script> 
</html>
