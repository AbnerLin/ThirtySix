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
	$LAB //
	.script("plugin/socket/sockjs.min.js") //
	.script("plugin/socket/stomp.min.js") //
	.script("js/core/core.js").wait(function() {
		App.loadBaseCSS();
		App.loadCSS("css/index.css");
		App.loadBaseJS(function() {
			$("body").fadeIn();
		});
		App.loadJS("js/module/WebSocket.js") //
		.loadJS("js/index.js", function() {
			init();
		});
	});
</script>
</head>
<body style="display: none;">

	<sec:authorize access="hasRole('ROLE_ADMIN')">
			has role admin.
	</sec:authorize>

	<button onclick="Auth.logout();" class="btn btn-sm btn-secondary">logout</button>

	<div id="main" class="container-fluid text-center">
		<div id="mapBlock" class="row w-100 m-0 p-0">
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<!-- btn option -->
				<div id="option" class="col-12 row" style="display: none;">
					<div
						class="col-6 text-left d-flex align-items-center justify-content-start">
						<div class="d-flex align-items-center">
							<label class="switch"> <input type="checkbox"
								id="seatMap-toggle"> <span class="slider"></span>
							</label>
						</div>
						<div>調整</div>
					</div>
					<div class="col-6 d-flex align-items-center justify-content-end">
						<button onclick="Map._save(this);" type="button"
							class="default-none btn btn-secondary mapSettingTool">存檔</button>
					</div>
				</div>
			</sec:authorize>

			<div class="col-12" id="mapSetting" style="display: none;">
				<!-- furnish selection. -->
				<div id="imageSelection" class="col-12 p-3">
					<%@ include file="/WEB-INF/jsp/template/mapOption.jsp"%>
				</div>
				<!-- map size info -->
				<div class="col-12 py-3" id="mapSizeOption">
					<div class="row">
						<div class="col-6">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">寬</span> <input
									type="text" class="form-control" id="mapWidth"
									placeholder="width" aria-describedby="basic-addon1" value="600">
							</div>
						</div>
						<div class="col-6">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">高</span> <input
									type="text" class="form-control" id="mapHeight"
									placeholder="height" aria-describedby="basic-addon1"
									value="400">
							</div>
						</div>
					</div>
				</div>
			</div>

			<div class="col-12 m-0 p-0">
				<div class="col-12 row">
					<div class="mx-3">
						<img id="tableIconSM"
							src="<c:url value="images/table-mini.png" />"> 使用中
					</div>
					<div class="mx-3">
						<img id="tableIconSM"
							src="<c:url value="images/empty-table-mini.png" />"> 空桌
					</div>
				</div>
				<div class="col-12 m-0 p-0">
					<div id="seatMap" class="disableSelection">
						<input id="mapLocation" type="hidden" value=""> <input
							id="mapId" type="hidden" value="">
						<div id="garbageBlock" class="mapSettingTool">
							<i class="material-icons">&#xe872;</i>
						</div>
					</div>
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

	<!-- Customer check in Modal -->
	<%@ include file="/WEB-INF/jsp/template/checkInModal.jsp"%>

	<!-- Customer Service Modal -->
	<%@ include file="/WEB-INF/jsp/template/customerModal.jsp"%>
</body>
</html>
