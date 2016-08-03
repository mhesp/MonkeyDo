var services = angular.module('apiFactory', ['ngRoute']);

services.factory('apiFactory', ['$http', function ($http) {

    var urlBase = "/monkeydo";
    var apiFactory = {};
    
    apiFactory.loadData = function (owner) {
        return $http.get(urlBase + "/load/" + owner, {}, {
            get: {
                isArray: true
            }
        });
    };

    apiFactory.deleteTask = function (task) {
        return $http.put(urlBase + "/delete/task", task); 
    };

    apiFactory.deleteList = function (list) {
        return $http.put(urlBase + "/delete/list", list);
    }
    
    apiFactory.saveList = function (list) {
        console.log("Saving list!");
        return $http.put(urlBase + "/save/list", list);
    };

    apiFactory.saveTask = function (task) {
        console.log("Saving task!");
        return $http.put(urlBase + "/save/task", task); 
    };

    apiFactory.toggleTask = function (task) {
        console.log("Toggling...");
        return $http.put(urlBase + "/toggle/task", task);
    };

    return apiFactory;
    
}]);
