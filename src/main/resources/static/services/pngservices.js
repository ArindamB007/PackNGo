
PackNGo.factory('LoginService', ['$rootScope','$q','$http','$log',function($rootScope,$q,$http,$log) {
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
      },
      userLogin : function(loginDetails) {
        var deferred = $q.defer();
        $http.post("../services/userlogin",loginDetails)
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