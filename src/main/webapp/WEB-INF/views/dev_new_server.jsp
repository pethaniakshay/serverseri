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
  <div class="container">
    <div class="row">
    <h1>Add New Server</h1>
    <br/>
      <div class="col-md-6">
        <form method="POST" id="newServerForm" name="newServerForm" action="${contextPath}/dev/ajax_add_server_form">
          <div class="form-group">
            <label for="serverName">Server Name</label> <input type="text" class="form-control" name="serverName" id="serverName">
          </div>
        
          <div class="form-group">
            <label for="hostName">Host Name</label> <input type="text" class="form-control" name="hostName" id="hostName">
          </div>
          
          <div class="form-group">
            <label for="userName">User Name</label> <input type="text" class="form-control" name="userName" id="userName">
          </div>
          
          <div class="form-group">
            <label for="password">Password</label> <input type="password" class="form-control" name="password" id="password">
          </div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
          <button type="submit" id="serverFormSubmit"class="btn btn-default">Add my server</button>
        </form>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="row">
      <div class="col-md-12"></div>
    </div>
  </div>
  
  <!-- Business Js -->
  <script src="${contextPath}/resources/business_js/dev_add_new_server.js" type="text/javascript"></script>
</body>
</html>