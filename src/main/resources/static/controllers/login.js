PackNGo.controller('LoginCtrl',function($scope,LoginService,LocalStorageService,ModalService,$http){
  $scope.loginDetails = {};
  $scope.doLogin = function() {
    LoginService.userLogin($scope.loginDetails)
      .then(function(response){
      $scope.loginForm.$setPristine();
      $scope.loginDetails = {};
      LocalStorageService.setLocalStore("token",response.headers("x-auth-token"));
      console.log(response.headers("x-auth-token"));
      $http.defaults.headers.common["x-auth-token"] = LocalStorageService.getLocalStore("token");
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