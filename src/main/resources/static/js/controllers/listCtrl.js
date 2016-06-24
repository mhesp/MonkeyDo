var app = angular.module('listCtrl', ['ngRoute', 'ngMaterial', 'apiFactory']);

app.controller('listCtrl', ['$scope', '$rootScope', '$log', '$routeParams','apiFactory', function($scope, $rootScope, $log, $routeParams, apiFactory) {
    $rootScope.owner = $routeParams.owner;
    $scope.owner = $routeParams.owner;

    $scope.addList = function(name) {
        $scope.toDo = {'taskList':{'userId': '', 'listName':name, 'listId':'-1'}, "tasks":[]};
        $scope.lists.push($scope.toDo);
        $scope.toDo = "";
        $scope.name = "";

        apiFactory.save($scope.owner, $scope.lists);

        for (i = 0; i < $scope.lists.length; i++) {
            console.log("Name [" + $scope.lists[i].taskList.listName + "]");
            for (j = 0; j < $scope.lists[i].tasks.length; j++) {
                console.log("Task [" + $scope.lists[i].tasks[j].taskName + "]");
            }
        }
    };

    $scope.save = function() {
        console.log("Saving all lists for user [" + $scope.owner + "]");
        for (i = 0; i < $scope.lists.length; i++) {
            console.log("Name [" + $scope.lists[i].taskList.listName + "]");
            for (j = 0; j < $scope.lists[i].tasks.length; j++) {
                console.log("Task [" + $scope.lists[i].tasks[j].taskName + "]");
            }
        }

        apiFactory.save('Maria', $scope.lists)
            .then(function () {
                console.log("Managed to save!");
            }, function (error) {
                $scope.status = 'Unable to save...' + error.message;
            });
    };

    $(document).ready(function(){
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal-trigger').leanModal();
        $('select').material_select();
    });

}]);

app.run(['$rootScope', 'apiFactory', function($rootScope, apiFactory) {
    console.log("Loading data from DB...");
    apiFactory.loadData('Maria')
        .then(function (response) {
            console.log("Loaded data: " + response.data);
            $rootScope.lists = response.data;
        }, function (error) {
            console.log("ERROR! " + error.message);    
        }); 
}]);

//TODO: Create list for done and undone tasks, give option to hide done tasks
//TODO: Create option to delete tasks
//TODO: Somehow show which category a list goes into
//TODO: Have the possibility for a field where notes can be written freely (inside the to-do list)
//TODO: Be able to sort lists by category or age 
app.directive('taskList', ['apiFactory', '$rootScope', function(apiFactory, $rootScope) {
    return {
        templateUrl: 'view/tasklist.html',
        link: function (scope) {
            scope.selected = [];

            scope.addTask = function(list, lists) {
                list.push({'taskName': scope.newtask, 'taskDueDate': null, 'taskCreatedDate': null, 'done': 'false'});
                scope.newtask = '';

                apiFactory.save($rootScope.owner, lists);

                console.log("All tasks: ");
                for (i = 0; i < list.length; i++) {
                    console.log("TaskName [" + list[i].taskName + "] Done [" + list[i].done + "]");
                }
            };

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
}]);
