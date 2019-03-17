
PackNGo.factory('LoginService',function($rootScope,$q,$http,$log,UserContext) {
  return{
      signUp : function(userDetails) {
        var deferred = $q.defer();
          $http.post("../api/user/sign_up", userDetails)
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
          $http.post("../api/user/user_login", loginDetails)
          .then(function(response){
            deferred.resolve(response);
          })
          .catch(function(response){
            deferred.reject(response);
            $log.error(response);
          });
        return deferred.promise;
      },
      userLogoff : function() {
        var deferred = $q.defer();
          $http.get("../api/user/user_logoff")
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
          $http.post("../api/user/user_menu", userContext)
            .then(function(response){
              deferred.resolve(response);
            })
            .catch(function(response){
              deferred.reject(response);
              $log.error(response);
            });
          return deferred.promise;
        },
        isLoggedIn : function() {
        	return !(Object.keys(UserContext.value).length==0)
        },
        verifyEmail : function(emailValidationCode) {
            var deferred = $q.defer();
            $http.post("../api/user/verify_email/" + emailValidationCode)
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