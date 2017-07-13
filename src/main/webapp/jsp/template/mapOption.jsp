<%@page language="java" pageEncoding="UTF-8"%>

<script id="mapOptionTemplate" type="text/html">
	<img class="mapOption" src={{= imagePath}} id="{{= classID}}" nameable="{{= isNameable}}" onclick="mapOptionListener(this);"/>
</script>
