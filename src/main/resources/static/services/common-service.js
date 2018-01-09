PackNGo.factory('CommonService',function(ModalService) {
  return{
      handleDefaultErrorResponse : function(modalSize,modalTitle,response,modalButtonsList) {
    	  var modalInstance = undefined;
    	  if (response.status === 400 && response.data)
    		  modalInstance = ModalService.showModal(modalSize,modalTitle + "-" + response.data.error,
    				  response.data.message,modalButtonsList);
    	  else if (response.status === 404)
    		  modalInstance = ModalService.showModal(modalSize,modalTitle,response.data.message,modalButtonsList);
    	   modalInstance.result.then(function (response) {
    		        //need to put the focus on the element
    		      }, function (response) {
    		        //need to put the focus on the element
    		      });
        },
    getNightsFromCheckInOut : function(checkOutDateString, checkInDateString) {
        var checkOutDate = Date.parse(checkOutDateString);
        var checkInDate = Date.parse(checkInDateString);
        var checkOutMoment = moment(checkOutDate);
        var checkInMoment = moment(checkInDate);
        return checkOutMoment.diff(checkInMoment,'days');
    }

  };
});

PackNGo.value('UserContext', {value:{}});
PackNGo.value('LastUrl', {value:{}});
