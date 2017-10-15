//controllers for the carousel landing.html
PackNGo.controller('LandingCtrl',function($scope,$location,$timeout){
	rubik.initAnimationsCheck();
});
//controllers for the carousel landing.html
PackNGo.controller('PropertyCtrl',function($scope,$location){
	$scope.properties = [{
			"id" : "1",
			"name" : "Property XYZ",
			"price_start_pday" : "1200",
			"desc" : "This is a sprawling property in the heart of the city. Amazing location of the property and connections from this city makes this property and excellent option to spend your stay.",
			"img" : "../img/prop1_img/prop1_cover.jpg",
			"facility" : {
				"wifi" : "true",
				"parking" : "true",
				"breakfast" : "false",
				"restaurant" : "false"}
		},
		{
			"id" : "2",
			"name" : "Property EFGH",
			"price_start_pday" : "1400",
			"desc" : "Amazing location of the property and connections from this city makes this property and excellent option to spend your stay. This is a sprawling property in the heart of the city.",
			"img" : "../img/prop2_img/prop2_cover.jpg",
			"facility" : {
				"wifi" : "false",
				"parking" : "false",
				"breakfast" : "true",
				"restaurant" : "true"}
		}];
	$scope.goBooking = function (prop){
		console.log("Going for Property: " + prop.name)
		$location.path('/booking');
	}
});


PackNGo.controller('CarouselCtrl',function($scope){
	$scope.myInterval = 3000;
	$scope.slides = [
		{
			image: '../img/image_seq(1).jpg'
		},
		{
			image: '../img/image_seq(2).jpg'
		},
		{
			image: '../img/image_seq(3).jpg'
		},
		{
			image: '../img/image_seq(4).jpg'
		}
	];
});

PackNGo.directive('propFeatureTooltip', function() {
    return {
    link: function($scope, $element, $attrs) {
        $scope.$watch(
            function () { return $element.attr('data-attr-on'); },
            function (newVal) { 
                var attr = $element.attr('data-attr-name');

                if(!eval(newVal)) {
                    $element.removeAttr(attr);
                }
                else {
                    $element.attr(attr, attr);
                }
            }
        );
        }
    };
});

