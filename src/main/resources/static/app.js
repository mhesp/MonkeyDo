var app = angular.module('monkeyDo', ['ngRoute']);




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

app.controller('mainCtrl', function($scope) {
    $scope.message = 'Home screen.'
});

app.controller('listCtrl', function($scope, $routeParams) {
    $scope.owner = $routeParams.owner;

    $scope.lists = [];
    $scope.addList = function(name) {
        $scope.lists.push(name);
        $scope.name = "";
    };

    $(document).ready(function(){
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal-trigger').leanModal();
        $('select').material_select();
    });

});

app.directive('taskList', function() {
    return {
        templateUrl: 'templates/tasklist.html',
        link: function (scope) {
            scope.myList = [];
            scope.addTask = function() {
                scope.myList.push(scope.newtask);
                scope.newtask = '';
            }
        }
    };
});