angular.module('market_front').controller('ordersController', function ($scope, $http) {

    const corePath = 'http://localhost:5000/core/api/v1';

    $scope.loadOrders = function () {
        $http.get(corePath + "/orders")
            .then(function (response) {
                $scope.orders = response.data;
            });
    }

    $scope.loadOrders();
});