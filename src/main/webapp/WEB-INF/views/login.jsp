<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<meta charset="utf-8" />
<title>Serverseri</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta content="" name="description" />
<meta content="" name="author" />
<!-- BEGIN PLUGIN CSS -->
<link href="${contextPath}/resources/assets/plugins/pace/pace-theme-flash.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" integrity="sha256-j+P6EZJVrbXgwSR5Mx+eCS6FvP9Wq27MBRC/ogVriY0=" crossorigin="anonymous" />
<link href="${contextPath}/resources/assets/plugins/jquery-scrollbar/jquery.scrollbar.css" rel="stylesheet" type="text/css" />
<!-- END PLUGIN CSS -->
<!-- BEGIN CORE CSS FRAMEWORK -->
<link href="${contextPath}/resources/webarch/css/webarch.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/resources/webarch/css/style.css" rel="stylesheet" type="text/css" />
<!-- END CORE CSS FRAMEWORK -->
</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->

<body class="error-body no-top">
  <div class="container">
    <div class="row login-container column-seperation">
      <div class="col-md-5 col-md-offset-1">
        <h2>Sign in to Serverseri.</h2>
        <p>
          Use your email to sign in. <br> Click below to Sign up Now! for a Serverseri account.
        </p>
        <br> <a href="${contextPath}/signup" class="btn btn-block btn-primary col-md-8" type="button"> <span class="pull-left"></span> <span class="bold">Sign Up using Email</span>
        </a>
        <!-- <button class="btn btn-block btn-info col-md-8" type="button">
          <span class="pull-left icon-facebook" style="font-style: italic"></span>
          <span class="bold">Sign In Facebook</span>
        </button>
        <button class="btn btn-block btn-success col-md-8" type="button">
          <span class="pull-left icon-twitter" style="font-style: italic"></span>
          <span class="bold">Sign In Twitter</span>
        </button> -->
      </div>
      <div class="col-md-5">
        <br>
        <form action="${contextPath}/login" class="login-form validate" id="login-form" method="POST" name="login-form">
          <div class="row">
            <div class="form-group col-md-10">
              <label class="form-label">Email</label>
              <input class="form-control" id="reg_email" name="username" placeholder="Email" type="email" required>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-md-10">
              <label class="form-label">Password</label>
              <span class="help"></span>
              <input class="form-control" id="txtpassword" name="password" placeholder="Password" type="password" required>
            </div>
          </div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <div class="row">
            <div class="control-group col-md-10">
              <div class="checkbox checkbox check-success">
                <a href="#">Forgot Password?</a>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-md-10">
              <a href="${contextPath}" class="btn btn-cons pull-right" type="submit">Cancel</a>
              <button class="btn btn-primary btn-cons pull-right" type="submit">Login</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <!-- END CONTAINER -->
  <script src="${contextPath}/resources/assets/plugins/pace/pace.min.js" type="text/javascript"></script>
  <!-- BEGIN JS DEPENDECENCIES-->
  <script src="https://code.jquery.com/jquery-1.11.3.min.js" integrity="sha256-7LkWEzqTdpEfELxcZZlS6wAx5Ff13zZ83lYO2/ujj7g=" crossorigin="anonymous"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
  <script src="${contextPath}/resources/assets/plugins/jquery-block-ui/jqueryblockui.min.js" type="text/javascript"></script>
  <script src="${contextPath}/resources/assets/plugins/jquery-unveil/jquery.unveil.min.js" type="text/javascript"></script>
  <script src="${contextPath}/resources/assets/plugins/jquery-scrollbar/jquery.scrollbar.min.js" type="text/javascript"></script>
  <script src="${contextPath}/resources/assets/plugins/jquery-numberAnimate/jquery.animateNumbers.js" type="text/javascript"></script>
  <script src="${contextPath}/resources/assets/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
  <script src="${contextPath}/resources/assets/plugins/bootstrap-select2/select2.min.js" type="text/javascript"></script>
  <!-- END CORE JS DEPENDECENCIES-->
  <!-- BEGIN CORE TEMPLATE JS -->
  <script src="${contextPath}/resources/webarch/js/webarch.js" type="text/javascript"></script>
  <script src="${contextPath}/resources/assets/js/chat.js" type="text/javascript"></script>
  <!-- END CORE TEMPLATE JS -->
</body>
</html>