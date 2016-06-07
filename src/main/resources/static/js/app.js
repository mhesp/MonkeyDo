var app = angular.module('monkeyDo', ['ngRoute', 'ngMaterial', 'apiFactory', 'ngMessages', 'mainCtrl', 'listCtrl']);

app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/ListMaria/:owner', {
            templateUrl: 'templates/personal-space.html',
            controller: 'listCtrl'
        })
        .when('/ListKim/:owner', {
            templateUrl: 'templates/personal-space.html',
            controller: 'listCtrl'
        })
        .when('/home', {
            templateUrl: 'templates/home.html',
            controller: 'mainCtrl'
        })
        .otherwise({
            redirectTo: '/home'
        });
}]);