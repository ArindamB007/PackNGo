PackNGo.factory('BookingService', function ($rootScope, $q, $http, $log) {
    return{
        searchRoom : function(checkInOutDetails) {
            var deferred = $q.defer();
            $http.post("../api/property/search_room_types", checkInOutDetails)
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
            $http.post("../api/invoice/prepare_invoice", bookingCart)
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
            $http.post("../api/invoice/process_invoice", invoice)
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