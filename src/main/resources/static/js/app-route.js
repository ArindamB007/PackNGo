//provides the routes for angular application

PackNGo.config(['$routeProvider',
	function($routeProvider){
		$routeProvider.
		when('/', {
			templateUrl : '/landing',
			controller : 'LandingCtrl'
			//Controller is in the index.html file
		}).
		when('/login', {
      templateUrl : '/login',
      controller : 'LoginCtrl'
    }).
    when('/logoff', {
      templateUrl : '/logoff',
      controller : 'LogoffCtrl'
    }).
		when('/signup', {
			templateUrl : '/signup',
			controller : 'SignupCtrl'
		}).
		when('/booking', {
			templateUrl : '/booking',
			controller : 'BookingCtrl'
		}).
		when('/samples', {
			templateUrl : '/samples',
			controller : 'SamplesCtrl'
		}).
		when('/about', {
			templateUrl : '/about',
			controller : 'aboutCtrl'
		})
	}]);