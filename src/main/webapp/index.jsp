<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width initial-scale=1 maximum-scale=2">
<!-- bootstrap -->
<link type="text/css"
	href="<c:url value="/plugin/bootstrap-3.3.7/css/bootstrap.min.css" />"
	rel="stylesheet">
<link type="text/css"
	href="<c:url value="/plugin/bootstrap-3.3.7/css/bootstrap.min.css.map" />"
	rel="stylesheet">
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
<!-- jquery -->
<script src="<c:url value="/plugin/jquery/js/jquery-3.2.1.min.js" />"></script>
<!-- socket -->
<script src="<c:url value="/plugin/socket/sockjs-1.1.1.js" />"></script>
<script src="<c:url value="/plugin/socket/stomp.min.js" />"></script>
<!-- bootstrap -->
<script
	src="<c:url value="/plugin/bootstrap-3.3.7/js/bootstrap.min.js" />"></script>
<!-- alertify -->
<script src="<c:url value="/plugin/alertify/js/alertify.min.js" />"></script>
<!-- index -->
<script src="<c:url value="/js/index.js" />"></script>

</head>
<body>

	<center>
		<div id="serverTime"></div>
	</center>

	<button onclick="getDiningCustomer();">getDiningCustomer</button>
	<div id="diningCustomerList"></div>

	<button onclick="customerCheckIn();">customerCheckIn</button>
	中文測試
	<!-- 	<nav class="w3-sidenav w3-white" style="width: 200px; display: none;" id="sideNav"> -->
	<!-- 		<ul class="myMenu"> -->
	<!-- 			<li data-menuanchor="intro" class="active"><a href="#intro">INTRODUCTION</a></li> -->
	<!-- 			<li data-menuanchor="news"><a href="#news">中文測試</a></li> -->
	<!-- 			<li data-menuanchor="production"><a href="#production">PRODUCTION</a></li> -->
	<!-- 			<li data-menuanchor="location"><a href="#location">LOCATION</a></li> -->
	<!-- 			<li data-menuanchor="question"><a href="#question">Q&A</a></li> -->
	<!-- 		</ul> -->
	<!-- 	</nav> -->

	<!-- 	<div id="main"> -->
	<!-- 		<header id="mobileMenu"> -->
	<!-- 			<span class="w3-opennav w3-xlarge" onclick="w3_open()">&#9776;</span> -->
	<!-- 		</header> -->

	<!-- 		<header id="desktopMenu"> -->
	<!-- 			<div class="text-center"> -->
	<!-- 				<div> -->
	<!-- 					<ul class="myMenu"> -->
	<!-- 						<li data-menuanchor="intro" class="active"><a href="#intro">INTRODUCTION</a></li> -->
	<!-- 						<li data-menuanchor="news"><a href="#news">NEWS</a></li> -->
	<!-- 						<li data-menuanchor="production"><a href="#production">PRODUCTION</a></li> -->
	<!-- 						<li data-menuanchor="location"><a href="#location">LOCATION</a></li> -->
	<!-- 						<li data-menuanchor="question"><a href="#question">Q&A</a></li> -->
	<!-- 					</ul> -->
	<!-- 				</div> -->
	<!-- 			</div> -->
	<!-- 		</header> -->

	<!-- 		<section id="firstView" class="bg01"> -->
	<!-- 			<img src="images/healthy.png" id="healthy" class="storyTopic"/> -->
	<!-- 		</section> -->

	<!-- 		<section id="secondView"></section> -->

	<!-- 		<div id="fullpage"> -->
	<!-- 			<div class="section"> -->
	<%-- 				<%@ include file="/WEB-INF/jsp/intro.jsp"%> --%>
	<!-- 			</div> -->

	<!-- 			<div class="section"> -->
	<%-- 				<%@ include file="/WEB-INF/jsp/news.jsp"%> --%>
	<!-- 			</div> -->

	<!-- 			<div class="section"> -->
	<%-- 				<%@ include file="/WEB-INF/jsp/production.jsp"%> --%>
	<!-- 			</div> -->

	<!-- 			<div class="section"> -->
	<%-- 				<%@ include file="/WEB-INF/jsp/location.jsp"%> --%>
	<!-- 			</div> -->

	<!-- 			<div class="section"> -->
	<%-- 				<%@ include file="/WEB-INF/jsp/question.jsp"%> --%>
	<!-- 			</div> -->
	<!-- 		</div> -->
	<!-- 	</div> -->
</body>
</html>
