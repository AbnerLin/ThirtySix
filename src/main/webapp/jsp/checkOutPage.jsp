<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div class="panel panel-info">
		<div class="panel-heading" role="tab" id="service-customerInfo-header">
			<div role="button" data-toggle="collapse"
				data-parent="#checkOutInner" href="#service-customerInfo"
				aria-expanded="true" aria-controls="service-customerInfo">顧客資訊</div>
		</div>

		<div id="service-customerInfo" class="panel-collapse collapse in"
			role="tabpanel" aria-labelledby="service-customerCustomerInfo-header">
			<div class="panel-body">
				<%@ include file="customerInfo.jsp"%>
			</div>
		</div>
	</div>

	<div class="panel-group" id="checkOutInner" role="tablist"
		aria-multiselectable="true">

		<div class="panel panel-warning">
			<div class="panel-heading" role="tab"
				id="service-customerOrderHistory-header">
				<div role="button" data-toggle="collapse"
					data-parent="#checkOutInner" href="#service-orderHistory"
					aria-expanded="true" aria-controls="service-orderHistory">點餐紀錄</div>
			</div>
			<div id="service-orderHistory" class="panel-collapse collapse"
				role="tabpanel"
				aria-labelledby="service-customerOrderHistory-header">
				<div class="panel-body">
					<div id="orderListInfo" class="row">
						<div class="row">
							<div class="col-md-6 col-sm-12">
								<button type="button" class="btn btn-primary btn-lg btn-block" onclick="displayOrderList(true);">
									顯示已出餐</button>
							</div>
							<div class="col-md-6 col-sm-12">
								<button type="button" class="btn btn-default btn-lg btn-block" onclick="displayOrderList(false);">
									顯示未出餐</button>
							</div>
						</div>
						<div>
							<%@ include file="orderHistory.jsp"%>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>