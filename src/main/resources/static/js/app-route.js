//provides the routes for angular application

PackNGo.config(['$routeProvider',
	function($routeProvider){
		$routeProvider.
		when('/', {
			templateUrl : '/landing',
			controller : 'LandingCtrl'
			//Controller is in the index.html file
		}).
		when('/booking', {
			templateUrl : '/booking',
			controller : 'BookingCtrl'
			//Controller is in the index.html file
		}).		
		when('/about', {
			templateUrl : '/about',
			controller : 'aboutCtrl'
		})
	}]);