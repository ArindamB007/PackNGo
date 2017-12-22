PackNGo.factory('BookingService',function($rootScope,$q,$http,$log,UserContext) {
  return{
    searchRoom : function(checkInOutDetails) {
      var deferred = $q.defer();
      $http.post("../services/search_room_types",checkInOutDetails)
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