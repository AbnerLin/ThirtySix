<%@page language="java" pageEncoding="UTF-8"%>

<script id="mapOptionTemplate" type="text/html">
	<img class="mapOption" enum="{{= enumName}}" src={{= imagePath}} id="{{= classID}}" nameable="{{= isNameable}}" onclick="Map.mapOptionClick(this);"/>
</script>
