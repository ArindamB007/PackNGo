PackNGo.controller('LoginCtrl',function($scope,$http,$location, $rootScope,LoginService,
		LocalStorageService,ModalService,MenuService,CommonService,UserContext){
  $rootScope.userDetails = {};
  $scope.doLogin = function() {
    LoginService.userLogin($scope.loginDetails)
      .then(function(response){
      $scope.loginForm.$setPristine();
      UserContext = response.data;
      console.log(UserContext);
      LocalStorageService.setLocalStore('token',response.headers("x-auth-token"));
      console.log(response.headers('x-auth-token'));
      $http.defaults.headers.common['x-auth-token'] = LocalStorageService.getLocalStore('token');
      //redirect to home page after login
      if(UserContext.idUser) {
    	$rootScope.$broadcast("RefreshUserMenu",UserContext);
        alert ("Login Success!");
        $location.path('/');
      }

    })
      .catch(function(response){
      $scope.loginDetails = {};
      console.log(response);
      CommonService.handleDefaultErrorResponse("sm","Error Fetching Menu", response,["OK"]);
      /*var modalInstance = ModalService.showModal("sm","Login Failed!",
        response.data[0].message,["OK"]);
      modalInstance.result.then(function (response) {
        //need to put the focus on the element
      }, function (response) {
        //need to put the focus on the element
      });*/
    });
  };
});