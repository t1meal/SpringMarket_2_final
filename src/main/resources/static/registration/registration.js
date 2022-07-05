angular.module('market_front').controller('registrationController', function ($scope, $http, $routeParams, $location) {

    const contextPath = 'http://localhost:8080/market/api/v1/';

    $scope.tryToRegistration = function () {
        if ($scope.new_user == null){
            alert("Form not completed!");
            return;
        }
        $http.post(contextPath + 'new_user', $scope.new_user)
            .then(
                function successCallback(response) {
                    console.log(response);
                    $scope.new_user = null;
                    alert("New user has been saved!");
                    $location.path('/');
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }

});