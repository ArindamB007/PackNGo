
PackNGo.factory('loginService', ['$rootScope','$q','$http','$log',function($rootScope,$q,$http,$log) {
  return{
      signUp : function(userDetails) {
        var deferred = $q.defer();
        $http.post("../signup",userDetails)
          .success(function(data){
            deferred.resolve(data);
          })
          .error(function(msg, code){
            deferred.reject(msg);
            $log.error(msg,code);

          });
        return deferred.promise;
      }
  };
}]);