var app = angular.module('listCtrl', ['ngRoute', 'ngMaterial', 'apiFactory']);

app.controller('listCtrl', ['$scope', '$log', 'apiFactory', function($scope, $routeParams, apiFactory) {
    $scope.owner = $routeParams.owner;

    $scope.lists = [];
    $scope.toDo = "";
    $scope.addList = function(name) {
        $scope.toDo = {"name": name, "tasks":[]};
        $scope.lists.push($scope.toDo);
        $scope.name = "";
        $scope.toDo = "";

        for (i = 0; i < $scope.lists.length; i++) {
            console.log("Name [" + $scope.lists[i].name + "] and tasks [" + $scope.lists[i].tasks + "]");
        }
    };

    $scope.save = function() {
        console.log("Saving...");
        apiFactory.save()
            .then(function (response) {
                console.log("Manages to save! Response [" + response.data + "]")
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

app.run(['apiFactory', function(apiFactory) {
    console.log("Loading data from DB...");
    apiFactory.loadData()
        .then(function (response) {
            console.log("Loaded data: " + response.data);
        }, function (error) {
            console.log("ERROR! " + error.message);    
        }); 
}]);

//TODO: Create list for done and undone tasks, give option to hide done tasks
//TODO: Create option to delete tasks
//TODO: Somehow show which category a list goes into
//TODO: Have the possibility for a field where notes can be written freely (inside the to-do list)
//TODO: Be able to sort lists by category or age 
app.directive('taskList', function() {
    return {
        templateUrl: 'view/tasklist.html',
        link: function (scope) {
            scope.selected = [];

            scope.addTask = function(list) {
                list.push(scope.newtask);
                scope.newtask = '';
                console.log("List of tasks [" + list + "]");
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
});
