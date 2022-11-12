angular.module('market_front').controller('cartProductController', function ($scope, $http) {

    const contextPathCart = 'http://localhost:8090/market-carts/api/v1';
    const contextPathCore = 'http://localhost:8080/market/api/v1';

    $scope.loadCartProducts = function () {
        $http.get(contextPathCore + '/cart')
            .then(function (response) {
                $scope.cartItems = response.data.cartItems;
            });
    }
    $scope.loadSumOrder = function () {
        $http.get(contextPathCart + '/cart/sum')
            .then(function (response) {
                $scope.sumOfOrder = response.data;
            });
    }

    $scope.deleteProductFromCart = function (item) {
        $http.delete(contextPathCart + '/cart/' + item.productId)
            .then(function successCallback() {
                    $scope.loadCartProducts();
                    $scope.loadSumOrder();
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }

    $scope.incCountOfProduct = function (item) {
        $http.put(contextPathCart + '/cart/inc/' + item.productId)
            .then(function successCallback() {
                    $scope.loadCartProducts();
                    $scope.loadSumOrder();
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }
    $scope.decCountOfProduct = function (item) {
        $http.put(contextPathCart + '/cart/dec/' + item.productId)
            .then(function successCallback() {
                    $scope.loadCartProducts();
                    $scope.loadSumOrder();
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });
    }

    $scope.removeAllProductsInCart = function () {
        $http.delete(contextPathCart + '/cart')
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
        $http.get(contextPathCart + '/order')
            .then(function successCallback() {
                    alert("Заказ успешно размещен!");
                    $scope.removeAllProductsInCart();
                    $scope.loadSumOrder();
                },
                function failCallback(response) {
                    alert(response.data.messages);
                });

    }

    $scope.loadCartProducts();
    $scope.loadSumOrder();

});