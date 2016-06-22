var services = angular.module('apiFactory', ['ngRoute']);

services.factory('apiFactory', ['$http', function ($http) {

    var urlBase = "/monkeydo";
    var apiFactory = {};

    apiFactory.save = function(owner, lists) {
        var res = {'user': owner, 'lists': lists};
        return $http.put(urlBase + "/save", res);
    };

    apiFactory.loadData = function (owner) {
        return $http.get(urlBase + "/load/" + owner, {}, {
            get: {
                isArray: true
            }
        });
    };

    return apiFactory;
    
}]);
