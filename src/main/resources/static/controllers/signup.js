PackNGo.controller('SignupCtrl',function($scope,LoginService,ModalService,$location,$log){
$scope.userDetails = {};
$scope.doSignUp = function(){
  LoginService.signUp($scope.userDetails)
  .then(function(response){
    //reset the user details after successful submission
    $scope.signup.$setPristine();
    $scope.userDetails = {};
    var modalInstance = ModalService.showModal("sm","Signup Success",
			"We are delighted to have you with us! Please login!",["OK"]);
    modalInstance.result.then(function (response) {
      $log.info('Modal dismissed at: ' + new Date() + 'Response: ' + response);
      $location.path("\login");
    }, function (response) {
      $log.info('Modal dismissed at: ' + new Date() + 'Response: ' + response);
      $location.path("\login");
    });
	  console.log(response);
	  })
  .catch(function(response){
	  //alert("Signup failed");
	  //$scope.signup.email.$error.email = true;
	  angular.forEach(response.data,function(map){
		  console.log(map);
		  console.log(map.fieldName);
		  $scope.signup[map.fieldName].$error[map.errorType] = true;
		  $scope.signup[map.fieldName].errMsg = map.message;
	  })
	  console.log("Error:" + response.data);
	  console.log(response.data);
	  });
};
$scope.resetErrors = function(fieldName){
	$scope.signup[fieldName].$error['custom'] = false;
};
});