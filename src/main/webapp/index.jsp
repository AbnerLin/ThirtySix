<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">

<script src="plugin/LAB/LAB.min.js"></script>
<script>
	$LAB.script("js/core/core.js").wait(function() {
		App.loadBaseCSS();
		App.loadCSS("css/index.css");
		App.loadBaseJS(function() {
			$("body").fadeIn();
		});
		App.loadJS("js/index.js", function() {
			init();
		});
	});
</script>
</head>
<body style="display: none;">
	<sec:authorize access="hasRole('ROLE_ADMIN')">
			This content will only be visible to users who have
			the "supervisor" authority in their list of <tt>GrantedAuthority</tt>s.
		</sec:authorize>
	<a href="logout">logout.</a>

	<!-- 	<form action="logout" method="POST"> -->
	<button onclick="Auth.logout();">logout</button>
	<!-- 	</form> -->


	<div id="main" class="container-fluid">
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			<!-- btn option -->
			<div id="option" class="row w-100">
				<div class="col-6 text-left">
					<input type="checkbox" id="seatMap-toggle" data-size="mini"
						data-toggle="toggle" data-onstyle="success" data-offstyle="danger" />
					調整座位表
				</div>
				<div class="col-6 text-right">
					<button onclick="Map.save();" type="button"
						class="default-none btn btn-success mapSettingTool">存檔</button>
				</div>
			</div>
		</sec:authorize>

		<div class="row" id="mapSetting" style="display: none;">
			<!-- furnish selection. -->
			<div id="imageSelection" class="col-12">
				<%@ include file="jsp/template/mapOption.jsp"%>
			</div>

			<!-- map size info -->
			<div class="col-12 py-3" id="mapSizeOption">
				<div class="row">
					<div class="col-6">
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1">寬</span> <input
								type="text" class="form-control" id="mapWidth"
								placeholder="witdh" aria-describedby="basic-addon1" value="600">
						</div>
					</div>
					<div class="col-6">
						<div class="input-group">
							<span class="input-group-addon" id="basic-addon1">高</span> <input
								type="text" class="form-control" id="mapHeight"
								placeholder="height" aria-describedby="basic-addon1" value="400">
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-12 row">
				<div class="mx-3">
					<img id="tableIconSM" src="<c:url value="images/table-mini.png" />">
					使用中
				</div>
				<div class="mx-3">
					<img id="tableIconSM"
						src="<c:url value="images/empty-table-mini.png" />"> 空桌
				</div>
			</div>
			<div class="col-12">
				<div id="seatMap">
					<input id="mapLocation" type="hidden" value=""> <input
						id="mapId" type="hidden" value="">
					<div id="garbageBlock" class="mapSettingTool">
						<i class="material-icons">&#xe872;</i>
					</div>
				</div>
			</div>
		</div>

		<!-- -----------------------以下未修改 bootstrap4------------------------- -->
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
</body>
</html>
