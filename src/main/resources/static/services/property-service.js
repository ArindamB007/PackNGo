PackNGo.factory('PropertyService',function($rootScope,$q,$http,$log,UserContext) {
  return{
    getProperties : function() {
      var deferred = $q.defer();
      $http.get("../services/getAllEnabledProperties")
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