var app = angular.module('monkeyDo', ['ngRoute', 'ngMaterial', 'apiFactory', 'ngMessages', 'mainCtrl', 'listCtrl', 'authCtrl']);

app.config(['$routeProvider', function($routeProvider) {

    $routeProvider
        .when('/ListMaria/:owner', {
            templateUrl: 'view/personal-space.html',
            controller: 'listCtrl'
        })
        .when('/ListKim/:owner', {
            templateUrl: 'view/personal-space.html',
            controller: 'listCtrl'
        })
        .when('/home', {
            templateUrl: 'view/home.html',
            controller: 'mainCtrl'
        })
        .when('/login', {
            templateUrl: 'view/login.html',
            controller: 'authCtrl'
        })
        .otherwise('/home');

    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
}]);