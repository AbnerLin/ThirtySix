<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<!-- 	<div id="checkOutInner" class="row"> -->
	<!-- 		<div class="panel panel-info"> -->
	<!-- 			<!-- customer info -->
	<!-- 			<div class="panel-heading"> -->
	<!-- 				<h3 class="panel-title">顧客資訊</h3> -->
	<!-- 			</div> -->
	<!-- 			<div class="panel-body customerInfo"> -->
	<%-- 				<%@ include file="customerInfo.jsp"%> --%>
	<!-- 			</div> -->

	<!-- 			<!-- order history -->
	<!-- 			<div class="panel-heading"> -->
	<!-- 				<h3 class="panel-title">點餐紀錄</h3> -->
	<!-- 			</div> -->
	<!-- 			<div class="panel-body"> -->
	<%-- 				<%@ include file="orderHistory.jsp"%> --%>
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</div> -->



	<div class="panel-group" id="checkOutInner" role="tablist"
		aria-multiselectable="true">
		<div class="panel panel-info">
			<div class="panel-heading" role="tab"
				id="service-customerCustomerInfo-header">
				<div role="button" data-toggle="collapse" data-parent="#checkOutInner"
					href="#service-customerInfo" aria-expanded="true"
					aria-controls="service-customerInfo">顧客資訊</div>
			</div>

			<div id="service-customerInfo" class="panel-collapse collapse in"
				role="tabpanel"
				aria-labelledby="service-customerCustomerInfo-header">
				<div class="panel-body">
					<%@ include file="customerInfo.jsp"%>
				</div>
			</div>
		</div>

		<div class="panel panel-warning">
			<div class="panel-heading" role="tab"
				id="service-customerOrderHistory-header">
				<div role="button" data-toggle="collapse" data-parent="#checkOutInner"
					href="#service-orderHistory" aria-expanded="true"
					aria-controls="service-orderHistory">點餐紀錄</div>
			</div>
			<div id="service-orderHistory" class="panel-collapse collapse"
				role="tabpanel"
				aria-labelledby="service-customerOrderHistory-header">
				<div class="panel-body">
					<%@ include file="orderHistory.jsp"%>
				</div>
			</div>
		</div>
	</div>

</body>
</html>