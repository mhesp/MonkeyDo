var app = angular.module('monkeyDo', ['ngRoute']);


app.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/ListMaria/:owner', {
            templateUrl: 'templates/list.html',
            controller: 'listCtrl'
        })
        .when('/ListKim/:owner', {
            templateUrl: 'templates/list.html',
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

app.controller('mainCtrl', function($scope) {
    $scope.message = 'Home screen.'
});

app.controller('listCtrl', function($scope, $routeParams) {
    $scope.owner = $routeParams.owner;
    $scope.message = 'List screen for ' +  $scope.owner;
});