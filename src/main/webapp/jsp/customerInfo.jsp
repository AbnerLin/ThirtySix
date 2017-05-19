<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
	<div class="row">
		<div class="col-md-6 col-sm-12">
			<div class="row">
				<div class="col-md-3 col-sm-12">顧客名稱</div>
				<div class="service-customerName col-md-9 col-sm-12 text-left"></div>
			</div>
			<div class="row">
				<div class="col-md-3 col-sm-12">人數</div>
				<div class="service-peopleCount col-md-9 col-sm-12 text-left"></div>
			</div>
			<div class="row">
				<div class="col-md-3 col-sm-12">桌號</div>
				<div class="service-tableNumber col-md-9 col-sm-12 text-left"></div>
			</div>
			<div class="row">
				<div class="col-md-3 col-sm-12">進場時間</div>
				<div class="service-checkInTime col-md-9 col-sm-12 text-left"></div>
			</div>
			<div class="row">
				<div class="col-md-3 col-sm-12">顧客電話</div>
				<div class="service-customerPhone col-md-9 col-sm-12 text-left"></div>
			</div>
		</div>
		<div class="col-md-6 col-sm-12">
			<div class="row">
				<div class="col-md-3 col-sm-12">
					<h3>總金額</h3>
				</div>
				<div class="col-md-9 col-sm-12">
					<h3 class="service-checkout-amount"></h3>
				</div>
			</div>
			<div class="row col-md-12 col-sm-12 checkOutBtn">
				<button type="button" class="btn btn-lg btn-block btn-warning" onclick="checkOut();">結帳</button>
			</div>
		</div>
	</div>
</body>
</html>