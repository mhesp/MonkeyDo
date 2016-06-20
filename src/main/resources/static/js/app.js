var app = angular.module('monkeyDo', ['ngRoute', 'ngMaterial', 'apiFactory', 'ngMessages', 'mainCtrl', 'listCtrl']);

app.config(['$routeProvider', '$httpProvider', function($routeProvider, $httpProvider) {

    $routeProvider
        .when('/ListMaria/:owner', {
            templateUrl: 'view/personal-space.html',
            controller: 'listCtrl'
        })
        .when('/ListKim/:owner', {
            templateUrl: 'view/personal-space.html',
            controllerAs: 'ctrl',
            controller: 'listCtrl'
        })
        .when('/home', {
            templateUrl: 'view/home.html',
            controller: 'mainCtrl'
        })
        .otherwise({
            redirectTo: '/home'
        });
}]);