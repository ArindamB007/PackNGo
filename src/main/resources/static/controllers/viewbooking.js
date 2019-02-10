PackNGo.controller('ViewBookingCtrl', function ($scope, $window, InvoiceSearchService, UserContext) {
    var searchBookingObject = {
        "userId": UserContext.value.idUser,
        "sortOrder": 0,
        "pageNo": 1,
        "pageSize": 20
    };
    $scope.isServiceAvailable = false;
    $scope.invoicePage = false;
    $scope.bookingHistory = false;

    InvoiceSearchService.invoiceSearch(searchBookingObject).then(function (response) {
        if (response.data.rows.length > 0) {
            $scope.isServiceAvailable = true;
            $scope.bookingHistory = true;
            $scope.invoicePage = false;
            console.log("Booking Service Response");
            $scope.invoiceList = response.data.rows;
            console.log($scope.invoiceList);
        }

        $scope.checkCancel = function (item) {
            return !item.allowCancelFlag;
        };

        $scope.GetRowPrint = function (index) {
            $scope.bookingHistory = false;
            $scope.invoicePage = true;
            // $window.alert("Row Index: " + $scope.invoiceList[index].invoiceNo);
            $scope.FinalInvoice = $scope.invoiceList[index];
            $scope.invoiceLines = $scope.invoiceList[index].invoiceLines;
        };

        $scope.goBack = function () {
            $scope.bookingHistory = true;
            $scope.invoicePage = false;
        }

    });

});