PackNGo.controller('VerifyEmailCtrl',function($scope,$routeParams,LoginService,CommonService){
    $scope.doVerifyEmail = function() {
        $scope.message = "";
        $scope.info="";
        $scope.login=false;
        LoginService.verifyEmail($routeParams.verificationCode)
            .then(function(response){
                console.log(response);
                $scope.message = "";
                $scope.info="";
                $scope.info="success_email";
                $scope.login=true;
                $scope.message = " Your Email Validated Successfully";
            })
            .catch(function(response){
                console.log(response);
                //.handleDefaultErrorResponse("md","Error Validating Email", response,["OK"]);
                $scope.message = "";
                $scope.info="";
                $scope.login=false;
                $scope.info="error_email";
                $scope.message = "We are facing some error validating your email id";
            });
    };
    $scope.doVerifyEmail();
});