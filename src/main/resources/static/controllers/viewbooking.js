PackNGo.controller('ViewBookingCtrl', function ($scope, InvoiceSearchService, UserContext) {
    var searchBookingObject = {
        "userId": UserContext.value.idUser,
        "sortOrder": 0,
        "pageNo": 1,
        "pageSize": 20
    };

    InvoiceSearchService.invoiceSearch(searchBookingObject).then(function (response) {
        console.log("Booking Service Response");
        console.log(response);
    });
    alert("View booking Controller Loaded");
});