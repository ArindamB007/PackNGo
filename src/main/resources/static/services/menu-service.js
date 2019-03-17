PackNGo.factory('MenuService',function($q,$http,$log) {
  return{
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
        }
  };
});