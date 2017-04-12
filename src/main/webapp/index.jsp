<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width initial-scale=1 maximum-scale=2">
<link type="text/css" href="<c:url value="/css/bootstrap3/bootstrap-theme.min.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/bootstrap3/bootstrap.min.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/bootstrap4.min.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/jquery-ui/jquery-ui.min.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/jquery-ui/jquery-ui.structure.min.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/jquery-ui/jquery-ui.theme.min.css" />" rel="stylesheet">
<link rel="stylesheet" href="http://www.w3schools.com/lib/w3.css">
<link type="text/css" href="<c:url value="/css/index.css" />" rel="stylesheet">

<script src="<c:url value="/js/jquery-2.1.4.js" />"></script>
<script src="<c:url value="/js/socket/sockjs-1.1.1.js" />"></script>
<script src="<c:url value="/js/socket/stomp.min.js" />"></script>
<script src="<c:url value="/js/jquery-ui/jquery-ui.min.js" />"></script>
<script src="<c:url value="/js/bootstrap3/bootstrap.min.js" />"></script>
<script src="<c:url value="/js/index.js" />"></script>
</head>
<body>
	
	<div id="serverTime">123</div>
	
	<button onclick="getDiningCustomer();"> getDiningCustomer </button>
	<div id="diningCustomerList">
		
	</div>
	
	<button onclick="customerCheckIn();"> customerCheckIn </button>
	中文測試
	<nav class="w3-sidenav w3-white" style="width: 200px; display: none;" id="sideNav">
		<ul class="myMenu">
			<li data-menuanchor="intro" class="active"><a href="#intro">INTRODUCTION</a></li>
			<li data-menuanchor="news"><a href="#news">中文測試</a></li>
			<li data-menuanchor="production"><a href="#production">PRODUCTION</a></li>
			<li data-menuanchor="location"><a href="#location">LOCATION</a></li>
			<li data-menuanchor="question"><a href="#question">Q&A</a></li>
		</ul>
	</nav>

	<div id="main">
		<header id="mobileMenu">
			<span class="w3-opennav w3-xlarge" onclick="w3_open()">&#9776;</span>
		</header>

		<header id="desktopMenu">
			<div class="text-center">
				<div>
					<ul class="myMenu">
						<li data-menuanchor="intro" class="active"><a href="#intro">INTRODUCTION</a></li>
						<li data-menuanchor="news"><a href="#news">NEWS</a></li>
						<li data-menuanchor="production"><a href="#production">PRODUCTION</a></li>
						<li data-menuanchor="location"><a href="#location">LOCATION</a></li>
						<li data-menuanchor="question"><a href="#question">Q&A</a></li>
					</ul>
				</div>
			</div>
		</header>

		<section id="firstView" class="bg01">
			<img src="images/healthy.png" id="healthy" class="storyTopic"/>
		</section>

		<section id="secondView"></section>

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
	</div>
</body>
</html>
