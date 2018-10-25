PackNGo.controller('LoginCtrl',function($scope,$http,$location, $rootScope,LoginService,
                                        LocalStorageService,ModalService,MenuService,CommonService,
                                        UserContext,LastUrl){
  $scope.doLogin = function() {
    LoginService.userLogin($scope.loginDetails)
      .then(function(response){
        var userContext;
        $scope.loginForm.$setPristine();
        console.log(UserContext.value);
        UserContext.value = response.data; // for access across the application
        userContext = UserContext.value;
        console.log(userContext);
        LocalStorageService.setLocalStore('token',response.headers("x-auth-token"));
        console.log(response.headers('x-auth-token'));
        $http.defaults.headers.common['x-auth-token'] = LocalStorageService.getLocalStore('token');
        //redirect to home page after login
        if(UserContext.value.idUser) {
          $rootScope.$broadcast("RefreshUserMenu",userContext);
          alert ("Login Success!");
          if (Object.keys(LastUrl.value).length===0)
            $location.path('/');
          else{
            $location.path(LastUrl.value);
            LastUrl.value = {};
          }
        }

      })
      .catch(function(response){
        $scope.loginDetails = {};
        console.log(response);
        CommonService.handleDefaultErrorResponse("md","Login Failed!", response,["OK"]);
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