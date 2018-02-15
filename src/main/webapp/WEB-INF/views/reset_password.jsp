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
<!-- BEGIN HEAD -->
<meta http-equiv="content-type" content="text/html;charset=UTF-8" />
<meta charset="utf-8" />
<title>Serverseri</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="theme-color" content="#db5945">
<!-- BEGIN PLUGIN CSS -->
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" />
<link href="${contextPath}/resources/assets/plugins/jquery-metrojs/MetroJs.min.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/assets/plugins/shape-hover/css/component.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/assets/plugins/owl-carousel/owl.carousel.css" />
<link rel="stylesheet" type="text/css" href="${contextPath}/resources/assets/plugins/owl-carousel/owl.theme.css" />
<link href="${contextPath}/resources/assets/plugins/pace/pace-theme-flash.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${contextPath}/resources/assets/plugins/jquery-slider/css/jquery.sidr.light.css" rel="stylesheet" type="text/css" media="screen" />
<!-- END PLUGIN CSS -->
<!-- BEGIN PLUGIN CSS -->
<link href="${contextPath}/resources/assets/plugins/pace/pace-theme-flash.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/3.5.2/animate.min.css" />
<link href="${contextPath}/resources/assets/plugins/jquery-scrollbar/jquery.scrollbar.css" rel="stylesheet" type="text/css" />
<!-- END PLUGIN CSS -->
<!-- BEGIN CORE CSS FRAMEWORK -->
<link href="${contextPath}/resources/webarch/css/webarch.css" rel="stylesheet" type="text/css" />
<link href="${contextPath}/resources/webarch/css/style.css" rel="stylesheet" type="text/css" />
<script src="http://code.jquery.com/jquery-3.3.1.min.js" type="text/javascript"></script>
<!-- END CORE CSS FRAMEWORK -->
<!-- END HEAD -->
</head>
<!-- BEGIN BODY -->
<body class="error-body no-top">
  <div class="container">
    <div class="row login-container column-seperation">
      <div class="col-md-5 col-md-offset-1">
        <h2>Ho! you made it!</h2>
        <p>
          Type in your new password. <br> Don't forget to confirm it, &amp; memorise it mate!
        </p>
      </div>
      <div class="col-md-5">
        <br>
        <form action="${contextPath}/ajax_reset_passwd" class="login-form validate" id="passwordResetForm" method="POST" name="passwordResetForm">
           <jsp:include page="/WEB-INF/views/csrf.jsp" />
           <input type="hidden" name="email" value="${email}"/>
          <div class="row">
            <div class="form-group col-md-10">
              <label class="form-label">Password</label> <span class="help"></span> <input class="form-control" id="password" name="password" placeholder="Password" type="password" required>
            </div>
          </div>
          <div class="row">
            <div class="form-group col-md-10">
              <label class="form-label">Confirm Password</label> <span class="help"></span> <input class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" type="password" required>
            </div>
          </div>
          <div class="row">
            <div class="col-md-10">
              <!-- <a href="#" class="btn btn-cons pull-right" type="submit">Cancel</a> -->
              <button class="btn btn-primary btn-cons pull-right" type="submit">Save this shit!</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div>
  <script src="${contextPath}/resources/business_js/reset_password.js" type="text/javascript"></script>
  <script src="${contextPath}/resources/assets/plugins/pace/pace.min.js" type="text/javascript"></script>
  <!-- BEGIN JS DEPENDECENCIES-->
  <script src="${contextPath}/resources/assets/plugins/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
  <script src="${contextPath}/resources/assets/plugins/bootstrapv3/js/bootstrap.min.js" type="text/javascript"></script>
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
  <!-- BEGIN PAGE LEVEL JS -->
  <script src="${contextPath}/resources/assets/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
  <script src="${contextPath}/resources/assets/plugins/jquery-ricksaw-chart/js/raphael-min.js"></script>
  <script src="${contextPath}/resources/assets/plugins/jquery-ricksaw-chart/js/d3.v2.js"></script>
  <script src="${contextPath}/resources/assets/plugins/jquery-sparkline/jquery-sparkline.js"></script>
  <script src="${contextPath}/resources/assets/plugins/skycons/skycons.js"></script>
  <script src="${contextPath}/resources/assets/plugins/owl-carousel/owl.carousel.min.js" type="text/javascript"></script>
  <script src="${contextPath}/resources/assets/plugins/jquery-flot/jquery.flot.js" type="text/javascript"></script>
  <script src="${contextPath}/resources/assets/plugins/jquery-metrojs/MetroJs.min.js" type="text/javascript"></script>
  <!-- BEGIN CORE TEMPLATE JS -->
  <script src="${contextPath}/resources/assets/plugins/jquery-ui/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>
  <script src="${contextPath}/resources/assets/plugins/jquery-ui-touch/jquery.ui.touch-punch.min.js" type="text/javascript"></script>
</body>
</html>