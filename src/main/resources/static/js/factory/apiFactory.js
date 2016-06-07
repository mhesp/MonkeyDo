var services = angular.module('apiFactory', ['ngRoute']);

services.factory('apiFactory', ['$http', function ($http) {

    var urlBase = "/monkeydo";
    var apiFactory = {};

    return apiFactory;
    
}]);
