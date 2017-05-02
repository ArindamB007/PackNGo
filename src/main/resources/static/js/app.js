var PackNGo = angular.module('PackNGo', ['ngRoute','ngAnimate','ui.bootstrap']);

PackNGo.config(['$routeProvider',
    function($routeProvider){
	$routeProvider.
	when('/', {
		templateUrl : '/landing'
		//Controller is in the index.html file
	}).
	when('/about', {
		templateUrl : '/about',
		controller : 'aboutCtrl' 
	})
}]);

// configure the tooltipProvider to disable angular ui tooltip
PackNGo.config(['$tooltipProvider', function ($tooltipProvider) {
	//var parser = new UAParser();
	//var result = parser.getResult();
	//var touch = result.device && (result.device.type === 'tablet' || result.device.type === 'mobile');
	if (true) {
		var options = {
			trigger: 'dontTrigger' // default dummy trigger event to show tooltips
		};

		$tooltipProvider.options(options);
	}

}]);


PackNGo.controller('HomeCtrl', function($scope) {
	$scope.greeting = {id: 'xxx', content: 'Hello World!'}
});
PackNGo.controller('MainCtrl', function($scope) {
    $scope.companyDetails = {id: '12345', name: 'ABC inc.'};
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




PackNGo.controller('MenuCtrl', function($scope) {
	$scope.menuItems = [
        {menuLabel: "Home", linkURL: "#/"},
				{menuLabel: "ABC", dataId:"#topOfPage"},
				{menuLabel: "DEF", linkURL: "/"},
				{menuLabel: "IJK", linkURL: "/"},
				{menuLabel: "IJK", linkURL: "/"},
				{menuLabel: "IJK", linkURL: "/"},
				{menuLabel: "About Us", dataId:"#whoWeAre"},
				{menuLabel: "Booking", type: "dropDown",
            submenuItems: [{menuLabel: "Sub Option 1",linkURL: "home.html"},
                {menuLabel: "Sub Option 2",linkURL: "#"}]},
        {menuLabel: "Hotels", type: "dropDown",
            submenuItems: [{menuLabel: "Hotel 1",linkURL: "home.html"},
                {menuLabel: "Hotel 2",linkURL: "#"}]},
		{menuLabel: "Contact Us",linkURL: "#"},
		{menuLabel: "Account", showLabel:true, type: "dropDown", icon: "material-icons md-24 orangeC00", iconClass: "account_box",
			submenuItems: [{menuLabel: "Login",linkURL: "#"},
				{menuLabel: "Sign-up",linkURL: "#"}]}];
});

PackNGo.directive('navMenuItemInternalLink', function(){
	return{
		restrict: 'A',
		link: function(scope, element, attrs){
			element.on('click', function(e){
				var scroll_target = attrs.id;
				var scroll_trigger = attrs.scroll;
				if(scroll_trigger.toString().toLowerCase() == "true" && scroll_target !== undefined){
					e.preventDefault();
					$('html, body').animate({
						scrollTop: $(scroll_target).offset().top - 50
					}, 1000);
				}
				e.stopPropagation();
				if (attrs.setActiveOnClick) {
					element.children().toggleClass('active');
				}
			})
		}
	}
})

PackNGo.directive('fixOnTopWhenScrolled',['$window',function($window){
    return {
        restrict: 'A',
        link: function (scope, element, attrs) {
            var topClass = attrs.fixOnTopWhenScrolled, // get CSS class from directive's attribute value
                offsetTop = element.offsetTop;   // get element's top relative to the document
            angular.element($window).bind('scroll', function () {
                if ($window.pageYOffset > element.prop('offsetTop'))
                    element.addClass(topClass);
                else
                    element.removeClass(topClass);
            });
        }
    };

}]);
