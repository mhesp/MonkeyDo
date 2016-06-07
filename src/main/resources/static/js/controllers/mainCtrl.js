var app = angular.module('mainCtrl', ['ngRoute', 'ngMaterial']);

app.controller('mainCtrl', ['$scope', '$log', function($scope) {
    $scope.message = 'Home screen.'
}]);
