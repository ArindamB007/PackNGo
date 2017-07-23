PackNGo.factory('LocalStorageService',function($localStorage) {
  return{
    setLocalStore : function(localStore, value) {
      $localStorage[localStore] = value;
    }
  };
});