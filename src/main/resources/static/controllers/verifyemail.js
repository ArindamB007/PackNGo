PackNGo.controller('VerifyEmailCtrl',function($scope,$routeParams,LoginService,CommonService){
    $scope.doVerifyEmail = function() {
        LoginService.verifyEmail($routeParams.verificationCode)
            .then(function(response){
                console.log(response);
            })
            .catch(function(response){
                console.log(response);
                CommonService.handleDefaultErrorResponse("sm","Error Validating Email", response,["OK"]);
            });
    };
    $scope.doVerifyEmail();
});