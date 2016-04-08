function Test($scope, $http) {
    $http.get('http://localhost:8088/monkeydo/test').
        success(function (data) {
            $scope.task = data;
        });
}