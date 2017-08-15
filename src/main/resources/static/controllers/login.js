PackNGo.controller('LoginCtrl',function($scope,$http,$location, $rootScope,LoginService,LocalStorageService,ModalService){
  $rootScope.userDetails = undefined;
  $scope.doLogin = function() {
    LoginService.userLogin($scope.loginDetails)
      .then(function(response){
      $scope.loginForm.$setPristine();
      $rootScope.userDetails = response.data;
      LocalStorageService.setLocalStore('token',response.headers("x-auth-token"));
      console.log(response.headers('x-auth-token'));
      $http.defaults.headers.common['x-auth-token'] = LocalStorageService.getLocalStore('token');
      //redirect to home page after login
      if($rootScope.userDetails !== undefined) {
        alert ("Login Success!");
        $location.path('/');
      }

    })
      .catch(function(response){
      $scope.loginDetails = {};
      console.log(response);
      var modalInstance = ModalService.showModal("sm","Login Failed!",
        response.data[0].message,["OK"]);
      modalInstance.result.then(function (response) {
        //need to put the focus on the element
      }, function (response) {
        //need to put the focus on the element
      });
    });
  };
});