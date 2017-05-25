PackNGo.controller('BookingCtrl',function($scope,$sce){
	$(function(){
		//$('#checkindate').datepicker({format: 'dd-mm-yyyy'});
		//$('#checkoutdate').datepicker({format: 'dd-mm-yyyy'});
		var nowTemp = new Date();
		var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);
		 
		var checkin = $('#checkindate').datepicker({
		  onRender: function(date) {
		    return date.valueOf() < now.valueOf() ? 'disabled' : '';
		  },
		  format: 'dd-mmm-yyyy'
		}).on('changeDate', function(ev) {
		  if (ev.date.valueOf() > checkout.date.valueOf()) {
		    var newDate = new Date(ev.date)
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
	})
	
});
