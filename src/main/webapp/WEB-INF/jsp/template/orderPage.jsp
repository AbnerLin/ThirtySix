<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div class="row" id="orderPageInner">
		<div id="customerInfo" class="col-md-3 col-sm-3">
			<h3>
				<span class="label label-info">桌號</span>
			</h3>
			<input class="form-control" type="text" id="orderTableNumber" /> <input
				class="form-control" type="hidden" id="orderCustomerId" />
			<div id="orderListInfo"></div>
		</div>
		<div id="menu" class="col-md-9 col-sm-9">
			<div class="panel-group" id="menuBlock" role="tablist"
				aria-multiselectable="true"></div>
		</div>
	</div>
	<div class="row" id="sendOrder">
		<button type="button" class="btn btn-danger btn-lg btn-block"
			onclick="sendOrder();">
			<span class="glyphicon glyphicon-shopping-cart" aria-hidden="true">
				出餐</span>
		</button>
	</div>
</body>
</html>