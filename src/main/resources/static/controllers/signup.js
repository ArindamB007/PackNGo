PackNGo.controller('SignupCtrl',function($scope,loginService){
$scope.userDetails = {};

$scope.doSignUp = function(){
  signupResponse = loginService.signUp($scope.userDetails)
  .then(function(response){
	  alert("Signup successful");
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