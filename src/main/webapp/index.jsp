<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<link type="text/css" href="<c:url value="/css/bootstrap3/bootstrap-theme.min.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/bootstrap3/bootstrap.min.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/fullPage.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/index.css" />" rel="stylesheet">

<script src="<c:url value="/js/jquery-2.1.4.js" />"></script>
<script src="<c:url value="/js/fullPage.min.js" />"></script>
<script src="<c:url value="/js/bootstrap3/bootstrap.min.js" />"></script>
<script src="<c:url value="/js/index.js" />"></script>
</head>
<body>

	<div id="menu">
		<div class="text-center">
			<ul id="myMenu">
				<li data-menuanchor="news" class="active"><a href="#news">NEWS</a></li>
				<li data-menuanchor="production"><a href="#production">PRODUCTION</a></li>
				<li data-menuanchor="location"><a href="#location">LOCATION</a></li>
				<li data-menuanchor="question"><a href="#question">Q&A</a></li>
			</ul>
		</div>
	</div>

	<div id="fullpage">
		<div class="section">
			<%@ include file="/WEB-INF/jsp/news.jsp"%>
		</div>

		<div class="section">
			<%@ include file="/WEB-INF/jsp/production.jsp"%>
		</div>

		<div class="section">
			<%@ include file="/WEB-INF/jsp/location.jsp"%>
		</div>

		<div class="section">
			<%@ include file="/WEB-INF/jsp/question.jsp"%>
		</div>
	</div>

</body>
</html>
