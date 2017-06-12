PackNGo.factory('pngServices', ['$rootScope','$q','$http','$log',function($rootScope,$q,$http,$log) {
    var user= {};
    user.signUp= function(userDetails) {
        var deferred = $q.defer();
        $http.post("../service/signup",userDetails)
            .success(function(data){
                deferred.resolve(data);
            })
            .error(function(msg, code){
                deferred.reject(msg);
                $log.error(msg,code);

            });
        return deferred.promise;
    };
     return user;
}]);