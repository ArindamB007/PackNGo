PackNGo.controller('LoginCtrl',function($scope,LoginService,ModalService){
  $scope.loginDetails = {};
  $scope.doLogin = function() {
    LoginService.userLogin($scope.loginDetails)
      .then(function(response){
      $scope.loginForm.$setPristine();
      $scope.loginDetails = {};
      $location.path("\login");
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