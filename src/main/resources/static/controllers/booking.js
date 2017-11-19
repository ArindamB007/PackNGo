PackNGo.controller('BookingCtrl',function($scope,BookingService,CONSTANTS){
	$scope.BOOKING_NAV_CONSTANTS = CONSTANTS.BOOKING_NAV; // booking nav constants in scope variable
  $scope.availableRoomTypes = {}; //available rooms
  $scope.bookingDetails = {}; // selected rooms for booking
  /*Booking navigation control logic*/
  $scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_DATE;
  $scope.moveNext = function(){
    switch ($scope.bookingStage){
      case $scope.BOOKING_NAV_CONSTANTS.SELECT_DATE :
        $scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_ROOM;
    }
  };
  $scope.movePrev = function(){
    switch ($scope.bookingStage){
      case CONSTANTS.BOOKING_NAV.SELECT_ROOM :
        $scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_DATE;
        $scope.availableRoomTypes = {}; // clear search results
    }
  };
  /*Booking Search Function doing service call*/
  $scope.searchRoomsByDate = function() {
    BookingService.searchRoom({"hello": "hello world"}).then(function (response) {
      $scope.availableRoomTypes = response.data.availableRoomTypes;
      $scope.moveNext(); //move to next booking stage
    }).catch(function (response) {
      $scope.availableRoomTypes = {};
      CommonService.handleDefaultErrorResponse("sm", "Search Room Failed!", response, ["OK"]);
    });
  }



	/*Date picker control logic*/
	$scope.$on('$routeChangeStart', function(event){
		$('.datepicker').remove();
    });
	$(function(){
		var nowTemp = new Date();
		var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
		var chkInDateCtrl = $('#checkindate');
    var chkOutDateCtrl = $('#checkoutdate');
		console.log(BookingService.searchRoom({"hello": "hello world"}));
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
		  }
		  checkin.hide();
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
