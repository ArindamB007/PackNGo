PackNGo.factory('CommonService',function(ModalService,CONSTANTS) {
  return{
      handleDefaultErrorResponse : function(modalSize,modalTitle,response,modalButtonsList) {
    	  var modalInstance = undefined;
    	  if (response.status === 400 && response.data)
    		  modalInstance = ModalService.showModal(modalSize,modalTitle + "-" + response.data.type,
    				  response.data.message,modalButtonsList);
          else if (response.status === 404 && response.data.apiError)
              modalInstance = ModalService.showModal(modalSize, modalTitle, response.data.apiError.message, modalButtonsList);
    	  else if (response.status === CONSTANTS.RESPONSE_CODES.REQUEST_IN_PROGRESS)
    		  {
	    		  modalInstance = ModalService.showModal(modalSize,modalTitle,response.data.message, modalButtonsList);
	    		  alert("Duplicate Request");
    		  }
    		  
    	  /* modalInstance.result.then(function (response) {
    		        //need to put the focus on the element
    		      }, function (response) {
    		        //need to put the focus on the element
    		      });*/
        },
    getNightsFromCheckInOut : function(checkOutDateString, checkInDateString) {
        var checkOutDate = Date.parse(checkOutDateString);
        var checkInDate = Date.parse(checkInDateString);
        var checkOutMoment = moment(checkOutDate);
        var checkInMoment = moment(checkInDate);
        return checkOutMoment.diff(checkInMoment,'days');
    },
      isObjectEmpty : function(object){
          return (Object.keys(object).length===0);
      },
      getPaisaFromRupee : function(rupeeValue){
          return (rupeeValue*100).toString(10);
      }

  };
});

PackNGo.value('UserContext', {value:{}});
PackNGo.value('LastUrl', {value:{}});
PackNGo.value('SelectedProperty',{value:{}});
