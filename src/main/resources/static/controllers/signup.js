PackNGo.controller('SignupCtrl',function($scope,loginService){
$scope.userDetails = undefined;

$scope.doSignUp = function(){
  alert("You Clicked SignUp...!!");
  loginService.signUp($scope.userDetails);
};
});