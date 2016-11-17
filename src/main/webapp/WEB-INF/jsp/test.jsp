<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width; initial-scale=1; maximum-scale=2;">
<link type="text/css" href="<c:url value="/css/style.css" />" rel="stylesheet">
<link type="text/css" href="<c:url value="/css/slicknav.min.css" />" rel="stylesheet">
<script src="<c:url value="/js/jquery-2.1.4.js" />"></script>
<script src="<c:url value="/js/jquery.slicknav.min.js" />"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.6.2/modernizr.min.js"></script>
</head>
<body>

	<ul id="menu">
		<li>Parent 1
			<ul>
				<li><a href="#">item 3</a></li>
				<li>Parent 3
					<ul>
						<li><a href="#">item 8</a></li>
						<li><a href="#">item 9</a></li>
						<li><a href="#">item 10</a></li>
					</ul>
				</li>
				<li><a href="#">item 4</a></li>
			</ul>
		</li>
		<li><a href="#">item 1</a></li>
		<li>non-link item</li>
		<li>Parent 2
			<ul>
				<li><a href="#">item 5</a></li>
				<li><a href="#">item 6</a></li>
				<li><a href="#">item 7</a></li>
			</ul>
		</li>
	</ul>
	<script>
		$(function() {
			$('#menu').slicknav();
		});
	</script>
</body>
</html>
