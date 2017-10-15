PackNGo.controller('BookingCtrl',function($scope,$sce){
	$scope.$on('$routeChangeStart', function(event){
		$('.datepicker').remove();
    });
	$(function(){
		var nowTemp = new Date();
		var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
		 
		var checkin = $('#checkindate').datepicker({
		  onRender: function(date) {
		    return date.valueOf() < now.valueOf() ? 'disabled' : '';
		  },
		  format: 'dd-mmm-yyyy'
		}).on('changeDate', function(ev) {
		  if (ev.date.valueOf() >= checkout.date.valueOf()) {
		    var newDate = new Date(ev.date);
		    newDate.setDate(newDate.getDate() + 1);
		    checkout.setValue(newDate);
		  }
		  checkin.hide();
		  $('#checkoutdate')[0].focus();
		}).data('datepicker');
		var checkout = $('#checkoutdate').datepicker({
		  onRender: function(date) {
		    return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
		  },
		format: 'dd-mmm-yyyy'
		}).on('changeDate', function(ev) {
		  checkout.hide();
		}).data('datepicker');
	});
  $scope.roomtypes = [{
    "id" : "1",
    "type" : "Delux",
    "base_price" : "INR 1200",
		"count_available" : "2",
		"discount" : "10%",
    "description" : "This is a sprawling property in the heart of the city. Amazing location of the property and connections from this city makes this property and excellent option to spend your stay.",
    "img" : "../img/prop1_img/rooms/delux_cover.jpg",
    "facility" : {
      "wifi" : "true",
      "parking" : "true",
      "breakfast" : "false",
      "restaurant" : "false"}
  },
    {
      "id" : "3",
      "type" : "Super Delux",
      "base_price" : "INR 1500",
      "count_available" : "15",
      "discount" : "10%",
      "description" : "This is a sprawling property in the heart of the city. Amazing location of the property and connections from this city makes this property and excellent option to spend your stay.",
      "img" : "../img/prop1_img/rooms/super_delux_cover.jpg",
      "facility" : {
        "wifi" : "true",
        "parking" : "true",
        "breakfast" : "true",
        "restaurant" : "false"}
    }];
	
});
