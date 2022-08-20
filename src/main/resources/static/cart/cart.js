angular.module('market_front').controller('cartProductController', function ($scope, $http) {

    const contextPath = 'http://localhost:8080/market/api/v1/';

    $scope.loadCartProducts = function () {
        $http.get(contextPath + 'products/cart')
            .then(function (response) {
                $scope.cartItems = response.data;
            });
    }
    $scope.loadSumOrder = function () {
        $http.get(contextPath + 'products/cart/sum')
            .then(function (response) {
                $scope.sumOfOrder = response.data;
            });
    }

    $scope.deleteProductFromCart = function (item) {
        $http.delete(contextPath + 'products/cart/' + item.title)
            .then(function successCallback() {
                    $scope.loadCartProducts();
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }

    $scope.incCountOfProduct = function (item) {
        $http.put(contextPath + 'products/cart/inc/' + item.id)
            .then(function successCallback() {
                    $scope.loadCartProducts();
                    $scope.loadSumOrder();
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }
    $scope.decCountOfProduct = function (item) {
        $http.put(contextPath + 'products/cart/dec/' + item.id)
            .then(function successCallback() {
                    $scope.loadCartProducts();
                    $scope.loadSumOrder();
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }

    $scope.removeAllProductsInCart = function () {
        $http.delete(contextPath + 'products/cart')
            .then(function successCallback() {
                    alert("Корзина очищена");
                    $scope.loadCartProducts();
                    $scope.loadSumOrder();
                },
                function failCallback() {
                    alert("Невозможно очистить корзину!");
                }
            );
    }

    $scope.sendOrder = function () {
        alert("Заказ успешно размещен!");
    }

    $scope.loadCartProducts();
    $scope.loadSumOrder();

});