PackNGo.factory('LocalStorageService',function($localStorage) {
  return{
    setLocalStore : function(localStore, value) {
      $localStorage[localStore] = value;
    },
    getLocalStore : function(localStore){
      return $localStorage[localStore];
    },
    deleteKeyLocalStore : function(localStore){
      delete $localStorage[localStore];
    }
  };
});