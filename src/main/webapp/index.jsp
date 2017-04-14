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
	<div id="main">
		<div class="row">
			<div id="serverTime" class="col-md-12 text-center"></div>
		</div>

		<div class="row">
			<!-- left -->
			<div class="col-md-6 col-sm-12">
				<!-- 				<button onclick="getDiningCustomer();">getDiningCustomer</button> -->
				<div id="diningCustomerList"></div>

				<button onclick="customerCheckIn();">customerCheckIn</button>
			</div>

			<!-- right -->
			<div class="col-md-6 col-sm-12">
				<div class="row">
					<div id="customerInfo" class="col-md-3 col-sm-3">
						<h3>
							<span class="label label-info">桌號</span>
						</h3>
						<input class="form-control" type="text" id="orderTableNumber" />
						<input class="form-control" type="hidden" id="orderCustomerId" />
						<div id="orderListInfo"></div>
					</div>
					<div id="menu" class="col-md-9 col-sm-9">
						<div class="panel-group" id="menuBlock" role="tablist"
							aria-multiselectable="true"></div>
					</div>
				</div>
				<div class="row">
					<button type="button" class="btn btn-danger btn-lg btn-block" onclick="sendOrder();">
					 	<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true">　出餐</span>
					</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
