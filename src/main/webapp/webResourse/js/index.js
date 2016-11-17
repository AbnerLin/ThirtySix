function w3_open() {
	// document.getElementById("sideNav").style.display = "block";
	$('#sideNav').show();
	$('#main').css({
		"margin-left" : "200px"
	});
}
function w3_close() {
	// document.getElementById("sideNav").style.display = "none";
	$('#sideNav').hide();$('#main').css({
		"margin-left" : "0px"
	});
}

$(document).ready(function() {
	$('#healthy').show('blind', 1500);
	
});