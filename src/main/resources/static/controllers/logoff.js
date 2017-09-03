PackNGo.controller('LogoffCtrl',function($scope,$http,$location, $rootScope,LoginService,
		LocalStorageService,ModalService,MenuService,CommonService,UserContext){
  $scope.doLogoff = function() {
    if (UserContext.value.email ==='' || UserContext.value.email===undefined) return;
    LoginService.userLogoff()
      .then(function(response){
      console.log(response);
      LocalStorageService.deleteKeyLocalStore('token');
      UserContext.value = {};
      $rootScope.$broadcast("RefreshUserMenu",UserContext.value);
    })
      .catch(function(response){
      $scope.loginDetails = {};
      console.log(response);
      CommonService.handleDefaultErrorResponse("sm","Error Logging Off", response,["OK"]);
    });
  };
  alert("Logoff");
  $scope.doLogoff();
});