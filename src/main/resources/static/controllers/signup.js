PackNGo.controller('SignupCtrl', function ($scope, LoginService, ModalService, $location, $log, CommonService) {
$scope.userDetails = {};
$scope.doSignUp = function(){
    $scope.signup.$setPristine();
  LoginService.signUp($scope.userDetails)
  .then(function(response){
    //reset the user details after successful submission
    $scope.signup.$setPristine();
      $scope.userDetails = response.data.responseData;
      var modalInstance = ModalService.showModal("md", "Signup Success", response.data.message,
          ["OK"]);
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
      if (response.data.validationErrors) {
          angular.forEach(response.data.validationErrors, function (map) {
              $scope.signup[map.fieldName].$error[map.errorType] = true;
              $scope.signup[map.fieldName].errMsg = map.message;
          });
      }
      else if (response.data.apiError) {
          CommonService.handleDefaultErrorResponse("sm", "Error Signing up", response, ["OK"]);
      }

	  });
};
$scope.resetErrors = function(fieldName){
	$scope.signup[fieldName].$error['custom'] = false;
    $scope.signup[fieldName].$error['required'] = false;

};
});