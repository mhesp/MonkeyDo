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
    
    apiFactory.saveList = function (list) {
        return $http.put(urlBase + "/save/list", list);   
    };

    apiFactory.saveTask = function (task) {
        console.log("ListID of task [" + task.listId + "]");
        return $http.put(urlBase + "/save/task", task); 
    };

    return apiFactory;
    
}]);
