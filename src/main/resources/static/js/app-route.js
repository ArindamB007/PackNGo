//provides the routes for angular application

PackNGo.config(['$routeProvider',
    function($routeProvider){
        $routeProvider.
        when('/', {
            templateUrl : '/landing',
            controller : 'LandingCtrl',
            allowAnonymous : true
            //Controller is in the index.html file
        }).
        when('/login', {
            templateUrl : '/login',
            controller : 'LoginCtrl',
            allowAnonymous : true
        }).
        when('/logoff', {
            templateUrl : '/logoff',
            controller : 'LogoffCtrl',
            allowAnonymous : true
        }).
        when('/signup', {
            templateUrl : '/signup',
            controller : 'SignupCtrl',
            allowAnonymous : true
        }).
        when('/booking', {
            templateUrl : '/booking',
            controller : 'BookingCtrl',
            allowAnonymous : false //allows the url to be accessed anonymously
        }).
        when('/verify-email/:verificationCode', {
            templateUrl : '/verify-email',
            controller : 'VerifyEmailCtrl',
            allowAnonymous : true //allows the url to be accessed anonymously
        }).
        when('/samples', {
            templateUrl : '/samples',
            controller : 'SamplesCtrl'
        })
    }]);