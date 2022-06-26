angular.module('market_front').controller('cartProductController', function ($scope, $http) {

    const contextPath = 'http://localhost:8080/market/api/v1/';

    $scope.loadCartProducts = function () {
        $http.get(contextPath + 'products/cart')
            .then(function (response) {
                $scope.products = response.data;
            });
    }

    $scope.deleteProductFromCart = function (product) {

        $http.delete(contextPath + 'products/cart/' + product.id)
            .then(function successCallback() {
                $scope.loadCartProducts();
            },
            function failCallback(response) {
                alert(response.data.messages);
            });
    }

    $scope.loadCartProducts();

});