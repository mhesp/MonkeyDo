var app = angular.module('monkeyDo', ['ngRoute', 'ngMaterial', 'ngMessages']);

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
        console.log("Name of list: " + name);
        $scope.lists.push(name);
        $scope.name = "";
        console.log("Lists: " + $scope.lists);
    };

    $(document).ready(function(){
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal-trigger').leanModal();
        $('select').material_select();
    });

});

//TODO: Create list for done and undone tasks, give option to hide done tasks
//TODO: Create option to delete tasks
//TODO: Somehow show which category a list goes into
//TODO: Have the possibility for a field where notes can be written freely (inside the to-do list)
//TODO: Be able to sort lists by category or age 
app.directive('taskList', function() {
    return {
        templateUrl: 'templates/tasklist.html',
        link: function (scope) {
            scope.myList = [];
            scope.selected = [];
            scope.addTask = function() {
                console.log("newtask [" + scope.newtask + "]");
                scope.myList.push(scope.newtask);
                scope.newtask = '';
            }

            scope.toggle = function(task, selected, index) {
                var idx = selected.indexOf(index);
                if (idx > -1) {
                    selected.splice(idx, 1);
                } else {
                    selected.push(index);
                }
            };

            scope.exists = function (task, selected, index) {
                var idx = selected.indexOf(index);
                return idx > -1;
            };

        }
    };
});