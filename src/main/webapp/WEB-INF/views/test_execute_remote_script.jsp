<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!doctype html>
<html lang="en">
<head>
<title>Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<input type="hidden" id="conPa" name="contextPath" value="${contextPath}"/>
  <div class="container">
  <br>
    <div class="row">
      <div class="col-md-12">
      <input type="text" id="file" class="form-control">
       <button id="exec" class="btn btn-danger">Execute Script</button>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="row">
      <div class="col-md-12"></div>
    </div>
  </div>
  <!-- Business Js -->
  <jsp:include page="/WEB-INF/views/csrf.jsp" />
  <script src="${contextPath}/resources/business_js/dev_execute_remote_script.js" type="text/javascript"></script>
</body>
</html>