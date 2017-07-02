//The Modal Service
PackNGo.service('ModalService',['$uibModal', function($uibModal){
  var modalInstance;

  this.showModal = function(size,title,msg,buttons){
    modalInstance = $uibModal.open(
      {animation: true,
        templateUrl: 'myModalContent.html',
        controller: 'ModalInstanceCtrl',
        size: size,
        backdrop  : 'static',
        keyboard  : false,
        resolve: {
          msg: function () {
            return msg;
          },
          title: function () {
            return title;
          },
          buttons: function () {
            return buttons;
          }
        }});
    return modalInstance;
  };

  this.hideModal = function(){
    modalInstance.close();
  };

}]);

//Blank Controller
PackNGo.controller('ModalController',function($scope,ModalService,$uibModal,$log){
  console.log("Arindam!!!!");
  /*$scope.openModal = function (size,msg,title,buttons) {
   var modalInstance = $uibModal.open(
   { animation: true,
     templateUrl: 'myModalContent.html',
     controller: 'ModalInstanceCtrl',
     backdrop: 'static',
     windowClass: "modal",
     size: size,
     resolve: {
     msg: function () {
     return msg;
     },
     title: function () {
     return title;
     },
   buttons: function () {
   return buttons;
   }
   }});
   //ModalService.setModalInstance(modalInstance);
   modalInstance.result.then(function (response) {
   $scope.response = response;
   $log.info('Modal dismissed at: ' + new Date() + 'Response: ' + $scope.response);
   }, function (response) {
   $scope.response = response;
   $log.info('Modal dismissed at: ' + new Date() + 'Response: ' + $scope.response);
   });
   };*/

   //$scope.openModal("sm","My modal message test", "My test title",["OK","Hello"]);
});

// This is important a controller for the modal instance
PackNGo.controller("ModalInstanceCtrl",function($scope,$uibModalInstance,msg,title,buttons){
  $scope.msg = msg;
  $scope.title = title;
  $scope.buttons = buttons;
  $scope.buttonClick = function (buttonTxt) {
    $uibModalInstance.close(buttonTxt);
  };
  $scope.cancel = function () {
    $uibModalInstance.dismiss('cancel');
  }
});