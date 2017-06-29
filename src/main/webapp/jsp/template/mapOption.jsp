<img class="mapOption" src="images/table-sm.png" />


<%@page language="java" pageEncoding="UTF-8"%>

<script id="mapOptionTemplate" type="text/html">
	<tr isSend="{{= isSend}}">
		<td class="col-md-3">{{= itemName}}</td>
		<td class="col-md-3">{{= orderTime}}</td>
		<td class="col-md-3">{{= deliveryTime}}</td>
		<td class="col-md-3">{{= volume}}</td>
		<td class="col-md-3"><button type="button" class="btn btn-success" style="display:{{= send}}" onclick="sendItem('{{= bookingID}}', '{{= customerID}}');">出餐</button></td>
	</tr>
</script>
