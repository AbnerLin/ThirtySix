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

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

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

<!-- spring security csrf -->
<sec:csrfMetaTags />
<meta name="_csrf" th:content="${_csrf.token}" />
</head>
<body>
	<sec:authorize access="isAuthenticated()">
		<%
			response.sendRedirect("index.jsp");
		%>
	</sec:authorize>
	<div id="loginPage"
		class="container-fluid d-flex align-items-center justify-content-center">

		<%-- 		<sec:authorize access="isAuthenticated()"> --%>
		<%-- 			<% --%>
		<!--  				response.sendRedirect("index.jsp"); -->
		<%-- 			%> --%>
		<!-- 		is authenicated -->
		<%-- 	</sec:authorize> --%>

		<%-- 		<sec:authorize access="isAnonymous()"> --%>
		<!-- 			anonymouse -->
		<%-- 		</sec:authorize> --%>

		<%-- 		<sec:authorize ifAnyGranted="ROLE_ADMIN"> --%>
		<sec:authorize access="hasRole('ROLE_ADMIN')">
			This content will only be visible to users who have
			the "supervisor" authority in their list of <tt>GrantedAuthority</tt>s.
		</sec:authorize>
		aa

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
	<script src="<c:url value="/js/login.js" />"></script>
</body>
</html>
