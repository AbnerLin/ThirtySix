<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width initial-scale=1 maximum-scale=2">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">

<link type="text/css"
	href="<c:url value="/plugin/bootstrap-toggle/css/bootstrap-toggle.min.css" />"
	rel="stylesheet">

<!-- alertify -->
<link type="text/css"
	href="<c:url value="/plugin/alertify/css/alertify.core.css" />"
	rel="stylesheet">
<link type="text/css"
	href="<c:url value="/plugin/alertify/css/alertify.bootstrap.css" />"
	rel="stylesheet">

<!-- jquery ui -->
<link type="text/css"
	href="<c:url value="/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.css" />"
	rel="stylesheet">
<link type="text/css"
	href="<c:url value="/plugin/jquery-ui-1.12.1.custom/jquery-ui.structure.min.css" />"
	rel="stylesheet">
<link type="text/css"
	href="<c:url value="/plugin/jquery-ui-1.12.1.custom/jquery-ui.theme.min.css" />"
	rel="stylesheet">

<!-- index -->
<link type="text/css" href="<c:url value="/css/index.css" />"
	rel="stylesheet">
</head>
<body>
	<div id="main" class="container-fluid">

		<div class="row">
			<div class="col-12 text-center">
				<div id="option" class="row">
					<div class="col-6 text-left">
						<input type="checkbox" id="seatMap-toggle" data-size="mini"
							data-toggle="toggle" data-onstyle="success"
							data-offstyle="danger" /> 調整座位表
					</div>
					<div class="col-6 text-right">
						<button id="saveSeatMap" onclick="saveSeatMap();" type="button"
							class="default-none btn btn-success">存檔</button>
					</div>
				</div>

				<div class="row">
					<div id="imageSelection" class="col-12 default-none">
						<img id="tableIconSM" class="canvasInnerObj"
							src="<c:url value="images/table-sm.png" />">
					</div>
				</div>

				<div class="row default-none" id="mapSizeOption">
					<div class="col-6">
						寬 use input group..
						<input type="number" class="form-control shortInput"
							id="mapWidth" value=100 />
					</div>
					<div class="col-6">
						<div class="float-left">高</div>
						<input type="number" class="form-control shortInput float-left"
							id="mapHeight" value=100 />
					</div>
				</div>

				<div class="row">
					<div id="seatMap">
						<div class="row">
							<div class="col-2">
								<img id="tableIconSM"
									src="<c:url value="images/table-mini.png" />"> 使用中
							</div>
							<div class="col-2">
								<img id="tableIconSM"
									src="<c:url value="images/empty-table-mini.png" />"> 空桌
							</div>
						</div>
						<input id="mapLocation" type="hidden" value=""> <input
							id="mapID" type="hidden" value="">
						<div id="garbageBlock" class="glyphicon glyphicon-trash"></div>
					</div>
				</div>
			</div>
		</div>


		<!-- -------------------------------------------------- -->
		<div class="row">
			<div id="serverTime" class="col-md-12 text-center"></div>
		</div>

		<div class="row">
			<!-- left -->
			<div class="col-md-6 col-sm-12">
				<div id="diningCustomerList"></div>
			</div>

			<!-- right -->
			<div class="col-md-6 col-sm-12"></div>
		</div>
	</div>

	<!-- Customer check in Modal -->
	<div class="modal fade" id="checkInModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-md" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="myModalLabel">顧客資訊</h3>
				</div>

				<div class="modal-body text-center">
					<h4>顧客名稱</h4>
					<input class="form-control" type="text" id="checkInCustomerName"
						value="路人甲" />
					<h4>顧客電話</h4>
					<input class="form-control" type="text" id="checkInCustomerPhone"
						value="----------" />
					<h4>人數</h4>
					<div class="input-group">
						<div class="input-group-btn">
							<button type="button" aria-label="-1"
								class="btn btn-success minusBtn">
								<span class="glyphicon glyphicon-minus-sign" aria-hidden="true"></span>
							</button>
						</div>
						<input id="checkInPeopleCount" class="form-control volume" min="1"
							type="number" value="1" />
						<div class="input-group-btn">
							<button type="button" aria-label="+1"
								class="btn btn-danger plusBtn">
								<span class="glyphicon glyphicon-plus-sign" aria-hidden="true"></span>
							</button>
						</div>
					</div>
					<h4>桌號</h4>
					<input class="form-control" disabled type="text"
						id="checkInTableNumber" />
				</div>

				<div class="modal-footer">
					<div class="row" id="sendOrder">
						<button type="button" class="btn btn-danger btn-lg btn-block"
							onclick="customerCheckIn();">
							<span class="glyphicon glyphicon-cutlery" aria-hidden="true">
								CheckIn!</span>
						</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Customer Service Modal -->
	<div class="modal fade" id="serviceModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content">

				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h3 class="modal-title" id="myModalLabel">服務選單</h3>
				</div>

				<div class="modal-body">
					<!-- Nav tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li role="presentation" class="active"><a
							href="#orderPageTab" aria-controls="orderPageTab" role="tab"
							data-toggle="tab">點餐</a></li>
						<li role="presentation"><a href="#checkOutTab"
							aria-controls="checkOutTab" role="tab" data-toggle="tab">結帳</a></li>
					</ul>

					<!-- Tab panes -->
					<div class="tab-content">
						<!-- order tab -->
						<div role="tabpanel" class="tab-pane active" id="orderPageTab">
							<%@ include file="jsp/orderPage.jsp"%>
						</div>
						<!-- check out -->
						<div role="tabpanel" class="tab-pane" id="checkOutTab">
							<%@ include file="jsp/checkOutPage.jsp"%>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<!-- 					<div class="row" id="sendOrder"> -->
					<!-- 						<button type="button" class="btn btn-danger btn-lg btn-block" -->
					<!-- 							onclick="sendOrder();"> -->
					<!-- 							<span class="glyphicon glyphicon-shopping-cart" -->
					<!-- 								aria-hidden="true"> 出餐</span> -->
					<!-- 						</button> -->
					<!-- 					</div> -->
				</div>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
		integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
		integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
		crossorigin="anonymous"></script>


	<!-- jquery ui -->
	<script
		src="<c:url value="/plugin/jquery-ui-1.12.1.custom/jquery-ui.min.js" />"></script>
	<script
		src="<c:url value="/plugin/jquery-ui-1.12.1.custom/jquery-ui.touch-punch.min.js" />"></script>
	<!-- jquery tmpl -->
	<script src="<c:url value="/plugin/jquery/js/jquery-tmpl.min.js" />"></script>
	<!-- bootstrap-toggle -->
	<script
		src="<c:url value="/plugin/bootstrap-toggle/js/bootstrap-toggle.min.js" />"></script>

	<!-- socket -->
	<script src="<c:url value="/plugin/socket/sockjs-1.1.1.js" />"></script>
	<script src="<c:url value="/plugin/socket/stomp.min.js" />"></script>
	<script src="<c:url value="/plugin/alertify/js/alertify.min.js" />"></script>

	<!-- index -->
	<script src="<c:url value="/js/index.js" />"></script>
</body>
</html>
