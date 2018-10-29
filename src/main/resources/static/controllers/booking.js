PackNGo.controller('BookingCtrl',function($scope,BookingService,CONSTANTS,CommonService,PropertyService){
	$scope.BOOKING_NAV_CONSTANTS = CONSTANTS.BOOKING_NAV; // booking nav constants
	// in scope variable
	$scope.currency = "INR ";
	$scope.availableRoomTypes = {}; // available rooms
	$scope.checkInOutDetails = {}; // selected check in out dates
	$scope.bookingDetails = {}; // selected booking details
	/*assigning the selected property*/
	//$scope.bookingDetails.selectedProperty = PropertyService.getSelectedProperty();
    $scope.selectedProperty = PropertyService.getSelectedProperty();
	/* Booking navigation control logic */
	$scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_DATE;
	$scope.moveNext = function(){
		switch ($scope.bookingStage){
		case $scope.BOOKING_NAV_CONSTANTS.SELECT_DATE :
			$scope.checkInOutDetails.nights =
				CommonService.getNightsFromCheckInOut($scope.checkInOutDetails.checkOutTimestamp,
						$scope.checkInOutDetails.checkInTimestamp);
			/*$scope.bookingDetails.checkInTimestamp = $scope.checkInOutDetails.checkInTimestamp;
			$scope.bookingDetails.checkOutTimestamp = $scope.checkInOutDetails.checkOutTimestamp;
			$scope.bookingDetails.nights = $scope.checkInOutDetails.nights;*/
			$scope.bookingDetails.checkInOutDetails = $scope.checkInOutDetails;
			$scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_ROOM;
			break;
		case $scope.BOOKING_NAV_CONSTANTS.SELECT_ROOM :
			if (!$scope.enableMoveToSelectMealPlan())
				return;
			$scope.bookingDetails.selectedRoomTypes = $scope.getSelectedRoomType($scope.availableRoomTypes);
			$scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_MEAL_PLAN;
			$scope.bookingDetails.selectedRoomTypes = $scope.setDefaultMealPlan($scope.bookingDetails.selectedRoomTypes);
			$scope.calculateGrandTotal();
			console.log($scope.bookingDetails);
			break;
		case $scope.BOOKING_NAV_CONSTANTS.SELECT_MEAL_PLAN :
			$scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.GET_GUEST_INFO;
            console.log($scope.bookingDetails);
            createBookingCart();
			break;
		}
	};
	$scope.movePrev = function(){
		switch ($scope.bookingStage){
		case $scope.BOOKING_NAV_CONSTANTS.SELECT_ROOM :
			$scope.availableRoomTypes = {}; // clear search results
			$scope.checkInOutDetails = {}; // clear checkin checkout date details
			$('#checkindate').datepicker('setValue', '');
			$('#checkoutdate').datepicker('setValue', '');
			$scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_DATE;
			break;
		case $scope.BOOKING_NAV_CONSTANTS.SELECT_MEAL_PLAN:
			$scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_ROOM;
			break;
		case $scope.BOOKING_NAV_CONSTANTS.GET_GUEST_INFO:
			$scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_MEAL_PLAN;
		}
	};
	/* Booking Search Function doing service call */
	$scope.searchRoomsByDate = function() {
		$scope.checkInOutDetails.idProperty = $scope.selectedProperty.idProperty;
		BookingService.searchRoom($scope.checkInOutDetails).then(function (response) {
			$scope.availableRoomTypes = response.data;
			if ($scope.availableRoomTypes.length>0)
				$scope.sortMealPlansByPriceAsc();
			$scope.moveNext(); // move to next booking stage
		}).catch(function (response) {
			$scope.availableRoomTypes = {};
			CommonService.handleDefaultErrorResponse("sm", "Search Room Failed!", response, ["OK"]);
		});
	};
	/* Sort mealplans of all rooms as per base price */
	$scope.sortMealPlansByPriceAsc = function (){
		$scope.availableRoomTypes.map(function(roomType){
			roomType.mealPlans.sort(function(mp1,mp2){
				return  mp1.mealPlanItem.itemPrice.basePrice - mp2.mealPlanItem.itemPrice.basePrice;
			})
		});
	};
	//setting default meal plan, extra occupancy count
	$scope.setDefaultMealPlan = function(selectedRoomTypes){
		selectedRoomTypes.map(function(roomType){
      roomType.maxExtraAdultOccupancyList = getOccupancySelectList(roomType.maxExtraAdultOccupancy*roomType.selectedRoomCount);
      roomType.maxExtraChildOccupancyList = getOccupancySelectList(roomType.maxExtraChildOccupancy*roomType.selectedRoomCount);
			roomType.selectedExtraAdultCount = roomType.maxExtraAdultOccupancyList[0];
			roomType.selectedExtraChildCount = roomType.maxExtraChildOccupancyList[0];
			roomType.selectedMealPlan = roomType.mealPlans[0];
			if (parseInt(roomType.maxAdultOccupancy) > 1)
				roomType.maxAdultOccupancyText = roomType.maxAdultOccupancy + " Adults";
			else
				roomType.maxAdultOccupancyText = roomType.maxAdultOccupancy + " Adult";
			if (parseInt(roomType.maxChildOccupancy) > 1)
				roomType.maxChildOccupancyText = roomType.maxChildOccupancy + " Children";
			else
				roomType.maxChildOccupancyText = roomType.maxChildOccupancy + " Child";
			return roomType;
		});
		return selectedRoomTypes;
	};
	//get selection list array from a max number
	var getOccupancySelectList = function(maxOccupancy){
	  var selectionList = [];
	  if (!maxOccupancy){
	    return ["0"];
    }
	  for (i=0;i<=maxOccupancy;i++){
      selectionList.push(i.toString());
    }
    return selectionList;
  };
	//calculating the grand total of the items selected
	$scope.calculateGrandTotal = function(){
		var roomSubTotal = 0;
		$scope.bookingDetails.selectedRoomTypes.map(function(selectedRoomType){
			roomSubTotal = roomSubTotal + (selectedRoomType.selectedMealPlan.mealPlanItem.itemPrice.basePrice * selectedRoomType.selectedRoomCount +
								selectedRoomType.selectedMealPlan.adultExtraBedItem.itemPrice.basePrice * selectedRoomType.selectedExtraAdultCount +
								selectedRoomType.selectedMealPlan.childExtraBedItem.itemPrice.basePrice * selectedRoomType.selectedExtraChildCount);
		});
		$scope.bookingDetails.grandTotal = roomSubTotal * $scope.checkInOutDetails.nights;
	};

	/* Guest Confirmation Screen */
	$scope.showGuestConfirmation = function() {
		$scope.moveNext(); // move to next booking stage
	};
	/* add rooms for selection */
	$scope.addRoomsByIndex = function(roomTypeIndex){
		if (!$scope.availableRoomTypes[roomTypeIndex].selectedRoomCount ||
				$scope.availableRoomTypes[roomTypeIndex].selectedRoomCount === 0) {
			$scope.availableRoomTypes[roomTypeIndex].selectedRoomCount = 1;
		}
		else
		{
			$scope.availableRoomTypes[roomTypeIndex].selectedRoomCount =
				parseInt($scope.availableRoomTypes[roomTypeIndex].selectedRoomCount) + 1;
		}
	};
	/* remove rooms for selection */
	$scope.removeRoomsByIndex = function(roomTypeIndex){
		if ($scope.availableRoomTypes[roomTypeIndex].selectedRoomCount > 0) {
			$scope.availableRoomTypes[roomTypeIndex].selectedRoomCount =
				parseInt($scope.availableRoomTypes[roomTypeIndex].selectedRoomCount) - 1;
		}
	};
	/* disable room qty increase */
	$scope.disableRoomQtyIncrease = function(roomTypeIndex){
		return ($scope.availableRoomTypes[roomTypeIndex].selectedRoomCount ===
			$scope.availableRoomTypes[roomTypeIndex].availableCount);
	};
	/* disable room qty decrease */
	$scope.disableRoomQtyDecrease = function(roomTypeIndex){
		return ($scope.availableRoomTypes[roomTypeIndex].selectedRoomCount === 0);
	};
	/* allow movenext to select meal plan */
	$scope.enableMoveToSelectMealPlan = function(){
		if ($scope.availableRoomTypes.length>0) {
			var atLeastOneRoomSelected = $scope.availableRoomTypes.some(function(roomType){
				return roomType.selectedRoomCount > 0;
			}) 
		}
		return atLeastOneRoomSelected;
	};
	/* allow movenext to guest info */
	$scope.enableMoveToGuestInfo = function(){
		/*if ($scope.bookingDetails.selectedRoomTypes.length>0) {
			$scope.bookingDetails.selectedRoomTypes
		}*/
		return true;
	};

	/* get rooms selected */
	$scope.getSelectedRoomType = function(availableRoomTypes){
		var selectedRoomTypes = availableRoomTypes.filter(function(roomType){
			if (roomType.selectedRoomCount>0)
				return roomType;
		});
		return selectedRoomTypes;
	};
	var createBookingCart = function(){
	    var bookingCart = {};
	    bookingCart.checkInOutDetails = $scope.bookingDetails.checkInOutDetails;
        bookingCart.roomDetails = [];
        $scope.bookingDetails.selectedRoomTypes.map(function(selectedRoomType){
            var roomDetail = {};
            roomDetail.idRoomType = selectedRoomType.idRoomType;
            roomDetail.selectedRoomCount = selectedRoomType.selectedRoomCount;
            roomDetail.selectedMealPlanItemId = selectedRoomType.selectedMealPlan.idMealPlan;
            var mealPlanItems = {};
            mealPlanItems.adultExtraBedIdItem =
                selectedRoomType.selectedMealPlan.adultExtraBedItem.idItem;
            mealPlanItems.childExtraBedIdItem =
                selectedRoomType.selectedMealPlan.childExtraBedItem.idItem;
            mealPlanItems.adultExtraBedCount =
                selectedRoomType.selectedExtraAdultCount;
            mealPlanItems.childExtraBedCount =
                selectedRoomType.selectedExtraChildCount;
            roomDetail.mealPlanItems = mealPlanItems;
            bookingCart.roomDetails.push(roomDetail);
        });
        console.log(bookingCart);
    };


	/* Date picker control logic */
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
