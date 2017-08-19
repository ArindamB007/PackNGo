
PackNGo.factory('LoginService',function($rootScope,$q,$http,$log) {
  return{
      signUp : function(userDetails) {
        var deferred = $q.defer();
        $http.post("../services/sign_up",userDetails)
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
        $http.post("../services/user_login",loginDetails)
          .then(function(response){
            deferred.resolve(response);
          })
          .catch(function(response){
            deferred.reject(response);
            $log.error(response);
          });
        return deferred.promise;
      },
      userMenu : function(userContext) {
          var deferred = $q.defer();
          $http.post("../services/user_menu",userContext)
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
});