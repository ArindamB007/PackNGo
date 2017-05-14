var PackNGo = angular.module('PackNGo', ['ngRoute','ngAnimate','ui.bootstrap']);


/***************************FB Login initiation*************************************/
PackNGo.run(['$rootScope', '$window', 'faceBookLoginService',
	function($rootScope, $window, fbService) {
		$rootScope.user = {};
		$window.fbAsyncInit = function() {
			// Executed when the SDK is loaded
			FB.init({
				version    : 'v2.9',
				/*				 The app id of the web app;
				 To register a new app visit Facebook App Dashboard
				 ( https://developers.facebook.com/apps/ )
				 */

				appId: '465874257093281',

				/*
				 Adding a Channel File improves the performance
				 of the javascript SDK, by addressing issues
				 with cross-domain communication in certain browsers.
				 */

				channelUrl: 'app/channel.html',

				/*
				 Set if you want to check the authentication status
				 at the start up of the app
				 */

				status: true,

				/*
				 Enable cookies to allow the server to access
				 the session
				 */

				cookie: true,

				/* Parse XFBML */

				xfbml: true
			});

			fbService.watchLoginChange();

		};

		(function(d){
			// load the Facebook javascript SDK

			var js,
				id = 'facebook-jssdk',
				ref = d.getElementsByTagName('script')[0];

			if (d.getElementById(id)) {
				return;
			}

			js = d.createElement('script');
			js.id = id;
			js.async = true;
			js.src = "//connect.facebook.net/en_US/sdk.js";

			ref.parentNode.insertBefore(js, ref);

		}(document));

	}]);

PackNGo.factory('faceBookLoginService', ['$rootScope','$q',function($rootScope,$q) {
	var fbService= {};
	fbService.getUserInfo = function() {
		var deferred = $q.defer();
		var _self = this;

		FB.api('/me', function(res) {
			if (!res||res.error){
				deferred.reject('Error Occurred');
			} else{
				deferred.resolve(res);
			}

		});
		return deferred.promise;
	};
	fbService.logOut = function() {
		FB.logout(function(response) {
			// user is now logged out
			alert("You have been logged out :" + JSON.stringify(response));
		});
	};
	fbService.watchLoginChange = function () {

		var _self = this;
		var userInfo;

		FB.Event.subscribe('auth.authResponseChange', function (res) {

			if (res.status === 'connected') {

				/*
				 The user is already logged,
				 is possible retrieve his personal info
				 */
				_self.getUserInfo().then(function(user){
					alert("You have been logged in : " + JSON.stringify(user));
					console.log(user);
					_self.logOut();
				});
				/*
				 This is also the point where you should create a
				 session for the current user.
				 For this purpose you can use the data inside the
				 res.authResponse object.
				 */

			}
			else {
				userInfo = {};
				alert("You have been logged out!");
				/*
				 The user is not logged to the app, or into Facebook:
				 destroy the session on the server.
				 */

			}

		});
	};
	return fbService;
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

//Controller for the main page index.html
PackNGo.controller('MainCtrl', function($scope,$timeout) {
	$().UItoTop({ easingType: 'easeOutQuart' }); //uitotopplugin
	$scope.companyDetails = {id: '12345', name: 'ABC Inc.'};
	$scope.$on('$viewContentLoaded', function(event){
        $timeout(function() {
            Waypoint.refreshAll();
        },1000);
    });
});

//controller for the menu index.html
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
});

/*PackNGo.directive('wayPoint',function($timeout){
  return {
    link: function(scope, elem, attrs) {
    	$timeout(function ()
    	{alert("property directive");
    	offset_diff = 30;
    	elem.waypoint(function(direction) {
        	window_height = $(window).height();
        	//alert("Direction: " + direction + " " + window_height);
        	console.log("Direction: " + direction + " " + window_height);
            if($(this.element).hasClass('title')){
                 offset_diff = 110;
             }
            console.log("Element Id: " + $(this.element).id);
            if(direction == 'down'){
            	$(this.element).addClass("animate");
            	//this.element.addClass("animate");  
            } else {
            	$(this.element).removeClass("animate");
            	//this.element.removeClass('animate');
            }
            //$this.toggleClass('animate');
          //scope.$parent.last = scope.post.id;
          //scope.$parent.$apply();
        },{offset: window_height - offset_diff})},0);
    }
  }
});*/

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
