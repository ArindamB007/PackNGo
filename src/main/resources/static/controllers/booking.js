PackNGo.controller('BookingCtrl',function($scope,BookingService,CONSTANTS,CommonService,PropertyService){
    $scope.BOOKING_NAV_CONSTANTS = CONSTANTS.BOOKING_NAV; // booking nav constants in scope variable
    $scope.availableRoomTypes = {}; //available rooms
    $scope.checkInOutDetails = {}; // selected rooms for booking
    $scope.selectedProperty = PropertyService.getSelectedProperty();
    /*Booking navigation control logic*/
    $scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_DATE;
    $scope.moveNext = function(){
        switch ($scope.bookingStage){
            case $scope.BOOKING_NAV_CONSTANTS.SELECT_DATE :
                $scope.checkInOutDetails.nights =
                    CommonService.getNightsFromCheckInOut($scope.checkInOutDetails.checkOutTimestamp,
                        $scope.checkInOutDetails.checkInTimestamp);
                $scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_ROOM;
                break;
            case $scope.BOOKING_NAV_CONSTANTS.SELECT_ROOM :
                $scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SHOW_GUEST_INFO;
                break;
        }
    };
    $scope.movePrev = function(){
        switch ($scope.bookingStage){
            case $scope.BOOKING_NAV_CONSTANTS.SELECT_ROOM :
                $scope.availableRoomTypes = {}; // clear search results
                $scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_DATE;
                break;
            case $scope.BOOKING_NAV_CONSTANTS.SHOW_GUEST_INFO:
                $scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_ROOM;
        }
    };
    /*Booking Search Function doing service call*/
    $scope.searchRoomsByDate = function() {
        $scope.checkInOutDetails.idProperty = $scope.selectedProperty.idProperty;
        BookingService.searchRoom($scope.checkInOutDetails).then(function (response) {
            $scope.availableRoomTypes = response.data;
            $scope.moveNext(); //move to next booking stage
        }).catch(function (response) {
            $scope.availableRoomTypes = {};
            CommonService.handleDefaultErrorResponse("sm", "Search Room Failed!", response, ["OK"]);
        });
    };
    /*Guest Confirmation Screen*/
    $scope.showGuestConfirmation = function() {
        $scope.moveNext(); //move to next booking stage
    };
    /*add rooms for selection*/
    $scope.addRoomsByIndex = function(roomTypeIndex){
        if (!$scope.availableRoomTypes[roomTypeIndex].selectedCount ||
            $scope.availableRoomTypes[roomTypeIndex].selectedCount === 0) {
            $scope.availableRoomTypes[roomTypeIndex].selectedCount = 1;
        }
        else
        {
            $scope.availableRoomTypes[roomTypeIndex].selectedCount =
                parseInt($scope.availableRoomTypes[roomTypeIndex].selectedCount) + 1;
        }
    };
    /*remove rooms for selection*/
    $scope.removeRoomsByIndex = function(roomTypeIndex){
        if ($scope.availableRoomTypes[roomTypeIndex].selectedCount > 0) {
            $scope.availableRoomTypes[roomTypeIndex].selectedCount =
                parseInt($scope.availableRoomTypes[roomTypeIndex].selectedCount) - 1;
        }
    };
    /*disable room qty increase*/
    $scope.disableRoomQtyIncrease = function(roomTypeIndex){
        return ($scope.availableRoomTypes[roomTypeIndex].selectedCount ===
            $scope.availableRoomTypes[roomTypeIndex].availableCount);
    };
    /*disable room qty decrease*/
    $scope.disableRoomQtyDecrease = function(roomTypeIndex){
        return ($scope.availableRoomTypes[roomTypeIndex].selectedCount === 0);
    };
    /*allow movenext to guest info*/
    $scope.enableMoveToGuestInfo = function(){
        if ($scope.availableRoomTypes.length>0) {
            for (var i = 0, len = $scope.availableRoomTypes.length; i < len; i++) {
                if ($scope.availableRoomTypes[i].selectedCount > 0)
                    return true;
            }
        }
        return false;
    };




    /*Date picker control logic*/
    $scope.$on('$routeChangeStart', function(event){
        $('.datepicker').remove();
    });
    $(function(){
        var nowTemp = new Date();
        var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
        var chkInDateCtrl = $('#checkindate');
        var chkOutDateCtrl = $('#checkoutdate');
        var checkin = chkInDateCtrl.datepicker({
            onRender: function(date) {
                return date.valueOf() < now.valueOf() ? 'disabled' : '';
            },
            format: 'dd-mmm-yyyy'
        }).on('changeDate', function(ev) {
            chkInDateCtrl
                .trigger('input')
                .trigger('change');
            if (ev.date.valueOf() >= checkout.date.valueOf()) {
                var newDate = new Date(ev.date);
                newDate.setDate(newDate.getDate() + 1);
                checkout.setValue(newDate);
                chkOutDateCtrl
                    .trigger('input')
                    .trigger('change');
                checkin.hide();
            }
            chkOutDateCtrl[0].focus();
        }).data('datepicker');
        var checkout = chkOutDateCtrl.datepicker({
            onRender: function(date) {
                return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
            },
            format: 'dd-mmm-yyyy'
        }).on('changeDate', function(ev) {
            chkOutDateCtrl
                .trigger('input')
                .trigger('change');
            checkout.hide();
        }).data('datepicker');
    });
});
