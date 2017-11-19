var PackNGo = angular.module('PackNGo', ['ngRoute','ngAnimate','ui.bootstrap','ngMessages','ngStorage']);


/***************************FB Login initiation*************************************/
PackNGo.run(['$rootScope', '$window', 'faceBookLoginService', 'LoginService','$location','LastUrl',
	function($rootScope, $window, fbService,LoginService,$location,LastUrl) {
	$rootScope.$on("$routeChangeStart", function (event, next, current) {
        if (next.$$route.templateUrl && !next.$$route.allowAnonymous && !LoginService.isLoggedIn()) {
          LastUrl.value = next.$$route.templateUrl;
        	$location.path('/login');
        }

    });
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

/*Adding Constants to be used application wide*/
PackNGo.constant('CONSTANTS',{
	BOOKING_NAV:{
		SELECT_DATE:'SELECT_DATE',
		SELECT_ROOM:'SELECT_ROOM'
	}
});

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
	
	/*$stateProvider.state('login', {
	    url: '/login',
	    allowAnonymous: true, //if you move this, don't forget to update
	                          //variable path in the force-page check.
	    views: {
	        root: {
	            templateUrl: "app/auth/login/login.html",
	            controller: 'LoginCtrl'
	        }
	    }
	    //Any other config
	});*/

}]);


PackNGo.controller('HomeCtrl', function($scope) {
	$scope.greeting = {id: 'xxx', content: 'Hello World!'}
});

//Controller for the main page index.html
PackNGo.controller('MainCtrl', function($scope,$rootScope,$timeout, $location) {
	$().UItoTop({ easingType: 'easeOutQuart' }); //uitotopplugin
	$scope.companyDetails = {id: '12345', name: 'ABC Inc.'};
	
	$scope.$on('$viewContentLoaded', function(event){
		// handling menu transparency flag
		if ($location.path()==="/") {
				$('nav[role="navigation"]').addClass('navbar-transparent');
			transparent = $menu_transparency = true;
		} else {
			$('nav[role="navigation"]').removeClass('navbar-transparent');
			transparent = $menu_transparency = false;
		}
        // refreshing waypoint for animations to sync
		$timeout(function() {
            Waypoint.refreshAll();
        },1000);
    });
});

//controller for the menu index.html
PackNGo.controller('MenuCtrl', function($scope,$rootScope,MenuService,ModalService,CommonService,
		UserContext) {
	//fetch user menu
	$scope.getUserMenu = function(userContext){
		MenuService.userMenu(userContext)
	      .then(function(response){
	    	  $scope.menuItems = response.data;
	    	  console.log(response.data);
	    })
	      .catch(function(response){
	      console.log(response);
	      CommonService.handleDefaultErrorResponse("lg","Error Fetching Menu", response,["OK"]);
	    });
	}
	$scope.getUserMenu(UserContext);
	
	$scope.$on("RefreshUserMenu",function(event,data){
		$scope.getUserMenu(data);
	});
	
	/*$scope.menuItems = [
		{menuLabel: "Home", linkURL: "#/"},
		{menuLabel: "Top", dataId:"#topOfPage"},
		{menuLabel: "Samples", linkURL: "#/samples"},
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
			submenuItems: [{menuLabel: "Login",linkURL: "#/login"},
				{menuLabel: "Sign-up",linkURL: "#/signup"}]}];*/
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

// price formatter
(function($){
	$.fn.priceFormat=function(options)
	{var defaults={
			prefix:'US$ ',
			suffix:'',
			centsSeparator:'.',
			thousandsSeparator:',',
			limit:9,
			centsLimit:2,
			clearPrefix:false,
			clearSufix:false,
			allowNegative:false,
			insertPlusSign:false};
	var options=$.extend(defaults,options);
	return this.each(function()
			{var obj=$(this);
			var is_number=/[0-9]/;
			var prefix=options.prefix;
			var suffix=options.suffix;
			var centsSeparator=options.centsSeparator;
			var thousandsSeparator=options.thousandsSeparator;
			var limit=options.limit;
			var centsLimit=options.centsLimit;
			var clearPrefix=options.clearPrefix;
			var clearSuffix=options.clearSuffix;
			var allowNegative=options.allowNegative;
			var insertPlusSign=options.insertPlusSign;
			if(insertPlusSign)allowNegative=true;
			
			function to_numbers(str){
				var formatted='';
				for(var i=0;i<(str.length);i++){
					char_=str.charAt(i);
					if(formatted.length==0&&char_==0)char_=false;
					if(char_&&char_.match(is_number)){
						if(limit){
							if(formatted.length<limit) formatted=formatted+char_
							}else{
								formatted=formatted+char_
								}
						}
					}
				return formatted;
				}
			
			function fill_with_zeroes(str){
				while(str.length<(centsLimit+1))
					str='0'+str;
				return str;
			}
			function price_format(str){
				var formatted= fill_with_zeroes(to_numbers(str));
				var thousandsFormatted='';
				var thousandsCount=0;
				if(centsLimit==0){
					centsSeparator="";
					centsVal=""
				}
				var centsVal=formatted.substr(formatted.length-centsLimit,centsLimit);
				var integerVal=formatted.substr(0,formatted.length-centsLimit);
				formatted=(centsLimit==0)?integerVal:integerVal+centsSeparator+centsVal;
				if(thousandsSeparator||$.trim(thousandsSeparator)!=""){
					for(var j=integerVal.length;j>0;j--){
						char_=integerVal.substr(j-1,1);
						thousandsCount++;
						if(thousandsCount%3==0)
							char_=thousandsSeparator+char_;thousandsFormatted=char_+thousandsFormatted;
					}
				if(thousandsFormatted.substr(0,1)==thousandsSeparator)
					thousandsFormatted=thousandsFormatted.substring(1,thousandsFormatted.length);
				formatted=(centsLimit==0)?thousandsFormatted:thousandsFormatted+centsSeparator+centsVal
				}
				if(allowNegative&&(integerVal!=0||centsVal!=0)){
					if(str.indexOf('-')!=-1&&str.indexOf('+')<str.indexOf('-')){
						formatted='-'+formatted
						}else{
							if(!insertPlusSign)
								formatted=''+formatted;
							else formatted='+'+formatted
							}
					}
				if(prefix)
					formatted=prefix+formatted;
				if(suffix)
					formatted=formatted+suffix;
				return formatted
				}
			function key_check(e){
				var code=(e.keyCode?e.keyCode:e.which);
				var typed=String.fromCharCode(code);
				var functional=false;
				var str=obj.val();
				var newValue=price_format(str+typed);
				if((code>=48&&code<=57)||(code>=96&&code<=105))
					functional=true;
				if(code==8)functional=true;
				if(code==9)functional=true;
				if(code==13)functional=true;
				if(code==46)functional=true;
				if(code==37)functional=true;
				if(code==39)functional=true;
				if(allowNegative&&(code==189||code==109))
					functional=true;
				if(insertPlusSign&&(code==187||code==107))
					functional=true;
				if(!functional){
					e.preventDefault();
					e.stopPropagation();
					if(str!=newValue)
						obj.val(newValue)
				}
			}
			function price_it(){
				var str=obj.val();
				var price=price_format(str);
				if(str!=price)
					obj.val(price);
				}
			function add_prefix(){
				var val=obj.val();
				obj.val(prefix+val)
			}
			function add_suffix(){
				var val=obj.val();
				obj.val(val+suffix)
			}
			function clear_prefix(){
				if($.trim(prefix)!=''&&clearPrefix){
					var array=obj.val().split(prefix);
					obj.val(array[1])
				}
			}
			function clear_suffix(){
				if($.trim(suffix)!=''&&clearSuffix){
					var array=obj.val().split(suffix);
					obj.val(array[0])
					}
				}
			$(this).bind('keydown.price_format',key_check);
			$(this).bind('keyup.price_format',price_it);
			$(this).bind('focusout.price_format',price_it);
			if(clearPrefix){
				$(this).bind('focusout.price_format',function(){clear_prefix()});
				$(this).bind('focusin.price_format',function(){add_prefix()})
			}
			if(clearSuffix){
				$(this).bind('focusout.price_format',function(){clear_suffix()});
				$(this).bind('focusin.price_format',function(){add_suffix()})
			}
			if($(this).val().length>0){
				price_it();
				clear_prefix();
				clear_suffix()
			}
		})};
		$.fn.unpriceFormat=function(){
			return $(this).unbind(".price_format")
		};
		$.fn.unmask=function(){
			var field=$(this).val();
			var result="";
			for(var f in field){
				if(!isNaN(field[f])||field[f]=="-")
					result+=field[f]
			}
			return result;
		}})(jQuery);

/*(function($){$.fn.priceFormat=function(options){var defaults={prefix:'US$ ',suffix:'',centsSeparator:'.',thousandsSeparator:',',limit:false,centsLimit:2,clearPrefix:false,clearSufix:false,allowNegative:false,insertPlusSign:false};var options=$.extend(defaults,options);return this.each(function(){var obj=$(this);var is_number=/[0-9]/;var prefix=options.prefix;var suffix=options.suffix;var centsSeparator=options.centsSeparator;var thousandsSeparator=options.thousandsSeparator;var limit=options.limit;var centsLimit=options.centsLimit;var clearPrefix=options.clearPrefix;var clearSuffix=options.clearSuffix;var allowNegative=options.allowNegative;var insertPlusSign=options.insertPlusSign;if(insertPlusSign)allowNegative=true;function to_numbers(str){var formatted='';for(var i=0;i<(str.length);i++){char_=str.charAt(i);if(formatted.length==0&&char_==0)char_=false;if(char_&&char_.match(is_number)){if(limit){if(formatted.length<limit)formatted=formatted+char_}else{formatted=formatted+char_}}}return formatted}function fill_with_zeroes(str){while(str.length<(centsLimit+1))str='0'+str;return str}function price_format(str){var formatted=fill_with_zeroes(to_numbers(str));var thousandsFormatted='';var thousandsCount=0;if(centsLimit==0){centsSeparator="";centsVal=""}var centsVal=formatted.substr(formatted.length-centsLimit,centsLimit);var integerVal=formatted.substr(0,formatted.length-centsLimit);formatted=(centsLimit==0)?integerVal:integerVal+centsSeparator+centsVal;if(thousandsSeparator||$.trim(thousandsSeparator)!=""){for(var j=integerVal.length;j>0;j--){char_=integerVal.substr(j-1,1);thousandsCount++;if(thousandsCount%3==0)char_=thousandsSeparator+char_;thousandsFormatted=char_+thousandsFormatted}if(thousandsFormatted.substr(0,1)==thousandsSeparator)thousandsFormatted=thousandsFormatted.substring(1,thousandsFormatted.length);formatted=(centsLimit==0)?thousandsFormatted:thousandsFormatted+centsSeparator+centsVal}if(allowNegative&&(integerVal!=0||centsVal!=0)){if(str.indexOf('-')!=-1&&str.indexOf('+')<str.indexOf('-')){formatted='-'+formatted}else{if(!insertPlusSign)formatted=''+formatted;else formatted='+'+formatted}}if(prefix)formatted=prefix+formatted;if(suffix)formatted=formatted+suffix;return formatted}function key_check(e){var code=(e.keyCode?e.keyCode:e.which);var typed=String.fromCharCode(code);var functional=false;var str=obj.val();var newValue=price_format(str+typed);if((code>=48&&code<=57)||(code>=96&&code<=105))functional=true;if(code==8)functional=true;if(code==9)functional=true;if(code==13)functional=true;if(code==46)functional=true;if(code==37)functional=true;if(code==39)functional=true;if(allowNegative&&(code==189||code==109))functional=true;if(insertPlusSign&&(code==187||code==107))functional=true;if(!functional){e.preventDefault();e.stopPropagation();if(str!=newValue)obj.val(newValue)}}function price_it(){var str=obj.val();var price=price_format(str);if(str!=price)obj.val(price)}function add_prefix(){var val=obj.val();obj.val(prefix+val)}function add_suffix(){var val=obj.val();obj.val(val+suffix)}function clear_prefix(){if($.trim(prefix)!=''&&clearPrefix){var array=obj.val().split(prefix);obj.val(array[1])}}function clear_suffix(){if($.trim(suffix)!=''&&clearSuffix){var array=obj.val().split(suffix);obj.val(array[0])}}$(this).bind('keydown.price_format',key_check);$(this).bind('keyup.price_format',price_it);$(this).bind('focusout.price_format',price_it);if(clearPrefix){$(this).bind('focusout.price_format',function(){clear_prefix()});$(this).bind('focusin.price_format',function(){add_prefix()})}if(clearSuffix){$(this).bind('focusout.price_format',function(){clear_suffix()});$(this).bind('focusin.price_format',function(){add_suffix()})}if($(this).val().length>0){price_it();clear_prefix();clear_suffix()}})};$.fn.unpriceFormat=function(){return $(this).unbind(".price_format")};$.fn.unmask=function(){var field=$(this).val();var result="";for(var f in field){if(!isNaN(field[f])||field[f]=="-")result+=field[f]}return result}})(jQuery);
*/
PackNGo.directive('format', ['$filter', function ($filter) {
    return {
        require: '?ngModel',
        restrict: 'A',
        link: function (scope, elem, attrs, ctrl) {
            if (!ctrl) return;
            ctrl.$formatters.unshift(function (a) {
                return $filter(attrs.format)(ctrl.$modelValue)
            });


            ctrl.$parsers.unshift(function (viewValue) {
                              
          elem.priceFormat({
            prefix: '',
            centsSeparator: '.',
            thousandsSeparator: ''
        });                
                         
                return elem[0].value;
            });
        }
    };
}]);
