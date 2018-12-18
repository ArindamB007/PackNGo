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
        },
        prepareInvoice : function(bookingCart) {
            var deferred = $q.defer();
            $http.post("../services/prepare_invoice",bookingCart)
                .then(function(response){
                    deferred.resolve(response);
                })
                .catch(function(response){
                    deferred.reject(response);
                    $log.error(response);
                });
            return deferred.promise;
        },
        processInvoice : function(invoice) {
            var deferred = $q.defer();
            $http.post("../services/process_invoice",invoice)
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