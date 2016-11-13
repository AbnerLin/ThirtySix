$(document).ready(function() {
	$('#fullpage').fullpage({
		anchors : [ 'intro', 'news', 'production', 'location', 'question' ],
		menu : '#myMenu',
		afterLoad : function(anchorLink, index) {
			var loadedSection = $(this);

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