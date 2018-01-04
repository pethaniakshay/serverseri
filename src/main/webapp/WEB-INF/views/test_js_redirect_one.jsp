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

<script type="text/javascript">
$(document).ready(function(){
  $("#l_redirect").click(function(){
    $(location).attr('href', '${contextPath}/test/redirected?token=hi')
  });
  
  $("#a_redirect").click(function(){
    $(location).attr('href', '${contextPath}/test/redirected/optional')
  });
});
</script>

</head>
<body>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <button class="btn btn-success" id="l_redirect">Leagacy Redirect</button>
        
        <button class="btn btn-success" id="a_redirect">Advanced Redirect</button>
      </div>
    </div>
  </div>

  <div class="container">
    <div class="row">
      <div class="col-md-12"></div>
    </div>
  </div>
</body>
</html>