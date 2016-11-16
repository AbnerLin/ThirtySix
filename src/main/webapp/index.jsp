<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link type="text/css" href="<c:url value="/css/bootstrap3/bootstrap-theme.min.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/bootstrap3/bootstrap.min.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/bootstrap4.min.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/jquery-ui/jquery-ui.min.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/jquery-ui/jquery-ui.structure.min.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/jquery-ui/jquery-ui.theme.min.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/fullPage.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/index.css" />" rel="stylesheet">

<script src="<c:url value="/js/jquery-2.1.4.js" />"></script>
<script src="<c:url value="/js/jquery-ui/jquery-ui.min.js" />"></script>
<script src="<c:url value="/js/fullPage.min.js" />"></script>
<script src="<c:url value="/js/bootstrap3/bootstrap.min.js" />"></script>
<script src="<c:url value="/js/index.js" />"></script>
</head>
<body>


	<nav class="navbar navbar-default" id="menu">
		<div class="text-center">
			<div class="container-fluid">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
						<span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="#"><img class="pageBg" src="images/healthy.png" id="web_logo"></a>
				</div>

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav" id="myMenu">
						<li data-menuanchor="intro" class="active"><a href="#intro">INTRODUCTION</a></li>
						<li data-menuanchor="news"><a href="#news">NEWS</a></li>
						<li data-menuanchor="production"><a href="#production">PRODUCTION</a></li>
						<li data-menuanchor="location"><a href="#location">LOCATION</a></li>
						<li data-menuanchor="question"><a href="#question">Q&A</a></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->
			</div>
			<!-- /.container-fluid -->
		</div>
	</nav>



	<div id="fullpage">
		<div class="section">
			<%@ include file="/WEB-INF/jsp/intro.jsp"%>
		</div>

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
