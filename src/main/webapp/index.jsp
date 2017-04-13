<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta name="viewport" -->
<!-- 	content="width=device-width initial-scale=1 maximum-scale=2"> -->
<!-- bootstrap -->
<link type="text/css"
	href="<c:url value="/plugin/bootstrap-3.3.7/css/bootstrap.min.css" />"
	rel="stylesheet">
<link type="text/css"
	href="<c:url value="/plugin/bootstrap-3.3.7/css/bootstrap.min.css.map" />"
	rel="stylesheet">
<!-- alertify -->
<link type="text/css"
	href="<c:url value="/plugin/alertify/css/alertify.core.css" />"
	rel="stylesheet">
<link type="text/css"
	href="<c:url value="/plugin/alertify/css/alertify.bootstrap.css" />"
	rel="stylesheet">
<!-- index -->
<link type="text/css" href="<c:url value="/css/index.css" />"
	rel="stylesheet">
<!-- jquery -->
<script src="<c:url value="/plugin/jquery/js/jquery-3.2.1.min.js" />"></script>
<!-- socket -->
<script src="<c:url value="/plugin/socket/sockjs-1.1.1.js" />"></script>
<script src="<c:url value="/plugin/socket/stomp.min.js" />"></script>
<!-- bootstrap -->
<script
	src="<c:url value="/plugin/bootstrap-3.3.7/js/bootstrap.min.js" />"></script>
<!-- alertify -->
<script src="<c:url value="/plugin/alertify/js/alertify.min.js" />"></script>
<!-- index -->
<script src="<c:url value="/js/index.js" />"></script>

</head>
<body>

	<div class="row">
		<div id="serverTime" class="col-md-12 text-center"></div>
	</div>

	<div class="row">
		<div class="col-md-6">
			<button onclick="getDiningCustomer();">getDiningCustomer</button>
			<div id="diningCustomerList"></div>

			<button onclick="customerCheckIn();">customerCheckIn</button>
		</div>
		<div class="col-md-6">
			<div id="customerInfo" class="col-md-3">11</div>
			<div id="menu" class="col-md-9">

				
			</div>
		</div>
</body>
</html>
