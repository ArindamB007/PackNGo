PackNGo.factory('InvoiceSearchService', function ($q, $http, $log) {
    return {
        invoiceSearch: function (invoiceSearchObject) {
            var deferred = $q.defer();
            $http.post("../api/invoice/findInvoice", invoiceSearchObject)
                .then(function (response) {
                    deferred.resolve(response);
                })
                .catch(function (response) {
                    deferred.reject(response);
                    $log.error(response);
                });
            return deferred.promise;
        }
    };
});