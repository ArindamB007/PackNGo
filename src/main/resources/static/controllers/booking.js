PackNGo.controller('BookingCtrl',function($scope,BookingService,CONSTANTS,CommonService,PropertyService,
                                          UserContext){
	$scope.BOOKING_NAV_CONSTANTS = CONSTANTS.BOOKING_NAV; // booking nav constants
	// in scope variable
	$scope.currency = "INR ";
    var availableRoomTypesSearchResult = {};// available rooms as returned from search
	$scope.availableRoomTypes = {}; // available rooms
	$scope.checkInOutSearchParams = {}; // selected check in out dates
	$scope.bookingDetails = {}; // selected booking details
	/*assigning the selected property*/
	//$scope.bookingDetails.selectedProperty = PropertyService.getSelectedProperty();
    $scope.selectedProperty = PropertyService.getSelectedProperty();
	/* Booking navigation control logic */
	$scope.bookingStage = $scope.BOOKING_NAV_CONSTANTS.SELECT_DATE;
	$scope.moveNext = function(){
		switch ($scope.bookingStage){
		case $scope.BOOKING_NAV_CONSTANTS.SELECT_DATE :
			$scope.checkInOutSearchParams.nights =
				CommonService.getNightsFromCheckInOut($scope.checkInOutSearchParams.checkOutTimestamp,
						$scope.checkInOutSearchParams.checkInTimestamp);
			/*$scope.bookingDetails.checkInTimestamp = $scope.checkInOutSearchParams.checkInTimestamp;
			$scope.bookingDetails.checkOutTimestamp = $scope.checkInOutSearchParams.checkOutTimestamp;
			$scope.bookingDetails.nights = $scope.checkInOutSearchParams.nights;*/
			$scope.bookingDetails.checkInOutSearchParams = $scope.checkInOutSearchParams;
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
			$scope.checkInOutSearchParams = {}; // clear checkin checkout date details
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
		$scope.checkInOutSearchParams.idProperty = $scope.selectedProperty.idProperty;
		BookingService.searchRoom($scope.checkInOutSearchParams).then(function (response) {
		    availableRoomTypesSearchResult = response.data;
			$scope.availableRoomTypes = angular.copy(availableRoomTypesSearchResult);
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
		$scope.bookingDetails.grandTotal = roomSubTotal * $scope.checkInOutSearchParams.nights;
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
        //initializing booking cart
        var bookingCart = {};
        //assigning checkinoutdetails
        var checkInOutDetails = $scope.bookingDetails.checkInOutSearchParams;
        delete checkInOutDetails.idProperty;
        bookingCart.checkInOutDetails = checkInOutDetails;

        bookingCart.selectedRoomTypes = [];
        $scope.bookingDetails.selectedRoomTypes.map(function(selectedRoomType){
            for (var i = 0; i < availableRoomTypesSearchResult.length;i++){
                if (availableRoomTypesSearchResult[i].idRoomType === selectedRoomType.idRoomType)
                    {
                        //set meal plan
                        for (var j = 0; j< availableRoomTypesSearchResult[i].mealPlans.length; j++) {
                            if (availableRoomTypesSearchResult[i].mealPlans[j].idMealPlan === selectedRoomType.selectedMealPlan.idMealPlan)
                            {
                            	availableRoomTypesSearchResult[i].mealPlans[j].mealPlanItem.quantity = selectedRoomType.selectedRoomCount;
                                availableRoomTypesSearchResult[i].mealPlans[j].adultExtraBedItem.quantity = selectedRoomType.selectedExtraAdultCount;
                                availableRoomTypesSearchResult[i].mealPlans[j].childExtraBedItem.quantity = selectedRoomType.selectedExtraChildCount;
                            } else{
                                availableRoomTypesSearchResult[i].mealPlans[j].mealPlanItem.quantity = 0;
                                availableRoomTypesSearchResult[i].mealPlans[j].adultExtraBedItem.quantity = 0;
                                availableRoomTypesSearchResult[i].mealPlans[j].childExtraBedItem.quantity = 0;
							}
                        }
                        bookingCart.selectedRoomTypes.push (availableRoomTypesSearchResult[i]);
                        break;
                    }
            }
            bookingCart.userContext = UserContext.value;
            bookingCart.selectedProperty = $scope.selectedProperty;
        });
        console.log(bookingCart);
        var invoice = BookingService.prepareInvoice(bookingCart);
        console.log("Invoice");
        console.log(invoice);
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
