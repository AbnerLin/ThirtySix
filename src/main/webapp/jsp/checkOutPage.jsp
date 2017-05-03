<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div id="checkOutInner" class="row">
		<div class="panel panel-info">
			<!-- customer info -->
			<div class="panel-heading">
				<h3 class="panel-title">顧客資訊</h3>
			</div>
			<div class="panel-body customerInfo">
				<%@ include file="customerInfo.jsp"%>
			</div>

			<!-- order history -->
			<div class="panel-heading">
				<h3 class="panel-title">點餐紀錄</h3>
			</div>
			<div class="panel-body orderHistory">
				<%@ include file="customerInfo.jsp"%>
			</div>
		</div>
	</div>
</body>
</html>