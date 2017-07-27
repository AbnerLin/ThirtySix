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
	});

	function login(btn) {
		App.showLoadingByBtn($("#loginPage"), $(btn));
		Auth.login($("#username").val(), $("#password").val()).done(function() {
			App.hideLoadingByBtn($("#loginPage"), 1000, $(btn));
		});
	}
</script>
</head>
<body style="display: none;">
	<sec:authorize access="isAuthenticated()">
		<%
			response.sendRedirect("index");
		%>
	</sec:authorize>

	<div id="loginPage"
		class="container-fluid d-flex align-items-center justify-content-center">

		<div class="row d-flex justify-content-center">
			<div class="row">
				<div class="col-12 col-md-6 d-flex align-items-center justify-content-center">
					<h1>ThirtySix</h1>
				</div>
				<div class="col-12 col-md-6 d-flex align-items-center justify-content-center">
					<div>
						<div class="input-group m-1 px-1">
							<span class="input-group-addon">帳號</span> <input type="text"
								class="form-control" id="username" name="j_username"
								aria-describedby="basic-addon1" value="" />
						</div>
						<div class="input-group m-1 px-1">
							<span class="input-group-addon">密碼</span> <input type="password"
								class="form-control" id="password" name="j_password"
								aria-describedby="basic-addon1" value="" />
						</div>
						<div class="d-flex justify-content-end">
							<button type="submit" class="btn btn-sm btn-secondary"
								onclick="login(this);">登入</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
