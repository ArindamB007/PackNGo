PackNGo.factory('PropertyService',function($rootScope,$q,$http,$log,SelectedProperty,
		LocalStorageService) {
  return{
        getProperties : function() {
          var deferred = $q.defer();
            $http.get("../api/property/getAllEnabledProperties")
            .then(function(response){
              deferred.resolve(response);
            })
            .catch(function(response){
              deferred.reject(response);
              $log.error(response);
            });
          return deferred.promise;
        },
        getSelectedProperty: function () {
            //return SelectedProperty.value;
        	return LocalStorageService.getLocalStore('SelectedProperty');
        },
        setSelectedProperty: function (property) {
        	LocalStorageService.setLocalStore('SelectedProperty',property)
            //SelectedProperty.value = property;
        }
      };
});