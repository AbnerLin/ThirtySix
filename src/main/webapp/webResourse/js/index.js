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

	// one page scroll
	$('#fullpage').fullpage({
		anchors : [ 'intro', 'news', 'production', 'location', 'question' ],
		menu : '.myMenu',
		afterLoad : function(anchorLink, index) {
			var loadedSection = $(this);

			console.log(anchorLink);

			// using anchorLink
			if (anchorLink == 'production') {
				$('#firstSlideBG').show('blind', 1500);
			}
		},
		afterSlideLoad : function(anchorLink, index, slideAnchor, slideIndex) {
			var loadedSlide = $(this);

			// production slide effect.
			if (anchorLink == 'production' && slideIndex == 1) {
				$('#secondSlideBG').show('blind', 1500);
			}

		}
	});

});