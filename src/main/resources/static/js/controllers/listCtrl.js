var app = angular.module('listCtrl', ['ngRoute', 'ngMaterial', 'apiFactory']);

app.controller('listCtrl', ['$scope', '$rootScope', '$log', '$routeParams','apiFactory', function($scope, $rootScope, $log, $routeParams, apiFactory) {
    $rootScope.owner = $routeParams.owner;
    $scope.owner = $routeParams.owner;
    $scope.lists = $rootScope.lists;

    $scope.addList = function(name) {
        //TODO: Remove hardcoded userId
        $scope.toDo = {'taskList':{'userId': '1', 'listId':'-1', 'listName':name}, 'tasks':[]};

        apiFactory.saveList($scope.toDo.taskList)
            .then(function(response) {
                console.log("Saved list & got ListID [" + response.data + "]");
                //TODO: Fix!
                $scope.toDo = {'taskList':{'userId': '1', 'listId':response.data, 'listName':name}, 'tasks':[]};
                $scope.lists.push($scope.toDo);
            });

        $scope.toDo = "";
        $scope.name = "";

        for (i = 0; i < $scope.lists.length; i++) {
            console.log("Name [" + $scope.lists[i].taskList.listName + "]");
            for (j = 0; j < $scope.lists[i].tasks.length; j++) {
                console.log("Task [" + $scope.lists[i].tasks[j].taskName + "]");
            }
        }
    };

    $(document).ready(function(){
        // the "href" attribute of .modal-trigger must specify the modal ID that wants to be triggered
        $('.modal-trigger').leanModal();
        $('select').material_select();
    });

}]);

app.run(['$rootScope', 'apiFactory', function($rootScope, apiFactory) {
    console.log("Loading data from DB...");
    apiFactory.loadData(1)
        .then(function (response) {
            console.log("Successfully loaded data for User with ID [" + 1 + "]");
            console.log("Number of lists loaded [" + response.data.length + "]");
            if (response.data.length == 0) {
                console.log("No lists created yet!");
                $rootScope.lists = [];
            }
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

            scope.openMenu = function($mdOpenMenu, event) {
                $mdOpenMenu(event);
            };

            scope.addTask = function(list) {
                var task = {'id':'-1', 'listId': list.taskList.listId,'taskName': scope.newtask, 'taskDueDate': null, 'taskCreatedDate': null, 'done': false};

                apiFactory.saveTask(task)
                    .then(function(response) {
                        console.log("!Saved task & got ID [" + response.data + "]");
                        task.id = response.data;
                        console.log("Tasks listID [" + task.listId + "] & ID [" + task.id + "]");
                        list.tasks.push(task);
                    });

                scope.newtask = '';
            };

            scope.delete = function(task, list) {
                var index = list.indexOf(task);
                console.log("Deleting... list[index] = " + list[index]);
                if (index > -1) {
                    list.splice(index, 1);
                }
                apiFactory.deleteTask(task);
            };

            scope.deleteList = function(list, index) {
                console.log("Deleting list with listId [" + list.taskList.listId + "]");
                apiFactory.deleteList(list.taskList);
                $rootScope.lists.splice(index, 1);
                
            };

            scope.toggle = function(task) {
                var isDone = task.done;
                task.done = !isDone;
                apiFactory.toggleTask(task);
            };

            scope.isDone = function (task) {
                console.log("Done [" + task.done + "]");
                return task.done;
            };

        }
    };
}]);
