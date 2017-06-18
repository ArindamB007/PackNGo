
PackNGo.factory('loginService', ['$rootScope','$q','$http','$log',function($rootScope,$q,$http,$log) {
  return{
      signUp : function(userDetails) {
        var deferred = $q.defer();
        $http.post("../services/signup",userDetails)
          .then(function(response){
            deferred.resolve(response);
          })
          .catch(function(response){
            deferred.reject(response);
            $log.error(response);
          });
        return deferred.promise;
      }
  };
}]);