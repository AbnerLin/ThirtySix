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

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">

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
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<%
			response.sendRedirect("index.jsp");
		%>
	</sec:authorize>
	<div id="loginPage"
		class="container-fluid d-flex align-items-center justify-content-center">

		<div class="row d-flex justify-content-center">
			<div class="row">
				<div class="col-6 d-flex align-items-center justify-content-center">
					<h1>ThirtySix</h1>
				</div>
				<div class="col-6 d-flex align-items-center justify-content-center">
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
								onclick="login();">登入</button>
						</div>
					</div>
				</div>
			</div>
			<div class="col-12 d-flex align-items-center justify-content-center">
				<div id="msg" class="alert alert-danger" role="alert"
					style="display: none;">
					<strong>Oops!</strong>Username and Password not accepted.
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

	<script src="<c:url value="/plugin/alertify/js/alertify.min.js" />"></script>

	<!-- index -->
	<script src="<c:url value="/js/core/core.js" />"></script>

	<script>
		function login() {
			Auth.login($("#username").val(), $("#password").val());
		}
	</script>
</body>
</html>
