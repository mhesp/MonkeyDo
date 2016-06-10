var services = angular.module('apiFactory', ['ngRoute']);

services.factory('apiFactory', ['$http', function ($http) {

    var urlBase = "/monkeydo";
    var apiFactory = {};

    apiFactory.save = function() {
        return $http.get(urlBase + "/save");
    };

    return apiFactory;
    
}]);
