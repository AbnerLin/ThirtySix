<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body class="text-center">
	<%@ include file="template/orderList.jsp"%>

	<table class="table">
		<thead>
			<tr>
				<th class="text-center">項目</th>
				<th class="text-center">下單時間</th>
				<th class="text-center">出餐時間</th>
				<th class="text-center">數量</th>
				<th class="text-center">動作</th>
			</tr>
		</thead>
		<tbody id="orderListTable">
		</tbody>
	</table>

</body>
</html>